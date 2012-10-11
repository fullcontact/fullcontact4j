package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.google.gson.annotations.SerializedName;

public class NameEntity {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("likelihood")
    private double likelihood;

    @SerializedName("region")
    private String region;

    @SerializedName("nameDetails")
    private NameInfo nameInfo;

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

    public NameInfo getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(NameInfo nameInfo) {
        this.nameInfo = nameInfo;
    }
}
