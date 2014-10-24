package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
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

        protected void validate() {
            if(path == null) {
                throw new IllegalArgumentException("Generic request must specify a path");
            }
        }

        protected GenericRequest createInstance() {
            return new GenericRequest(path, params);
        }
    }
}
