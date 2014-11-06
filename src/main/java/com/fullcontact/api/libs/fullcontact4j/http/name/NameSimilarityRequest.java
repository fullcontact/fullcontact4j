package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.Map;

public class NameSimilarityRequest extends FCRequest<NameSimilarityResponse> {

    protected NameSimilarityRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback callback) {
        api.getNameSimilarity(params, callback);
    }

    public static class Builder extends BaseBuilder<NameSimilarityRequest> {

        @Override
        protected NameSimilarityRequest createInstance() {
            return new NameSimilarityRequest(params);
        }

        public Builder name1(String name1) {
            params.put(FCConstants.PARAM_NAME_NAME1, name1);
            return this;
        }

        public Builder name2(String name2) {
            params.put(FCConstants.PARAM_NAME_NAME2, name2);
            return this;
        }

        @Override
        protected void validate() {
            String name1 = params.get(FCConstants.PARAM_NAME_NAME1);
            String name2 = params.get(FCConstants.PARAM_NAME_NAME2);
            if(name1 == null || name2 == null || name1.isEmpty() || name2.isEmpty()) {
                throw new IllegalArgumentException("Both names being compared need to be valid and non-null");
            }
        }
    }
}
