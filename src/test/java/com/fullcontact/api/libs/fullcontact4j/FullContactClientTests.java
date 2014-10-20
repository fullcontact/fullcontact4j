package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterPolicy;
import com.fullcontact.api.libs.fullcontact4j.request.FCCallback;
import com.fullcontact.api.libs.fullcontact4j.request.FCRequest;
import com.fullcontact.api.libs.fullcontact4j.request.PersonRequest;
import com.fullcontact.api.libs.fullcontact4j.request.RequestExecutorHandler;
import com.fullcontact.api.libs.fullcontact4j.response.FCResponse;
import com.fullcontact.api.libs.fullcontact4j.response.PersonResponse;
import com.squareup.okhttp.OkHttpClient;
import org.easymock.EasyMockSupport;
import org.junit.Test;
import retrofit.client.OkClient;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FullContactClientTests extends EasyMockSupport {


    @Test
    //Assure the builder is returning the corresponding client
    public void clientBuilderTest() {
        String apiKey = "example-key";
        String baseUrl = "not.fullcontact.com";
        int maxThreads = 5;

        FullContact client1 = new FullContact(new OkClient(new OkHttpClient()), apiKey, null, baseUrl, maxThreads);
        FullContact client2 = FullContact.withApiKey(apiKey)
                .baseUrl(baseUrl).threadCount(maxThreads).build();
        assertEquals(client1.httpInterface.apiKey, client2.httpInterface.apiKey);
        assertEquals(client1.httpInterface.getBaseUrl(), client2.httpInterface.getBaseUrl());
    }


    @Test
    //Tests to make sure that there aren't any funny async / race stuff that would mismatch requests from their response
    //counterparts
    public void asyncTest() throws Exception {
        FullContact client = FullContact.withApiKey("bad-api-key").build();
        client.httpInterface.setRequestExecutorHandler(new MockRequestHandler(RateLimiterPolicy.SMOOTH, 1));
        //this is not a good method but will properly test
        final AtomicInteger replies = new AtomicInteger(0);

        //async
        for(int i = 0; i != 75; i++) {
            final PersonRequest req = client.buildPersonRequest().email(UUID.randomUUID().toString()).build();
            client.sendRequest(req, new FCCallback<PersonResponse>() {
                @Override
                public void success(PersonResponse response) {
                    replies.addAndGet(1);
                    String reqID = response.getRequestId();
                    if(!reqID.equals(req.getParam(Constants.PARAM_PERSON_EMAIL))) {
                        replies.set(-1);
                    }
                }

                @Override
                public void failure(FullContactException exception) {
                    fail(exception.getMessage());
                }
            });
        }

        //sync
        for(int i = 0; i != 75; i++) {
            final PersonRequest req = client.buildPersonRequest().email(UUID.randomUUID().toString()).build();
            String reqID = client.sendRequest(req).getRequestId();
            if(!reqID.equals(req.getParam(Constants.PARAM_PERSON_EMAIL))) {
                replies.set(-1);
            } else {
                replies.addAndGet(1);
            }
        }

        while(replies.get() != 150) {
            if(replies.get() == -1) {
                fail("Not all email responses matched their request counterparts");
            }
            Thread.sleep(50);
        }

    }

    @Test
    public void fullContactExceptionParseTest() {
        //parse an example error response
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

    private PersonResponse newMockResponse(String email) {
        PersonResponse mock = createMock(PersonResponse.class);
        expect(mock.getRequestId()).andReturn(email);
        replay(mock);
        return mock;
    }
}
