package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import retrofit.Callback;

import java.util.HashMap;
import java.util.Map;

public abstract class FCRequest<T extends FCResponse> {

    protected final Map<String, String> params;

    protected FCRequest(Map<String, String> params) {
        for(Map.Entry<String, String> ents: params.entrySet()) {
            Boolean badKey = ents.getKey() == null || ents.getKey().isEmpty();
            Boolean badValue = ents.getValue() == null || ents.getValue().isEmpty();
            if(badKey || badValue) {
                throw new IllegalArgumentException("A parameter is null or empty. Parameter: \"" + ents.getKey() +
                "\", Value: \"" + ents.getValue() + "\"");
            }
        }
        this.params = params;
    }

    public boolean hasParam(String key) {
        return params.containsKey(key);
    }

    public String getParam(String key) {
        return params.get(key);
    }
    /**
     * This and the builders are the only things that should be overridden for any request class.
     * This method should do exactly one thing: make an api call through the FullContactHttpInterface object.
     * @param callback the callback supplied by the client
     */
    protected abstract void makeRequest(FullContactApi api, Callback<T> callback);

    private String getFullURL(String baseUrl) {
        StringBuilder builder = new StringBuilder();
        String result = baseUrl + "?";
        for(Map.Entry<String, String> entry : params.entrySet()) {
            if(builder.length() != 0) {
                builder.append("&");
            }
            builder.append(entry.getKey() + "=" + entry.getValue());
        }
        return result + builder.toString();
    }

    protected abstract static class BaseBuilder<T extends FCRequest> {
        protected Map<String, String> params;

        public BaseBuilder() {
            params = new HashMap<String, String>();
        }

        /**
         * Actually validates and builds the request.
         * If there are missing/bad parameters, an IllegalArgumentException will be thrown.
         * @return
         */
        public T build() {
            validate();

            // if a param was empty or null, remove it from the param list.
            Map<String, String> validParamsOnly = new HashMap<String, String>();
            for(Map.Entry<String, String> entry : params.entrySet()) {
                if(entry.getValue() != null && !entry.getValue().trim().isEmpty()) {
                    validParamsOnly.put(entry.getKey(), entry.getValue());
                }
            }
            params = validParamsOnly;
            return createInstance();
        }

        protected abstract T createInstance();

        /**
         * Validates all the parameters in this request before it's built (it is considered a 100% valid request after it is built).
         * If something about the parameters is incorrect, IllegalArgumentException should be thrown.
         */
        protected abstract void validate();

        protected boolean hasParam(String param) {
            return params.containsKey(param);
        }
    }



}
