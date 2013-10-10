package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.builders.CardReaderUploadRequestBuilder;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderCasing;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderSandboxStatus;
import com.fullcontact.api.libs.fullcontact4j.enums.ResponseFormat;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class FullContactHttpRequest {

    /**
     * customRequestProperties
     *
     * If populated, these will be added as request properties on the Http connection
     *
     * For example:
     *
     * HashMap<String,String> headers = new HashMap<String,String>();
     * headers.put("My-Header","My-Value");
     *
     * FullContactHttpRequest.setCustomRequestProperties(headers);
     *
     * Now <b>every</b> request will have the properties added to 'headers' in the connection
     *
     * Note: User-Agent by default is already added
     *      To override, use {@link FullContactHttpRequest#setUserAgent}
     *
     *
     * Important: As mentioned, setting these request parameters sets them for good
     * and they will be present in every request.
     *
     *
     * **/
    private static HashMap<String,String> customRequestProperties = new HashMap<String, String>();
    public static void setCustomRequestProperties(HashMap<String,String> requestProperties) throws NullPointerException {
        if (requestProperties == null) throw new NullPointerException("requestProperties");
        customRequestProperties = requestProperties;
    }

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

    private static String FC_USER_AGENT = "fullcontact4j/1.0";
    private static final String STR_USER_AGENT = "User-Agent";

    public static void setUserAgent(String str) {
        FC_USER_AGENT = str;
    }

    public static String sendRequest(String apiUrl)
            throws FullContactException {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(apiUrl);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty(STR_USER_AGENT, FC_USER_AGENT);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), Constants.UTF_8_CHARSET));
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

    public static String sendCardReaderViewRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_CARDREADER_VIEW_REQUESTS + paramString));
    }

    public static String sendCardReaderViewRequest(String requestId, String paramString)
            throws FullContactException {
        if (requestId == null) {
            return sendCardReaderViewRequest(paramString);
        }
        return sendRequest((MessageFormat.format(Constants.API_URL_CARDREADER_VIEW_REQUEST, requestId) + paramString));
    }

    @Deprecated
    public static String sendCardSharkViewRequest(String paramString)
            throws FullContactException {
        return sendCardReaderViewRequest(paramString);
    }

    @Deprecated
    public static String sendCardSharkViewRequest(String requestId, String paramString)
            throws FullContactException {
        return sendCardReaderViewRequest(requestId, paramString);
    }

    public static String sendEmailDisposableDomainRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_EMAIL_DISPOSABLE_DOMAIN + paramString));
    }

    public static String sendIconsListRequest(String paramString)
            throws FullContactException {
        return sendRequest((Constants.API_URL_ICON + paramString));
    }

    public static InputStream sendIconRequest(String typeId, int size, String style, String paramString)
            throws FullContactException {
        String url = MessageFormat.format(Constants.API_URL_ICON_TYPE_ID, typeId, size, style);
        try {
            URLConnection connection = new URL(url+paramString).openConnection();
            connection.setRequestProperty(STR_USER_AGENT, FC_USER_AGENT);
            return connection.getInputStream();
        } catch (IOException e) {
            throw new FullContactException(e.getMessage(), e);
        }
    }

    public static String postCardReaderRequest(String apiKey, CardReaderUploadRequestBuilder.CardReaderUploadRequest request)
            throws FullContactException {
        Map<String, String> queryParams = generateQueryParams(apiKey, request);
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("front", new String(Base64.encodeBase64(Utils.getBytesFromInputStream(request.getFrontImage()))));
            if (request.getBackImage() != null)
                jsonObject.addProperty("back", new String(Base64.encodeBase64(Utils.getBytesFromInputStream(request.getBackImage()))));
        } catch (Throwable throwable) {
            throw new FullContactException("Failed to encode inputstream content to Base64", throwable);
        }

        byte[] payload = jsonObject.toString().replace("\\r\\n", "").getBytes();
        return postWithGZip(Constants.API_URL_CARDREADER_UPLOAD, queryParams, payload, "application/json");
    }

    public static HashMap<String, String> generateQueryParams(String apiKey, CardReaderUploadRequestBuilder.CardReaderUploadRequest request) throws FullContactException {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put(Constants.PARAM_API_KEY, apiKey);
        queryParams.put(Constants.PARAM_WEBHOOK_URL, request.getWebhookUrl());
        queryParams.put(Constants.PARAM_FORMAT, request.getFormat().toString().toLowerCase());
        queryParams.put(Constants.PARAM_VERIFIED, request.getFormat().toString().toLowerCase());
        if (request.getAccessToken() != null)
            queryParams.put(Constants.PARAM_ACCESS_TOKEN, request.getAccessToken());
        if (request.getURID() != null)
            queryParams.put(Constants.PARAM_URID, request.getURID());
        queryParams.putAll(request.getCustomParams());
        if(request.isVerifiedOnly())
            queryParams.put(Constants.PARAM_RETURNED_DATA, "verifiedOnly");
        queryParams.put(Constants.PARAM_VERIFIED, request.getVerification().toString().toLowerCase());
        if(request.getCasing() != CardReaderCasing.Default)
            queryParams.put(Constants.PARAM_CASING, request.getCasing().toString().toLowerCase());
        if(request.isSandbox()) {
            CardReaderSandboxStatus sandboxStatus = request.getSandboxStatus();
            if(sandboxStatus != null)
                queryParams.put(Constants.PARAM_SANDBOX, request.getSandboxStatus().toString());
            else
                throw new FullContactException("sandboxStatus is required if request isSandbox");
        }
        return queryParams;
    }

    @Deprecated
    public static String postCardRequest(Map<String, String> queryParams, InputStream frontStream, InputStream backStream)
        throws FullContactException {
        String apiKey = queryParams.get(Constants.PARAM_API_KEY);
        String webhookUrl = queryParams.get(Constants.PARAM_WEBHOOK_URL);
        String format = queryParams.containsKey(Constants.PARAM_FORMAT) ?
                queryParams.get(Constants.PARAM_FORMAT) :
                null;
        ResponseFormat responseFormat = ResponseFormat.JSON;
        if(format != null && format.toLowerCase() == "xml")
            responseFormat = ResponseFormat.XML;

        CardReaderUploadRequestBuilder builder = new CardReaderUploadRequestBuilder();
        builder.setWebhookUrl(webhookUrl)
                .setFormat(responseFormat)
                .setFrontImage(frontStream)
                .setBackImage(backStream);
        return postCardReaderRequest(apiKey, builder.build());
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
            HttpURLConnection connection = createHttpConnectionForQuery(baseUrl, params);
            addConnectionProperties(contentType, connection);
            writeDataForConnection(data, connection);
            return readResponse(connection);
        } catch (Throwable throwable) {
            throw new FullContactException("Failed to execute API Request", throwable);
        }
    }

    private static HttpURLConnection createHttpConnectionForQuery(String baseUrl, Map<String, String> params) throws IOException {
        String qs = toQueryString(params);
        String fullUrl = baseUrl;
        if (qs.length() > 0) {
            if (!fullUrl.endsWith("?")) {
                fullUrl += "?";
            }
            fullUrl += qs;
        }
        URL url = new URL(fullUrl);
        return (HttpURLConnection) url.openConnection();
    }

    private static void addConnectionProperties(String contentType, HttpURLConnection connection) throws ProtocolException {
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setConnectTimeout(6000);
        connection.setConnectTimeout(60000);
        connection.setRequestProperty(STR_USER_AGENT, FC_USER_AGENT);
        for (Map.Entry<String,String> requestProperty : customRequestProperties.entrySet()) {
            connection.setRequestProperty(requestProperty.getKey(), requestProperty.getValue());
        }
        connection.setRequestProperty("Accept-Encoding", "gzip");
        connection.setRequestProperty("Content-Encoding", "gzip");
        connection.setRequestProperty("Content-Type", contentType);
    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
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
    }

    private static void writeDataForConnection(byte[] data, HttpURLConnection connection) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        GZIPOutputStream wr = new GZIPOutputStream(byteStream);
        wr.write(data);
        wr.finish();
        wr.flush();
        connection.setRequestProperty("Content-Length", "" + Integer.toString(byteStream.size()));
        OutputStream out = connection.getOutputStream();
        byteStream.writeTo(out);
        out.flush();
        out.close();
        byteStream.close();
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