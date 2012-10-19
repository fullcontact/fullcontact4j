package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AgeModStats {

    @SerializedName("count")
    private long count;

    @SerializedName("modeAge")
    private List<Integer> modeAge;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Integer> getModeAge() {
        return modeAge;
    }

    public void setModeAge(List<Integer> modeAge) {
        this.modeAge = modeAge;
    }
}
