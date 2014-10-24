package com.fullcontact.api.libs.fullcontact4j.entity.name.similarity;

public class SimilarityInfo {


    private Double similarity;


    private Double distance;


    private String timeTaken;


    private Double timeActual;


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
