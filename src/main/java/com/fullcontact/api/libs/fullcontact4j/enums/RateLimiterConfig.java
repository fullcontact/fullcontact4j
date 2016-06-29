package com.fullcontact.api.libs.fullcontact4j.enums;

import com.fullcontact.api.libs.fullcontact4j.guava.RateLimiter;
import com.fullcontact.api.libs.fullcontact4j.guava.SmoothRateLimiter;

public class RateLimiterConfig {
    private static final int DEFAULT_REQUESTS_PER_SECOND = 10;
    private final double initReqsPerSec;
    private final double maxBurstSeconds;

    public RateLimiterConfig(double initReqsPerSec, double maxBurstSeconds) {
        this.initReqsPerSec = initReqsPerSec;
        this.maxBurstSeconds = maxBurstSeconds;
    }

    public SmoothRateLimiter.SmoothBursty createRateLimiter() {
        return (SmoothRateLimiter.SmoothBursty) RateLimiter.create(RateLimiter.SleepingStopwatch.createFromSystemTimer(),
                getInitReqsPerSec(), maxBurstSeconds);
    }

    public final double getInitReqsPerSec() {
        return initReqsPerSec;
    }

    /**
     * Requests are permitted every (1 / rate) seconds. If the rate limiter is unused, it will generate
     * "free" requests that can be used faster than the regular rate. It will only generate up to 5 seconds worth of
     * free requests.
     */
    public static final RateLimiterConfig SMOOTH = new RateLimiterConfig(DEFAULT_REQUESTS_PER_SECOND, 5.0);

    /**
     * Equivilent to the SMOOTH policy, but the rate limiter will allow up to 8 second's worth of free requests.
     */
    public static final RateLimiterConfig BURST = new RateLimiterConfig(DEFAULT_REQUESTS_PER_SECOND, 8.0);

    public static final RateLimiterConfig DISABLED = new RateLimiterConfig(-1, -1);
}
