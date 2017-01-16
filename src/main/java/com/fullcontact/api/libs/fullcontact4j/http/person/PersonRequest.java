package com.fullcontact.api.libs.fullcontact4j.http.person;

import com.fullcontact.api.libs.fullcontact4j.*;
import com.fullcontact.api.libs.fullcontact4j.http.*;
import retrofit.Callback;

import java.util.*;

public class PersonRequest extends FCRequest<PersonResponse> {

    protected PersonRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<PersonResponse> callback) {
        api.getPerson(params, callback);
    }

    public static class Builder extends WebhookBuilder<Builder, PersonRequest> {

        public Builder email(String email) {
            params.put(FCConstants.PARAM_PERSON_EMAIL, email);
            return this;
        }

        public Builder emailMd5(String emailMd5) {
            params.put(FCConstants.PARAM_PERSON_EMAIL_MD5, emailMd5);
            return this;
        }

        public Builder emailSha256(String emailSha256) {
            params.put(FCConstants.PARAM_PERSON_EMAIL_SHA256, emailSha256);
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

        public Builder lookup(String lookupValue) {
            params.put(FCConstants.PARAM_PERSON_LOOKUP, lookupValue);
            return this;
        }

        public Builder macromeasures(boolean enabled) {
            params.put(FCConstants.PARAM_PERSON_MACROMEASURES, enabled?"true":"false");
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
                            "search parameters: email, emailMd5, emailSha256, twitter handle, phone.");
                }
            }
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        protected PersonRequest createInstance() {
            return new PersonRequest(params);
        }

        public static final List<String> SEARCH_PARAMS = Arrays.asList(FCConstants.PARAM_PERSON_EMAIL,
                FCConstants.PARAM_PERSON_EMAIL_MD5, FCConstants.PARAM_PERSON_EMAIL_SHA256,
                FCConstants.PARAM_PERSON_TWITTER, FCConstants.PARAM_PERSON_PHONE, FCConstants.PARAM_PERSON_LOOKUP);
    }
}
