package com.fullcontact.api.libs.fullcontact4j.http.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

import java.util.Collections;
import java.util.List;

public class LocationEnrichmentResponse extends FCResponse {

    private List<Location> locations = Collections.emptyList();

    @JsonProperty("locations")
    public List<Location> getPossibleLocations() { return locations; }

    public void setLocations(List<Location> locs) { locations = locs; }

    public static class Location {
        private String city;
        private String county;
        private LocationCodeInfo state = new LocationCodeInfo();
        private LocationCodeInfo country = new LocationCodeInfo();
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

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Location{");
            sb.append("normalizedLocation='").append(normalizedLocation).append('\'');
            sb.append(", city='").append(city).append('\'');
            sb.append(", state=").append(state);
            sb.append(", country=").append(country);
            sb.append('}');
            return sb.toString();
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

        @Override
        public String toString() {
            return code + "(" + name + ")";
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocationEnrichmentResponse{");
        sb.append("locations=").append(locations);
        sb.append('}');
        return sb.toString();
    }
}
