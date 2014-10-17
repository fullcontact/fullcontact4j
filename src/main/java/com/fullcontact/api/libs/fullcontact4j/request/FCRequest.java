package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.FullContactHttpInterface;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.response.FCResponse;
import retrofit.Callback;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        public abstract T build();

    }



}
