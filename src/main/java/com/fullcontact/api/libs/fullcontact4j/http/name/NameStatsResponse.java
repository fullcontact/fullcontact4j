package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NameStatsResponse extends FCResponse {
    private NameStatsResult name = new NameStatsResult();
    private String region;

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
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

        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        @EqualsAndHashCode
        @ToString
        public static class GivenNameInfo {
            private NameInfo male = new NameInfo();
            private NameInfo female = new NameInfo();
            private int count;
            private int rank;
        }

        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        @EqualsAndHashCode
        @ToString
        public static class NameInfo {
            private int count;
            private double likelihood;
            private int rank;
            private double frequencyRatio;
            private AgeCurve age = new AgeCurve();
        }

        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        @EqualsAndHashCode
        @ToString
        public static class AgeCurve {
            @JsonProperty("densityCurve")
            private AgeInfo age = new AgeInfo();

            public AgeInfo getAgeInfo() {
                return age;
            }
        }

        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        @EqualsAndHashCode
        @ToString
        @JsonIgnoreProperties({"mode", "quartiles"})
        public static class AgeInfo {
            private double meanAge;
        }
    }
}
