package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.icon.IconListResponse;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.text.MessageFormat;

public class IconHandler extends BaseHandler {

    public static final int SIZE_16 = 16;
    public static final int SIZE_24 = 24;
    public static final int SIZE_32 = 32;
    public static final int SIZE_64 = 64;
    public static final String STYLE_DEFAULT = "default";
    public static final String STYLE_DARK = "dark";
    public static final String STYLE_LIGHT = "light";

    public IconHandler(String apiKey) {
        super(apiKey);
    }

    public IconListResponse getValidIconTypes() throws FullContactException {
        String paramString = MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        String response = FullContactHttpRequest.sendIconsListRequest(paramString);
        return parseIconsListJsonResponse(response);
    }

    public InputStream getIcon(String typeId) throws FullContactException {
        return getIcon(typeId, SIZE_16);
    }

    public InputStream getIcon(String typeId, int size) throws FullContactException {
        return getIcon(typeId, size, STYLE_DEFAULT);
    }

    public InputStream getIcon(String typeId, int size, String style) throws FullContactException {
        String paramString = MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return FullContactHttpRequest.sendIconRequest(typeId, size, style, paramString);
    }

    public IconListResponse parseIconsListJsonResponse(String response) {
        return parseJsonResponse(response);
    }

    public IconListResponse parseJsonResponse(String response){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, IconListResponse.class);
    }

}
