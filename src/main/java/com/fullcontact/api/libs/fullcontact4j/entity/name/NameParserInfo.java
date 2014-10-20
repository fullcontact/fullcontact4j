package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameParserInfo {


    private String givenName;


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
