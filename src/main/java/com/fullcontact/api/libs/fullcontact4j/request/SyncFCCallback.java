package com.fullcontact.api.libs.fullcontact4j.request;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.response.FCResponse;

/**
 * Created on 10/16/14 at 2:12 PM
 *
 * This is a synchronously implemented Callback. Consumers should use the `get` method which will either return the
 * result from the callback, or throw the exception that encountered
 *
 * @author michie <ken@fullcontact.com>
 */
public class SyncFCCallback<T extends FCResponse> extends FCCallback<T> {
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
