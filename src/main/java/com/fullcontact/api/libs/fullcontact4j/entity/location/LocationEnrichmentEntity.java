package com.fullcontact.api.libs.fullcontact4j.entity.location;

import java.util.List;

public class LocationEnrichmentEntity {


    private int statusCode;


    private String requestId;


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
