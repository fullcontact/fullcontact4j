package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import lombok.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties("status")
public class CardReaderFullResponse extends FCResponse {
    private static ObjectMapper mapper = new ObjectMapper();

    @Getter private String lastWebhookAttempt;
    @Getter private int webhookAttempts;
    @Getter private String webhookUrl;
    @Getter private CardReaderQuality quality;
    @Getter private String submitted;
    @Getter private ContactInfo contact = new ContactInfo();
    @Getter private String id;
    @JsonProperty("vCardUrl")
    @Getter private String vCardUrl;
    @Getter private ContactInfo unverifiedContact = new ContactInfo();
    @Getter private List<String> unverifiedFields = Collections.emptyList();
    @Getter private String unverifiedVCardUrl;
    @Getter private String clientServerResponseCode;
    @Getter private String clientServerResponseBody;
    private String status;

    @JsonProperty("status")
    public String getCardStatus() {
        return status;
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
}
