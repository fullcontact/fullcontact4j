package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.response.AccountStatsResponse;
import retrofit.Callback;

import java.util.Map;

public class AccountStatsRequest extends FCRequest<AccountStatsResponse> {

    protected AccountStatsRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<AccountStatsResponse> callback) {
        api.getAccountStats(params, callback);
    }

    public static class Builder extends BaseBuilder<AccountStatsRequest> {

        @Override
        protected AccountStatsRequest createInstance() {
            return new AccountStatsRequest(params);
        }

        @Override
        protected void validate() {

        }
    }
}
