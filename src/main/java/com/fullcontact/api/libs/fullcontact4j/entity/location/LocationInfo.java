package com.fullcontact.api.libs.fullcontact4j.entity.location;

import com.google.gson.annotations.SerializedName;

public class LocationInfo {

    @SerializedName("city")
    private String city;

    @SerializedName("county")
    private String county;

    @SerializedName("state")
    private LocationNameCode state;

    @SerializedName("country")
    private LocationNameCode country;

    @SerializedName("continent")
    private String continent;

    @SerializedName("normalizedLocation")
    private String normalizedLocation;

    @SerializedName("population")
    private long population;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public LocationNameCode getState() {
        return state;
    }

    public void setState(LocationNameCode state) {
        this.state = state;
    }

    public LocationNameCode getCountry() {
        return country;
    }

    public void setCountry(LocationNameCode country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getNormalizedLocation() {
        return normalizedLocation;
    }

    public void setNormalizedLocation(String normalizedLocation) {
        this.normalizedLocation = normalizedLocation;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
