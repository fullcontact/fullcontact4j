package com.fullcontact.api.libs.fullcontact4j.entity.cardshark;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("id")
    private String requestId;

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
}
