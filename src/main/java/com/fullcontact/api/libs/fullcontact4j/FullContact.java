package com.fullcontact.api.libs.fullcontact4j;


import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterPolicy;
import com.fullcontact.api.libs.fullcontact4j.request.*;
import com.fullcontact.api.libs.fullcontact4j.response.FCResponse;
import com.squareup.okhttp.OkHttpClient;
import retrofit.client.Client;
import retrofit.client.OkClient;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class FullContact {
    protected static Level logLevel = Level.OFF;

    /**
     * This interface holds references to all the objects used by a FullContact client to communicate with the api,
     * convert responses, etc.
     */
    protected FullContactHttpInterface httpInterface;

    protected FullContact(Client httpClient, String apiKey, RateLimiterPolicy policy, String baseUrl,
                        Integer threadPoolCount) {
        httpInterface = new FullContactHttpInterface(httpClient, apiKey, policy, baseUrl, threadPoolCount);
        Utils.info("Created new FullContact client.");
    }

    /**
     * Set what level to log at (default OFF). Level.INFO will post important logs (client creations, errors).
     * Level.FINE will log FullContact4j's workflow.
     * @param log
     */
    public static void setLogLevel(Level log) {
        logLevel = log;
    }

    /**
     * Initializes a new FullContact Client Builder with an api key.
     * This is the most common way to create a client.
     * @param apiKey an api key associated with an account.
     * @return a new FullContact client builder
     */
    public static Builder withApiKey(String apiKey) {
        return new Builder(apiKey);
    }



    //TODO update /developer/docs/libraries/
    //TODO force utf8
    //TODO schema docs is missing SocialProfile.type.
    //why do we have type, typeId, typeName...? type = typeId

    /////API Methods//////

    /**
     * Creates a new generic request builder, where parameters and endpoint are all customizable.
     * @return
     */
    public GenericRequest.Builder buildGenericRequest() {
        return new GenericRequest.Builder();
    }

    /**
     * Creates a new Person search.
     */
    public PersonRequest.Builder buildPersonRequest() { return new PersonRequest.Builder(); }

    /**
     * Makes a synchronous request to the FullContact APIs.
     * @throws FullContactException if the request fails, this method will throw a FullContactException with a reason.
     * @param req the request, generated with a call to build____Request().
     * @param <T> the Response type
     * @return if the request is successful, this method returns the corresponding {@link com.fullcontact.api.libs.fullcontact4j.response.FCResponse}.
     */
    public <T extends FCResponse> T sendRequest(FCRequest<T> req) throws FullContactException {
        return httpInterface.sendRequest(req);
    }

    /**
     * Makes an asynchronous request to the FullContact APIs.
     * Exceptions will call {@link com.fullcontact.api.libs.fullcontact4j.request.FCCallback#failure(FullContactException)}.
     * Successful responses will call {@link com.fullcontact.api.libs.fullcontact4j.request.FCCallback#success(com.fullcontact.api.libs.fullcontact4j.response.FCResponse)}
     * @param req the request, generated with a call to build____Request().
     * @param callback your callback
     * @param <T> the Response type
     */
    public <T extends FCResponse> void sendRequest(FCRequest<T> req, FCCallback<T> callback) {
        httpInterface.sendRequest(req, callback);
    }


    /////////////////////


    public static class Builder {

        private String authKey;
        private Client httpClient;
        private Integer threadPoolCount = 1;
        private OkHttpClient defaultClient = new OkHttpClient();
        private String baseUrl = Constants.API_BASE_DEFAULT;
        private RateLimiterPolicy ratePolicy = RateLimiterPolicy.SMOOTH;

        public Builder(String apiKey) {
            //default client is OkHttpClient
            this.authKey = apiKey;
        }

        /**
         * What HTTP client FullContact should use; useful to configure read timeouts, etc.
         * By default, FullContact uses a default {@link retrofit.client.OkClient}.
         * @param client the configured http client
         * @return
         */
        public Builder httpClient(Client client) {
            httpClient = client;
            return this;
        }

        /**
         * How many threads to use to execute API queries (default 1).
         * @param threads
         * @return
         */
        public Builder threadCount(Integer threads) {
            threadPoolCount = threads;
            return this;
        }

        /**
         * Sets the read timeout for the default client.
         * If a custom httpClient is specified with {@link #httpClient}, this method will have no effect.
         * Configure a custom client beforehand.
         * @param timeoutMs
         * @return
         */
        public Builder setReadTimeout(Integer timeoutMs) {
            defaultClient.setReadTimeout(timeoutMs, TimeUnit.MILLISECONDS);
            return this;
        }

        /**
         * Sets the connect timeout for the default client.
         * If a custom httpClient is specified with {@link #httpClient}, this method will have no effect.
         * Configure a custom client beforehand.
         * @param timeoutMs
         * @return
         */
        public Builder setConnectTimeout(Integer timeoutMs) {
            defaultClient.setConnectTimeout(timeoutMs, TimeUnit.MILLISECONDS);
            return this;
        }

        /**
         * Sets the base URL that the client accesses FullContact's endpoints from.
         * This defaults to the standard api.fullcontact.com/v2/.
         * @param url
         * @return
         */
        public Builder baseUrl(String url) {
            baseUrl = url;
            return this;
        }

        public Builder rateLimiterPolicy(RateLimiterPolicy policy) {
            this.ratePolicy = policy;
            return this;
        }

        /**
         * Builds a new FullContact client.
         * @return a new, fully configured, FullContact client.
         */
        public FullContact build() {
            if(authKey == null || authKey.isEmpty()) {
                throw new IllegalArgumentException("Authentication key cannot be null");
            }
            //if a custom client wasn't specified, use ours
            if(httpClient == null) {
                httpClient = new OkClient(defaultClient);
            }
            return new FullContact(httpClient, authKey, ratePolicy, baseUrl, threadPoolCount);
        }
    }


}