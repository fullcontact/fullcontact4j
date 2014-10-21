package com.fullcontact.api.libs.fullcontact4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.request.UploadCardRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestModelTests {

    @Test
    public void cardReaderBodyJsonSerializationTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UploadCardRequest.RequestBodyJson body = new UploadCardRequest.RequestBodyJson();
        body.setFront("test");
        body.setBack("back test");
        assertTrue(mapper.writeValueAsString(body).equals("{\"front\":\"test\",\"back\":\"back test\"}"));
    }
}
