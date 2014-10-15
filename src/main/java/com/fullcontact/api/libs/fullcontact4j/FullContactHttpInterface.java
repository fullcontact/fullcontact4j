package com.fullcontact.api.libs.fullcontact4j;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterPolicy;
import com.fullcontact.api.libs.fullcontact4j.request.RequestExecutorHandler;
import retrofit.RequestInterceptor;
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
    private final RequestExecutorHandler requestExecutorHandler;
    private final Converter jsonConverter;

    /**
     * Handles communication (using Retrofit) with the FullContact api
     */
    private FullContactApi fullContactApi;
    protected String authString;

    public FullContactHttpInterface(Client httpClient, String authString, RateLimiterPolicy policy) {
        ObjectMapper mapper = new ObjectMapper();
        //Properties not present in the POJO are ignored instead of throwing exceptions
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //An empty string ("") is interpreted as null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        //when we intercept a request, this object adds the proper auth headers
        requestExecutorHandler = new RequestExecutorHandler(policy);
        RequestExecutorHandler.FCRequestInterceptor requestInterceptor = requestExecutorHandler.getInterceptor(authString);

        jsonConverter = new JacksonConverter(mapper);
        //create the API from a template interface using Retrofit
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Constants.API_BASE + Constants.API_VERSION)
                .setClient(httpClient)
                .setRequestInterceptor(requestInterceptor).setConverter(jsonConverter).build();
        fullContactApi = adapter.create(FullContactApi.class);
        this.authString = authString;
    }

    public RequestExecutorHandler getRequestExecutorHandler() {
        return requestExecutorHandler;
    }

    public Converter getJsonConverter() {
        return jsonConverter;
    }

    public FullContactApi getFullContactApi() {
        return fullContactApi;
    }
}
