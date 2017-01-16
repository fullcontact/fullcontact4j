package com.fullcontact.api.libs.fullcontact4j.http;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import lombok.*;

import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WebhookResponse<R> extends FCResponse {

    private static ObjectMapper mapper = new ObjectMapper();
    private R result;
    private String webhookId;

    public R getResult() {
        return result;
    }

    public String getWebhookId() {
        return webhookId;
    }

    /**
     * Factory method to create a webhook response from json.
     * @param json the webhook response string
     * @param responseType the model class wrapped in the webhook response
     * @return a new WebhookResponse represented by the Json string
     * @throws com.fullcontact.api.libs.fullcontact4j.FullContactException if there is a parsing/mapping error.
     */
    public static <T> WebhookResponse<T> fromJson(String json, Class<T> responseType) throws FullContactException {
        //Properties not present in the POJO are ignored instead of throwing exceptions
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //An empty string ("") is interpreted as null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        try {
            return mapper.readValue(json,
                    mapper.getTypeFactory().constructParametricType(WebhookResponse.class, responseType));
        } catch(JsonMappingException e) {
            throw new FullContactException("Failed to convert person json to a result", e);
        } catch(JsonParseException e) {
            throw new FullContactException("Json is not valid format", e);
        } catch(IOException e) {
            throw new FullContactException("Unexpected exception when parsing json", e);
        }
    }
}
