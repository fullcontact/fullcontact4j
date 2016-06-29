package com.fullcontact.api.libs.fullcontact4j.http;

/**
 * A very simple class to represent responses back from FullContact APIs.
 * The designation is more important than the functionality.
 */

public abstract class FCResponse {

    public int status;
    private Integer rateLimitPerMinute;
    private Integer rateLimitRemaining;
    private Integer rateLimitReset;

    public int getStatus() {
        return status;
    }

    public Integer getRateLimitPerMinute() {
        return rateLimitPerMinute;
    }

    public void setRateLimitPerMinute(Integer rateLimitPerMinute) {
        this.rateLimitPerMinute = rateLimitPerMinute;
    }

    public Integer getRateLimitRemaining() {
        return rateLimitRemaining;
    }

    public void setRateLimitRemaining(Integer rateLimitRemaining) {
        this.rateLimitRemaining = rateLimitRemaining;
    }

    public Integer getRateLimitReset() {
        return rateLimitReset;
    }

    public void setRateLimitReset(Integer rateLimitReset) {
        this.rateLimitReset = rateLimitReset;
    }

    public String toString() {
        return this.getClass().getSimpleName() + ":" + status;
    }
}
