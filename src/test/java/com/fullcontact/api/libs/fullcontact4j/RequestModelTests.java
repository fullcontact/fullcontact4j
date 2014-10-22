package com.fullcontact.api.libs.fullcontact4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.request.UploadCardRequest;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestModelTests {

    @Test
    public void cardReaderBodyJsonSerializationTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UploadCardRequest.RequestBodyJson body = new UploadCardRequest.RequestBodyJson();
        body.setFront("test");
        body.setBack("back test");
        assertTrue(mapper.writeValueAsString(body).equals("{\"front\":\"test\",\"back\":\"back test\"}"));
    }

    @Test
    public void cardReaderImageEncodeTest() throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        File origFile = Utils.loadFile("businesscard.jpg");
        String byteString = Utils.encodeToStringAndClose(new FileInputStream(origFile));
        byte[] bytes = decoder.decodeBuffer(byteString);
        assertEquals(bytes.length, origFile.length());
    }
}
