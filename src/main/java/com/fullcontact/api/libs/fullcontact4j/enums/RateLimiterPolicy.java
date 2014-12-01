package com.fullcontact.api.libs.fullcontact4j.enums;

public enum RateLimiterPolicy {
    /**
     * Requests are permitted every (1 / rate) seconds. If the rate limiter is unused, it will generate
     * "free" requests that can be used faster than the regular rate. It will only generate up to 5 seconds worth of
     * free requests.
     */
    SMOOTH,
    /**
     * Equivilent to the SMOOTH policy, but the rate limiter will allow up to 8 second's worth of free requests.
     */
    BURST;
}
