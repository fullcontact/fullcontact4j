package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseEntity {


    private int statusCode;


    private double likelihood;


    private String requestId;


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
