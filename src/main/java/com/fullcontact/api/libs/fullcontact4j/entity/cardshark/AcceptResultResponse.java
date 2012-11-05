package com.fullcontact.api.libs.fullcontact4j.entity.cardshark;

import com.google.gson.annotations.SerializedName;

public class AcceptResultResponse {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("accepted")
    private int accepted;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }
}
