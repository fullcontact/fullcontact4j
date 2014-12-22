package com.fullcontact.api.libs.fullcontact4j;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterPolicy;
import com.fullcontact.api.libs.fullcontact4j.http.*;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.Converter;
import retrofit.converter.JacksonConverter;

/**
 * This interface holds references to all the objects used by a FullContact client to communicate with the api,
 * convert responses, etc.
 */
public class FullContactHttpInterface {

    /**
     * This handles requests made by the client.
     * When a request is made, it is sent to an ExecutorService which
     * accounts for rate limiting and then sends the request.
     */
    private RequestExecutorHandler requestExecutorHandler;
    private final Converter jsonConverter;
    private final String baseUrl;

    /**
     * Handles communication (using Retrofit) with the FullContact api
     */
    private FullContactApi fullContactApi;

    public FullContactHttpInterface(Client httpClient, RateLimiterPolicy policy, String baseUrl,
                                    Integer threadPoolCount) {
        ObjectMapper mapper = new ObjectMapper();
        //Properties not present in the POJO are ignored instead of throwing exceptions
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //An empty string ("") is interpreted as null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        //when we intercept a request, this object adds the proper auth headers
        requestExecutorHandler = new RequestExecutorHandler(policy, threadPoolCount);

        jsonConverter = new JacksonConverter(mapper);
        //create the API from a template interface using Retrofit
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(baseUrl)
                .setClient(httpClient).setConverter(jsonConverter).build();
        fullContactApi = adapter.create(FullContactApi.class);
        this.baseUrl = baseUrl;
    }

    /**
     * Makes a request just like an async request, but uses a synchronous callback to do so.
     */
    public <T extends FCResponse> T sendRequest(FCRequest<T> req) throws FullContactException {
        final FCCallback.SyncFCCallback<T> callback = new FCCallback.SyncFCCallback<T>();
        sendRequest(req, callback);
        try {
            return callback.get();
        } catch(InterruptedException e) {
            e.printStackTrace();
            throw new FullContactException("Interrupted while waiting for a result!", e);
        }
    }

    public <T extends FCResponse> void sendRequest(FCRequest<T> req, FCCallback<T> callback) {
        if(callback == null) {
            if(!req.hasParam(FCConstants.PARAM_WEBHOOK_URL)) {
                throw new IllegalArgumentException(
                        "Cannot make an asynchronous request without either a callback or a webhook");
            }

            //if the user didn't specify a callback, create a no-op callback instead
            callback = new FCCallback<T>() {
                @Override
                public void success(T response) {}

                @Override
                public void failure(FullContactException exception) {}
            };
        }
        //make a retrofit request with a callback that will call FCCallback
        requestExecutorHandler.sendRequestAsync(fullContactApi, req, new FCRetrofitCallback<T>(callback, this));
    }

    public RequestExecutorHandler getRequestExecutorHandler() { return requestExecutorHandler; }

    public String getBaseUrl() { return baseUrl; }

    public Converter getJsonConverter() {
        return jsonConverter;
    }

    protected void setRequestExecutorHandler(RequestExecutorHandler handler) {
        requestExecutorHandler = handler;
    }

}
