package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class FullContactHttpRequest {

    public static String sendPersonRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_PERSON + paramString));
    }

    public static String sendNameNormalizationRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_NAME_NORMALIZATION + paramString));
    }

    public static String sendNameDeducerRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_NAME_DEDUCER + paramString));
    }

    public static String sendNameSimilarityRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_NAME_SIMILARITY + paramString));
    }

    public static String sendNameStatsRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_NAME_STATS + paramString));
    }

    public static String sendNameParserRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_NAME_PARSER + paramString));
    }

    public static String sendPersonEnhancedDataRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_PERSON_ENHANCED_DATA + paramString));
    }

    public static String sendLocationNormalizationRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_LOCATION_NORMALIZATION + paramString));
    }

    public static String sendLocationEnrichmentRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_LOCATION_ENRICHMENT + paramString));
    }

    public static String sendRequest(String apiUrl)
            throws FullContactException {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(apiUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    url.openStream(), Constants.UTF_8_CHARSET));
            String str;
            while ((str = in.readLine()) != null) {
                buffer.append(str);
            }
            in.close();
        } catch (MalformedURLException e) {
            throw new FullContactException(e.getMessage());
        } catch (IOException e) {
            throw new FullContactException(e.getMessage());
        }
        return buffer.toString();
    }

    public static String sendCardSharkViewRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_CARDSHARK_VIEW_REQUESTS + paramString));
    }

    public static String sendCardSharkViewRequest(String requestId, String paramString)
            throws FullContactException {
        if (requestId == null) {
            return sendCardSharkViewRequest(paramString);
        }
        return sendRequest((MessageFormat.format(Constants.API_URL_CARDSHARK_VIEW_REQUEST, requestId) + paramString));
    }

    public static String sendEmailDisposableDomainRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_EMAIL_DISPOSABLE_DOMAIN + paramString));
    }

    public static String postCardRequest(Map<String, String> queryParams, InputStream frontStream, InputStream backStream)
            throws FullContactException {
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("front", new String(Base64.encodeBase64(Utils.getBytesFromInputStream(frontStream))));
            if (backStream != null)
                jsonObject.addProperty("back", new String(Base64.encodeBase64(Utils.getBytesFromInputStream(backStream))));
        } catch (Throwable throwable) {
            throw new FullContactException("Failed to encode inputstream content to Base64", throwable);
        }

        byte[] payload = jsonObject.toString().replace("\\r\\n", "").getBytes();
        return postWithGZip(Constants.API_URL_CARDSHARK_UPLOAD, queryParams, payload, "application/json");
    }

    public static String postCardSharkAcceptResult(String requestId, Map<String, String> queryParams)
            throws FullContactException {
        String url = MessageFormat.format(Constants.API_URL_CARDSHARK_ACCEPT, requestId);
        return postWithGZip(url, queryParams, new byte[0], "application/json");
    }

    public static String postCardSharkRejectResult(String requestId, Map<String, String> queryParams)
            throws FullContactException {
        String url = MessageFormat.format(Constants.API_URL_CARDSHARK_REJECT, requestId);
        return postWithGZip(url, queryParams, new byte[0], "application/json");
    }

    public static String postBatchRequest(Map<String, String> queryParams, List<String> queries)
            throws FullContactException {
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.add("requests", new Gson().toJsonTree(queries));
        } catch (Throwable throwable) {
            throw new FullContactException("Failed to encode inputstream content to Base64", throwable);
        }
        byte[] payload = jsonObject.toString().replace("\\r\\n", "").getBytes();
        return postWithGZip(Constants.API_URL_BATCH_PROCESS, queryParams, payload, "application/json");
    }

    private static String postWithGZip(String baseUrl, Map<String, String> params, byte[] data, String contentType)
            throws FullContactException {
        try {
            String qs = toQueryString(params);
            String fullUrl = baseUrl;
            if (qs.length() > 0) {
                if (!fullUrl.endsWith("?")) {
                    fullUrl += "?";
                }
                fullUrl += qs;
            }
            URL url = new URL(fullUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(6000);
            connection.setConnectTimeout(60000);
            connection.setRequestProperty("Accept-Encoding", "gzip");
            connection.setRequestProperty("Content-Encoding", "gzip");
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("Content-Length", "" + Integer.toString(data.length));
            GZIPOutputStream wr = new GZIPOutputStream(connection.getOutputStream());
            wr.write(data);
            wr.finish();
            wr.flush();
            wr.close();
            GZIPInputStream stream = new GZIPInputStream(connection.getInputStream());
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            bufferedReader.close();
            return sb.toString();
        } catch (Throwable throwable) {
            throw new FullContactException("Failed to execute API Request", throwable);
        }
    }

    private static String toQueryString(Map<String, String> params) throws UnsupportedEncodingException {
        String qs = "";
        for (String paramName : params.keySet()) {
            String paramValue = params.get(paramName);
            if (qs.length() > 0) {
                qs += "&";
            }
            qs += (paramName + "=" + URLEncoder.encode(paramValue, "UTF-8"));
        }
        return qs;
    }

}