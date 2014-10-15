package com.fullcontact.api.libs.fullcontact4j;


import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterPolicy;
import com.fullcontact.api.libs.fullcontact4j.request.GenericRequest;
import retrofit.client.Client;
import retrofit.client.OkClient;

import java.util.logging.Level;

public class FullContact {
    protected static Level logLevel = Level.OFF;

    /**
     * This interface holds references to all the objects used by a FullContact client to communicate with the api,
     * convert responses, etc.
     */
    protected FullContactHttpInterface httpInterface;

    private FullContact(Client httpClient, String authString, RateLimiterPolicy policy) {
        httpInterface = new FullContactHttpInterface(httpClient, authString, policy);
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
        private RateLimiterPolicy ratePolicy = RateLimiterPolicy.SMOOTH;

        public Builder(String apiKey) {
            //default client is OkHttpClient
            httpClient = new OkClient();
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

        public Builder rateLimiterPolicy(RateLimiterPolicy policy) {
            this.ratePolicy = policy;
            return this;
        }

        /**
         * Builds a new FullContact client.
         * @return a new, fully configured, FullContact client.
         */
        public FullContact build() {
            if(httpClient == null || authKey == null) {
                throw new IllegalArgumentException("Authentication token/keys and HTTP client cannot be null");
            }
            return new FullContact(httpClient, authKey, ratePolicy);
        }
    }


}