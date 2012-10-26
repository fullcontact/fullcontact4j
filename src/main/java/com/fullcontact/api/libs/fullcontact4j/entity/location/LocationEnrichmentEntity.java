package com.fullcontact.api.libs.fullcontact4j.entity.location;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationEnrichmentEntity {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("requestId")
    private String requestId;

    @SerializedName("locations")
    private List<LocationInfo> locations;

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

    public List<LocationInfo> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationInfo> locations) {
        this.locations = locations;
    }

    public void addLocation(LocationInfo location){
        this.locations.add(location);
    }
}
