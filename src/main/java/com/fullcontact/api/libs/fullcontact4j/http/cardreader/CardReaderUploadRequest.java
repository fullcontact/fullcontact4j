package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderQuality;
import com.fullcontact.api.libs.fullcontact4j.enums.Casing;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import com.fullcontact.api.libs.fullcontact4j.http.WebhookBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import retrofit.Callback;
import retrofit.mime.TypedFile;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class CardReaderUploadRequest extends FCRequest<CardReaderUploadConfirmResponse> {

    private String cardFrontStr;
    private String cardBackStr;
    private File cardFrontFile;
    private File cardBackFile;
    private String mimeType;
    private String accessToken;

    public CardReaderUploadRequest(Map<String, String> params, String cardFrontStr, String cardBackStr, File
        cardFrontFile, File cardBackFile, String mimeType, String accessToken) {
        super(params);
        this.cardFrontStr = cardFrontStr;
        this.cardBackStr = cardBackStr;
        this.cardFrontFile = cardFrontFile;
        this.cardBackFile = cardBackFile;
        this.mimeType = mimeType;
        this.accessToken = accessToken;
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<CardReaderUploadConfirmResponse> callback) {
        //if accesstoken is null, it is not added to the headers. if it is not null, it replaces API Key header
        //(see FullContactHttpInterface.DynamicHeaderOkClient)
        if(cardFrontStr != null) {
            RequestBodyJson body = new RequestBodyJson();
            body.setFront(cardFrontStr);
            body.setBack(cardBackStr);
            api.uploadCard(accessToken, params, body, callback);
        } else {
            if(cardBackFile == null) {
                api.uploadCardForm(accessToken, params, new TypedFile(mimeType, cardFrontFile), callback);
            } else {
                api.uploadCardForm(accessToken, params, new TypedFile(mimeType, cardFrontFile),
                    new TypedFile(mimeType, cardBackFile), callback);
            }
        }
    }

    public static class Builder extends WebhookBuilder<Builder, CardReaderUploadRequest> {

        private String cardFrontStr;
        private String cardBackStr;
        private File cardFrontFile;
        private File cardBackFile;
        private String mimeType;
        private String accessToken;

        public Builder verified(CardReaderQuality quality) {
            params.put(FCConstants.PARAM_CARD_VERIFIED, quality.name());
            return this;
        }

        public Builder cardFront(InputStream stream) {
            cardFrontStr = Utils.encodeToStringAndClose(stream);
            return this;
        }

        public Builder cardFront(File file) {
            cardFrontFile = file;
            return this;
        }

        public Builder cardBack(InputStream stream) {
            cardBackStr = Utils.encodeToStringAndClose(stream);
            return this;
        }

        public Builder cardBack(File file) {
            cardBackFile = file;
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
            if(cardFrontStr == null && cardFrontFile == null) {
                throw new IllegalArgumentException("Cards must have a front image.");
            }
            if(cardFrontStr != null || cardBackStr != null) {
                if(cardFrontFile != null || cardBackFile != null) {
                    throw new IllegalArgumentException("Cannot mix file upload with InputStream upload");
                }
            }
            if(cardFrontFile != null && mimeType == null) {
                throw new IllegalArgumentException("mimeType must be set with file upload");
            }
        }

        @Override
        protected CardReaderUploadRequest createInstance() {
            return new CardReaderUploadRequest(params, cardFrontStr, cardBackStr, cardFrontFile, cardBackFile,
                mimeType, accessToken);
        }

        @Override
        protected Builder self() {
            return this;
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
