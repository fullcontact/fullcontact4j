package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class CardReaderUploadRequestTest {
    @Test(expected = IllegalArgumentException.class)
    public void missingMime() throws IOException {
        new CardReaderUploadRequest.Builder()
            .cardFront(new ByteArrayInputStream(new byte[0]))
            .build();
    }

    @Test
    public void happy() throws IOException {
        new CardReaderUploadRequest.Builder()
            .cardFront(new ByteArrayInputStream(new byte[0]))
            .cardBack(new ByteArrayInputStream(new byte[0]))
            .mimeType("something")
            .webhookUrl("http://abc.com/webhook")
            .build();
    }

}
