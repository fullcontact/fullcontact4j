package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContact;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.FullContactHttpInterface;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.GenericResponse;
import retrofit.Callback;

import java.util.Map;

public class GenericRequest extends FCRequest<GenericResponse> {

    private String path;

    protected GenericRequest(String path, Map<String, String> params) {
        super(params);
        this.path = path;
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<GenericResponse> callback) {
        api.genericGet(path, params, callback);
    }

    public static class Builder extends BaseBuilder<GenericRequest> {
        private String path;

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder param(String key, String value) {
            params.put(key, value);
            return this;
        }

        public GenericRequest build() {
            if(path == null) {
                throw new IllegalArgumentException("Generic Requests need to specify a relative URL.");
            }
            return new GenericRequest(path, params);
        }
    }
}
