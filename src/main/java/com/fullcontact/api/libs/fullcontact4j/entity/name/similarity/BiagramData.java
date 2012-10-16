package com.fullcontact.api.libs.fullcontact4j.entity.name.similarity;

import com.google.gson.annotations.SerializedName;

public class BiagramData {

    @SerializedName("dice")
    private SimilarityInfo diceInfo;

    public SimilarityInfo getDiceInfo() {
        return diceInfo;
    }

    public void setDiceInfo(SimilarityInfo diceInfo) {
        this.diceInfo = diceInfo;
    }
}
