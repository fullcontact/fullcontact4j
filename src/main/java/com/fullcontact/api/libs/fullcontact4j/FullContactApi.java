package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.request.UploadCardRequest;
import com.fullcontact.api.libs.fullcontact4j.response.*;
import retrofit.Callback;
import retrofit.http.*;

import java.util.Map;

/**
 * Hosts all the actual retrofit endpoints for the FullContact API.
 * All methods utilize asynchronous processing (even synchronous requests, which are made synchronous on the client side)
 * and so have a callback parameter.
 * All methods utilize a query map, since all parameter validation is handled in the Request's Builder class.
 */
public interface FullContactApi {

    @GET("/{path}")
    public void genericGet(@Path("path") String path, @QueryMap Map<String, String> opts, Callback<GenericResponse> response);

    @GET(Constants.API_ENDPOINT_PERSON)
    public void getPerson(@QueryMap Map<String, String> opts, Callback<PersonResponse> response);

    @POST(Constants.API_ENDPOINT_CARDREADER)
    public void uploadCard(@Header(Constants.HEADER_AUTH_ACCESS_TOKEN) String accessToken, @QueryMap Map<String, String> opts, @Body UploadCardRequest.RequestBodyJson bodyJson, Callback<UploadCardConfirmResponse> callback);

    @GET(Constants.API_ENDPOINT_CARDREADER + "/{id}")
    public void viewCard(@QueryMap Map<String, String> opts, @Path("id") String id, Callback<CardReaderFullResponse> callback);

    @GET(Constants.API_ENDPOINT_CARDREADER)
    public void viewAll(@QueryMap Map<String, String> opts, Callback<CardReaderViewAllResponse> callback);

}
