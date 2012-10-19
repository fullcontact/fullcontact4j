package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import com.google.gson.annotations.SerializedName;

public class BasicNameStats {

    @SerializedName("likelihood")
    private double likelihood;

    @SerializedName("count")
    private long count;

    @SerializedName("rank")
    private int rank;

    @SerializedName("frequencyRatio")
    private String frequencyRatio;

    public double getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(double likelihood) {
        this.likelihood = likelihood;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getFrequencyRatio() {
        return frequencyRatio;
    }

    public void setFrequencyRatio(String frequencyRatio) {
        this.frequencyRatio = frequencyRatio;
    }
}
