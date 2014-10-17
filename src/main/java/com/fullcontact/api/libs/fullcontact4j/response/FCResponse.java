package com.fullcontact.api.libs.fullcontact4j.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * A very simple class to represent responses back from FullContact APIs.
 * The designation is more important than the functionality.
 */
@JsonAutoDetect
public class FCResponse {

    public int status;

    public int getStatus() {
        return status;
    }
}
