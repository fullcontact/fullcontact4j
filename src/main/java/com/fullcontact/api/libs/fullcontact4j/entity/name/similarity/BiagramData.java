package com.fullcontact.api.libs.fullcontact4j.entity.name.similarity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiagramData {


    private SimilarityInfo diceInfo;

    public SimilarityInfo getDiceInfo() {
        return diceInfo;
    }

    public void setDiceInfo(SimilarityInfo diceInfo) {
        this.diceInfo = diceInfo;
    }
}
