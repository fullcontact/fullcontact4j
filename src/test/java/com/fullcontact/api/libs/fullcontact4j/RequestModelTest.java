package com.fullcontact.api.libs.fullcontact4j;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestModelTest {

    @Test
    //encode and then decode a card, make sure length is the same
    public void cardReaderImageEncodeTest() throws IOException {
        File origFile = Utils.loadFile("businesscard.jpg");
        String byteString = Utils.encodeToStringAndClose(new FileInputStream(origFile));
        byte[] bytes = Base64.decodeBase64(byteString.getBytes());
        assertEquals(bytes.length, origFile.length());
    }
}
