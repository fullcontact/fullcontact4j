package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import com.google.gson.annotations.SerializedName;

public class NameStatsInfo {

    @SerializedName("value")
    private String value;

    @SerializedName("given")
    private GivenNameStats givenNameStats;

    @SerializedName("family")
    private BasicNameStats familyNameStats;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public GivenNameStats getGivenNameStats() {
        return givenNameStats;
    }

    public void setGivenNameStats(GivenNameStats givenNameStats) {
        this.givenNameStats = givenNameStats;
    }

    public BasicNameStats getFamilyNameStats() {
        return familyNameStats;
    }

    public void setFamilyNameStats(BasicNameStats familyNameStats) {
        this.familyNameStats = familyNameStats;
    }
}
