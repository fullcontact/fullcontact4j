package com.fullcontact.api.libs.java;

@SuppressWarnings("serial")
public class FullContactException extends Exception {

    /**
     * Constructs a new exception with the specified message.
     *
     * @param message
     *            the reason for the exception
     */
    public FullContactException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified message and wrapped
     * exception.
     *
     * @param message
     *            the reason for the exception
     * @param cause
     *            the internal exception that caused this exception
     */
    public FullContactException(String message, Throwable cause) {
        super(message, cause);
    }
}
