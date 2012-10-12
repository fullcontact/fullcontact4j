package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameInfo;
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

    public NameEntity parseJsonResponse(String response) {
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

}
