package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameStatsInfo {


    private String value;


    private GivenNameStats givenNameStats;


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
