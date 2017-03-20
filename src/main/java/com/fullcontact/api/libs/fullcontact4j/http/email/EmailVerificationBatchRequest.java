package com.fullcontact.api.libs.fullcontact4j.http.email;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.*;

public class EmailVerificationBatchRequest extends FCRequest<EmailVerificationAsyncResponse> {

    private Body body;

    protected EmailVerificationBatchRequest(Body body) {
        super(new HashMap<String, String>());
        this.body = body;
    }

    protected void makeRequest(FullContactApi api, Callback<EmailVerificationAsyncResponse> callback) {
        api.submitEmailVerificationBatch(body, callback);
    }

    public static class Body {
        private List<String> emails;
        private String webhookUrl;
        private String batchId;

        public Body(List<String> emails, String webhookUrl) {
            this.emails = emails;
            this.webhookUrl = webhookUrl;
        }

        public List<String> getEmails() {
            return emails;
        }

        public String getWebhookUrl() {
            return webhookUrl;
        }
    }

    public static class Builder extends FCRequest.BaseBuilder<Builder, EmailVerificationBatchRequest> {
        private List<String> emails;
        private String webhookUrl;
        private String batchId;

        protected EmailVerificationBatchRequest createInstance() {
            return new EmailVerificationBatchRequest(new Body(emails, webhookUrl));
        }

        public Builder emails(List<String> emails) {
            this.emails = emails;
            return this;
        }

        public Builder webhookUrl(String webhookUrl) {
            this.webhookUrl = webhookUrl;
            return this;
        }

        public Builder batchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        protected void validate() {
            if(emails == null || emails.size() == 0) {
                throw new IllegalArgumentException("request must include emails");
            }
        }

        protected Builder self() {
            return this;
        }
    }
}
