package com.fullcontact.api.libs.fullcontact4j.handlers;

public class BaseHandler {
    protected String apiKey;

    public BaseHandler(String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("apiKey cannot be null.");
        }

        if (apiKey.trim().length() == 0) {
            throw new IllegalArgumentException("apiKey cannot be empty.");
        }

        this.apiKey = apiKey;
    }

}
