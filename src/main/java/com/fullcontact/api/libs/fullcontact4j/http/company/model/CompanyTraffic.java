package com.fullcontact.api.libs.fullcontact4j.http.company.model;

import java.util.List;

public class CompanyTraffic {

    private List<TrafficRank> topCountryRanking;
    private List<TrafficRank> ranking;
    private List<TrafficVolume> volume;

    public static class TrafficRank {
        private Integer rank;
        private String locale;

        public Integer getRank() {
            return rank;
        }

        public String getLocale() {
            return locale;
        }
    }

    public static class TrafficVolume {
        private Integer monthlyReach;
        private Integer dailyReach;

        public Integer getMonthlyReach() {
            return monthlyReach;
        }

        public Integer getDailyReach() {
            return dailyReach;
        }
    }

    public List<TrafficRank> getTopCountryRanking() {
        return topCountryRanking;
    }

    public List<TrafficRank> getRanking() {
        return ranking;
    }

    public List<TrafficVolume> getVolume() {
        return volume;
    }
}
