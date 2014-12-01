package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterPolicy;
import com.fullcontact.api.libs.fullcontact4j.guava.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class handles requests made by the client.
 * When a request is made, it is sent to an ExecutorService which
 * accounts for rate limiting and then sends the request.
 */
public class RequestExecutorHandler {

    //the default reqs per second. when rate limit headers come back from the client, those will be used instead.
    private static final int DEFAULT_REQUESTS_PER_SECOND = 10;
    // how often to check for a rate limit change
    private static final long RATE_LIMIT_CHECK_INTERVAL_MS = TimeUnit.MINUTES.convert(5, TimeUnit.MILLISECONDS);

    //will execute the requests on a separate thread.
    protected final ExecutorService executorService;

    //if not null, will limit the request rate.
    private RateLimiter rateLimiter;
    private volatile long lastRateLimitCheck = 0;
    private final RateLimiterPolicy policy;

    public RequestExecutorHandler(RateLimiterPolicy policy, Integer threadPoolCount) {
        this.policy = policy;
        executorService = Executors.newFixedThreadPool(threadPoolCount);
        if(policy == RateLimiterPolicy.BURST) {
            rateLimiter = RateLimiter.create(RateLimiter.SleepingStopwatch.createFromSystemTimer(),
                    DEFAULT_REQUESTS_PER_SECOND, 5.0);
        } else {
            rateLimiter = RateLimiter.create(DEFAULT_REQUESTS_PER_SECOND);
        }
    }

    /**
     * If the check interval time has passed, update the rate limit
     * @param requestsPerMinute
     */
    public synchronized void notifyRateLimitPerMinute(Double requestsPerMinute) {
        // this check will double-check if updating the rate limit is valid, but
        // ideally clients should call shouldNotifyRateLimit() before wasting time blocking on this method
        if(shouldNotfiyRateLimit()) {
            double requestsPerSecond = requestsPerMinute / 60d;
            rateLimiter.setRate(requestsPerSecond);
            lastRateLimitCheck = System.currentTimeMillis();
        }
    }

    /**
     * Has RATE_LIMIT_CHECK time passed since we last made a rate limit update?
     */
    public boolean shouldNotfiyRateLimit() {
        return System.currentTimeMillis() - lastRateLimitCheck > RATE_LIMIT_CHECK_INTERVAL_MS;
    }

    public <T extends FCResponse> void sendRequestAsync(final FullContactApi api, final FCRequest<T> req,
                                                        final FCRetrofitCallback<T> callback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                waitForPermit();
                Utils.verbose("Sending a new asynchronous " + req.getClass().getSimpleName());
                req.makeRequest(api, callback);
            }
        });
    }

    protected void waitForPermit() {
        if(rateLimiter != null) {
            Utils.verbose("Waiting for ratelimiter to allow a request... (" + rateLimiter.getRate() + " reqs/s)");
            rateLimiter.acquire();
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

}
