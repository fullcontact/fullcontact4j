package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactHttpInterface;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.GenericResponse;

import java.util.Map;

public class GenericRequest extends FCRequest<GenericResponse> {

    private String path;

    protected GenericRequest(String path, FullContactHttpInterface httpInterface, Map params) {
        super(httpInterface, params);
        this.path = path;
    }

    @Override
    protected void makeRequest(FCCallback<GenericResponse> callback) {
        httpInterface.getFullContactApi().genericGet(path, params, callback.getCoreCallback());
    }

    protected String getBaseURL() {
        return Constants.API_BASE + Constants.API_VERSION;
    }

    public static class Builder extends BaseBuilder<GenericRequest> {
        private String path;

        public Builder(FullContactHttpInterface httpInterface) {
            super(httpInterface);
        }

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
            return new GenericRequest(path, httpInterface, params);
        }
    }
}
