package com.fullcontact.api.libs.fullcontact4j.http.person.model;

import java.util.Collections;
import java.util.List;

public class DigitalFootPrints {
    private List<DigitalFootPrintsTopic> topics = Collections.emptyList();

    private List<DigitalFootPrintsScore> scores = Collections.emptyList();
    public List<DigitalFootPrintsTopic> getTopics() {
        return topics;
    }
    public void setTopics(List<DigitalFootPrintsTopic> topics) {
        this.topics = topics;
    }
    public List<DigitalFootPrintsScore> getScores() {
        return scores;
    }
    public void setScores(List<DigitalFootPrintsScore> scores) {
        this.scores = scores;
    }


    public static class DigitalFootPrintsScore {

        private Float value;
        private String provider;
        private String type;

        public Float getValue() {
            return value;
        }
        public void setValue(Float value) {
            this.value = value;
        }
        public String getProvider(){
            return provider;
        }
        public void setProvider(String provider){
            this.provider = provider;
        }
        public String getType(){
            return type;
        }
        public void setType(String type){
            this.type = type;
        }
    }

    public static class DigitalFootPrintsTopic {
        private String value;
        private String provider;
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public String getProvider(){
            return provider;
        }
        public void setProvider(String provider){
            this.provider = provider;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DigitalFootPrints{");
        sb.append("topics=").append(topics.size());
        sb.append(", scores=").append(scores.size());
        sb.append('}');
        return sb.toString();
    }
}
