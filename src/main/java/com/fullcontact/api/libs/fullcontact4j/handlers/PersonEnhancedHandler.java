package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.enhanced.*;
import com.fullcontact.api.libs.fullcontact4j.entity.PersonEnhancedEntity;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.MessageFormat;
import java.util.*;

public class PersonEnhancedHandler extends BaseHandler {

    public PersonEnhancedHandler(String apiKey) {
        super(apiKey);
    }

    public PersonEnhancedEntity getPersonEnhancedInfo(String email)
            throws FullContactException {
        String paramString = MessageFormat.format(Constants.EMAIL_FORMAT, email) + "&" + MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseJsonResponse(FullContactHttpRequest.sendPersonEnhancedDataRequest(paramString));
    }

    public PersonEnhancedEntity parseJsonResponse(String response) {
        PersonEnhancedEntity message = new PersonEnhancedEntity();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        message.setStatusCode(jsonObject.get("status").getAsInt());
        message.setContactInfo(gson.fromJson(jsonObject.get("contactInfo"), ContactInfo.class));
        message.setDemographics(gson.fromJson(jsonObject.get("demographics"), Demographics.class));
        JsonElement organizationsJsonObject = jsonObject.get("organizations");
        if(organizationsJsonObject != null && organizationsJsonObject.isJsonArray()){
            Iterator<JsonElement> iterator = organizationsJsonObject.getAsJsonArray().iterator();
            List<Organization> organizations = new ArrayList<Organization>();
            while(iterator.hasNext()){
                JsonElement org = iterator.next();
                organizations.add(gson.fromJson(org, Organization.class));
            }
            message.setOrganizations(organizations);
        }
        return message;
    }

}
