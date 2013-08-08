package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.cardshark.*;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class CardSharkHandler extends BaseHandler {

    @Deprecated
    public CardSharkHandler(String apiKey) {
        super(apiKey);
    }

    @Deprecated
    public UploadResponse uploadCardImage(InputStream frontImageStream, String webhookUrl)
            throws FullContactException {
        return uploadCardImage(frontImageStream, null, webhookUrl);
    }

    @Deprecated
    public UploadResponse uploadCardImage(InputStream frontImageStream, InputStream backImageStream, String webhookUrl)
            throws FullContactException {
        return uploadCardImage(frontImageStream, backImageStream, webhookUrl, null);
    }

    private UploadResponse uploadCardImage(InputStream frontImageStream, InputStream backImageStream, String webhookUrl, String format)
            throws FullContactException {

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put(Constants.PARAM_API_KEY, apiKey);
        queryParams.put(Constants.PARAM_WEBHOOK_URL, webhookUrl);
        if (format != null)
            queryParams.put(Constants.PARAM_FORMAT, format);

        String response = FullContactHttpRequest.postCardRequest(queryParams, frontImageStream, backImageStream);
        return parseUploadJsonResponse(response);
    }

    @Deprecated
    public ViewRequestsEntity viewRequestResults() throws FullContactException {
        return viewRequestResults(0);
    }

    @Deprecated
    public ViewRequestsEntity viewRequestResults(int page) throws FullContactException {
        return viewRequestResults(page, "json");
    }

    private ViewRequestsEntity viewRequestResults(int page, String format) throws FullContactException {
        String paramString = MessageFormat.format(Constants.PAGE, page) + "&" +
                MessageFormat.format(Constants.FORMAT, format) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseViewRequestsJsonResponse(FullContactHttpRequest.sendCardSharkViewRequest(paramString));
    }

    @Deprecated
    public ViewRequestEntity viewRequestResult(String requestId) throws FullContactException {
        return viewRequestResult(requestId, "json");
    }

    private ViewRequestEntity viewRequestResult(String requestId, String format) throws FullContactException {
        String paramString = MessageFormat.format(Constants.FORMAT, format) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseViewRequestJsonResponse(FullContactHttpRequest.sendCardSharkViewRequest(requestId, paramString));
    }

    @Deprecated
    public UploadResponse parseUploadJsonResponse(String response) {
        return parseJsonResponse(response);
    }

    @Deprecated
    public UploadResponse parseJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, UploadResponse.class);
    }

    @Deprecated
    public UploadRequestResult parseUploadWebhookJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, UploadRequestResult.class);
    }

    @Deprecated
    public ViewRequestsEntity parseViewRequestsJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, ViewRequestsEntity.class);
    }

    @Deprecated
    public ViewRequestEntity parseViewRequestJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, ViewRequestEntity.class);
    }

}
