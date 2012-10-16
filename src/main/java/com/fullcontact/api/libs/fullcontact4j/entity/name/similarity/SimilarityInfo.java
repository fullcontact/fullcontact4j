package com.fullcontact.api.libs.fullcontact4j.entity.name.similarity;

import com.google.gson.annotations.SerializedName;

public class SimilarityInfo {

    @SerializedName("similarity")
    private Double similarity;

    @SerializedName("distance")
    private Double distance;

    @SerializedName("timeTaken")
    private String timeTaken;

    @SerializedName("timeActual")
    private Double timeActual;

    @SerializedName("timeEstimated")
    private Double timeEstimated;

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Double getTimeActual() {
        return timeActual;
    }

    public void setTimeActual(Double timeActual) {
        this.timeActual = timeActual;
    }

    public Double getTimeEstimated() {
        return timeEstimated;
    }

    public void setTimeEstimated(Double timeEstimated) {
        this.timeEstimated = timeEstimated;
    }
}
