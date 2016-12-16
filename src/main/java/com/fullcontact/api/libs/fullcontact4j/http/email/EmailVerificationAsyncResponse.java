package com.fullcontact.api.libs.fullcontact4j.http.email;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

import java.io.IOException;

public class EmailVerificationAsyncResponse extends FCResponse {
    private static ObjectMapper mapper = new ObjectMapper();

    private String batchId;
    private String webhookUrl;
    private boolean completed;
    private EmailVerificationResponse response;

    public String getBatchId() {
        return batchId;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public boolean isCompleted() {
        return completed;
    }

    public EmailVerificationResponse getResponse() {
        return response;
    }

    /**
     * Factory method to create a webhook response from json.
     * @throws com.fullcontact.api.libs.fullcontact4j.FullContactException if there is a parsing/mapping error.
     */
    public static EmailVerificationAsyncResponse fromJson(String json) throws FullContactException {
        //Properties not present in the POJO are ignored instead of throwing exceptions
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //An empty string ("") is interpreted as null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try {
            return mapper.readValue(json, EmailVerificationAsyncResponse.class);
        } catch(JsonMappingException e) {
            throw new FullContactException("Failed to convert person json to a response", e);
        } catch(JsonParseException e) {
            throw new FullContactException("Json is not valid format", e);
        } catch(IOException e) {
            throw new FullContactException("Unexpected exception when parsing json", e);
        }
    }
}
