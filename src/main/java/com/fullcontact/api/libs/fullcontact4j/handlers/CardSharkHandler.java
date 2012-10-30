package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationInfo;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationNormalizerEntity;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.util.URIUtil;

import java.io.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class CardSharkHandler extends BaseHandler {

    public CardSharkHandler(String apiKey) {
        super(apiKey);
    }

    //TODO: return type, parameters, url
    public void uploadCardImage(InputStream frontImageInputStream, String webhookUrl, String format)
            throws FullContactException, IOException {
        webhookUrl = URIUtil.encodeAll(webhookUrl);
        String paramString = MessageFormat.format(Constants.WEBHOOK_URL, webhookUrl) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        if (format != null) {
            paramString += ("&" + MessageFormat.format(Constants.FORMAT, format));
        }
        String url = Constants.API_URL_CARDSHARK_UPLOAD + paramString;
        Map<String, String> parameters = new HashMap<String, String>();
        Base64.encodeBase64(getBytes(frontImageInputStream));
        parameters.put("data", frontImageInputStream.toString());
        String response = FullContactHttpRequest.postResponse(url, parameters);
        parseJsonResponse(response);
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

    public static byte[] getBytes(InputStream is) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1)
                bos.write(buf, 0, len);
            buf = bos.toByteArray();
        }
        return buf;
    }

}
