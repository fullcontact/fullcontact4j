package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;

/**
 * A FCCallback class is technically a wrapper for a regular, Retrofit Callback class.
 * After the core retrofit callback ({@link com.fullcontact.api.libs.fullcontact4j.http.FCRetrofitCallback})
 * generates a FullContactException or intercepts headers, it calls the respective methods here
 * to be consumed by an implementing class.
 * @param <T>
 */
public interface FCCallback<T extends FCResponse> {

    public void success(T response);

    public void failure(FullContactException exception);

    /**
     *
     * This is a synchronously implemented Callback. Consumers should use the `get` method which will either return the
     * result from the callback, or throw the exception that encountered
     *
     */
    public static class SyncFCCallback<T extends FCResponse> implements FCCallback<T> {
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
