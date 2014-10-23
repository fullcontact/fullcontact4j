package com.fullcontact.api.libs.fullcontact4j.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

//might need to include DeserializationFeature.UNWRAP_ROOT_VALUE
@JsonRootName("results")
public class NameSimilarityResponse extends FCResponse {
    private SimMetricsInfo simMetrics;
    private SecondStringInfo secondString;
    private FullContactInfo fullContact;

    @JsonProperty("simMetrics")
    public SimMetricsInfo getSimMetricsAlgorithmResults() {
        return simMetrics;
    }

    @JsonProperty("secondString")
    public SecondStringInfo getSecondStringAlgorithmResults() {
        return secondString;
    }

    @JsonProperty("fullContact")
    public FullContactInfo getFullContactAlgorithmResults() {
        return fullContact;
    }

    public static class SimilarityInfo {
        private double similarity;
        private String timeTaken;
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
        private SimilarityInfo bigramAnalysis;

        public SimilarityInfo getBigramAnalysis() {
            return bigramAnalysis;
        }
    }
}
