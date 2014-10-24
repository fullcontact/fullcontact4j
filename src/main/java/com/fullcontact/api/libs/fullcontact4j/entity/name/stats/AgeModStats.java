package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import java.util.List;

public class AgeModStats {


    private long count;


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
