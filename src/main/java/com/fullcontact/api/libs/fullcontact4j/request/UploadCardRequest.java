package com.fullcontact.api.libs.fullcontact4j.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderCasing;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderQuality;
import com.fullcontact.api.libs.fullcontact4j.response.UploadCardConfirmResponse;
import retrofit.Callback;

import java.io.InputStream;
import java.util.Map;

public class UploadCardRequest extends FCRequest<UploadCardConfirmResponse> {

    private RequestBodyJson body;
    private String accessToken;

    protected UploadCardRequest(String accessToken, String front, String back, Map<String, String> params) {
        super(params);
        this.accessToken = accessToken;
        body = new RequestBodyJson();
        body.setFront(front);
        body.setBack(back);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<UploadCardConfirmResponse> callback) {
       //if accesstoken is null, it is not added to the headers. if it is not null, it replaces API Key header
        //(see FullContactHttpInterface.DynamicHeaderOkClient)
       api.uploadCard(accessToken, params, body, callback);
    }

    public static class Builder extends WebhookBuilder<UploadCardRequest> {

        private String cardFront;
        private String accessToken;
        private String cardBack;

        public Builder verified(CardReaderQuality quality) {
            params.put(Constants.PARAM_CARD_VERIFIED, quality.name());
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
                params.put(Constants.PARAM_CARD_RETURNED_DATA, "verifiedOnly");
            } else {
                params.remove(Constants.PARAM_CARD_RETURNED_DATA);
            }
            return this;
        }

        public Builder casing(CardReaderCasing casing) {
            params.put(Constants.PARAM_CARD_CASING, casing.name().toLowerCase());
            return this;
        }

        public Builder sandbox(String sandboxParam) {
            params.put(Constants.PARAM_CARD_SANDBOX, sandboxParam);
            return this;
        }

        public Builder urid(String urid) {
            params.put(Constants.PARAM_CARD_URID, urid);
            return this;
        }

        public Builder accessToken(String token) {
            accessToken = token;
            return this;
        }

        public Builder webhookUrl(String url) {
            params.put(Constants.PARAM_WEBHOOK_URL, url);
            return this;
        }

        public Builder webhookId(String id) {
            params.put(Constants.PARAM_WEBHOOK_ID, id);
            return this;
        }

        public Builder webhookBody(Boolean responseInBody) {
            if(responseInBody) {
                params.put(Constants.PARAM_WEBHOOK_BODY, "json");
            } else {
                params.remove(Constants.PARAM_WEBHOOK_BODY);
            }
            return this;
        }

        protected void validate() {
            super.validate();
            if(!hasParam(Constants.PARAM_WEBHOOK_URL)) {
                throw new IllegalArgumentException("All CardReader requests must specify a webhook.");
            }
            if(cardFront == null) {
                throw new IllegalArgumentException("Cards must have a front image.");
            }
        }

        @Override
        protected UploadCardRequest createInstance() {
            return new UploadCardRequest(accessToken, cardFront, cardBack, params);
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
}
