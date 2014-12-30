package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import retrofit.Callback;

import java.util.Map;

public class CardReaderViewRequest extends FCRequest<CardReaderFullResponse> {

    private String id;
    private String accessToken;
    public CardReaderViewRequest(String accessToken, String id, Map<String, String> params) {
        super(params);
        this.id = id;
    }
    @Override
    protected void makeRequest(FullContactApi api, Callback<CardReaderFullResponse> callback) {
        api.viewCard(accessToken, params, id, callback);
    }

    public static class Builder extends BaseBuilder<CardReaderViewRequest> {
        private String id;
        private String accessToken;

        public Builder cardId(String id) {
            this.id = id;
            return this;
        }

        public Builder verifiedOnly(Boolean verifiedOnly) {
            if(verifiedOnly) {
                params.put(FCConstants.PARAM_CARD_RETURNED_DATA, "verifiedOnly");
            } else {
                params.remove(FCConstants.PARAM_CARD_RETURNED_DATA);
            }
            return this;
        }

        public Builder diagnostics(boolean use) {
            params.put(FCConstants.PARAM_CARD_DIAGNOSTICS, Boolean.toString(use));
            return this;
        }

        public Builder accessToken(String token) {
            accessToken = token;
            return this;
        }

        public void validate() {
            if(id == null) {
                throw new IllegalArgumentException("Card View Request must have a card ID");
            }
        }
        @Override
        protected CardReaderViewRequest createInstance() {
            return new CardReaderViewRequest(accessToken, id, params);
        }
    }
}
