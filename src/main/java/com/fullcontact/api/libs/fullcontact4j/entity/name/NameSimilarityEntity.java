package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.fullcontact.api.libs.fullcontact4j.entity.name.similarity.BigramData;
import com.fullcontact.api.libs.fullcontact4j.entity.name.similarity.SimilarityData;

public class NameSimilarityEntity {


    private int statusCode;


    private SimilarityData SimMetricsData;


    private SimilarityData SecondStringData;


    private BigramData bigramData;

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

    public BigramData getBigramData() {
        return bigramData;
    }

    public void setBigramData(BigramData bigramData) {
        this.bigramData = bigramData;
    }

}
