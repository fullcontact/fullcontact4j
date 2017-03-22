package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.enums.RateLimiterConfig;
import com.fullcontact.api.libs.fullcontact4j.http.*;
import com.fullcontact.api.libs.fullcontact4j.http.cardreader.CardReaderUploadConfirmResponse;
import com.fullcontact.api.libs.fullcontact4j.http.person.PersonRequest;
import com.fullcontact.api.libs.fullcontact4j.http.person.PersonResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.junit.*;
import retrofit.client.*;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;

public class FullContactClientTests {

    @Test
    //Assure the builder is returning the corresponding client
    public void clientBuilderTest() {
        String apiKey = "example-key";
        String baseUrl = "not.fullcontact.com";
        RateLimiterConfig rateLimiterConfig = new RateLimiterConfig(10, 1);

        FullContact client1 = new FullContact(new FCUrlClient(baseUrl, apiKey), rateLimiterConfig, baseUrl, Executors.newSingleThreadExecutor(), Executors.newSingleThreadExecutor());
        FullContact client2 = FullContact.withApiKey(apiKey)
                .baseUrl(baseUrl).httpExecutor(Executors.newSingleThreadExecutor()).rateLimitExecutorService(Executors.newSingleThreadExecutor()).build();
        assertEquals(client1.httpInterface.getBaseUrl(), client2.httpInterface.getBaseUrl());
    }

    private static final int REQUEST_AMOUNT = 75;

    @Test(timeout = 8000)
    //Tests to make sure that there aren't any funny async / race stuff that would mismatch requests from their response
    //counterparts
    public void asyncTest() throws Exception {
        FullContact client = FullContact.withApiKey("bad-api-key").build();
        client.httpInterface.setRequestHandler(new MockRequestHandler(
            RateLimiterConfig.SMOOTH, Executors.newSingleThreadExecutor()));
        final CountDownLatch latch = new CountDownLatch(REQUEST_AMOUNT);
        //async
        for (int i = 0; i != REQUEST_AMOUNT; i++) {
            final PersonRequest req = client.buildPersonRequest().email(UUID.randomUUID().toString()).build();
            client.sendRequest(req, new FCCallback<PersonResponse>() {
                @Override
                public void success(PersonResponse response) {
                    latch.countDown();
                    String reqID = response.getRequestId();
                    if (reqID.equals(req.getParam(FCConstants.PARAM_PERSON_EMAIL))) {
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

    @Test(timeout = 8000)
    public void syncTest() throws Exception {
        FullContact client = FullContact.withApiKey("bad-api-key").build();
        client.httpInterface.setRequestHandler(
            new MockRequestHandler(RateLimiterConfig.SMOOTH, Executors.newSingleThreadExecutor()));
        //sync
        for (int i = 0; i != REQUEST_AMOUNT; i++) {
            final PersonRequest req = client.buildPersonRequest().email(UUID.randomUUID().toString()).build();
            try {
                PersonResponse res = client.sendRequest(req);
                String reqID = res.getRequestId();
                if (!reqID.equals(req.getParam(FCConstants.PARAM_PERSON_EMAIL))) {
                    fail("Request ID from request and response did not match.");
                }
            } catch (FullContactException e) {
                fail("Request threw an error");
            }
        }
    }

    @Test
    //make sure client parses FC errors properly
    public void fullContactExceptionParseTest() throws Exception {
        ErrorResponse r = (new ObjectMapper()).readValue(Utils.loadFile("example-error-response.json"), ErrorResponse.class);
        assertTrue(r.status == 403);
        assertTrue(r.message.contains("API Key"));
        assertTrue(r.requestId != null);
    }

    private static final String BAD_API_KEY = "bad-api-key";
    private static FullContact mockFc;
    private static Map<String, String> mockHeaders = new HashMap<String, String>();
    private static MockRetrofitClient mockClient;

    @BeforeClass
    public static void before() {
        FullContact.setLogLevel(Level.FINEST);

        mockHeaders.put("test-custom", "1234");
        mockHeaders.put("another-test", "test");
        mockClient = new MockRetrofitClient("test", mockHeaders, new OkHttpClient(), BAD_API_KEY);
        //create a new FullContact client that uses an http client that never makes requests and points towards nothing
        mockFc = new FullContact(mockClient,
                RateLimiterConfig.SMOOTH, "http://badbadbad.not.exist", Executors.newSingleThreadExecutor(), Executors.newSingleThreadExecutor());
    }

    @AfterClass
    public static void after() {
        mockFc.shutdown();
    }


    @Test(timeout = 2000)
    //assure the api key is being added as a header
    public void apiKeyHeaderTest() throws Exception {
        mockFc.sendRequest(mockFc.buildPersonRequest().email("bad@bad.com").build(), noOpCallback(PersonResponse.class));
        Exception error = null;
        try {
            HttpURLConnection connection = mockClient.getCreatedConnection();
            Map<String, List<String>> headers = connection.getRequestProperties();
            List<String> apiHeader = headers.get(FCConstants.HEADER_AUTH_API_KEY);
            if (apiHeader == null || apiHeader.size() != 1) {
                fail("No API Key header present on request");
            }
            assertEquals(BAD_API_KEY, apiHeader.get(0));
        } catch (Exception e) {
            error = e;
        } finally {
            mockClient.resetConnection();
        }
        if (error != null) {
            throw error;
        }
    }

    @Test(timeout = 2000)
    //assure that if a token param is present, it is the only auth header on the request
    public void authTokenHeaderTest() throws Exception {
        mockFc.sendRequest(mockFc.buildUploadCardRequest(new FileInputStream(Utils.loadFile("businesscard.jpg")))
                .accessToken("bad-token").webhookUrl("http://www.bad-url.com").build(), noOpCallback(CardReaderUploadConfirmResponse.class));
        Exception error = null;
        try {
            HttpURLConnection connection = mockClient.getCreatedConnection();
            Map<String, List<String>> headers = connection.getRequestProperties();
            List<String> tokenHeader = headers.get(FCConstants.HEADER_AUTH_ACCESS_TOKEN);
            if (headers.get(FCConstants.HEADER_AUTH_API_KEY) != null) {
                fail("API Header was still present when an access token param was used.");
            }
            if (tokenHeader == null || tokenHeader.size() < 1) {
                fail("Token header wasn't present for a request with an access token header");
            }
            assertEquals("bad-token", tokenHeader.get(0));
        } catch (Exception e) {
            error = e;
        } finally {
            mockClient.resetConnection();
        }
        if (error != null) {
            throw error;
        }
    }

    @Test(timeout = 2000)
    //assure user agent is being added to all requests
    public void userAgentTest() throws Exception {
        mockFc.sendRequest(mockFc.buildPersonRequest().email("bad@bad.com.net").build(), noOpCallback(PersonResponse.class));
        final String EXPECTED_USER_AGENT = FCConstants.USER_AGENT_BASE + " test";
        Exception error = null;
        try {
            HttpURLConnection connection = mockClient.getCreatedConnection();
            Map<String, List<String>> headers = connection.getRequestProperties();
            List<String> agentHeader = headers.get(FCConstants.HEADER_USER_AGENT);
            if (agentHeader == null || agentHeader.size() != 1) {
                fail("No user agent where one was expected");
            }
            assertEquals(EXPECTED_USER_AGENT, agentHeader.get(0));
        } catch (Exception e) {
            error = e;
        } finally {
            mockClient.resetConnection();
        }
        if (error != null) {
            throw error;
        }
    }

    @Test(timeout = 2000)
    //assure custom headers are being added to all requests
    public void customHeaderTest() throws Exception {
        mockFc.sendRequest(mockFc.buildPersonRequest().email("bad@bad.com.net").build(), noOpCallback(PersonResponse.class));
        Exception error = null;
        try {
            HttpURLConnection connection = mockClient.getCreatedConnection();
            Map<String, List<String>> headers = connection.getRequestProperties();
            for(String key : mockHeaders.keySet()) {
                assertTrue(headers.containsKey(key));
                assertEquals(mockHeaders.get(key), headers.get(key).get(0));
            }
        } catch (Exception e) {
            error = e;
        } finally {
            mockClient.resetConnection();
        }
        if (error != null) {
            throw error;
        }
    }

    @Test
    //test the client correctly models what is required in a webhook.
    //related: issue #15
    public void webhookRequiredTest() throws Exception {
        PersonRequest personRequest = mockFc.buildPersonRequest()
                .webhookId("Some_Webhook_Id")
                .webhookUrl("http://www.fullcontact.com")
                .twitterName("bartlorang")
                .build();
        //this should pass just fine, has both url and id
    }

    @Test(expected=IllegalArgumentException.class)
    public void webhookIdOnlyTest() {
        PersonRequest personRequest = mockFc.buildPersonRequest()
                .webhookId("Some_Webhook_Id")
                .twitterName("bartlorang")
                .build();
    }

    @Test(expected=IllegalArgumentException.class)
    public void webhookBodyOnlyTest() {
        PersonRequest personRequest = mockFc.buildPersonRequest()
                .webhookBody(true)
                .twitterName("bartlorang")
                .build();
    }

    //same as a regular client used in FullContact, but allows direct access to the connection being established
    //and does not actually connect to any service.
    private static class MockRetrofitClient extends FCUrlClient {
        private volatile CountDownLatch latch = new CountDownLatch(1);
        public MockRetrofitClient(String userAgent, Map<String, String> headers, OkHttpClient client, String apiKey) {
            super(userAgent, headers, client, apiKey);
        }

        private volatile HttpURLConnection currentConnection;

        protected HttpURLConnection getCreatedConnection() {
            while(currentConnection == null) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return currentConnection;
        }

        protected void resetConnection() {
            currentConnection = null;
            latch = new CountDownLatch(1);
        }

        public Response execute(Request request) throws IOException {
            //remove body from request, it causes Retrofit to establish a connection to this URL before we can head it
            //off and stop the request from being made.
            request = new Request(request.getMethod(), request.getUrl(), request.getHeaders(), null);
            currentConnection = openConnection(request);
            prepareRequest(currentConnection, request);
            latch.countDown();
            return new Response("bad-url", 200, "Success", Collections.EMPTY_LIST, null);
        }
    }

    private class MockRequestHandler extends RequestExecutorHandler {

        public MockRequestHandler(RateLimiterConfig policy, ExecutorService executorService) {
            super(policy, executorService);
        }

        //just return a success rather than actually hitting any apis
        public <T extends FCResponse> void sendRequestAsync(final FullContactApi api, final FCRequest<T> req,
                                                            final FCRetrofitCallback<T> callback) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    waitForPermit();
                    callback.success((T) newMockResponse(req.getParam(FCConstants.PARAM_PERSON_EMAIL)), new Response("", 200, "", Collections.<Header>emptyList(), null));
                }
            });
        }
    }

    private <T extends FCResponse> FCCallback<T> noOpCallback(Class<T> type) {
        return (new FCCallback<T>() {
            @Override
            public void success(T response) {
                //no-op
            }

            @Override
            public void failure(FullContactException exception) {
                //no-op
                exception.printStackTrace();
            }
        });
    }

    private PersonResponse newMockResponse(final String email) {
        return new PersonResponse(null,null,null,null,null,.99,null,null,email,null);
    }
}
