package com.fullcontact.api.libs.fullcontact4j.builders;

import java.text.MessageFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Skiggz
 * Date: 10/11/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class CardReaderViewResultsRequestBuilder {

    public class CardReaderViewResultsRequest {

        private String requestId;
        private boolean verifiedOnly = false;
        // includes clientServerResponseCode and clientServerResponseBody if true
        private boolean diagnostics = false;

        private CardReaderViewResultsRequest(String id) throws NullPointerException {
            if (id == null) throw new NullPointerException("Request ID is required");
            requestId = id;
        }

        public boolean isDiagnostics() {
            return diagnostics;
        }

        public void setDiagnostics(boolean diagnostics) {
            this.diagnostics = diagnostics;
        }

        public boolean isVerifiedOnly() {
            return verifiedOnly;
        }

        public void setVerifiedOnly(boolean verifiedOnly) {
            this.verifiedOnly = verifiedOnly;
        }

        public String getRequestId() {
            return requestId;
        }

        public String toQueryString() {
            StringBuilder params = new StringBuilder();
            if (this.isDiagnostics()) params.append("&diagnostics=true");
            if (this.isVerifiedOnly()) params.append("&returnedData=verifiedOnly");
            return params.toString();
        }

    }

    private CardReaderViewResultsRequest _request;

    public CardReaderViewResultsRequestBuilder(String requestId) throws NullPointerException {
        _request = new CardReaderViewResultsRequest(requestId);
    }

    public CardReaderViewResultsRequest build() {
        return this._request;
    }

    public CardReaderViewResultsRequestBuilder setDiagnostics(boolean diagnostics) {
        this._request.setDiagnostics(diagnostics);
        return this;
    }

    public CardReaderViewResultsRequestBuilder setVerifiedOnly(boolean verifiedOnly) {
        this._request.setVerifiedOnly(verifiedOnly);
        return this;
    }

}
