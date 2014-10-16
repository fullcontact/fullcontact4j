package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContact;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.FullContactHttpInterface;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.ErrorResponse;
import com.fullcontact.api.libs.fullcontact4j.response.FCResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.ConversionException;

/**
 * A FCCallback class is technically a wrapper for a regular, Retrofit Callback class.
 * However, it also handles information from the header and presents more user-friendly errors.
 * @param <T>
 */
public abstract class FCCallback<T extends FCResponse> {

    private FullContactHttpInterface httpInterface;

    private Callback<T> callback = new Callback<T>() {
        @Override
        public void success(T t, Response response) {
            //intercept headers
            try {
                for (Header h : response.getHeaders()) {
                    if (Constants.HEADER_RATE_LIMIT_PER_MINUTE.equals(h.getName())) {
                        httpInterface.getRequestExecutorHandler().setRateLimitPerMinute(Integer.parseInt(h.getValue()));
                        //TODO break;
                    }
                    Utils.verbose(h.getName() + " : " + h.getValue());
                }
            } catch(NumberFormatException e) {
                //rate limit per minute wasn't a number (???), don't set any limits
                e.printStackTrace();
                Utils.info("FullContact response had a rate limit that was not a number.");
            }

            FCCallback.this.success(t);
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Throwable ex = retrofitError;
            //do some logic to figure out why this error occured
            String reason = "Unknown reason for exception, see stack trace";
            Integer errorCode = null;
            Response response = retrofitError.getResponse();
            switch(retrofitError.getKind()) {
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
                        reason = errorResponse.message;
                        errorCode = errorResponse.status;
                    } catch(ConversionException e) {
                        //response did not have a formatted error response...should not happen
                        ex = e;
                        reason = "Although the FullContact API responded with an error, " +
                                "the response was not in the proper format.";
                    } catch(ClassCastException e) {
                        ex = e;
                        reason = "Although the FullContact API responded with an error, " +
                                "the response was not in the proper format.";
                    }
                case UNEXPECTED:
                default:
                    break;
            }
            FCCallback.this.failure(new FullContactException(reason, errorCode, ex));
        }
    };

    protected void setHttpInterface(FullContactHttpInterface httpInterface) {
        this.httpInterface = httpInterface;
    }

    /**
     * Gets the actual Retrofit callback that FCCallback hooks into.
     */
    public Callback<T> getCoreCallback() {
        return callback;
    }

    public abstract void success(T response);

    public abstract void failure(FullContactException exception);
}
