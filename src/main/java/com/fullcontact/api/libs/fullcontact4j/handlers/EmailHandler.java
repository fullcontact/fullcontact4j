package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.email.DisposableResponse;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

public class EmailHandler extends BaseHandler {

    public EmailHandler(String apiKey) {
        super(apiKey);
    }

    public DisposableResponse getDisposableInfo(String email) throws FullContactException {
        try {
            email = URLEncoder.encode(email, "UTF-8");
        } catch (Exception e) {
            throw new FullContactException(e.getMessage(), e);
        }
        String paramString = MessageFormat.format(Constants.EMAIL_FORMAT, email) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        String response = FullContactHttpRequest.sendEmailDisposableDomainRequest(paramString);
        return parseJsonResponse(response);
    }

    public DisposableResponse parseJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, DisposableResponse.class);
    }


}
