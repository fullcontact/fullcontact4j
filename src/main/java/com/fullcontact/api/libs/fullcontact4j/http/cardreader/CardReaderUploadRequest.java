package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderQuality;
import com.fullcontact.api.libs.fullcontact4j.enums.Casing;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import com.fullcontact.api.libs.fullcontact4j.http.WebhookBuilder;
import com.fullcontact.api.libs.fullcontact4j.http.retrofit.TypedInputStream;

import com.fasterxml.jackson.annotation.JsonInclude;
import retrofit.Callback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class CardReaderUploadRequest extends FCRequest<CardReaderUploadConfirmResponse> {

    private InputStream cardFrontIS;
    private InputStream cardBackIS;
    private String mimeType;
    private String accessToken;

    public CardReaderUploadRequest(Map<String, String> params, InputStream cardFrontIS, InputStream cardBackIS,
        String mimeType, String accessToken) {
        super(params);
        this.cardFrontIS = cardFrontIS;
        this.cardBackIS = cardBackIS;
        this.mimeType = mimeType;
        this.accessToken = accessToken;
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<CardReaderUploadConfirmResponse> callback) {
        //if accesstoken is null, it is not added to the headers. if it is not null, it replaces API Key header
        //(see FullContactHttpInterface.DynamicHeaderOkClient)
        if(cardBackIS == null) {
            api.uploadCardForm(accessToken, params, new TypedInputStream(mimeType, cardFrontIS), callback);
        } else {
            api.uploadCardForm(accessToken, params, new TypedInputStream(mimeType, cardFrontIS),
                new TypedInputStream(mimeType, cardBackIS), callback);
        }
    }

    public static class Builder extends WebhookBuilder<Builder, CardReaderUploadRequest> {

        private InputStream cardFrontIS;
        private InputStream cardBackIS;
        private String mimeType;
        private String accessToken;

        public Builder verified(CardReaderQuality quality) {
            params.put(FCConstants.PARAM_CARD_VERIFIED, quality.name());
            return this;
        }

        public Builder cardFront(InputStream stream) {
            cardFrontIS = stream;
            return this;
        }

        public Builder cardFront(File file) throws FileNotFoundException {
            cardFrontIS = new FileInputStream(file);
            return this;
        }

        public Builder cardBack(InputStream stream) {
            cardBackIS = stream;
            return this;
        }

        public Builder cardBack(File file) throws FileNotFoundException {
            cardBackIS = new FileInputStream(file);
            return this;
        }

        // specify the file type for this card. only needed if using cardFront(File) or cardBack(File).
        public Builder mimeType(String m) {
            mimeType = m;
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

        protected void validate() {
            super.validate();
            if(!hasParam(FCConstants.PARAM_WEBHOOK_URL)) {
                throw new IllegalArgumentException("All CardReader requests must specify a webhook.");
            }
            if(cardFrontIS == null) {
                throw new IllegalArgumentException("Cards must have a front image.");
            }
            if(cardFrontIS != null && mimeType == null) {
                throw new IllegalArgumentException("mimeType must be set with file upload");
            }
        }

        @Override
        protected CardReaderUploadRequest createInstance() {
            return new CardReaderUploadRequest(params, cardFrontIS, cardBackIS, mimeType, accessToken);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public enum Sandbox {
        PROCESSING,
        CALLBACK_MADE,
        CALLBACK_FAILED,
        CALLBACK_MADE_NOT_PROCESSABLE,
        CALLBACK_FAILED_NOT_PROCESSABLE;
    }
}
