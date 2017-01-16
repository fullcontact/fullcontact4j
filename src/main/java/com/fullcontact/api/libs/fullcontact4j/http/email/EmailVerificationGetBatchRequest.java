package com.fullcontact.api.libs.fullcontact4j.http.email;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.HashMap;

public class EmailVerificationGetBatchRequest extends FCRequest<EmailVerificationAsyncResponse> {
    private String batchId;

    protected EmailVerificationGetBatchRequest(String batchId) {
        super(new HashMap<String, String>());
        this.batchId = batchId;
    }

    protected void makeRequest(FullContactApi api, Callback<EmailVerificationAsyncResponse> callback) {
        api.getEmailVerificationBatch(batchId, callback);
    }

    public static class Builder extends BaseBuilder<Builder, EmailVerificationGetBatchRequest> {
        private String batchId;

        public Builder batchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        protected EmailVerificationGetBatchRequest createInstance() {
            return new EmailVerificationGetBatchRequest(batchId);
        }

        protected void validate() {
            if(batchId == null) {
                throw new IllegalArgumentException("batchId is required");
            }
        }

        protected Builder self() {
            return this;
        }
    }
}
