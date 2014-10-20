package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.response.PersonResponse;
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
            params.put(Constants.PARAM_PERSON_EMAIL, email);
            return this;
        }

        public Builder emailMd5(String emailMd5) {
            params.put(Constants.PARAM_PERSON_EMAIL_MD5, emailMd5);
            return this;
        }

        public Builder phone(String phone) {
            params.put(Constants.PARAM_PERSON_PHONE, phone);
            return this;
        }

        public Builder phoneCountryCode(String code) {
            params.put(Constants.PARAM_PERSON_PHONE_COUNTRY_CODE, code);
            return this;
        }

        public Builder twitterName(String twitterName) {
            params.put(Constants.PARAM_PERSON_TWITTER, twitterName);
            return this;
        }

        public Builder facebookName(String facebookName) {
            params.put(Constants.PARAM_PERSON_FACEBOOK, facebookName);
            return this;
        }
        
        public Builder facebookId(String facebookId) {
            params.put(Constants.PARAM_PERSON_FACEBOOK_ID, facebookId);
            return this;
        }

        public Builder jsonpCallback(String function) {
            params.put(Constants.PARAM_PERSON_CALLBACK, function);
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
        public PersonRequest createInstance() {
            return new PersonRequest(params);
        }

        public static final List<String> SEARCH_PARAMS = Arrays.asList(Constants.PARAM_PERSON_EMAIL,
                Constants.PARAM_PERSON_FACEBOOK, Constants.PARAM_PERSON_EMAIL_MD5, Constants.PARAM_PERSON_TWITTER,
                Constants.PARAM_PERSON_FACEBOOK_ID, Constants.PARAM_PERSON_PHONE);
    }
}
