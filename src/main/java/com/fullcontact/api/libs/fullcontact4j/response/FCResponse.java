package com.fullcontact.api.libs.fullcontact4j.response;

/**
 * A very simple class to represent responses back from FullContact APIs.
 * The designation is more important than the functionality.
 */

public abstract class FCResponse {

    public int status;

    public int getStatus() {
        return status;
    }
}
