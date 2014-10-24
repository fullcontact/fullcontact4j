package com.fullcontact.api.libs.fullcontact4j.http.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

import java.util.List;

public class LocationEnrichmentResponse extends FCResponse {

    private List<Location> locations;

    @JsonProperty("locations")
    public List<Location> getPossibleLocations() { return locations; }

    public void setLocations(List<Location> locs) { locations = locs; }

    public static class Location {
        private String city;
        private String county;
        private LocationCodeInfo state;
        private LocationCodeInfo country;
        private String continent;
        private int population;
        private String normalizedLocation;

        public String getCity() {
            return city;
        }

        public String getCounty() {
            return county;
        }

        public LocationCodeInfo getState() {
            return state;
        }

        public LocationCodeInfo getCountry() {
            return country;
        }

        public String getContinent() {
            return continent;
        }

        public int getPopulation() {
            return population;
        }

        public String getNormalizedLocation() {
            return normalizedLocation;
        }
    }

    public static class LocationCodeInfo {
        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }
}
