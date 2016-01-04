package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Skiggz
 * @since 12/30/15.
 */
public class CardReaderViewRequestTest {

    @Test
    public void testNotStrippingToken() {
        CardReaderViewRequest request = new CardReaderViewRequest("foobar", "id", new HashMap<String, String>());
        assertEquals("foobar", request.getAccessToken());
        assertEquals("id", request.getId());
    }

}