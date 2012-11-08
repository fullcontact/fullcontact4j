package com.fullcontact.api.libs.fullcontact4j.entity.batch;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class BatchResponse {

    @SerializedName("status")
    private int statusCode;

    Map<String, String> results = new HashMap<String, String>();

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getResults() {
        return results;
    }

    public void setResults(Map<String, String> results) {
        this.results = results;
    }

    public void addResult(String key, String jsonResponse) {
        this.results.put(key, jsonResponse);
    }

}
