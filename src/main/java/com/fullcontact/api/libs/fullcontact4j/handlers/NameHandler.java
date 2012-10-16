package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameInfo;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameParserEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameParserInfo;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.MessageFormat;

public class NameHandler extends BaseHandler {

    public NameHandler(String apiKey) {
        super(apiKey);
    }

    public NameEntity getNameNormalizationInfo(String query)
            throws FullContactException {
        String paramString = MessageFormat.format(Constants.QUERY_FORMAT, query) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseJsonResponse(FullContactHttpRequest.sendNameNormalizationRequest(paramString));
    }

    public NameEntity getNameDeducerInfoByEmail(String email)
            throws FullContactException {
        String paramString = MessageFormat.format(Constants.EMAIL_FORMAT, email) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseDeducerJsonResponse(FullContactHttpRequest.sendNameDeducerRequest(paramString));
    }

    public NameEntity getNameDeducerInfoByUsername(String username)
            throws FullContactException {
        String paramString = MessageFormat.format(Constants.USERNAME_FORMAT, username) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseDeducerJsonResponse(FullContactHttpRequest.sendNameDeducerRequest(paramString));
    }

    public NameParserEntity getNameParserInfo(String query)
            throws FullContactException {
        String paramString = MessageFormat.format(Constants.QUERY_FORMAT, query) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseParserJsonResponse(FullContactHttpRequest.sendNameParserRequest(paramString));
    }

    public NameEntity parseJsonResponse(String response) {
        return parseNormalizationJsonResponse(response);
    }

    public NameEntity parseNormalizationJsonResponse(String response) {
        NameEntity message = new NameEntity();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        message.setStatusCode(jsonObject.get("status").getAsInt());
        message.setLikelihood(jsonObject.get("likelihood").getAsDouble());
        message.setRequestId(jsonObject.get("requestId").getAsString());
        message.setRegion(jsonObject.get("region").getAsString());
        message.setNameInfo(gson.fromJson(jsonObject.get("nameDetails"), NameInfo.class));
        return message;
    }

    public NameEntity parseDeducerJsonResponse(String response) {
        NameEntity message = new NameEntity();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        message.setStatusCode(jsonObject.get("status").getAsInt());
        message.setLikelihood(jsonObject.get("likelihood").getAsDouble());
        message.setRequestId(jsonObject.get("requestId").getAsString());
        message.setRegion(jsonObject.get("region").getAsString());
        message.setNameInfo(gson.fromJson(jsonObject.get("nameDetails"), NameInfo.class));
        return message;
    }

    public NameParserEntity parseParserJsonResponse(String response) {
        NameParserEntity message = new NameParserEntity();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        message.setStatusCode(jsonObject.get("status").getAsInt());
        message.setRequestId(jsonObject.get("requestId").getAsString());
        if(jsonObject.get("likelihood") != null){
            message.setLikelihood(jsonObject.get("likelihood").getAsDouble());
        }else if (jsonObject.get("result") != null){
            message.setLikelihood(jsonObject.get("result").getAsJsonObject().get("likelihood").getAsDouble());
        }
        message.setRegion(jsonObject.get("region").getAsString());
        message.setAmbiguousName(jsonObject.get("ambiguousName").getAsString());
        message.setNameInfo(gson.fromJson(jsonObject.get("result"), NameParserInfo.class));
        return message;
    }

}
