package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.*;
import com.fullcontact.api.libs.fullcontact4j.entity.person.*;
import com.google.gson.Gson;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.MessageFormat;
import java.util.*;

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

    public PersonEntity getPersonInformation(String email)
            throws FullContactException {
        return getPersonInfoByEmail(email);
    }

    public PersonEntity getPersonInfoByEmail(String email)
            throws FullContactException {
        return getPersonInfoByParameter(MessageFormat.format(Constants.EMAIL_FORMAT, email));
    }

    public PersonEntity getPersonInfoByTwitter(String twitterId)
            throws FullContactException {
        return getPersonInfoByParameter(MessageFormat.format(Constants.TWITTER_FORMAT, twitterId));
    }

    public PersonEntity getPersonInfoByFacebookUsername(String facebookUsername)
            throws FullContactException {
        return getPersonInfoByParameter(MessageFormat.format(Constants.FACEBOOK_FORMAT, facebookUsername));
    }

    public PersonEntity getPersonInfoByParameter(String paramName, String paramValue)
            throws FullContactException {
        String paramString = paramName+"="+paramValue;
        return getPersonInfoByParameter(paramString);
    }

    public PersonEntity getPersonInfoByEmailUsingWebhook(String email, String webhookUrl)
            throws FullContactException {
        return getPersonInfoByEmailUsingWebhook(email, webhookUrl, null);
    }

    public PersonEntity getPersonInfoByEmailUsingWebhook(String email, String webhookUrl, String webhookId)
            throws FullContactException {
        return getPersonInfoByParameters(getWebhookParamMap(Constants.PARAM_EMAIL, email, webhookUrl, webhookId));
    }

    public PersonEntity getPersonInfoByFacebookUsernameUsingWebhook(String facebookUsername, String webhookUrl)
            throws FullContactException {
        return getPersonInfoByFacebookUsernameUsingWebhook(facebookUsername, webhookUrl, null);
    }

    public PersonEntity getPersonInfoByFacebookUsernameUsingWebhook(String facebookUsername, String webhookUrl, String webhookId)
            throws FullContactException {
        return getPersonInfoByParameters(getWebhookParamMap(Constants.PARAM_FACEBOOK_USERNAME, facebookUsername, webhookUrl, webhookId));
    }

    public PersonEntity getPersonInfoByTwitterUsingWebhook(String twitterId, String webhookUrl)
            throws FullContactException {
        return getPersonInfoByTwitterUsingWebhook(twitterId, webhookUrl, null);
    }

    public PersonEntity getPersonInfoByTwitterUsingWebhook(String twitterId, String webhookUrl, String webhookId)
            throws FullContactException {
        return getPersonInfoByParameters(getWebhookParamMap(Constants.PARAM_TWITTER, twitterId, webhookUrl, webhookId));
    }

    public PersonEntity getPersonEnhancedInfo(String email)
            throws FullContactException {
        return getPersonInfoByParameter(MessageFormat.format(Constants.EMAIL_FORMAT, email));
    }

    private Map<String, String> getWebhookParamMap(String paramName, String paramValue, String webhookUrl, String webhookId){
        Map<String, String> params = new HashMap<String, String>();
        params.put(paramName, paramValue);
        params.put(Constants.PARAM_WEBHOOK_URL, webhookUrl);
        if(webhookId != null){
            params.put(Constants.PARAM_WEBHOOK_ID, webhookId);
        }
        return params;
    }

    public PersonEntity getPersonInfoByParameters(Map<String, String> params)
            throws FullContactException {
        Iterator<String> itr = params.keySet().iterator();
        List<String> paramStringList = new ArrayList<String>();
        while(itr.hasNext()){
            String key = itr.next();
            String value = params.get(key);
            String paramString = key+"="+value;
            paramStringList.add(paramString);
        }
        StringBuffer paramStringBuffer = null;
        for(String paramString : paramStringList){
            if(paramStringBuffer == null){
                paramStringBuffer = new StringBuffer();
            }else{
                paramStringBuffer.append("&");
            }
            paramStringBuffer.append(paramString);
        }
        if(paramStringBuffer != null){
            return getPersonInfoByParameter(paramStringBuffer.toString());
        }
        return null;
    }

    private PersonEntity getPersonInfoByParameter(String paramString)
            throws FullContactException {
        paramString = paramString + "&" + MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parsePersonJsonResponse(FullContactHttpRequest.sendPersonRequest(paramString));
    }

    public PersonEntity parsePersonJsonResponse(String response) {
        PersonEntity message = new PersonEntity();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        message.setStatusCode(jsonObject.get("status").getAsInt());
        JsonElement likelihood = jsonObject.get("likelihood");
        if(likelihood != null){
            message.setLikelihood(jsonObject.get("likelihood").getAsDouble());
        }
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
