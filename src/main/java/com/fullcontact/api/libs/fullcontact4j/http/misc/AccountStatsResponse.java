package com.fullcontact.api.libs.fullcontact4j.http.misc;


import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

import java.util.Collections;
import java.util.List;

public class AccountStatsResponse extends FCResponse {
    private String periodStart;
    private String periodEnd;
    private String plan;
    private double planBasePrice;
    private double planOveragePrice;
    private String applicationId;
    private List<AccountMetric> metrics = Collections.emptyList();

    public String getPeriodStart() {
        return periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public String getPlan() {
        return plan;
    }

    public double getPlanBasePrice() {
        return planBasePrice;
    }

    public double getPlanOveragePrice() {
        return planOveragePrice;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public List<AccountMetric> getMetrics() {
        return metrics;
    }

    public static class AccountMetric {
        private String metricName;
        private String metricId;
        private int planLevel;
        private int usage;
        private int remaining;
        private int overage;

        public int getRemaining() {
            return remaining;
        }

        public int getOverage() {
            return overage;
        }

        public String getMetricName() {
            return metricName;
        }

        public String getMetricId() {
            return metricId;
        }

        public int getPlanLevel() {
            return planLevel;
        }

        public int getUsage() {
            return usage;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("AccountMetric{");
            sb.append("metricName='").append(metricName).append('\'');
            sb.append(", usage=").append(usage);
            sb.append(", remaining=").append(remaining);
            sb.append(", planLevel=").append(planLevel);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountStatsResponse{");
        sb.append("plan='").append(plan).append('\'');
        sb.append(", planBasePrice=").append(planBasePrice);
        sb.append(", applicationId='").append(applicationId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
