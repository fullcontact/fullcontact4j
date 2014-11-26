package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A builder template that automatically validates any parameters surrounding webhooks.
 * @param <T>
 */
public abstract class WebhookBuilder<T extends FCRequest> extends FCRequest.BaseBuilder<T> {

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
