package com.fullcontact.api.libs.fullcontact4j.entity.location;

public class LocationInfo {


    private String city;


    private String county;


    private LocationNameCode state;


    private LocationNameCode country;


    private String continent;


    private String normalizedLocation;


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
