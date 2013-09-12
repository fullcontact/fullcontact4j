package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.builders.CardReaderUploadRequestBuilder;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.UploadRequestResult;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.UploadResponse;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.ViewRequestEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.ViewRequestsEntity;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.MessageFormat;

/***
 * Handler for all Card Reader related requests.
 * @see "http://www.fullcontact.com/developer/docs/card-reader/"
 */
public class CardReaderHandler extends BaseHandler {

    public CardReaderHandler(String apiKey) {
        super(apiKey);
    }

    /***
     * Uploads a new card to Card Reader
     * @param request
     * @return
     * @throws FullContactException
     */
    public UploadResponse uploadCard(CardReaderUploadRequestBuilder.CardReaderUploadRequest request) throws FullContactException {
        String response = FullContactHttpRequest.postCardReaderRequest(apiKey, request);
        return parseUploadJsonResponse(response);
    }

    /***
     * Gets the first page of uploaded card results
     * @return
     * @throws FullContactException
     */
    public ViewRequestsEntity viewRequestResults() throws FullContactException {
        return viewRequestResults(0);
    }

    /***
     * Gets the selected page of uploaded card results.
     * @param page
     * @return
     * @throws FullContactException
     */
    public ViewRequestsEntity viewRequestResults(int page) throws FullContactException {
        return viewRequestResults(page, "json");
    }

    private ViewRequestsEntity viewRequestResults(int page, String format) throws FullContactException {
        String paramString = MessageFormat.format(Constants.PAGE, page) + "&" +
                MessageFormat.format(Constants.FORMAT, format) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseViewRequestsJsonResponse(FullContactHttpRequest.sendCardReaderViewRequest(paramString));
    }

    /***
     * Gets a specific upload request
     * @param requestId
     * @return
     * @throws FullContactException
     */
    public ViewRequestEntity viewRequestResult(String requestId) throws FullContactException {
        return viewRequestResult(requestId, "json");
    }

    private ViewRequestEntity viewRequestResult(String requestId, String format) throws FullContactException {
        String paramString = MessageFormat.format(Constants.FORMAT, format) + "&" +
                MessageFormat.format(Constants.API_KEY_FORMAT, apiKey);
        return parseViewRequestJsonResponse(FullContactHttpRequest.sendCardReaderViewRequest(requestId, paramString));
    }

    /***
     * Parses the upload response from Card Reader
     * @param response
     * @return
     */
    public UploadResponse parseUploadJsonResponse(String response) {
        return parseJsonResponse(response);
    }

    /***
     * Parses the requests response from Card Reader
     * @param response
     * @return
     */
    public UploadResponse parseJsonResponse(String response) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, UploadResponse.class);
    }

    /***
     * Parses the upload webhook json response
     * @param response
     * @return
     */
    public UploadRequestResult parseUploadWebhookJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, UploadRequestResult.class);
    }

    /***
     * Parses the paged requests json response.
     * @param response
     * @return
     */
    public ViewRequestsEntity parseViewRequestsJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, ViewRequestsEntity.class);
    }

    /***
     * Parses the single request json response.
     * @param response
     * @return
     */
    public ViewRequestEntity parseViewRequestJsonResponse(String response) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        return gson.fromJson(jsonObject, ViewRequestEntity.class);
    }

}
