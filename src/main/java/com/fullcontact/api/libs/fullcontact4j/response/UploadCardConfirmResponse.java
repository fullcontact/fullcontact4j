package com.fullcontact.api.libs.fullcontact4j.response;

/**
 * This response is sent to the client after it successfully uploads a new card to be processed.
 * It does not contain the card's transcribed information, just data about when it will be available.
 */
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
