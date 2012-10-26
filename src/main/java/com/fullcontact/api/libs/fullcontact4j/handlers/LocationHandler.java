package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationEnrichmentEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationInfo;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationNormalizerEntity;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class LocationHandler extends BaseHandler {

    public LocationHandler(String apiKey) {
        super(apiKey);
    }

    public LocationNormalizerEntity getLocationNormalizationInfo(String place)
            throws FullContactException {
        String paramString = MessageFormat.format(Constants.PLACE_FORMAT, place) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseJsonResponse(FullContactHttpRequest.sendLocationNormalizationRequest(paramString));
    }

    public LocationEnrichmentEntity getLocationEnrichmentInfo(String place)
            throws FullContactException {
        String paramString = MessageFormat.format(Constants.PLACE_FORMAT, place) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseEnrichmentJsonResponse(FullContactHttpRequest.sendLocationEnrichmentRequest(paramString));
    }

    public LocationNormalizerEntity parseJsonResponse(String response) {
        return parseNormalizerJsonResponse(response);
    }

    public LocationNormalizerEntity parseNormalizerJsonResponse(String response) {
        LocationNormalizerEntity message = new LocationNormalizerEntity();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        message.setStatusCode(jsonObject.get("status").getAsInt());
        message.setLikelihood(jsonObject.get("likelihood").getAsDouble());
        message.setRequestId(jsonObject.get("requestId").getAsString());
        message.setLocationInfo(gson.fromJson(jsonObject, LocationInfo.class));
        return message;
    }

    public LocationEnrichmentEntity parseEnrichmentJsonResponse(String response) {
        LocationEnrichmentEntity message = new LocationEnrichmentEntity();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        message.setStatusCode(jsonObject.get("status").getAsInt());
        message.setRequestId(jsonObject.get("requestId").getAsString());
        if (jsonObject.get("locations") != null) {
            message.setLocations(new ArrayList<LocationInfo>());
            Iterator<JsonElement> itr = jsonObject.get("locations").getAsJsonArray().iterator();
            while (itr.hasNext()) {
                JsonElement locationElement = itr.next();
                message.addLocation(gson.fromJson(locationElement, LocationInfo.class));
            }
        }
        return message;
    }

}
