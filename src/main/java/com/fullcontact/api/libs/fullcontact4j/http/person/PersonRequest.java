package com.fullcontact.api.libs.fullcontact4j.http.person;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import com.fullcontact.api.libs.fullcontact4j.http.WebhookBuilder;
import com.fullcontact.api.libs.fullcontact4j.http.cardreader.CardReaderUploadConfirmResponse;
import retrofit.Callback;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PersonRequest extends FCRequest<PersonResponse> {

    protected PersonRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<PersonResponse> callback) {
        api.getPerson(params, callback);
    }

    public static class Builder extends WebhookBuilder<PersonRequest> {

        public Builder email(String email) {
            params.put(FCConstants.PARAM_PERSON_EMAIL, email);
            return this;
        }

        public Builder emailMd5(String emailMd5) {
            FCResponse r = new CardReaderUploadConfirmResponse();
            params.put(FCConstants.PARAM_PERSON_EMAIL_MD5, emailMd5);
            return this;
        }

        public Builder phone(String phone) {
            params.put(FCConstants.PARAM_PERSON_PHONE, phone);
            return this;
        }

        public Builder phoneCountryCode(String code) {
            params.put(FCConstants.PARAM_PERSON_PHONE_COUNTRY_CODE, code);
            return this;
        }

        public Builder twitterName(String twitterName) {
            params.put(FCConstants.PARAM_PERSON_TWITTER, twitterName);
            return this;
        }

        public Builder facebookName(String facebookName) {
            params.put(FCConstants.PARAM_PERSON_FACEBOOK, facebookName);
            return this;
        }
        
        public Builder facebookId(String facebookId) {
            params.put(FCConstants.PARAM_PERSON_FACEBOOK_ID, facebookId);
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
            //for want of a java 8!
            Integer foundSearchParams = 0;
            for(String key : params.keySet()) {
                if(SEARCH_PARAMS.contains(key)) {
                    foundSearchParams++;
                }
                if(foundSearchParams > 1) {
                    throw new IllegalArgumentException("A person request can only have one of the following " +
                            "search parameters: email, emailMd5, facebook, facebookId, twitter handle, phone.");
                }
            }
        }

        @Override
        protected PersonRequest createInstance() {
            return new PersonRequest(params);
        }

        public static final List<String> SEARCH_PARAMS = Arrays.asList(FCConstants.PARAM_PERSON_EMAIL,
                FCConstants.PARAM_PERSON_FACEBOOK, FCConstants.PARAM_PERSON_EMAIL_MD5, FCConstants.PARAM_PERSON_TWITTER,
                FCConstants.PARAM_PERSON_FACEBOOK_ID, FCConstants.PARAM_PERSON_PHONE);
    }
}
