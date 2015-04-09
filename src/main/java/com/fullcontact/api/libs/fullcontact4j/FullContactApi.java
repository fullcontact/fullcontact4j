package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.http.cardreader.CardReaderFullResponse;
import com.fullcontact.api.libs.fullcontact4j.http.cardreader.CardReaderUploadConfirmResponse;
import com.fullcontact.api.libs.fullcontact4j.http.cardreader.CardReaderUploadRequest;
import com.fullcontact.api.libs.fullcontact4j.http.cardreader.CardReaderViewAllResponse;
import com.fullcontact.api.libs.fullcontact4j.http.company.CompanyResponse;
import com.fullcontact.api.libs.fullcontact4j.http.location.LocationEnrichmentResponse;
import com.fullcontact.api.libs.fullcontact4j.http.location.LocationNormalizationResponse;
import com.fullcontact.api.libs.fullcontact4j.http.misc.AccountStatsResponse;
import com.fullcontact.api.libs.fullcontact4j.http.misc.DisposableEmailResponse;
import com.fullcontact.api.libs.fullcontact4j.http.name.NameParseResponse;
import com.fullcontact.api.libs.fullcontact4j.http.name.NameResponse;
import com.fullcontact.api.libs.fullcontact4j.http.name.NameSimilarityResponse;
import com.fullcontact.api.libs.fullcontact4j.http.name.NameStatsResponse;
import com.fullcontact.api.libs.fullcontact4j.http.person.PersonResponse;
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

    @GET(FCConstants.API_ENDPOINT_PERSON)
    public void getPerson(@QueryMap Map<String, String> opts, Callback<PersonResponse> response);

    @GET(FCConstants.API_ENDPOINT_COMPANY)
    public void getCompany(@QueryMap Map<String, String> opts, Callback<CompanyResponse> response);

    @POST(FCConstants.API_ENDPOINT_CARDREADER)
    public void uploadCard(@Header(FCConstants.HEADER_AUTH_ACCESS_TOKEN) String accessToken, @QueryMap Map<String, String> opts, @Body CardReaderUploadRequest.RequestBodyJson bodyJson, Callback<CardReaderUploadConfirmResponse> callback);

    @GET(FCConstants.API_ENDPOINT_CARDREADER + "/{id}")
    public void viewCard(@Header(FCConstants.HEADER_AUTH_ACCESS_TOKEN) String accessToken, @QueryMap Map<String, String> opts, @Path("id") String id, Callback<CardReaderFullResponse> callback);

    @GET(FCConstants.API_ENDPOINT_CARDREADER)
    public void viewAll(@Header(FCConstants.HEADER_AUTH_ACCESS_TOKEN) String accessToken, @QueryMap Map<String, String> opts, Callback<CardReaderViewAllResponse> callback);

    @GET(FCConstants.API_ENDPOINT_DISPOSABLE_EMAIL)
    public void getDisposableEmail(@Query("email") String email, Callback<DisposableEmailResponse> callback);

    @GET(FCConstants.API_ENDPOINT_NAME_NORMALIZER)
    public void getNormalizedName(@QueryMap Map<String, String> opts, Callback<NameResponse> response);

    @GET(FCConstants.API_ENDPOINT_NAME_PARSER)
    public void getParsedName(@QueryMap Map<String, String> opts, Callback<NameParseResponse> response);

    @GET(FCConstants.API_ENDPOINT_NAME_DEDUCER)
    public void getDeducedName(@QueryMap Map<String, String> opts, Callback<NameResponse> response);

    @GET(FCConstants.API_ENDPOINT_NAME_STATS)
    public void getNameStats(@QueryMap Map<String, String> opts, Callback<NameStatsResponse> response);

    @GET(FCConstants.API_ENDPOINT_NAME_SIMILARITY)
    public void getNameSimilarity(@QueryMap Map<String, String> opts, Callback<NameSimilarityResponse> response);

    @GET(FCConstants.API_ENDPOINT_LOCATION_ENRICHMENT)
    public void getEnrichedLocation(@QueryMap Map<String, String> opts, Callback<LocationEnrichmentResponse> response);

    @GET(FCConstants.API_ENDPOINT_LOCATION_NORMALIZER)
    public void getNormalizedLocation(@QueryMap Map<String, String> opts, Callback<LocationNormalizationResponse> response);

    @GET(FCConstants.API_ENDPOINT_ACCOUNT_STATS)
    public void getAccountStats(@QueryMap Map<String, String> opts, Callback<AccountStatsResponse> response);
}
