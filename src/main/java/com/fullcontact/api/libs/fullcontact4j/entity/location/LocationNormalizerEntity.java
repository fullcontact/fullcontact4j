package com.fullcontact.api.libs.fullcontact4j.entity.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationNormalizerEntity {


    private int statusCode;


    private double likelihood;


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
