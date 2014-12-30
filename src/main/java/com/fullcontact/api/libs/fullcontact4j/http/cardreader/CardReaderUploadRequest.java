package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderQuality;
import com.fullcontact.api.libs.fullcontact4j.enums.Casing;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import com.fullcontact.api.libs.fullcontact4j.http.WebhookBuilder;
import retrofit.Callback;

import java.io.InputStream;
import java.util.Map;

public class CardReaderUploadRequest extends FCRequest<CardReaderUploadConfirmResponse> {

    private RequestBodyJson body;
    private String accessToken;

    protected CardReaderUploadRequest(String accessToken, String front, String back, Map<String, String> params) {
        super(params);
        this.accessToken = accessToken;
        body = new RequestBodyJson();
        body.setFront(front);
        body.setBack(back);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<CardReaderUploadConfirmResponse> callback) {
       //if accesstoken is null, it is not added to the headers. if it is not null, it replaces API Key header
       //(see FullContactHttpInterface.DynamicHeaderOkClient)
       api.uploadCard(accessToken, params, body, callback);
    }

    public static class Builder extends WebhookBuilder<CardReaderUploadRequest> {

        private String cardFront;
        private String accessToken;
        private String cardBack;
        private Map<String, String> customParams;

        public Builder verified(CardReaderQuality quality) {
            params.put(FCConstants.PARAM_CARD_VERIFIED, quality.name());
            return this;
        }

        public Builder cardFront(InputStream stream) {
            cardFront = Utils.encodeToStringAndClose(stream);
            return this;
        }

        public Builder cardBack(InputStream stream) {
            cardBack = Utils.encodeToStringAndClose(stream);
            return this;
        }

        public Builder verifiedOnly(Boolean verifiedOnly) {
            if(verifiedOnly) {
                params.put(FCConstants.PARAM_CARD_RETURNED_DATA, "verifiedOnly");
            } else {
                params.remove(FCConstants.PARAM_CARD_RETURNED_DATA);
            }
            return this;
        }

        public Builder casing(Casing casing) {
            params.put(FCConstants.PARAM_CARD_CASING, casing.name().toLowerCase());
            return this;
        }

        public Builder sandbox(Sandbox sandboxParam) {
            params.put(FCConstants.PARAM_CARD_SANDBOX, sandboxParam.name());
            return this;
        }

        public Builder urid(String urid) {
            params.put(FCConstants.PARAM_CARD_URID, urid);
            return this;
        }

        public Builder accessToken(String token) {
            accessToken = token;
            return this;
        }

        public Builder customParams(Map<String, String> params) {
            customParams = params;
            return this;
        }

        public Builder webhookUrl(String url) {
            params.put(FCConstants.PARAM_WEBHOOK_URL, url);
            return this;
        }

        public Builder webhookId(String id) {
            params.put(FCConstants.PARAM_WEBHOOK_ID, id);
            return this;
        }

        public Builder webhookBody(Boolean rawJson) {
            if(rawJson) {
                params.put(FCConstants.PARAM_WEBHOOK_BODY, "json");
            } else {
                params.remove(FCConstants.PARAM_WEBHOOK_BODY);
            }
            return this;
        }

        protected void validate() {
            super.validate();
            if(!hasParam(FCConstants.PARAM_WEBHOOK_URL)) {
                throw new IllegalArgumentException("All CardReader requests must specify a webhook.");
            }
            if(cardFront == null) {
                throw new IllegalArgumentException("Cards must have a front image.");
            }
        }

        @Override
        protected CardReaderUploadRequest createInstance() {
            if(customParams != null) {
                params.putAll(customParams);
            }
            return new CardReaderUploadRequest(accessToken, cardFront, cardBack, params);
        }
    }

    //don't write null properties to json
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RequestBodyJson {
        private String front;

        public String getBack() {
            return back;
        }

        public void setBack(String back) {
            this.back = back;
        }

        public String getFront() {
            return front;
        }

        public void setFront(String front) {
            this.front = front;
        }

        private String back;

    }

    public enum Sandbox {
        PROCESSING,
        CALLBACK_MADE,
        CALLBACK_FAILED,
        CALLBACK_MADE_NOT_PROCESSABLE,
        CALLBACK_FAILED_NOT_PROCESSABLE;
    }
}
