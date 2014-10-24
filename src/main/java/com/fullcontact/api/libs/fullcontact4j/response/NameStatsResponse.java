package com.fullcontact.api.libs.fullcontact4j.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("name")
public class NameStatsResponse extends FCResponse {
    private String value;
    private String region;
    private GivenNameInfo given;
    private NameInfo family;

    public String getValue() {
        return value;
    }

    public String getRegion() {
        return region;
    }

    @JsonProperty("given")
    public GivenNameInfo getGivenNameInfo() {
        return given;
    }

    @JsonProperty("family")
    public NameInfo getFamily() {
        return family;
    }

    public static class GivenNameInfo {
        private NameInfo male;
        private NameInfo female;

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
        private AgeInfo age;

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

        public AgeInfo getAge() {
            return age;
        }
    }
    @JsonRootName("densityCurve")
    @JsonIgnoreProperties({"mode","quartiles"})
    public static class AgeInfo {
        private double meanAge;

        public double getMeanAge() {
            return meanAge;
        }
    }
}
