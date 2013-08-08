package com.fullcontact.api.libs.fullcontact4j.entity.cardreader;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("id")
    private String requestId;

    @SerializedName("queued")
    private boolean queued;

    @SerializedName("estimatedWaitTimeMinutes")
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
