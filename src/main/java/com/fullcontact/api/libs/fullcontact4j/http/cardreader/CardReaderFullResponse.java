package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderQuality;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import com.fullcontact.api.libs.fullcontact4j.http.cardreader.model.ContactInfo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CardReaderFullResponse extends FCResponse {

    private static ObjectMapper mapper = new ObjectMapper();
    private String lastWebhookAttempt;
    private int webhookAttempts;
    private String webhookUrl;
    private CardReaderQuality quality;
    private String submitted;
    private ContactInfo contact = new ContactInfo();
    private String id;
    @JsonProperty("vCardUrl")
    private String vCardUrl;
    private ContactInfo unverifiedContact = new ContactInfo();
    private List<String> unverifiedFields = Collections.emptyList();
    private String unverifiedVCardUrl;
    private String clientServerResponseCode;
    private String clientServerResponseBody;
    private String status;

    @JsonProperty("status")
    public String getCardStatus() {
        return status;
    }

    public String getClientServerResponseCode() {
        return clientServerResponseCode;
    }

    public String getClientServerResponseBody() {
        return clientServerResponseBody;
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

    public Date getSubbmittedDate() {
        if (submitted == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            return df.parse(submitted);
        } catch (ParseException e) {
            Utils.info("failed to parse date: " + submitted);
            return null;
        }
    }

    public Date getLastWebhookAttemptDate() {
        if (lastWebhookAttempt == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            return df.parse(lastWebhookAttempt);
        } catch (ParseException e) {
            Utils.info("failed to parse date: " + lastWebhookAttempt);
            return null;
        }
    }

    public ContactInfo getContact() {
        return contact;
    }

    public String getId() {
        return id;
    }

    public String getVCardUrl() {
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
     *
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
        } catch (JsonMappingException e) {
            throw new FullContactException("Failed to convert webhook json to a card reader response", e);
        } catch (JsonParseException e) {
            throw new FullContactException("Json is not valid format", e);
        } catch (IOException e) {
            throw new FullContactException("Unexpected exception when parsing json", e);
        }
    }

    private CardReaderFullResponse() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CardReaderFullResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", quality=").append(quality);
        sb.append('}');
        return sb.toString();
    }
}
