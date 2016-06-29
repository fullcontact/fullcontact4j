package com.fullcontact.api.libs.fullcontact4j.http;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A very simple class to represent responses back from FullContact APIs.
 * The designation is more important than the functionality.
 */

public abstract class FCResponse {

    public int status;
    @JsonIgnore private FCRateLimits rateLimits;

    public int getStatus() {
        return status;
    }

    public String toString() {
        return this.getClass().getSimpleName() + ":" + status;
    }

    /**
     * Get the rate limit information returned by FullContact for this request.
     *
     * Returns null if there is no rate limit information in the response headers or there was an error fetching it.
     */
    @JsonIgnore
    public FCRateLimits getRateLimitsOrNull() {
        return rateLimits;
    }

    void setRateLimits(FCRateLimits rateLimits) {
        this.rateLimits = rateLimits;
    }
}
