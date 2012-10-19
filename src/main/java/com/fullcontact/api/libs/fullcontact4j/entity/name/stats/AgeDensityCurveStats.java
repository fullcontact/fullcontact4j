package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import com.google.gson.annotations.SerializedName;

public class AgeDensityCurveStats {

    @SerializedName("meanAge")
    private double meanAge;

    @SerializedName("quartiles")
    private QuartilesStats quartiles;

    @SerializedName("mode")
    private AgeModStats mode;

    public double getMeanAge() {
        return meanAge;
    }

    public void setMeanAge(double meanAge) {
        this.meanAge = meanAge;
    }

    public QuartilesStats getQuartiles() {
        return quartiles;
    }

    public void setQuartiles(QuartilesStats quartiles) {
        this.quartiles = quartiles;
    }

    public AgeModStats getMode() {
        return mode;
    }

    public void setMode(AgeModStats mode) {
        this.mode = mode;
    }
}
