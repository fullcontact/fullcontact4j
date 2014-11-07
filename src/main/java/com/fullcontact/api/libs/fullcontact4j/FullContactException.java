package com.fullcontact.api.libs.fullcontact4j;

@SuppressWarnings("serial")

/**
 * A FullContactException should be thrown when an error relating specifically to the FullContact APIs or their
 * responses arises and needs to be handled by the client user.
 */
public class FullContactException extends Exception {

    public FullContactException(String message) {
        super(message);
    }

    public FullContactException(String message, Integer error, Throwable cause) {
        super(message, cause);
        errorCode = error;
    }

    public FullContactException(String message, Throwable cause) {
        super(message, cause);
    }

    private Integer errorCode;

    /**
     * Returns the HTTP error code that caused the exception,
     * or null if it was not an HTTP-based exception.
     * @return the HTTP error code, or null if it was not an HTTP based exception
     */
    public Integer getErrorCode() {
        return errorCode;
    }

}
