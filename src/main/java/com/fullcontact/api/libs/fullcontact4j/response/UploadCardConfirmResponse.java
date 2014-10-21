package com.fullcontact.api.libs.fullcontact4j.response;

public class UploadCardConfirmResponse extends FCResponse {

    private boolean queued;
    private String id;
    private int estimatedWaitTimeMinutes;

    public boolean isQueued() {
        return queued;
    }

    public String getId() {
        return id;
    }

    public int getEstimatedWaitTimeMinutes() {
        return estimatedWaitTimeMinutes;
    }
}
