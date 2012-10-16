package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.google.gson.annotations.SerializedName;

public class BaseEntity {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("likelihood")
    private double likelihood;

    @SerializedName("requestId")
    private String requestId;

    @SerializedName("region")
    private String region;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public double getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(double likelihood) {
        this.likelihood = likelihood;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
