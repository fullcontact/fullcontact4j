package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.enums.Casing;
import com.fullcontact.api.libs.fullcontact4j.response.NameResponse;
import retrofit.Callback;

import java.util.Map;

public class NameNormalizationRequest extends FCRequest<NameResponse> {


    protected NameNormalizationRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<NameResponse> callback) {
        api.getNormalizedName(params, callback);
    }

    public static class Builder extends BaseBuilder<NameNormalizationRequest> {

        public Builder query(String name) {
            params.put(Constants.PARAM_NAME_QUERY, name);
            return this;
        }

        public Builder casing(Casing casing) {
            params.put(Constants.PARAM_NAME_CASING, casing.name().toLowerCase());
            return this;
        }

        @Override
        protected NameNormalizationRequest createInstance() {
            return new NameNormalizationRequest(params);
        }

        @Override
        protected void validate() {
            String query = params.get(Constants.PARAM_NAME_QUERY);
            if(query == null || query.isEmpty()) {
                throw new IllegalArgumentException("Query cannot be empty or null");
            }
            if(!Utils.isAlphabeticalOnly(query)) {
                throw new IllegalArgumentException("Query can only use characters A-Z and a-z");
            }
        }
    }
}
