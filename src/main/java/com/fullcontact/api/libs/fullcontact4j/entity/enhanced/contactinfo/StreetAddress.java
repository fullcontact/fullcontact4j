package com.fullcontact.api.libs.fullcontact4j.entity.enhanced.contactinfo;

import com.google.gson.annotations.SerializedName;

public class StreetAddress {

    @SerializedName("postalCode")
    private String postalCode;

    @SerializedName("address")
    private String address;

    @SerializedName("state")
    private String state;

    @SerializedName("formattedAddress")
    private String formattedAddress;

    @SerializedName("country")
    private String country;

    @SerializedName("city")
    private String city;

    @SerializedName("typeId")
    private String typeId;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

}
