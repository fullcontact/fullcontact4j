package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

public class GenderStats {


    private double likelihood;


    private int rank;


    private long count;


    private double frequencyRatio;


    private AgeStats ageStats;

    public double getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(double likelihood) {
        this.likelihood = likelihood;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    //TODO: convert this to double
    public double getFrequencyRatio() {
        return frequencyRatio;
    }

    public void setFrequencyRatio(double frequencyRatio) {
        this.frequencyRatio = frequencyRatio;
    }

    public AgeStats getAgeStats() {
        return ageStats;
    }

    public void setAgeStats(AgeStats ageStats) {
        this.ageStats = ageStats;
    }
}
