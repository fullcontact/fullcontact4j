package com.fullcontact.api.libs.fullcontact4j.http.email;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.Map;

public class EmailVerificationRequest extends FCRequest<EmailVerificationResponse> {

    protected EmailVerificationRequest(Map<String, String> params) {
        super(params);
    }

    protected void makeRequest(FullContactApi api, Callback<EmailVerificationResponse> callback) {
        api.getEmailVerification(params, callback);
    }

    public static class Builder extends FCRequest.BaseBuilder<Builder, EmailVerificationRequest> {
        protected EmailVerificationRequest createInstance() {
            return new EmailVerificationRequest(params);
        }

        public Builder webhookUrl(String webhookUrl) {
            params.put("webhookUrl", webhookUrl);
            return this;
        }

        public Builder email(String email) {
            params.put("email", email);
            return this;
        }

        protected void validate() {
            if(params.get("email") == null) {
                throw new IllegalArgumentException("email parameter is required");
            }
        }

        protected Builder self() {
            return this;
        }
    }
}
