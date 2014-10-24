package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.http.cardreader.model.ContactInfo;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderQuality;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

import java.io.IOException;
import java.util.List;

public class CardReaderFullResponse extends FCResponse {

    private static ObjectMapper mapper = new ObjectMapper();
    private String lastWebhookAttempt;
    private int webhookAttempts;
    private String webhookUrl;
    private CardReaderQuality quality;
    private String submitted;
    private ContactInfo contact;
    private String id;
    private String vCardUrl;
    private ContactInfo unverifiedContact;
    private List<String> unverifiedFields;
    private String unverifiedVCardUrl;
    private String clientServerResponseCode;
    private String status;

    @JsonProperty("status")
    public String getCardStatus() { return status; }

    public String getClientServerResponseCode() {
        return clientServerResponseCode;
    }

    public String getLastWebhookAttempt() {
        return lastWebhookAttempt;
    }

    public int getWebhookAttempts() {
        return webhookAttempts;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public CardReaderQuality getQuality() {
        return quality;
    }

    public String getSubmitted() {
        return submitted;
    }

    public ContactInfo getContact() {
        return contact;
    }

    public String getId() {
        return id;
    }

    public String getvCardUrl() {
        return vCardUrl;
    }

    public ContactInfo getUnverifiedContact() {
        return unverifiedContact;
    }

    public List<String> getUnverifiedFields() {
        return unverifiedFields;
    }

    public String getUnverifiedVCardUrl() {
        return unverifiedVCardUrl;
    }

    /**
     * Factory method to create a webhook response from json.
     * @param json
     * @return a new CardReaderWebhookResponse
     * @throws FullContactException if there is a parsing/mapping error.
     */
    public static CardReaderFullResponse fromJson(String json) throws FullContactException {
        //Properties not present in the POJO are ignored instead of throwing exceptions
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //An empty string ("") is interpreted as null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try {
            return mapper.readValue(json, CardReaderFullResponse.class);
        } catch(JsonMappingException e) {
            throw new FullContactException("Failed to convert webhook json to a card reader response", e);
        } catch(JsonParseException e) {
            throw new FullContactException("Json is not valid format", e);
        } catch(IOException e) {
            throw new FullContactException("Unexpected exception when parsing json", e);
        }
    }

    private CardReaderFullResponse() {}
}
