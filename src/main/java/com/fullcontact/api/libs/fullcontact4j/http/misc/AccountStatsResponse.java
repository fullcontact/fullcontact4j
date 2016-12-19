package com.fullcontact.api.libs.fullcontact4j.http.misc;


import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountStatsResponse extends FCResponse {
    private String periodStart;
    private String periodEnd;
    private String plan;
    private double planBasePrice;
    private double planOveragePrice;
    private String applicationId;
    private List<AccountMetric> metrics = Collections.emptyList();

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class AccountMetric {
        private String metricName;
        private String metricId;
        private int planLevel;
        private int usage;
        private int remaining;
        private int overage;
    }
}
