package com.fullcontact.api.libs.fullcontact4j.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("result")
public class NameSimilarityResponse extends FCResponse {

    private NameSimilarityResult result;

    public NameSimilarityResult getResult() { return result; }

    public static class NameSimilarityResult {

        @JsonProperty("SimMetrics")
        private SimMetricsInfo simMetrics;
        @JsonProperty("SecondString")
        private SecondStringInfo secondString;
        @JsonProperty("FullContact")
        private FullContactInfo fullContact;

        public SimMetricsInfo getSimMetricsAlgorithmResults() {
            return simMetrics;
        }

        public SecondStringInfo getSecondStringAlgorithmResults() {
            return secondString;
        }

        public FullContactInfo getFullContactAlgorithmResults() {
            return fullContact;
        }

        @JsonIgnoreProperties("dice")
        public static class SimilarityInfo {
            private double similarity;
            private String timeTaken;
            private double distance;
            private double timeEstimated;
            private double timeActual;

            public double getTimeActual() {
                return timeActual;
            }

            public double getTimeEstimated() {
                return timeEstimated;
            }

            public String getTimeTaken() {
                return timeTaken;
            }

            public double getSimilarity() {
                return similarity;
            }

            public double getDistance() { return distance; }

        }

        public static class SimMetricsInfo {
            private SimilarityInfo jaroWinkler;
            private SimilarityInfo levenshtein;

            public SimilarityInfo getJaroWinkler() {
                return jaroWinkler;
            }

            public SimilarityInfo getLevenshtein() {
                return levenshtein;
            }
        }

        public static class SecondStringInfo {
            private SimilarityInfo jaroWinkler;
            private SimilarityInfo levenshtein;
            private SimilarityInfo level2jaroWinkler;
            private SimilarityInfo level2levenshtein;

            public SimilarityInfo getJaroWinkler() {
                return jaroWinkler;
            }

            public SimilarityInfo getLevenshtein() {
                return levenshtein;
            }

            public SimilarityInfo getLevel2jaroWinkler() {
                return level2jaroWinkler;
            }

            public SimilarityInfo getLevel2levenshtein() {
                return level2levenshtein;
            }
        }

        public static class FullContactInfo {
            @JsonProperty("BigramAnalysis")
            private SimilarityInfo bigramAnalysis;

            public SimilarityInfo getBigramAnalysis() {
                return bigramAnalysis;
            }
        }
    }
}
