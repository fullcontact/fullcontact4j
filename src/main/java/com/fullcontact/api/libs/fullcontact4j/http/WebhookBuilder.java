package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A builder that automatically provides/validates parameters surrounding webhooks.
 */
public abstract class WebhookBuilder<B extends FCRequest.BaseBuilder<B,R>, R extends FCRequest>
        extends FCRequest.BaseBuilder<B,R> {

    public B webhookUrl(String url) {
        params.put(FCConstants.PARAM_WEBHOOK_URL, url);
        return self();
    }

    public B webhookId(String id) {
        params.put(FCConstants.PARAM_WEBHOOK_ID, id);
        return self();
    }

    public B webhookBody(Boolean rawJson) {
        if(rawJson) {
            params.put(FCConstants.PARAM_WEBHOOK_BODY, "json");
        } else {
            params.remove(FCConstants.PARAM_WEBHOOK_BODY);
        }
        return self();
    }

    protected void validate() {
       if(!hasParam(FCConstants.PARAM_WEBHOOK_URL) &&
               ( hasParam(FCConstants.PARAM_WEBHOOK_BODY) || hasParam(FCConstants.PARAM_WEBHOOK_ID) )) {
           throw new IllegalArgumentException("Request has some webhook parameters set, but no webhook URL.");
       }
        try {
            String url = params.get(FCConstants.PARAM_WEBHOOK_URL);
            if(url != null) {
                URL webUrl = new URL(url);
            }
        } catch(MalformedURLException e) {
            throw new IllegalArgumentException("The webhook URL is not a valid URL");
        }
    }
}
