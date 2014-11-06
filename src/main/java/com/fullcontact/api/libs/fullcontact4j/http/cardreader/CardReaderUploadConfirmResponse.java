package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;



/**
 * This response is sent to the client after it successfully uploads a new card to be processed.
 * It does not contain the card's transcribed information, just data about when it will be available.
 */
public class CardReaderUploadConfirmResponse extends FCResponse {

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CardReaderUploadConfirmResponse{");
        sb.append("queued=").append(queued);
        sb.append(", id='").append(id).append('\'');
        sb.append(", estimatedWaitTimeMinutes=").append(estimatedWaitTimeMinutes);
        sb.append('}');
        return sb.toString();
    }
}
