package com.fullcontact.api.libs.fullcontact4j.response;

/**
 * A very simple class to represent responses back from FullContact APIs.
 * The designation is more important than the functionality.
 */

public abstract class FCResponse {

    public String status;

    public String getStatus() {
        return status;
    }
}
