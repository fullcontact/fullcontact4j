package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.Map;

public class CardReaderViewAllRequest extends FCRequest<CardReaderViewAllResponse> {

    private String accessToken;

    protected CardReaderViewAllRequest(String accessToken, Map<String, String> params) {
        super(params);
        this.accessToken = accessToken;
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<CardReaderViewAllResponse> callback) {
        api.viewAll(accessToken, params, callback);
    }

    public static class Builder extends BaseBuilder<CardReaderViewAllRequest> {

        private String accessToken;

        public Builder page(int page) {
            params.put(FCConstants.PARAM_CARD_PAGE, String.valueOf(page));
            return this;
        }

        public Builder diagnostics(boolean use) {
            params.put(FCConstants.PARAM_CARD_DIAGNOSTICS, Boolean.toString(use));
            return this;
        }

        public Builder verifiedOnly(boolean verifiedOnly) {
            if(verifiedOnly) {
                params.put(FCConstants.PARAM_CARD_RETURNED_DATA, "verifiedOnly");
            } else {
                params.remove(FCConstants.PARAM_CARD_RETURNED_DATA);
            }
            return this;
        }

        public Builder accessToken(String token) {
            accessToken = token;
            return this;
        }

        @Override
        protected CardReaderViewAllRequest createInstance() {
            return new CardReaderViewAllRequest(accessToken, params);
        }

        @Override
        protected void validate() {}
    }
}
