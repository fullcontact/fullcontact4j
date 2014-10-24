package com.fullcontact.api.libs.fullcontact4j.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NameStatsResponse extends FCResponse {
    private NameStatsResult name;
    private String region;

    public String getRegion() { return region; }
    public NameStatsResult getName() { return name; }

    public static class NameStatsResult {
        private String value;
        @JsonProperty("given")
        private GivenNameInfo given;
        @JsonProperty("family")
        private NameInfo family;

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
            private NameInfo male;
            private NameInfo female;
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
            private AgeCurve age;

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
            private AgeInfo age;

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
