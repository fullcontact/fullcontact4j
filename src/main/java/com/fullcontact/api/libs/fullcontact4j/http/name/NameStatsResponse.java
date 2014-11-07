package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

public class NameStatsResponse extends FCResponse {
    private NameStatsResult name = new NameStatsResult();
    private String region;

    public String getRegion() { return region; }
    public NameStatsResult getName() { return name; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NameStatsResponse{}");
        return sb.toString();
    }

    public static class NameStatsResult {
        private String value;
        @JsonProperty("given")
        private GivenNameInfo given = new GivenNameInfo();
        @JsonProperty("family")
        private NameInfo family = new NameInfo();

        public String getValue() {
            return value;
        }

        public GivenNameInfo getGivenNameInfo() {
            return given;
        }

        public NameInfo getFamilyInfo() {
            return family;
        }

        public static class GivenNameInfo {
            private NameInfo male = new NameInfo();
            private NameInfo female = new NameInfo();
            private int count;
            private int rank;

            public int getRank() { return rank; }

            public int getCount() { return count; }

            public NameInfo getMale() {
                return male;
            }

            public NameInfo getFemale() {
                return female;
            }
        }

        public static class NameInfo {
            private int count;
            private double likelihood;
            private int rank;
            private double frequencyRatio;
            @JsonProperty("age")
            private AgeCurve age = new AgeCurve();

            public int getCount() {
                return count;
            }

            public double getLikelihood() {
                return likelihood;
            }

            public int getRank() {
                return rank;
            }

            public double getFrequencyRatio() {
                return frequencyRatio;
            }

            public AgeCurve getAge() {
                return age;
            }
        }

        public static class AgeCurve {
            @JsonProperty("densityCurve")
            private AgeInfo age = new AgeInfo();

            public AgeInfo getAgeInfo() { return age; }
        }

        @JsonIgnoreProperties({"mode", "quartiles"})
        public static class AgeInfo {
            private double meanAge;

            public double getMeanAge() {
                return meanAge;
            }
        }
    }
}
