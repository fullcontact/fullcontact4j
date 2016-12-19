package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonRootName("result")
public class NameSimilarityResponse extends FCResponse {
    private NameSimilarityResult result = new NameSimilarityResult();

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class NameSimilarityResult {
        @JsonProperty("SimMetrics")
        private SimMetricsInfo simMetrics = new SimMetricsInfo();
        @JsonProperty("SecondString")
        private SecondStringInfo secondString = new SecondStringInfo();
        @JsonProperty("FullContact")
        private FullContactInfo fullContact = new FullContactInfo();

        public SimMetricsInfo getSimMetricsAlgorithmResults() {
            return simMetrics;
        }

        public SecondStringInfo getSecondStringAlgorithmResults() {
            return secondString;
        }

        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        @EqualsAndHashCode
        @ToString
        @JsonIgnoreProperties("dice")
        public static class SimilarityInfo {
            private double similarity;
            private String timeTaken;
            private double distance;
            private double timeEstimated;
            private double timeActual;

        }

        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        @EqualsAndHashCode
        @ToString
        public static class SimMetricsInfo {
            private SimilarityInfo jaroWinkler = new SimilarityInfo();
            private SimilarityInfo levenshtein = new SimilarityInfo();

        }

        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        @EqualsAndHashCode
        @ToString
        public static class SecondStringInfo {
            private SimilarityInfo jaroWinkler = new SimilarityInfo();
            private SimilarityInfo levenshtein = new SimilarityInfo();
            private SimilarityInfo level2jaroWinkler = new SimilarityInfo();
            private SimilarityInfo level2levenshtein = new SimilarityInfo();

        }

        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        @EqualsAndHashCode
        @ToString
        public static class FullContactInfo {
            @JsonProperty("BigramAnalysis")
            private SimilarityInfo bigramAnalysis = new SimilarityInfo();

        }
    }
}
