package com.fullcontact.api.libs.fullcontact4j.builders;

import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderCasing;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderSandboxStatus;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderVerification;
import com.fullcontact.api.libs.fullcontact4j.enums.ResponseFormat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class CardReaderUploadRequestBuilder {

    // lower case for comparison
    private static final String[] CUSTOM_PARAM_RESTRICTIONS = {
            "apikey",
            "webhookurl",
            "front",
            "back",
            "casing",
            "format",
            "verified",
            "returneddata",
            "sandbox",
            "accesstoken",
            "controller",
            "action"
    };

    public class CardReaderUploadRequest {
        // These are all documented in the corresponding builder methods for setting them below *
        private String webhookUrl;
        private InputStream frontImage;
        private InputStream backImage;
        private CardReaderVerification verification = CardReaderVerification.Low;
        private Boolean verifiedOnly = false;
        private ResponseFormat format = ResponseFormat.JSON;
        private CardReaderCasing casing = CardReaderCasing.Default;
        private Boolean sandbox = false;
        private CardReaderSandboxStatus sandboxStatus;
        // To prevent unnecessary complexity, you must use the String version of data
        // since these become query String parameters, instead of leaving it to the library to
        // stringify and encode, leading to who knows what kind of issues on the client
        private HashMap<String,String> customParams = new HashMap<String, String>();

        public String getWebhookUrl() {
            return webhookUrl;
        }

        private void setWebhookUrl(String webhookUrl) {
            this.webhookUrl = webhookUrl;
        }

        public InputStream getFrontImage() {
            return frontImage;
        }

        private void setFrontImage(InputStream frontImage) {
            this.frontImage = frontImage;
        }

        public InputStream getBackImage() {
            return backImage;
        }

        private void setBackImage(InputStream backImage) {
            this.backImage = backImage;
        }

        public CardReaderVerification getVerification() {
            return verification;
        }

        private void setVerification(CardReaderVerification verification) {
            this.verification = verification;
        }

        public Boolean isVerifiedOnly() {
            return verifiedOnly;
        }

        private void setVerifiedOnly(Boolean verifiedOnly) {
            this.verifiedOnly = verifiedOnly;
        }

        public ResponseFormat getFormat() {
            return format;
        }

        private void setFormat(ResponseFormat format) {
            this.format = format;
        }

        public CardReaderCasing getCasing() {
            return casing;
        }

        private void setCasing(CardReaderCasing casing) {
            this.casing = casing;
        }

        public Boolean isSandbox() {
            return sandbox;
        }

        private void setSandbox(Boolean sandbox) {
            this.sandbox = sandbox;
        }

        public CardReaderSandboxStatus getSandboxStatus() {
            return sandboxStatus;
        }

        private void setSandboxStatus(CardReaderSandboxStatus sandboxStatus) {
            this.sandboxStatus = sandboxStatus;
        }

        public HashMap<String, String> getCustomParams() {
            return customParams;
        }

        public void setCustomParams(HashMap<String, String> customParams) {
            this.customParams = customParams;
        }

    }

    private CardReaderUploadRequest _request;

    public CardReaderUploadRequestBuilder() {
        _request = new CardReaderUploadRequest();
    }

    /***
     * Sets the webhook url. (Required)
     * @param webhookUrl
     * @return
     */
    public CardReaderUploadRequestBuilder setWebhookUrl(String webhookUrl) {
        this._request.webhookUrl = webhookUrl;
        return this;
    }

    /***
     * Sets the front image stream. (Required)
     * @param frontImage
     * @return
     */
    public CardReaderUploadRequestBuilder setFrontImage(InputStream frontImage) {
        this._request.frontImage = frontImage;
        return this;
    }

    /***
     * Sets the back image stream. (Optional)
     * @param backImage
     * @return
     */
    public CardReaderUploadRequestBuilder setBackImage(InputStream backImage) {
        this._request.backImage = backImage;
        return this;
    }

    /***
     * Sets the level of verification: Low, Medium, or High. (Optional, Defaults to Low)
     * @param verification
     * @return
     * @see "http://www.fullcontact.com/developer/docs/card-reader/"
     */
    public CardReaderUploadRequestBuilder setVerification(CardReaderVerification verification) {
        this._request.verification = verification;
        return this;
    }

    /***
     * Sets whether or not you want only the data we could verify. (Optional, Defaults to false)
     * @param verifiedOnly
     * @return
     * @see "http://www.fullcontact.com/developer/docs/card-reader/"
     */
    public CardReaderUploadRequestBuilder setVerifiedOnly(Boolean verifiedOnly) {
        this._request.verifiedOnly = verifiedOnly;
        return this;
    }

    /***
     * Sets the format that you want the webhook to return. (Optional, Defaults to JSON)
     * @param format
     * @return
     */
    public CardReaderUploadRequestBuilder setFormat(ResponseFormat format) {
        this._request.format = format;
        return this;
    }

    /***
     * Sets the casing preference for the contact result. (Optional, Defaults to Unchanged)
     * @param casing
     * @return
     * @see "http://www.fullcontact.com/developer/docs/card-reader/"
     */
    public CardReaderUploadRequestBuilder setCasing(CardReaderCasing casing) {
        this._request.casing = casing;
        return this;
    }

    /***
     * If true, the request is treated as a test call and returns fake results.
     * A SandboxStatus is required if this is set to true. (Optional, Default is false)
     * @param sandbox
     * @return
     * @see "http://www.fullcontact.com/developer/docs/card-reader/"
     */
    public CardReaderUploadRequestBuilder setSandbox(Boolean sandbox) {
        this._request.sandbox = sandbox;
        return this;
    }

    /***
     * Sets the status for the sandbox upload. (Required if Sandbox)
     * @param sandboxStatus
     * @return
     * @see "http://www.fullcontact.com/developer/docs/card-reader/"
     */
    public CardReaderUploadRequestBuilder setSandboxStatus(CardReaderSandboxStatus sandboxStatus) {
        this._request.sandboxStatus = sandboxStatus;
        return this;
    }

    /**
     * Custom params come back in the response under the 'params' key as pass along values
     * This method removes all restricted params from custom params
     * See docs section "Custom Params" for restrictions and usage
     * http://www.fullcontact.com/developer/docs/card-reader/#upload-card
     */
    public CardReaderUploadRequestBuilder setCustomParams(HashMap<String,String> params) {
        removeRestrictedParams(params);
        this._request.setCustomParams(params);
        return this;
    }

    private void removeRestrictedParams(HashMap<String, String> params) {
        ArrayList<String> toRemove = new ArrayList<String>();
        // Not the greatest way to remove the keys, but the restrictions list is short
        for (String s : CUSTOM_PARAM_RESTRICTIONS) {
            for (String key : params.keySet()) {
                if (key.toLowerCase().equals(s)) {
                    toRemove.add(key); // avoid concurrent modifications
                }
            }
        }
        for (String s : toRemove) {
            params.remove(s);
        }
    }

    /***
     * Builds the CardReaderUploadRequest
     * @return
     */
    public CardReaderUploadRequest build() {
        return _request;
    }

}
