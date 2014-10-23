package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.response.*;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

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

    @GET(Constants.API_ENDPOINT_DISPOSABLE_EMAIL)
    public void getDisposableEmail(@Query("email") String email, Callback<DisposableResponse> response);

    @GET(Constants.API_ENDPOINT_NAME_NORMALIZER)
    public void getNormalizedName(@QueryMap Map<String, String> opts, Callback<NameResponse> response);

    @GET(Constants.API_ENDPOINT_NAME_DEDUCER)
    public void getDeducedName(@QueryMap Map<String, String> opts, Callback<NameResponse> response);

    @GET(Constants.API_ENDPOINT_NAME_SIMILARITY)
    public void getNameSimilarity(@QueryMap Map<String, String> opts, Callback<NameSimilarityResponse> response);

    @GET(Constants.API_ENDPOINT_NAME_STATS)
    public void getNameStats(@QueryMap Map<String, String> opts, Callback<NameStatsResponse> response);

    @GET(Constants.API_ENDPOINT_NAME_PARSER)
    public void getParsedName(@QueryMap Map<String, String> opts, Callback<NameParseResponse> response);


}
