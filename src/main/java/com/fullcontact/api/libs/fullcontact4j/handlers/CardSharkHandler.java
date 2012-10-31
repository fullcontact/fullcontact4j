package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.cardshark.UploadResponse;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.util.URIUtil;

import java.io.*;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class CardSharkHandler extends BaseHandler {

    public CardSharkHandler(String apiKey) {
        super(apiKey);
    }

    //TODO: return type, parameters, url
    public UploadResponse uploadCardImage(InputStream frontImageInputStream, String webhookUrl)
            throws FullContactException, IOException {
        return uploadCardImage(frontImageInputStream, webhookUrl, null);
    }

    public UploadResponse uploadCardImage(InputStream frontImageInputStream, String webhookUrl, String format)
            throws FullContactException, IOException {
        webhookUrl = URLEncoder.encode(webhookUrl, "UTF-8");
        String paramString = MessageFormat.format(Constants.WEBHOOK_URL, webhookUrl) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        if (format != null) {
            paramString += ("&" + MessageFormat.format(Constants.FORMAT, format));
        }

        Map<String, String> postParameters = new HashMap<String, String>();
        postParameters.put("front", new String(Base64.encodeBase64(getBytes(frontImageInputStream))));
        String response = FullContactHttpRequest.postCardResponse(paramString, postParameters);
        return parseUploadJsonResponse(response);
    }

    public UploadResponse parseUploadJsonResponse(String response) {
        return parseJsonResponse(response);
    }

    public UploadResponse parseJsonResponse(String response) {
        System.out.println("UPLOAD Response:\n"+response+"\n====================");
        UploadResponse message = new UploadResponse();
//        Gson gson = new Gson();
//        JsonParser parser = new JsonParser();
//        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
//        message.setStatusCode(jsonObject.get("status").getAsInt());
//        message.setRequestId(jsonObject.get("id").getAsString());
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
