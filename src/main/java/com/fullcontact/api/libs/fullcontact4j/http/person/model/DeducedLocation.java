package com.fullcontact.api.libs.fullcontact4j.http.person.model;

public class DeducedLocation {
    private double likelihood;
    private String normalizedLocation;
    private String deducedLocation;
    private LocationEntry city = new LocationEntry();
    private LocationEntry state = new LocationEntry();
    private LocationEntry country = new LocationEntry();
    private LocationEntry continent = new LocationEntry();
    private LocationEntry county = new LocationEntry();

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

        @Override
        public String toString() {
            return code + "(" + name + ")";
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeducedLocation{");
        sb.append("deducedLocation='").append(deducedLocation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
