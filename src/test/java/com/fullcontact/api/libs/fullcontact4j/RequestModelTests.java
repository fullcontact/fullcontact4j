package com.fullcontact.api.libs.fullcontact4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.http.cardreader.CardReaderUploadRequest;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestModelTests {

    @Test
    public void cardReaderBodyJsonSerializationTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CardReaderUploadRequest.RequestBodyJson body = new CardReaderUploadRequest.RequestBodyJson();
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
