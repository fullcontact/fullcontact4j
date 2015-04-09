package com.fullcontact.api.libs.fullcontact4j.http.company;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import com.fullcontact.api.libs.fullcontact4j.http.FCRequest;
import com.fullcontact.api.libs.fullcontact4j.http.WebhookBuilder;
import retrofit.Callback;

import java.util.Map;

public class CompanyRequest extends FCRequest<CompanyResponse> {
    protected CompanyRequest(Map<String, String> params) {
        super(params);
    }

    @Override
    protected void makeRequest(FullContactApi api, Callback<CompanyResponse> callback) {
        api.getCompany(params, callback);
    }

    public static class Builder extends WebhookBuilder<CompanyRequest> {

        public Builder domain(String domain) {
            params.put(FCConstants.PARAM_COMPANY_DOMAIN, domain);
            return this;
        }

        public Builder webhookUrl(String url) {
            params.put(FCConstants.PARAM_WEBHOOK_URL, url);
            return this;
        }

        public Builder webhookId(String id) {
            params.put(FCConstants.PARAM_WEBHOOK_ID, id);
            return this;
        }

        public Builder webhookBody(Boolean rawJson) {
            if(rawJson) {
                params.put(FCConstants.PARAM_WEBHOOK_BODY, "json");
            } else {
                params.remove(FCConstants.PARAM_WEBHOOK_BODY);
            }
            return this;
        }

        @Override
        protected CompanyRequest createInstance() {
            return new CompanyRequest(params);
        }

        @Override
        protected void validate() {
            super.validate();

            String domain = params.get(FCConstants.PARAM_COMPANY_DOMAIN);
            if(domain == null) {
                throw new IllegalArgumentException("Domain in company request cannot be null");
            } else {
                //company API will do more extensive validation on this
                if(!domain.contains(".") || domain.contains("@")) {
                    throw new IllegalArgumentException("Domain is not in a valid format");
                }
            }
        }
    }
}
