package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.config.Constants;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class WebhookBuilder<T extends FCRequest> extends FCRequest.BaseBuilder<T> {

    public WebhookBuilder webhookUrl(String url) {
        params.put(Constants.PARAM_WEBHOOK_URL, url);
        return this;
    }

    public WebhookBuilder webhookId(String id) {
        params.put(Constants.PARAM_WEBHOOK_ID, id);
        return this;
    }

    public WebhookBuilder webhookBody(Boolean responseInBody) {
        if(responseInBody) {
            params.put(Constants.PARAM_WEBHOOK_BODY, "json");
        } else {
            params.remove(Constants.PARAM_WEBHOOK_BODY);
        }
        return this;
    }

    protected void validate() {
       if(!hasParam(Constants.PARAM_WEBHOOK_URL) &&
               (hasParam(Constants.PARAM_WEBHOOK_BODY)) || hasParam(Constants.PARAM_WEBHOOK_ID)) {
           throw new IllegalArgumentException("Request has some webhook parameters set, but no webhook URL.");
       }
        try {
            String url = params.get(Constants.PARAM_WEBHOOK_URL);
            if(url != null) {
                URL webUrl = new URL(url);
            }
        } catch(MalformedURLException e) {
            throw new IllegalArgumentException("The webhook URL is not a valid URL");
        }
    }
}
