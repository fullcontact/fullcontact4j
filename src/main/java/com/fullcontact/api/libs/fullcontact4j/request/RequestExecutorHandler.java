package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContact;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterPolicy;
import com.fullcontact.api.libs.fullcontact4j.guava.RateLimiter;
import com.fullcontact.api.libs.fullcontact4j.response.FCResponse;
import retrofit.RequestInterceptor;

import java.util.concurrent.*;

/**
 * This class handles requests made by the client.
 * When a request is made, it is sent to an ExecutorService which
 * accounts for rate limiting and then sends the request.
 */
public class RequestExecutorHandler {

    //will execute the requests on a separate thread.
    private final ExecutorService executorService;

    //if not null, will limit the request rate.
    private RateLimiter rateLimiter;
    private final RateLimiterPolicy policy;
    private volatile FCRequest lastHandledRequest;

    public RequestExecutorHandler(RateLimiterPolicy policy, Boolean useThreadPool) {
        this.policy = policy;
        executorService = useThreadPool?Executors.newCachedThreadPool():Executors.newSingleThreadExecutor();
    }

    /**
     * Sets the maximum requests per second. Requests are unlimited until the first response,
     * when the maximum amount is set from the rate limiting headers. It cannot be changed after that.
     * @param requestsPerMinute
     */
    public void setRateLimitPerMinute(Integer requestsPerMinute) {
        if(rateLimiter == null) {
            Integer requestsPerSecond = requestsPerMinute / 60;

            if(policy == RateLimiterPolicy.BURST) {
                rateLimiter = RateLimiter.create(RateLimiter.SleepingStopwatch.createFromSystemTimer(),
                        requestsPerSecond, 5.0);
            } else {
                rateLimiter = RateLimiter.create(requestsPerSecond);
            }
        }
    }

    public <T extends FCResponse> T sendRequestSync(final FCRequest<T> req) throws FullContactException {
        lastHandledRequest = req;
        try {
            //this is functional and fairly safe, but dirty
            final BlockingQueue asyncResult = new SynchronousQueue();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    waitForPermit();
                    //make the request -- when we get any results, place them in the queue
                    Utils.verbose("Sending a new synchronous " + req.getClass().getSimpleName());
                    req.makeRequest(new FCCallback<T>() {
                        @Override
                        public void success(T response) {
                            try {
                                asyncResult.put(response);
                            } catch(InterruptedException e) { e.printStackTrace(); }
                        }

                        @Override
                        public void failure(FullContactException exception) {
                            try {
                                asyncResult.put(exception);
                            } catch(InterruptedException e) { e.printStackTrace(); }
                        }
                    });
                }
            });

            Object result = asyncResult.take();
            if (result instanceof Exception) {
                throw (FullContactException) result;
            }
            return (T) result;
        } catch(InterruptedException e) {
            e.printStackTrace();
            throw new FullContactException("Interrupted while waiting for a result!", e);
        }
    }

    public <T extends FCResponse> void sendRequestAsync(final FCRequest<T> req, final FCCallback<T> callback) {
        lastHandledRequest = req;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                waitForPermit();
                Utils.verbose("Sending a new asynchronous " + req.getClass().getSimpleName());
                req.makeRequest(callback);
            }
        });
    }

    /**
     * Waits until the rate limiter will permit the request, or under RateLimiterPolicy.REJECT,
     * will throw an exception if a request can't be made.
     */
    private void waitForPermit() {
        if(rateLimiter != null) {
            Utils.verbose("Waiting for ratelimiter to allow a request...");
            rateLimiter.acquire();
        }
    }

    public FCRequestInterceptor getInterceptor(String apikey) {
        return new FCRequestInterceptor(apikey);
    }

    public class FCRequestInterceptor implements RequestInterceptor {
        private String apiKey;

        public FCRequestInterceptor(String apikey) {
            apiKey = apikey;
        }

        @Override
        public void intercept(RequestFacade requestFacade) {
            //add the proper auth headers to every request made
            //if(!(lastHandledRequest instanceof CardReaderRequest)) {
                requestFacade.addHeader(Constants.HEADER_AUTH_API_KEY, apiKey);
            //}
        }
    }
}
