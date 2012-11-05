package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.cardshark.UploadResponse;
import com.fullcontact.api.libs.fullcontact4j.entity.cardshark.UploadWebhookResponse;
import com.fullcontact.api.libs.fullcontact4j.entity.cardshark.ViewRequestsEntity;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class CardSharkHandler extends BaseHandler {

    public CardSharkHandler(String apiKey) {
        super(apiKey);
    }

    public UploadResponse uploadCardImage(InputStream frontImageStream, String webhookUrl)
            throws FullContactException {
        return uploadCardImage(frontImageStream, null, webhookUrl);
    }

    public UploadResponse uploadCardImage(InputStream frontImageStream, InputStream backImageStream, String webhookUrl)
            throws FullContactException {
        return uploadCardImage(frontImageStream, backImageStream, webhookUrl, null);
    }

    public UploadResponse uploadCardImage(InputStream frontImageStream, InputStream backImageStream, String webhookUrl, String format)
            throws FullContactException {

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put(Constants.PARAM_API_KEY, apiKey);
        queryParams.put(Constants.PARAM_WEBHOOK_URL, webhookUrl);
        if (format != null)
            queryParams.put(Constants.PARAM_FORMAT, format);

        String response = FullContactHttpRequest.postCardResponse(queryParams, frontImageStream, backImageStream);
        return parseUploadJsonResponse(response);
    }

    public ViewRequestsEntity viewCardSharkRequests() throws FullContactException {
        return viewCardSharkRequests(0);
    }

    public ViewRequestsEntity viewCardSharkRequests(int page) throws FullContactException {
        return viewCardSharkRequests(page, "json");
    }

    public ViewRequestsEntity viewCardSharkRequests(int page, String format) throws FullContactException {
        String paramString = getParamString(page, format);
        return parseViewRequestsJsonResponse(FullContactHttpRequest.sendCardSharkViewRequest(paramString));
    }

    public void viewCardSharkRequest(String requestId) throws FullContactException {
        viewCardSharkRequest(requestId, 0);
    }

    public void viewCardSharkRequest(String requestId, int page) throws FullContactException {
        viewCardSharkRequest(requestId, page, "json");
    }

    public void viewCardSharkRequest(String requestId, int page, String format) throws FullContactException {
        String paramString = getParamString(page, format);
        parseViewRequestJsonResponse(FullContactHttpRequest.sendCardSharkViewRequest(requestId, paramString));
    }

    private String getParamString(int page, String format) {
        return MessageFormat.format(Constants.PAGE, page) + "&" +
                MessageFormat.format(Constants.FORMAT, format) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
    }

    public UploadResponse parseUploadJsonResponse(String response) {
        return parseJsonResponse(response);
    }

    public UploadResponse parseJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, UploadResponse.class);
    }

    public UploadWebhookResponse parseUploadWebhookJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, UploadWebhookResponse.class);
    }

    public ViewRequestsEntity parseViewRequestsJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, ViewRequestsEntity.class);
    }

    public void parseViewRequestJsonResponse(String response) {
    }

}
