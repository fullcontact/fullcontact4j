package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import com.google.gson.annotations.SerializedName;

public class AgeStats {

    @SerializedName("densityCurve")
    private AgeDensityCurveStats densityCurve;

    public AgeDensityCurveStats getDensityCurve() {
        return densityCurve;
    }

    public void setDensityCurve(AgeDensityCurveStats densityCurve) {
        this.densityCurve = densityCurve;
    }
}
