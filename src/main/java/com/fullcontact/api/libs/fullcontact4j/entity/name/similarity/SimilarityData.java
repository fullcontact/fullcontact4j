package com.fullcontact.api.libs.fullcontact4j.entity.name.similarity;

import com.google.gson.annotations.SerializedName;

public class SimilarityData {

    @SerializedName("jaroWinkler")
    private SimilarityInfo jaroWinklerInfo;

    @SerializedName("levenshtein")
    private SimilarityInfo levenshteinInfo;

    @SerializedName("level2levenshtein")
    private SimilarityInfo level2levenshteinInfo;

    @SerializedName("level2jaroWinkler")
    private SimilarityInfo level2jaroWinklerInfo;

    public SimilarityInfo getJaroWinklerInfo() {
        return jaroWinklerInfo;
    }

    public void setJaroWinklerInfo(SimilarityInfo jaroWinklerInfo) {
        this.jaroWinklerInfo = jaroWinklerInfo;
    }

    public SimilarityInfo getLevenshteinInfo() {
        return levenshteinInfo;
    }

    public void setLevenshteinInfo(SimilarityInfo levenshteinInfo) {
        this.levenshteinInfo = levenshteinInfo;
    }

    public SimilarityInfo getLevel2levenshteinInfo() {
        return level2levenshteinInfo;
    }

    public void setLevel2levenshteinInfo(SimilarityInfo level2levenshteinInfo) {
        this.level2levenshteinInfo = level2levenshteinInfo;
    }

    public SimilarityInfo getLevel2jaroWinklerInfo() {
        return level2jaroWinklerInfo;
    }

    public void setLevel2jaroWinklerInfo(SimilarityInfo level2jaroWinklerInfo) {
        this.level2jaroWinklerInfo = level2jaroWinklerInfo;
    }
}
