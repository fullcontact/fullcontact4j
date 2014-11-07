package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.Map;

public class NameStatsRequest extends FCRequest<NameStatsResponse> {

    protected NameStatsRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<NameStatsResponse> callback) {
        api.getNameStats(params, callback);
    }

    public static class Builder extends BaseBuilder<NameStatsRequest> {

        public Builder name(String name) {
            params.put(FCConstants.PARAM_NAME_NAME, name);
            return this;
        }

        public Builder givenName(String name) {
            params.put(FCConstants.PARAM_NAME_GIVEN_NAME, name);
            return this;
        }

        public Builder familyName(String name) {
            params.put(FCConstants.PARAM_NAME_FAMILY_NAME, name);
            return this;
        }

        @Override
        protected NameStatsRequest createInstance() {
            return new NameStatsRequest(params);
        }

        @Override
        protected void validate() {
            boolean hasName = hasParam(FCConstants.PARAM_NAME_NAME);
            if(hasParam(FCConstants.PARAM_NAME_GIVEN_NAME) || hasParam(FCConstants.PARAM_NAME_FAMILY_NAME)) {
                if(hasName) {
                    throw new IllegalArgumentException("Cannot specify name with given name or family name already specified.");
                }
                return;
            }
            if(!hasName) {
                throw new IllegalArgumentException("Stats must have a name parameter");
            }
        }
    }
}
