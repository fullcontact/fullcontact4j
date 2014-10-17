package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.GenericResponse;
import com.fullcontact.api.libs.fullcontact4j.request.FCCallback;
import com.fullcontact.api.libs.fullcontact4j.request.GenericRequest;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import retrofit.RetrofitError;
import retrofit.converter.JacksonConverter;

import java.util.logging.Level;

public class GenericRequestTest extends TestCase {

    public void test_generic() throws Exception {
        FullContact.setLogLevel(Level.FINEST);
        FullContact fc = FullContact.withApiKey("3a5316706b189050").build();
        GenericRequest r = fc.buildGenericRequest().path("person.json").param("email","bart@fullcontact.com").build();
        fc.sendRequest(r, new FCCallback<GenericResponse>() {
            @Override
            public void success(GenericResponse response) {
                System.out.println("Got response back 1");
            }

            @Override
            public void failure(FullContactException exception) {
                System.out.println("Printing failure exception:");
                exception.printStackTrace();
            }
        });
        Thread.sleep(1000);
        fc.sendRequest(r, new FCCallback<GenericResponse>() {
            @Override
            public void success(GenericResponse response) {
                System.out.println("Got response back 2");
            }

            @Override
            public void failure(FullContactException exception) {
                System.out.println("Printing failure exception:");
                exception.printStackTrace();
            }
        });
        Thread.sleep(1000);
        fc.sendRequest(r);
        Thread.sleep(8000);
    }

}
