package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.FullContactHttpInterface;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
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
        RequestExecutorHandler requestHandler = httpInterface.getRequestExecutorHandler();
        //should we comb the headers for rate limit info?
        if (requestHandler.shouldNotfiyRateLimit()) {
            for (Header h : response.getHeaders()) {
                if (FCConstants.HEADER_RATE_LIMIT_PER_MINUTE.equals(h.getName())) {
                    Utils.verbose("Updated rate limit based on response headers from FullContact.");
                    try {
                        // Guava's RateLimiter is actually about 5-10ms too fast per permit.
                        // A full minute of requests, hitting the rate limit every time, this can actually make the
                        // rate limiter exceed its limits by a few ms and trigger a rate limit exception.
                        // So, we use 90% of FullContact's expected rate limit to give it some headroom.
                        requestHandler.notifyRateLimitPerMinute(Double.parseDouble(h.getValue()) * .9);
                    } catch (NumberFormatException e) {
                        //rate limit per minute wasn't a number (???), don't set any limits
                        e.printStackTrace();
                        Utils.info("FullContact response had a rate limit that was not a number.");
                    }
                    break;
                }
            }
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
        parent.failure(new FullContactException(reason, (response == null ? null : response.getStatus()), ex));
    }
}
