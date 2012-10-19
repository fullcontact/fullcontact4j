package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import com.google.gson.annotations.SerializedName;

public class QuartilesStats {

    @SerializedName("q1")
    private double quartile1;

    @SerializedName("q2")
    private double quartile2;

    @SerializedName("q3")
    private double quartile3;

    public double getQuartile1() {
        return quartile1;
    }

    public void setQuartile1(double quartile1) {
        this.quartile1 = quartile1;
    }

    public double getQuartile2() {
        return quartile2;
    }

    public void setQuartile2(double quartile2) {
        this.quartile2 = quartile2;
    }

    public double getQuartile3() {
        return quartile3;
    }

    public void setQuartile3(double quartile3) {
        this.quartile3 = quartile3;
    }
}
