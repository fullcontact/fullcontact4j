package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import com.google.gson.annotations.SerializedName;

public class GivenNameStats extends BasicNameStats{

    @SerializedName("male")
    private GenderStats maleStats;

    @SerializedName("female")
    private GenderStats femaleStats;

    public GenderStats getMaleStats() {
        return maleStats;
    }

    public void setMaleStats(GenderStats maleStats) {
        this.maleStats = maleStats;
    }

    public GenderStats getFemaleStats() {
        return femaleStats;
    }

    public void setFemaleStats(GenderStats femaleStats) {
        this.femaleStats = femaleStats;
    }
}
