package com.fullcontact.api.libs.fullcontact4j.http;

/**
 * A very simple class to represent responses back from FullContact APIs.
 * The designation is more important than the functionality.
 */

public abstract class FCResponse {

    public int status;

    public int getStatus() {
        return status;
    }

    public String toString() {
        return this.getClass().getSimpleName() + ":" + status;
    }
}
