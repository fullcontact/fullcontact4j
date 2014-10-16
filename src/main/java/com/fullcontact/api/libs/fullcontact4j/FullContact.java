package com.fullcontact.api.libs.fullcontact4j;


import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterPolicy;
import com.fullcontact.api.libs.fullcontact4j.request.GenericRequest;
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

    private FullContact(Client httpClient, String authString, RateLimiterPolicy policy, String baseUrl,
                        Boolean useThreadPool) {
        httpInterface = new FullContactHttpInterface(httpClient, authString, policy, baseUrl, useThreadPool);
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

    /////API Methods//////

    /**
     * Creates a new generic request builder, where parameters and endpoint are all customizable.
     * @return
     */
    public GenericRequest.Builder buildGenericRequest() {
        return new GenericRequest.Builder(httpInterface);
    }




    /////////////////////


    public static class Builder {

        private String authKey;
        private Client httpClient;
        private boolean useThreadPool = false;
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
         * If false (default), asynchronous api requests will be made on a single thread.
         * If true, a thread pool will be used.
         * @param use
         * @return
         */
        public Builder useThreadPool(Boolean use) {
            useThreadPool = use;
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
            return new FullContact(httpClient, authKey, ratePolicy, baseUrl, useThreadPool);
        }
    }


}