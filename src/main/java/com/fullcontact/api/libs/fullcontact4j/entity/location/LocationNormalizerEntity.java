package com.fullcontact.api.libs.fullcontact4j.entity.location;

import com.google.gson.annotations.SerializedName;

public class LocationNormalizerEntity {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("likelihood")
    private double likelihood;

    @SerializedName("requestId")
    private String requestId;

    private LocationInfo locationInfo;

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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }
}
