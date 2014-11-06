package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

@JsonRootName("result")
public class NameSimilarityResponse extends FCResponse {

    private NameSimilarityResult result = new NameSimilarityResult();

    public NameSimilarityResult getResult() { return result; }

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

        public FullContactInfo getFullContactAlgorithmResults() {
            return fullContact;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("NameSimilarityResult{");
            sb.append('}');
            return sb.toString();
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
            private SimilarityInfo jaroWinkler = new SimilarityInfo();
            private SimilarityInfo levenshtein = new SimilarityInfo();

            public SimilarityInfo getJaroWinkler() {
                return jaroWinkler;
            }

            public SimilarityInfo getLevenshtein() {
                return levenshtein;
            }
        }

        public static class SecondStringInfo {
            private SimilarityInfo jaroWinkler = new SimilarityInfo();
            private SimilarityInfo levenshtein = new SimilarityInfo();
            private SimilarityInfo level2jaroWinkler = new SimilarityInfo();
            private SimilarityInfo level2levenshtein = new SimilarityInfo();

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
            private SimilarityInfo bigramAnalysis = new SimilarityInfo();

            public SimilarityInfo getBigramAnalysis() {
                return bigramAnalysis;
            }
        }
    }
}
