package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.response.CardReaderFullResponse;
import retrofit.Callback;

import java.util.Map;

public class CardReaderViewRequest extends FCRequest<CardReaderFullResponse> {

    private String id;
    public CardReaderViewRequest(String id, Map<String, String> params) {
        super(params);
        this.id = id;
    }
    @Override
    protected void makeRequest(FullContactApi api, Callback<CardReaderFullResponse> callback) {
        api.viewCard(params, id, callback);
    }

    public static class Builder extends WebhookBuilder<CardReaderViewRequest> {
        private String id;

        public Builder cardId(String id) {
            this.id = id;
            return this;
        }

        public Builder verifiedOnly(Boolean verifiedOnly) {
            if(verifiedOnly) {
                params.put(Constants.PARAM_CARD_RETURNED_DATA, "verifiedOnly");
            } else {
                params.remove(Constants.PARAM_CARD_RETURNED_DATA);
            }
            return this;
        }

        public Builder diagnostics(Boolean diagnostics) {
            if(diagnostics) {
                params.put(Constants.PARAM_CARD_DIAGNOSTICS, "true");
            } else {
                params.remove(Constants.PARAM_CARD_DIAGNOSTICS);
            }
            return this;
        }

        public void validate() {
            super.validate();
            if(id == null) {
                throw new IllegalArgumentException("Card View Request must have a card ID");
            }
        }
        @Override
        protected CardReaderViewRequest createInstance() {
            return new CardReaderViewRequest(id, params);
        }
    }
}
