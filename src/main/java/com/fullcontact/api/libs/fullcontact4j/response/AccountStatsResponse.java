package com.fullcontact.api.libs.fullcontact4j.response;

import java.util.List;

public class AccountStatsResponse extends FCResponse {
    private String periodStart;
    private String periodEnd;
    private String plan;
    private double planBasePrice;
    private double planOveragePrice;
    private String applicationId;
    private List<AccountMetric> metrics;

    public static class AccountMetric {
        private String metricName;
        private String metricId;
        private int planLevel;
        private int usage;
    }
}
