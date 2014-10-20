package com.fullcontact.api.libs.fullcontact4j.entity.name.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgeStats {


    private AgeDensityCurveStats densityCurve;

    public AgeDensityCurveStats getDensityCurve() {
        return densityCurve;
    }

    public void setDensityCurve(AgeDensityCurveStats densityCurve) {
        this.densityCurve = densityCurve;
    }
}
