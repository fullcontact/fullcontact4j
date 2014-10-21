package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.response.CardReaderViewAllResponse;
import retrofit.Callback;

import java.util.Map;

public class CardReaderViewAllRequest extends FCRequest<CardReaderViewAllResponse> {


    protected CardReaderViewAllRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<CardReaderViewAllResponse> callback) {
        api.viewAll(params, callback);
    }

    public static class Builder extends BaseBuilder<CardReaderViewAllRequest> {


        public Builder page(int page) {
            params.put(Constants.PARAM_CARD_PAGE, String.valueOf(page));
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

        @Override
        protected CardReaderViewAllRequest createInstance() {
            return new CardReaderViewAllRequest(params);
        }

        @Override
        protected void validate() {}
    }
}
