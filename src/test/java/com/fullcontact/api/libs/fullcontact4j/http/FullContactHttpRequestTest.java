package com.fullcontact.api.libs.fullcontact4j.http;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

/**
 * @author Skylar Lowery (skylar@fullcontact.com)
 * @date 2/6/14
 */
public class FullContactHttpRequestTest extends TestCase {

    private final String testData = "This is a test";
    /**
     * Not all Android phones un-gzip automatically
     * and if Content Encoding is gzip, we should
     * un-gzip it.
     *
     * Note: To avoid bringing in more dependencies
     * in v1, I added a mock connection class.
     *
     * v2 should head out eventually (sooner than later)
     * and make these classes much more easily testable
     */
    @Test
    public void testGzipByHeaderEncoding() throws  Exception {
        MockHttpURLConnection connection =
                new MockHttpURLConnection(null);
        connection.setHeader("Content-Encoding", "gzip");
        ByteArrayOutputStream gzipped =
                new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream =
                new GZIPOutputStream(gzipped);
        gzipOutputStream.write(testData.getBytes());
        gzipOutputStream.flush();
        gzipOutputStream.close();
        ByteArrayInputStream mockStream =
                new ByteArrayInputStream(gzipped.toByteArray());
        connection.setMockInputStream(mockStream);
        assertFalse(new String(gzipped.toByteArray()).equals(testData));
        String response = FullContactHttpRequest.readResponse(connection);
        assertNotNull(response);
        assertEquals(testData, response.trim());
    }

    /**
     * Alternate path for {@link FullContactHttpRequestTest#testGzipByHeaderEncodingNoGZIPHeader()}
     */
    @Test
    public void testGzipByHeaderEncodingNoGZIPHeader() throws  Exception {
        MockHttpURLConnection connection =
                new MockHttpURLConnection(null);
        connection.setHeader("Content-Encoding", "not-gzip");
        ByteArrayInputStream mockStream =
                new ByteArrayInputStream(testData.getBytes());
        connection.setMockInputStream(mockStream);
        String response = FullContactHttpRequest.readResponse(connection);
        assertNotNull(response);
        assertEquals(testData, response.trim());
    }

    private class MockHttpURLConnection extends HttpURLConnection {

        boolean connected;
        HashMap<String, String> mockHeaders = new HashMap<String, String>();
        InputStream mockInputStream;

        protected MockHttpURLConnection(URL url) {
            super(url);
        }

        private boolean isConnected() {
            return connected;
        }

        private void setMockInputStream(InputStream inputStream) {
            this.mockInputStream = inputStream;
        }

        public void setHeader(String key, String value) {
            this.mockHeaders.put(key, value);
        }

        @Override
        public void disconnect() {
            this.connected = false;
        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void connect() throws IOException {
            this.connected = true;
        }

        @Override
        public String getHeaderField(String headerKey)  {
            return mockHeaders.get(headerKey);
        }

        @Override
        public InputStream getInputStream() {
            return mockInputStream;
        }

    }

}
