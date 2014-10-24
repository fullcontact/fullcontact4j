package com.fullcontact.api.libs.fullcontact4j.http.location;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.Map;

public class LocationEnrichmentRequest extends FCRequest<LocationEnrichmentResponse> {
    protected LocationEnrichmentRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<LocationEnrichmentResponse> callback) {
        api.getEnrichedLocation(params, callback);
    }

    public static class Builder extends BaseBuilder<LocationEnrichmentRequest> {

        public Builder place(String place) {
            params.put(FCConstants.PARAM_LOCATION_PLACE, place);
            return this;
        }

        @Override
        protected LocationEnrichmentRequest createInstance() {
            return new LocationEnrichmentRequest(params);
        }

        @Override
        protected void validate() {
            if(params.get(FCConstants.PARAM_LOCATION_PLACE) == null) {
                throw new IllegalArgumentException("Location in location request cannot be null");
            }
        }
    }
}
