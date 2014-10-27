package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.FullContactHttpInterface;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import com.fullcontact.api.libs.fullcontact4j.http.person.PersonResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.ConversionException;

/**
 * A FCCallback class is technically a wrapper for a regular, Retrofit Callback class.
 * However, it also handles information from the response headers and presents more user-friendly errors.
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
                    if (FCConstants.HEADER_RATE_LIMIT_PER_MINUTE.equals(h.getName())) {
                        Utils.verbose("Updated rate limit based on response headers from FullContact.");
                        httpInterface.getRequestExecutorHandler().setRateLimitPerMinute(Integer.parseInt(h.getValue()));
                        break;
                    }
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
            //do some logic to figure out why this error occurred
            String reason = "Unknown reason for exception, see stack trace";
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
                        if(errorResponse.status == 404 && errorResponse.message.contains("No results found")) {
                            //on a 404 (person not found), return an empty PersonResponse as opposed to an exception
                            FCCallback.this.success((T)httpInterface.getJsonConverter().
                                    fromBody(response.getBody(), PersonResponse.class));
                            return;
                        }
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
            FCCallback.this.failure(new FullContactException(reason, response.getStatus(), ex));
        }
    };

    public void setHttpInterface(FullContactHttpInterface httpInterface) {
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


    /**
     * Created on 10/16/14 at 2:12 PM
     *
     * This is a synchronously implemented Callback. Consumers should use the `get` method which will either return the
     * result from the callback, or throw the exception that encountered
     *
     * @author michie <ken@fullcontact.com>
     */
    public static class SyncFCCallback<T extends FCResponse> extends FCCallback<T> {
        private T resp;
        private FullContactException t;

        @Override
        public synchronized void success(T response) {
            this.resp = response;
            notify();
        }

        @Override
        public synchronized void failure(FullContactException exception) {
            this.t = exception;
            notify();
        }

        public synchronized T get() throws FullContactException, InterruptedException {
            while (this.t == null && this.resp == null) {
                wait();
            }
            if (this.t != null) {
                throw t;
            }
            return this.resp;
        }
    }
}
