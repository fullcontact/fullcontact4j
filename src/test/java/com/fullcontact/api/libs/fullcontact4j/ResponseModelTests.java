package com.fullcontact.api.libs.fullcontact4j;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.response.PersonResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ResponseModelTests {

    public static FullContact client = FullContact.withApiKey("bad-key").build();
    public static ObjectMapper mapper = new ObjectMapper();

    @Before
    public void before() {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    @Test
    public void personDeserializationTest() throws IOException {
        PersonResponse r = mapper.readValue(Utils.loadFile("example-person-response.json"), PersonResponse.class);
        assertTrue(r.getDemographics().getGender().equals("Male"));
        assertTrue("Status Code", r.getStatus() == 200);
    }
}
