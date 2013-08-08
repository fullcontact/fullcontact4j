package com.fullcontact.api.libs.fullcontact4j.handlers;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.batch.BatchResponse;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/***
 * Handler for batching requests in parallel.
 * @see "http://www.fullcontact.com/developer/docs/batch/"
 */
public class BatchHandler extends BaseHandler {

    public BatchHandler(String apiKey) {
        super(apiKey);
    }

    /***
     * Batches a set of requests to the server to process in parallel.
     * @param queries
     * @return
     * @throws FullContactException
     * @see "http://www.fullcontact.com/developer/docs/batch/"
     */
    public BatchResponse processApiRequests(List<String> queries)
            throws FullContactException {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put(Constants.PARAM_API_KEY, apiKey);
        String response = FullContactHttpRequest.postBatchRequest(queryParams, queries);
        return parseJsonResponse(response);
    }

    /***
     * Parses the json from the batch request.
     * @param response
     * @return
     */
    public BatchResponse parseJsonResponse(String response) {
        BatchResponse batchResponse = new BatchResponse();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(response).getAsJsonObject();
        batchResponse.setStatusCode(jsonObject.get("status").getAsInt());
        Iterator<Map.Entry<String, JsonElement>> iterator = jsonObject.get("responses").getAsJsonObject().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonElement> entry = iterator.next();
            batchResponse.addResult(entry.getKey(), entry.getValue().toString());
        }
        return batchResponse;
    }


}
