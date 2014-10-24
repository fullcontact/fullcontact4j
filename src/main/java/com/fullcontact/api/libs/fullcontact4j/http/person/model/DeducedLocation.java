package com.fullcontact.api.libs.fullcontact4j.http.person.model;

public class DeducedLocation {
    private double likelihood;
    private String normalizedLocation;
    private String deducedLocation;
    private LocationEntry city;
    private LocationEntry state;
    private LocationEntry country;
    private LocationEntry continent;
    private LocationEntry county;

    public LocationEntry getCounty() {
        return county;
    }
    public double getLikelihood() {
        return likelihood;
    }
    public String getNormalizedLocation() {
        return normalizedLocation;
    }
    public String getDeducedLocation() {
        return deducedLocation;
    }
    public LocationEntry getCity() {
        return city;
    }
    public LocationEntry getState() {
        return state;
    }
    public LocationEntry getCountry() {
        return country;
    }
    public LocationEntry getContinent() {
        return continent;
    }
    public static class LocationEntry {
        private boolean deduced;
        private String name;
        private String code;
        public String getCode() {
            return code;
        }
        public boolean isDeduced() {
            return deduced;
        }
        public String getName() {
            return name;
        }
    }
}
