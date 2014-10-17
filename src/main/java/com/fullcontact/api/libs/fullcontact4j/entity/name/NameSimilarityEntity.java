package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullcontact.api.libs.fullcontact4j.entity.name.similarity.BiagramData;
import com.fullcontact.api.libs.fullcontact4j.entity.name.similarity.SimilarityData;

public class NameSimilarityEntity {


    private int statusCode;


    private SimilarityData SimMetricsData;


    private SimilarityData SecondStringData;


    private BiagramData biagramData;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public SimilarityData getSimMetricsData() {
        return SimMetricsData;
    }

    public void setSimMetricsData(SimilarityData simMetricsData) {
        SimMetricsData = simMetricsData;
    }

    public SimilarityData getSecondStringData() {
        return SecondStringData;
    }

    public void setSecondStringData(SimilarityData secondStringData) {
        SecondStringData = secondStringData;
    }

    public BiagramData getBiagramData() {
        return biagramData;
    }

    public void setBiagramData(BiagramData biagramData) {
        this.biagramData = biagramData;
    }

}
