package com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Skiggz
 * Date: 10/31/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 440365357105542572L;

    @SerializedName("country")
    private String country;

    @SerializedName("type")
    private String type;

    @SerializedName("locality")
    private String locality;

    @SerializedName("extendedAddress")
    private String extendedAddress;

    @SerializedName("postalCode")
    private String postalCode;

    @SerializedName("formatted")
    private String formatted;

    @SerializedName("region")
    private String region;

    @SerializedName("streetAddress")
    private String streetAddress;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getExtendedAddress() {
        return extendedAddress;
    }

    public void setExtendedAddress(String extendedAddress) {
        this.extendedAddress = extendedAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
