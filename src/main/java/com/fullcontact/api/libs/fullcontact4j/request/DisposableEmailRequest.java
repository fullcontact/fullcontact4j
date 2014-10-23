package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.response.DisposableResponse;
import retrofit.Callback;

public class DisposableEmailRequest extends FCRequest<DisposableResponse> {
    private final String email;

    public DisposableEmailRequest(String email) {
        super(null);
        this.email = email;
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<DisposableResponse> callback) {
        api.getDisposableEmail(email, callback);
    }

    public static class Builder extends BaseBuilder<DisposableEmailRequest> {

        private String email;
        @Override
        protected DisposableEmailRequest createInstance() {
            return new DisposableEmailRequest(email);
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        @Override
        protected void validate() {
            if(!Utils.isValidEmail(email)) {
                throw new IllegalArgumentException("Email is not of a valid format");
            }
        }
    }
}
