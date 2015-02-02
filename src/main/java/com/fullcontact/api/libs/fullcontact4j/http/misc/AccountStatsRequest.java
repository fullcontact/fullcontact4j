package com.fullcontact.api.libs.fullcontact4j.http.misc;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
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

        Integer year;
        Integer month;

        @Override
        protected AccountStatsRequest createInstance() {
            return new AccountStatsRequest(params);
        }

        public Builder setPeriod(Integer year, Integer month) {
            if(year < 2000 || year > 3000) {
                throw new IllegalArgumentException("invalid year - needs 4-digit year");
            }
            if(month < 0 || month > 12) {
                throw new IllegalArgumentException("invalid month - needs to be 1 through 12");
            }
            params.put(FCConstants.PARAM_ACCOUNT_STATS_PERIOD, String.format("%d-%02d", year, month));
            return this;
        }

        @Override
        protected void validate() {

        }
    }
}
