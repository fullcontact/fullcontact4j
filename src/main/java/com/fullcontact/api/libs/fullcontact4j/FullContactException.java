package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.http.FCRateLimits;

@SuppressWarnings("serial")

/**
 * A FullContactException should be thrown when an error relating specifically to the FullContact APIs or their
 * responses arises and needs to be handled by the client user.
 */
public class FullContactException extends Exception {

    public FullContactException(String message) {
        super(message);
    }

    public FullContactException(String message, Integer error, Throwable cause, FCRateLimits rateLimits) {
        super(message, cause);
        errorCode = error;
        this.rateLimits = rateLimits;
    }

    public FullContactException(String message, Throwable cause) {
        super(message, cause);
    }

    private Integer errorCode;
    private FCRateLimits rateLimits;

    /**
     * Returns the HTTP error code that caused the exception,
     * or null if it was not an HTTP-based exception.
     * @return the HTTP error code, or null if it was not an HTTP based exception
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * Returns the rate limit information returned by FullContact for this request.
     *
     * If this error is not an HTTP-based exception (i.e. FullContact API returning error 500), this will be null.
     */
    public FCRateLimits getRateLimitsOrNull() {
        return rateLimits;
    }

    public void setRateLimits(FCRateLimits rateLimits) {
        this.rateLimits = rateLimits;
    }
}