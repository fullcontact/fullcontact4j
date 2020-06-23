package com.fullcontact.api.libs.fullcontact4j.http;
/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.Utils;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import retrofit.client.*;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FCUrlClient implements Client {
    private static final int CHUNK_SIZE = 4096;
    private static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s
    private static final int READ_TIMEOUT_MILLIS = 20 * 1000; // 20s

    private static OkHttpClient generateDefaultOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .build();
    }

    private Map<String, String> headers = new HashMap<String, String>();
    private OkHttpClient client;
    private String apiKey;
    private String userAgent;
    public FCUrlClient(String userAgent, Map<String, String> customHeaders, OkHttpClient client, String apiKey) {
        this.client = client;

        if(customHeaders != null) {
            this.headers = customHeaders;
        }

        //disallow api key, token, or user agent headers to be supplied by the user
        boolean removedBlocked = headers.remove(FCConstants.HEADER_AUTH_API_KEY) != null;
        removedBlocked |= headers.remove(FCConstants.HEADER_AUTH_ACCESS_TOKEN) != null;
        if(removedBlocked) {
            Utils.info("Custom FullContact header for api key or access token was supplied. It has been ignored.");
        }

        this.userAgent = userAgent;
        this.apiKey = apiKey;
    }

    public FCUrlClient(String userAgent, String apiKey) {
        this(userAgent, null, generateDefaultOkHttp(), apiKey);
    }

    @Override
    public Response execute(Request request) throws IOException {
        okhttp3.Request okRequest = buildRequest(request);
        okhttp3.Response okResponse = client.newCall(okRequest).execute();
        Response response = buildResponse(okResponse);
        return response;
    }

    // build okhttp3.Request from retrofit.client.Request
    public okhttp3.Request buildRequest(Request request) {
        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder();

        boolean hasAuthToken = false;
        //---copy headers---
        List<retrofit.client.Header> requestHeaders = request.getHeaders();
        Headers.Builder okHeadersBulder = new Headers.Builder();
        if (requestHeaders != null && requestHeaders.size() > 0) {
            for (Header header : requestHeaders) {
                okHeadersBulder.add(header.getName(), header.getValue());
                if (header.getName().equals(FCConstants.HEADER_AUTH_ACCESS_TOKEN)) {
                    hasAuthToken = true;
                }
            }
        }
        //add custom global headers
        for (Map.Entry<String, String> header : headers.entrySet()) {
            if (header.getKey() != null && header.getValue() != null) {
                okHeadersBulder.add(header.getKey(), header.getValue());
            } else {
                Utils.verbose("Ignored null header in request (Key: " + header.getKey() + ", Value: " + header.getValue() + ")");
            }
        }

        if (!hasAuthToken) {
            okHeadersBulder.add(FCConstants.HEADER_AUTH_API_KEY, apiKey);
            Utils.verbose("Added API key to headers");
        } else {
            Utils.verbose("Added auth token instead of API key to headers");
        }
        okHeadersBulder.add(FCConstants.HEADER_USER_AGENT, FCConstants.USER_AGENT_BASE + " " + userAgent);

        requestBuilder.headers(okHeadersBulder.build());

        //--- copy url ----
        requestBuilder.url(request.getUrl());

        //--- copy method and create request body---
        if (!request.getMethod().equalsIgnoreCase("GET"))
            requestBuilder.method(request.getMethod(), new RequestBodyWrapper(request.getBody()));
        else
            requestBuilder.get();

        return requestBuilder.build();
    }

    private Response buildResponse(okhttp3.Response okResponse) {
        TypedInput inputBody = new ResponseBodyWrapper(okResponse.body());
        Response response = new retrofit.client.Response(okResponse.request().url().toString(),
                okResponse.code(), okResponse.message(), getHeaders(okResponse.headers()), inputBody);
        return response;
    }

    private List<Header> getHeaders(Headers headers) {
        List<Header> retrofitHeaders = new ArrayList<Header>();
        int headerCount = headers.names().size();
        for (int i = 0; i < headerCount; i++) {
            retrofitHeaders.add(new Header(headers.name(i), headers.value(i)));
        }
        return retrofitHeaders;
    }

    private static class RequestBodyWrapper extends RequestBody {
        private final TypedOutput mWrapped;

        public RequestBodyWrapper(TypedOutput output) {
            mWrapped = output;
        }

        @Override
        public long contentLength() {
            return mWrapped.length();
        }

        @Override
        public MediaType contentType() {
            return MediaType.parse(mWrapped.mimeType());
        }

        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            mWrapped.writeTo(sink.outputStream());
        }
    }

    public static class ResponseBodyWrapper implements TypedInput {
        private ResponseBody mWrapped;

        public ResponseBodyWrapper(ResponseBody body) {
            mWrapped = body;
        }

        @Override
        public String mimeType() {
            return mWrapped.contentType().type();
        }

        @Override
        public long length() {
            return mWrapped.contentLength();
        }

        @Override
        public InputStream in() throws IOException {
            return mWrapped.byteStream();
        }

    }
}