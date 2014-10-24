package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.Map;

public class NameParseRequest extends FCRequest<NameParseResponse> {
    protected NameParseRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<NameParseResponse> callback) {
        api.getParsedName(params, callback);
    }

    public static class Builder extends BaseBuilder<NameParseRequest> {

        @Override
        protected NameParseRequest createInstance() {
            return new NameParseRequest(params);
        }

        public Builder name(String name) {
            params.put(FCConstants.PARAM_NAME_QUERY, name);
            return this;
        }

        @Override
        protected void validate() {

        }
    }
}
