package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

public class AgeDensityCurveStats {


    private double meanAge;


    private QuartilesStats quartiles;


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
