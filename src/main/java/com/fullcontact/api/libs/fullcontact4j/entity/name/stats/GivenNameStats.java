package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

public class GivenNameStats extends BasicNameStats{


    private GenderStats maleStats;


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
