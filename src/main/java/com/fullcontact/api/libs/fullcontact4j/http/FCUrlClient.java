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
import com.fullcontact.api.libs.fullcontact4j.OkUrlFactory;
import com.fullcontact.api.libs.fullcontact4j.Utils;

import okhttp3.OkHttpClient;
import retrofit.client.*;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private OkUrlFactory okUrlFactory;
    private String apiKey;
    private String userAgent;
    public FCUrlClient(String userAgent, Map<String, String> customHeaders, OkHttpClient client, String apiKey) {
        okUrlFactory = new OkUrlFactory(client);

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

    @Override public Response execute(Request request) throws IOException {
        HttpURLConnection connection = openConnection(request);
        prepareRequest(connection, request);
        return readResponse(connection);
    }

    protected HttpURLConnection openConnection(Request request) throws IOException {
        return okUrlFactory.open(new URL(request.getUrl()));
    }

    protected void prepareRequest(HttpURLConnection connection, Request request) throws IOException {
        connection.setRequestMethod(request.getMethod());
        connection.setDoInput(true);

        //add request headers
        for (Header header : request.getHeaders()) {
            connection.addRequestProperty(header.getName(), header.getValue());
        }

        //add custom global headers
        for(Map.Entry<String, String> header : headers.entrySet()) {
            if(header.getKey() != null && header.getValue() != null) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            } else {
                Utils.verbose("Ignored null header in request (Key: " + header.getKey() + ", Value: " + header.getValue() + ")");
            }
        }

        boolean hasAuthToken = false;
        for(Header header : request.getHeaders()) {
            if(header.getName().equals(FCConstants.HEADER_AUTH_ACCESS_TOKEN)) {
                hasAuthToken = true;
            }
        }
        if(!hasAuthToken) {
            connection.setRequestProperty(FCConstants.HEADER_AUTH_API_KEY, apiKey);
            Utils.verbose("Added API key to headers");
        } else {
            Utils.verbose("Added auth token instead of API key to headers");
        }
        connection.setRequestProperty(FCConstants.HEADER_USER_AGENT, FCConstants.USER_AGENT_BASE + " " + userAgent);

        TypedOutput body = request.getBody();
        if (body != null) {
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", body.mimeType());
            long length = body.length();
            if (length != -1) {
                connection.setFixedLengthStreamingMode((int) length);
                connection.addRequestProperty("Content-Length", String.valueOf(length));
            } else {
                connection.setChunkedStreamingMode(CHUNK_SIZE);
            }
            body.writeTo(connection.getOutputStream());
        }
    }

    Response readResponse(HttpURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        String reason = connection.getResponseMessage();
        if (reason == null) reason = ""; // HttpURLConnection treats empty reason as null.

        List<Header> headers = new ArrayList<Header>();
        for (Map.Entry<String, List<String>> field : connection.getHeaderFields().entrySet()) {
            String name = field.getKey();
            for (String value : field.getValue()) {
                headers.add(new Header(name, value));
            }
        }

        String mimeType = connection.getContentType();
        int length = connection.getContentLength();
        InputStream stream;
        if (status >= 400) {
            stream = connection.getErrorStream();
        } else {
            stream = connection.getInputStream();
        }
        TypedInput responseBody = new TypedInputStream(mimeType, length, stream);
        return new Response(connection.getURL().toString(), status, reason, headers, responseBody);
    }

    private static class TypedInputStream implements TypedInput {
        private final String mimeType;
        private final long length;
        private final InputStream stream;

        private TypedInputStream(String mimeType, long length, InputStream stream) {
            this.mimeType = mimeType;
            this.length = length;
            this.stream = stream;
        }

        @Override public String mimeType() {
            return mimeType;
        }

        @Override public long length() {
            return length;
        }

        @Override public InputStream in() throws IOException {
            return stream;
        }
    }
}
