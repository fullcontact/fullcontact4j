package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.enums.Casing;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.Map;

public class NameDeduceRequest extends FCRequest<NameResponse> {
    protected NameDeduceRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<NameResponse> callback) {
        api.getDeducedName(params, callback);
    }

    public static class Builder extends BaseBuilder<NameDeduceRequest> {

        @Override
        protected NameDeduceRequest createInstance() {
            return new NameDeduceRequest(params);
        }

        public Builder email(String email) {
            params.put(FCConstants.PARAM_NAME_EMAIL, email);
            return this;
        }

        public Builder username(String username) {
            params.put(FCConstants.PARAM_NAME_USERNAME, username);
            return this;
        }

        public Builder casing(Casing casing) {
            params.put(FCConstants.PARAM_NAME_CASING, casing.name().toLowerCase());
            return this;
        }

        @Override
        protected void validate() {
            String email = params.get(FCConstants.PARAM_NAME_EMAIL);
            String username = params.get(FCConstants.PARAM_NAME_USERNAME);
            boolean hasEmail = email != null && !email.isEmpty();
            boolean hasUsername = username != null && !username.isEmpty();
            //if both email and username are present, or neither of them are...
            if(hasEmail == hasUsername) {
                throw new IllegalArgumentException("Request must specify exactly one: email or username");
            }
        }

    }
}
