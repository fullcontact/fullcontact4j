package com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Name implements Serializable {
    private static final long serialVersionUID = 2280620035191291965L;

    @SerializedName("familyName")
    private String familyName;

    @SerializedName("givenName")
    private String givenName;

    @SerializedName("middleName")
    private String middleName;

    @SerializedName("honorificPrefix")
    private String honorificPrefix;

    @SerializedName("honorificSuffix")
    private String honorificSuffix;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getHonorificPrefix() {
        return honorificPrefix;
    }

    public void setHonorificPrefix(String honorificPrefix) {
        this.honorificPrefix = honorificPrefix;
    }

    public String getHonorificSuffix() {
        return honorificSuffix;
    }

    public void setHonorificSuffix(String honorificSuffix) {
        this.honorificSuffix = honorificSuffix;
    }
}
