package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.google.gson.annotations.SerializedName;

public class NameParserInfo {

    @SerializedName("givenName")
    private String givenName;

    @SerializedName("familyName")
    private String familyName;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

}
