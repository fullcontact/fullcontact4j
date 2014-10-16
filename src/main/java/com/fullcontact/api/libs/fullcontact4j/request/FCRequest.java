package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.FullContactHttpInterface;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.response.FCResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public abstract class FCRequest<T extends FCResponse> {


    protected final FullContactHttpInterface httpInterface;
    protected final Map<String, String> params;

    protected FCRequest(FullContactHttpInterface httpInterface, Map<String, String> params) {
        this.httpInterface = httpInterface;
        this.params = params;
    }

    /**
     * This and the builders are the only things that should be overridden for any request class.
     * This method should do exactly one thing: make an api call through the FullContactHttpInterface object.
     * @param callback the callback supplied by the client
     */
    protected abstract void makeRequest(FCCallback<T> callback);

    private String getFullURL() {
        StringBuilder builder = new StringBuilder();
        String result = httpInterface.getBaseUrl() + "?";
        for(Map.Entry<String, String> entry : params.entrySet()) {
            if(builder.length() != 0) {
                builder.append("&");
            }
            builder.append(entry.getKey() + "=" + entry.getValue());
        }
        return result + builder.toString();
    }

    public void setParam(String key, String value) {
        params.put(key, value);
    }

    /**
     * Makes a synchronous request to the FullContact APIs.
     * @return if the request is successful, this method returns the corresponding {@link com.fullcontact.api.libs.fullcontact4j.response.FCResponse}.
     * @throws FullContactException if the request fails, this method will throw a FullContactException with a reason.
     */
    public final T send() throws FullContactException {
        return httpInterface.getRequestExecutorHandler().sendRequestSync(this);
    }

    /**
     * Makes an asynchronous request to the FullContact APIs.
     * callback.success() will be called on a good (code 200/202) response. Otherwise callback.failure() is called.
     * @param callback the callback, or can be null if a webhook is specified and all logic is handled on the webhook.
     */
    public final void send(FCCallback<T> callback) {
        if(callback == null) {
            if(params.get(Constants.API_WEBHOOK) == null) {
                throw new IllegalArgumentException(
                        "Cannot make an asynchronous request without either a callback or a webhook");
            }
        } else {
            callback.setHttpInterface(httpInterface);
        }
        httpInterface.getRequestExecutorHandler().sendRequestAsync(this, callback);
    }

    protected static class BaseBuilder<T extends FCRequest> {
        protected FullContactHttpInterface httpInterface;
        protected Map<String, String> params;

        public BaseBuilder(FullContactHttpInterface httpInterface) {
            this.httpInterface = httpInterface;
            params = new HashMap<String, String>();
        }

        /**
         * Actually builds the request. If there are missing/bad parameters, an IllegalArgumentException will be thrown.
         * @return
         */
        public T build() {
            throw new NotImplementedException();
        }

    }



}
