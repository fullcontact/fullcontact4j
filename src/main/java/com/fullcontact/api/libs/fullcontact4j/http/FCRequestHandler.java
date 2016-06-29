package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;

public interface FCRequestHandler {

    public void notifyRateLimits(FCRateLimits rateLimits);

    public void shutdown();

    public <T extends FCResponse> void sendRequestAsync(final FullContactApi api, final FCRequest<T> req,
                                                        final FCRetrofitCallback<T> callback);

    class NoRateLimitRequestHandler implements FCRequestHandler {

        public void notifyRateLimits(FCRateLimits rateLimits) {}

        public void shutdown() {}

        public <T extends FCResponse> void sendRequestAsync(FullContactApi api, FCRequest<T> req, FCRetrofitCallback
                    <T> callback) {
            req.makeRequest(api, callback);
        }
    }
}
