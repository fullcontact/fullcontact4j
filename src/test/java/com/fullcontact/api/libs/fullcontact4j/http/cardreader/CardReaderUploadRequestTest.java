package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;

public class CardReaderUploadRequestTest {

    @Test(expected = IllegalArgumentException.class)
    public void testMix() {
        new CardReaderUploadRequest.Builder()
            .accessToken("a")
            .cardBack(new File("irrelevant"))
            .cardFront(new ByteArrayInputStream(new byte[]{1,2,3}))
            .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMix2() {
        new CardReaderUploadRequest.Builder()
            .accessToken("a")
            .cardFront(new File("irrelevant"))
            .cardBack(new ByteArrayInputStream(new byte[]{1,2,3}))
            .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingMime() {
        new CardReaderUploadRequest.Builder()
            .cardFront(new File("irrelevant"))
            .cardBack(new File("hello"))
            .build();
    }

    @Test
    public void happy() {
        new CardReaderUploadRequest.Builder()
            .cardFront(new File("f"))
            .cardBack(new File("b"))
            .mimeType("something")
            .webhookUrl("http://abc.com/webhook")
            .build();
    }

}