package com.fullcontact.api.libs.fullcontact4j.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DigitalFootPrints {

    @SerializedName("topics")
    private List<DigitalFootPrintsTopic> topics;

    @SerializedName("scores")
    private List<DigitalFootPrintsScore> scores;

    public List<DigitalFootPrintsTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<DigitalFootPrintsTopic> topics) {
        this.topics = topics;
    }

    public List<DigitalFootPrintsScore> getScores() {
        return scores;
    }

    public void setScores(List<DigitalFootPrintsScore> scores) {
        this.scores = scores;
    }

}
