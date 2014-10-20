package com.fullcontact.api.libs.fullcontact4j.entity.cardreader;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadResponse {


    private int statusCode;


    private String requestId;


    private boolean queued;


    private int estimatedWaitTimeMinutes;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public boolean isQueued() {
        return queued;
    }

    public void setQueued(boolean queued) {
        this.queued = queued;
    }

    public int getEstimatedWaitTimeMinutes() {
        return estimatedWaitTimeMinutes;
    }

    public void setEstimatedWaitTimeMinutes(int estimatedWaitTimeMinutes) {
        this.estimatedWaitTimeMinutes = estimatedWaitTimeMinutes;
    }
}
