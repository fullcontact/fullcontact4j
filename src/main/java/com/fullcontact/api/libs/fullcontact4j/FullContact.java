package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.*;
import com.google.gson.Gson;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FullContact {
    private String apiKey;

    public FullContact(String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("apiKey cannot be null.");
        }

        if (apiKey.trim().length() == 0) {
            throw new IllegalArgumentException("apiKey cannot be empty.");
        }

        this.apiKey = apiKey;
    }

    public FullContactEntity getPersonInformation(String email)
            throws FullContactException {
        String requestParams =
            MessageFormat.format(Constants.EMAIL_FORMAT, email) + "&"
            + MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);

        return parsePersonJsonResponse(FullContactHttpRequest.sendRequest(requestParams));
    }

    public FullContactEntity parsePersonJsonResponse(String response) {
        FullContactEntity message = new FullContactEntity();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        message.setStatusCode(jsonObject.get("status").getAsInt());
        message.setContactInfo(gson.fromJson(jsonObject.get("contactInfo"), ContactInfo.class));
        message.setDemographics(gson.fromJson(jsonObject.get("demographics"), Demographics.class));
        message.setDigitalFootprint(gson.fromJson(jsonObject.get("digitalFootprint"), DigitalFootPrints.class));

        JsonElement organizationsJsonObject = jsonObject.get("organizations");
        if(organizationsJsonObject != null && organizationsJsonObject.isJsonArray()){
            Iterator<JsonElement> iterator = organizationsJsonObject.getAsJsonArray().iterator();
            List<Organizations> organizations = new ArrayList<Organizations>();
            while(iterator.hasNext()){
                JsonElement org = iterator.next();
                organizations.add(gson.fromJson(org, Organizations.class));
            }
            message.setOrganizations(organizations);
        }

        JsonElement photosJsonObject = jsonObject.get("photos");
        if(photosJsonObject != null && photosJsonObject.isJsonArray()){
            Iterator<JsonElement> iterator = photosJsonObject.getAsJsonArray().iterator();
            List<Photo> photos = new ArrayList<Photo>();
            while(iterator.hasNext()){
                JsonElement org = iterator.next();
                photos.add(gson.fromJson(org, Photo.class));
            }
            message.setPhotos(photos);
        }

        JsonElement socialProfilesJsonObject = jsonObject.get("socialProfiles");
        if(socialProfilesJsonObject != null && socialProfilesJsonObject.isJsonArray()){
            message.setSocialProfiles(new SocialProfiles(socialProfilesJsonObject));
        }

        JsonElement enhancedDataJsonObject = jsonObject.get("enhancedData");
        if(enhancedDataJsonObject != null && enhancedDataJsonObject.isJsonArray()){
            Iterator<JsonElement> iterator = enhancedDataJsonObject.getAsJsonArray().iterator();
            List<EnhancedDataEntity> enhancedData = new ArrayList<EnhancedDataEntity>();
            while(iterator.hasNext()){
                JsonElement org = iterator.next();
                enhancedData.add(gson.fromJson(org, EnhancedDataEntity.class));
            }
            message.setEnhancedData(enhancedData);
        }
        return message;
    }

}
