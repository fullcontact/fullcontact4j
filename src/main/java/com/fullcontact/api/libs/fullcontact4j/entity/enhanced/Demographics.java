package com.fullcontact.api.libs.fullcontact4j.entity.enhanced;

import com.google.gson.annotations.SerializedName;

public class Demographics {

    @SerializedName("locationGeneral")
    private String locationGeneral;

    @SerializedName("countryCode")
    private String countryCode;

    /** Field description */
    @SerializedName("postalCode")
    private String postalCode;

    public String getLocationGeneral() {
        return locationGeneral;
    }

    public void setLocationGeneral(String locationGeneral) {
        this.locationGeneral = locationGeneral;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
