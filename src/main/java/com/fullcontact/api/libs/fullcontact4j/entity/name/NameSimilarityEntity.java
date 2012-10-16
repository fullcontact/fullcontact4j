package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.fullcontact.api.libs.fullcontact4j.entity.name.similarity.BiagramData;
import com.fullcontact.api.libs.fullcontact4j.entity.name.similarity.SimilarityData;
import com.google.gson.annotations.SerializedName;

public class NameSimilarityEntity {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("SimMetrics")
    private SimilarityData SimMetricsData;

    @SerializedName("SecondString")
    private SimilarityData SecondStringData;

    @SerializedName("BigramAnalysis")
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
