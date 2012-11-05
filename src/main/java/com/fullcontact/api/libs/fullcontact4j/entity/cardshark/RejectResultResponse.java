package com.fullcontact.api.libs.fullcontact4j.entity.cardshark;

import com.google.gson.annotations.SerializedName;

public class RejectResultResponse {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("rejected")
    private int rejected;

    @SerializedName("id")
    private String requestId;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getRejected() {
        return rejected;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
