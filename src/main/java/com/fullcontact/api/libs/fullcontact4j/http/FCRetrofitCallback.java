package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.FullContactHttpInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.ConversionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        requestHandler.notifyHeaders(response.getHeaders());

        Map<String, Integer> rateLimits = getRateLimits(response.getHeaders());
        t.setRateLimitPerMinute(rateLimits.get(FCConstants.HEADER_RATE_LIMIT_PER_MINUTE));
        t.setRateLimitRemaining(rateLimits.get(FCConstants.HEADER_RATE_LIMIT_REMAINING));
        t.setRateLimitReset(rateLimits.get(FCConstants.HEADER_RATE_LIMIT_RESET_TIME));

        parent.success(t);
    }

    private Map<String, Integer> getRateLimits(List<Header> headers) {
        Map<String, Integer> rateLimiter = new HashMap<String, Integer>();
        for (Header h : headers) {
            if (FCConstants.HEADER_RATE_LIMIT_PER_MINUTE.equals(h.getName())) {
                rateLimiter.put(FCConstants.HEADER_RATE_LIMIT_PER_MINUTE, Integer.parseInt(h.getValue()));
            }
            if (FCConstants.HEADER_RATE_LIMIT_REMAINING.equals(h.getName())) {
                rateLimiter.put(FCConstants.HEADER_RATE_LIMIT_REMAINING, Integer.parseInt(h.getValue()));
            }
            if (FCConstants.HEADER_RATE_LIMIT_RESET_TIME.equals(h.getName())) {
                rateLimiter.put(FCConstants.HEADER_RATE_LIMIT_RESET_TIME, Integer.parseInt(h.getValue()));
            }
        }
        return rateLimiter;
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

        Map<String, Integer> rateLimits = getRateLimits(response.getHeaders());

        parent.failure(new FullContactException(
                reason,
                (response == null ? null : response.getStatus()),
                ex,
                rateLimits.get(FCConstants.HEADER_RATE_LIMIT_PER_MINUTE),
                rateLimits.get(FCConstants.HEADER_RATE_LIMIT_REMAINING),
                rateLimits.get(FCConstants.HEADER_RATE_LIMIT_RESET_TIME)
        ));
    }
}
