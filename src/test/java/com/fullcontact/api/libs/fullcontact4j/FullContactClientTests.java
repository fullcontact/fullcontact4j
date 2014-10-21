package com.fullcontact.api.libs.fullcontact4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterPolicy;
import com.fullcontact.api.libs.fullcontact4j.request.FCCallback;
import com.fullcontact.api.libs.fullcontact4j.request.FCRequest;
import com.fullcontact.api.libs.fullcontact4j.request.PersonRequest;
import com.fullcontact.api.libs.fullcontact4j.request.RequestExecutorHandler;
import com.fullcontact.api.libs.fullcontact4j.response.ErrorResponse;
import com.fullcontact.api.libs.fullcontact4j.response.FCResponse;
import com.fullcontact.api.libs.fullcontact4j.response.PersonResponse;
import com.squareup.okhttp.OkHttpClient;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import retrofit.client.OkClient;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;

import static org.junit.Assert.*;

public class FullContactClientTests extends EasyMockSupport {

    @Before
    public void before() {

    }

    @Test
    //Assure the builder is returning the corresponding client
    public void clientBuilderTest() {
        String apiKey = "example-key";
        String baseUrl = "not.fullcontact.com";
        int maxThreads = 5;

        FullContact client1 = new FullContact(new OkClient(new OkHttpClient()), null, baseUrl, maxThreads);
        FullContact client2 = FullContact.withApiKey(apiKey)
                .baseUrl(baseUrl).threadCount(maxThreads).build();
        assertEquals(client1.httpInterface.getBaseUrl(), client2.httpInterface.getBaseUrl());
    }

    
    private static final int REQUEST_AMOUNT = 75;

    @Test(timeout = 2000)
    //Tests to make sure that there aren't any funny async / race stuff that would mismatch requests from their response
    //counterparts
    public void asyncTest() throws Exception {
        FullContact client = FullContact.withApiKey("bad-api-key").build();
        client.httpInterface.setRequestExecutorHandler(new MockRequestHandler(RateLimiterPolicy.SMOOTH, 1));
        //this is not a good method but will properly test
        final CountDownLatch latch = new CountDownLatch(REQUEST_AMOUNT);

        //async
        for (int i = 0; i != REQUEST_AMOUNT; i++) {
            final PersonRequest req = client.buildPersonRequest().email(UUID.randomUUID().toString()).build();
            client.sendRequest(req, new FCCallback<PersonResponse>() {
                @Override
                public void success(PersonResponse response) {
                    latch.countDown();
                    String reqID = response.getRequestId();
                    if (reqID.equals(req.getParam(Constants.PARAM_PERSON_EMAIL))) {
                        latch.countDown();
                    } else {
                        throw new AssertionError("Request ID from request and response did not match.");
                    }
                }

                @Override
                public void failure(FullContactException exception) {
                    fail(exception.getMessage());
                }
            });
        }

        latch.await();
    }

    @Test(timeout = 2000)
    public void syncTest() throws Exception {
        FullContact client = FullContact.withApiKey("bad-api-key").build();
        client.httpInterface.setRequestExecutorHandler(new MockRequestHandler(RateLimiterPolicy.SMOOTH, 1));
        //sync
        for (int i = 0; i != REQUEST_AMOUNT; i++) {
            final PersonRequest req = client.buildPersonRequest().email(UUID.randomUUID().toString()).build();
            try {
                PersonResponse res = client.sendRequest(req);
                String reqID = res.getRequestId();
                if (!reqID.equals(req.getParam(Constants.PARAM_PERSON_EMAIL))) {
                    throw new AssertionError("Request ID from request and response did not match.");
                }
            } catch(FullContactException e) {
                throw new AssertionError("Request threw an error");
            }
        }
}

    @Test
    public void headerCheck() throws Exception {
        FullContact.setLogLevel(Level.FINEST);
        FullContact client = FullContact.withApiKey("bad-api-key").build();
        PersonRequest re = client.buildPersonRequest().email("bart@fullcontact.com").build();
        client.sendRequest(re);
    }

    @Test
    //make sure client parses FC errors properly
    public void fullContactExceptionParseTest() throws Exception {
        ErrorResponse r = (new ObjectMapper()).readValue(Utils.loadFile("example-error-response.json"), ErrorResponse.class);
        assertTrue(r.status == 403);
        assertTrue(r.message.contains("API Key"));
        assertTrue(r.requestId != null);
    }


    private class MockRequestHandler extends RequestExecutorHandler {

        public MockRequestHandler(RateLimiterPolicy policy, Integer threadPoolCount) {
            super(policy, threadPoolCount);
        }

        //just return a success rather than actually hitting any apis
        public <T extends FCResponse> void sendRequestAsync(final FullContactApi api, final FCRequest<T> req,
                                                            final FCCallback<T> callback) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    waitForPermit();
                    callback.success((T) newMockResponse(req.getParam(Constants.PARAM_PERSON_EMAIL)));
                }
            });
        }
    }

    private PersonResponse newMockResponse(final String email) {
        PersonResponse mock = new PersonResponse() {
            public String getRequestId() {
                return email;
            }
        };
        return mock;
    }
}
