package com.fullcontact.example.webhook;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.http.person.PersonResponse;

import java.net.URLDecoder;

/**
 * Created on 12/30/14 at 3:11 PM
 * Simple POJO to encapsulate Person API Webhook responses
 *
 * @author michie <ken@fullcontact.com>
 */
public class WebhookResponse {
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        //Properties not present in the POJO are ignored instead of throwing exceptions
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //An empty string ("") is interpreted as null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    protected PersonResponse result;
    protected String webhookId;

    public WebhookResponse(PersonResponse result, String webhookId) {
        this.result = result;
        this.webhookId = webhookId;
    }

    public WebhookResponse() {
    }

    public void setResult(PersonResponse result) {
        this.result = result;
    }

    public void setWebhookId(String webhookId) {
        this.webhookId = webhookId;
    }

    public PersonResponse getResult() {
        return result;
    }

    public String getWebhookId() {
        return webhookId;
    }

    public static WebhookResponse fromJson(String json) throws FullContactException {
        try {
            // 1) if webhookBody=json was provided, this just works.
            return mapper.readValue(json, WebhookResponse.class);
        } catch (Exception e) {
            try {
                // 2) Assume that 'webhookBody=json' was not provided, (legacy serialization)
                // Work some magic and to turn it back into normal JSON
                System.out.println("Unfortunate use of legacy serialization - webhookBody=json is preferred");

                String urlEncoded = URLDecoder.decode(json, "UTF-8");
                String[] parts = urlEncoded.split("=");
                PersonResponse person = null;
                String webhookId = null;

                // Iterate as key=value pairs
                for (int i = 0; i < parts.length; i = i + 2) {
                    if ("result".equals(parts[i])) {
                        person = PersonResponse.fromJson(parts[i + 1]);
                    } else if ("webhookId".equals(parts[i])) {
                        webhookId = parts[i + 1];
                    }
                }
                WebhookResponse response = new WebhookResponse(person, webhookId);
                return response;
            } catch (Exception e1) {
                throw new FullContactException("Unexpected exception when parsing json", e1);
            }
        }
    }
}
