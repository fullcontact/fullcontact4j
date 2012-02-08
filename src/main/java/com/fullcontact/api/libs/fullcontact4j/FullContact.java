package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.FullContactEntity;
import com.google.gson.Gson;

import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;

import java.text.MessageFormat;

public class FullContact {
    private String apiKey;

    public FullContact(String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("apiKey cannot be null.");
        }

        if (apiKey.trim().length() == 0) {
            throw new IllegalArgumentException("apiKey cannot be empty.");
        }

        this.apiKey = apiKey;
    }

    public FullContactEntity getPersonInformation(String email)
            throws FullContactException {
        String requestParams =
            MessageFormat.format(Constants.EMAIL_FORMAT, email) + "&"
            + MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);

        return parseResponseJson(FullContactHttpRequest.sendRequest(requestParams));
    }

    public FullContactEntity parseResponseJson(String response) {
        FullContactEntity message;Gson gson = new Gson();
        message = gson.fromJson(response, FullContactEntity.class);
        return message;
    }

    public FullContactEntity getPersonInformation(String email,
            int timeoutSeconds)
            throws FullContactException {
        String requestParams =
            MessageFormat.format(Constants.EMAIL_FORMAT, email) + "&"
            + MessageFormat.format(Constants.API_KEY_FORMAT, apiKey) + "&"
            + MessageFormat.format(Constants.TIMEOUT_SECONDS_FORMAT,
                                   timeoutSeconds);
        FullContactEntity message = null;
        Gson gson = new Gson();
        String response = FullContactHttpRequest.sendRequest(requestParams);

        message = gson.fromJson(response, FullContactEntity.class);

        return message;
    }
}
