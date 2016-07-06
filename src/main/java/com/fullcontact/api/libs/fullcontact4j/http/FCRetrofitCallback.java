package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.FullContactHttpInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.ConversionException;

/**
 * The core retrofit callback that all FCCallbacks use.
 * Before FCCallback receives the success() or failure() call,
 * this callback acts as an interceptor to update rate limits from headers
 * and provide a useful FullContactException from a RetrofitError.
 *
 * @param <T>
 */
public class FCRetrofitCallback<T extends FCResponse> implements Callback<T> {

    private final FCCallback<T> parent;
    private final FullContactHttpInterface httpInterface;

    public FCRetrofitCallback(FCCallback parent, FullContactHttpInterface httpInterface) {
        this.parent = parent;
        this.httpInterface = httpInterface;
    }

    public void success(T t, Response response) {
        FCRequestHandler requestHandler = httpInterface.getRequestHandler();
        FCRateLimits rateLimits = FCRateLimits.fromResponseNullable(response);

        if(rateLimits != null) {
            requestHandler.notifyRateLimits(t, rateLimits);
            t.setRateLimits(rateLimits);
        }

        parent.success(t);
    }

    @Override
    public void failure(RetrofitError retrofitError) {
        Throwable ex = retrofitError;
        //do some logic to figure out why this error occurred
        String reason = "Unknown reason for exception, see stack trace";
        Response response = retrofitError.getResponse();
        switch (retrofitError.getKind()) {
            case CONVERSION:
                reason = "Encountered an error converting to a Java object from response JSON";
                break;

            case NETWORK:
                reason = "A network error occurred";
                break;

            case HTTP:
                try {
                    ErrorResponse errorResponse = (ErrorResponse) httpInterface.getJsonConverter()
                            .fromBody(response.getBody(),
                                    ErrorResponse.class);
                    reason = (errorResponse.message == null ? "" : errorResponse.message);
                } catch (ConversionException e) {
                    //response did not have a formatted error response...should not happen
                    ex = e;
                    reason = "Although the FullContact API responded with an error, " +
                            "the response was not in the proper format.";
                } catch (ClassCastException e) {
                    ex = e;
                    reason = "Although the FullContact API responded with an error, " +
                            "the response was not in the proper format.";
                }
            case UNEXPECTED:
            default:
                break;
        }

        FullContactException fcEx;
        if(response == null) {
            fcEx = new FullContactException(reason, null, ex, null);
        } else {
            fcEx = new FullContactException(reason, response.getStatus(), ex, FCRateLimits.fromResponseNullable(response));
        }

        parent.failure(fcEx);
    }
}
