package com.fullcontact.api.libs.fullcontact4j;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderQuality;
import com.fullcontact.api.libs.fullcontact4j.response.CardReaderFullResponse;
import com.fullcontact.api.libs.fullcontact4j.response.CardReaderViewAllResponse;
import com.fullcontact.api.libs.fullcontact4j.response.PersonResponse;
import com.fullcontact.api.libs.fullcontact4j.response.UploadCardConfirmResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseModelTests {

    public static FullContact client = FullContact.withApiKey("bad-key").build();
    public static ObjectMapper mapper = new ObjectMapper();

    @Before
    public void before() {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
    }



    @Test
    public void personDeserializationTest() throws IOException {
        PersonResponse r = mapper.readValue(Utils.loadFile("example-person-response.json"), PersonResponse.class);
        assertTrue(r.getDemographics().getGender().equals("Male"));
    }

    @Test
    public void cardReaderAcceptDeserializationTest() throws Exception {
        UploadCardConfirmResponse conf = mapper.readValue(Utils.loadFile("example-upload-confirm-response.json"), UploadCardConfirmResponse.class);
        assertEquals(conf.getEstimatedWaitTimeMinutes(), 33);
        assertEquals(conf.getStatus(), "202");
    }
    @Test
    public void cardReaderViewDeserializationTest() throws Exception {
        CardReaderFullResponse conf = mapper.readValue(Utils.loadFile("example-card-full-response.json"), CardReaderFullResponse.class);
        assertEquals(conf.getQuality(), CardReaderQuality.MEDIUM);
        assertEquals(conf.getContact().getOrganizations().size(), 1);

    }
    @Test
    public void cardReaderViewAllDeserializationTest() throws Exception {
        CardReaderViewAllResponse conf = mapper.readValue(Utils.loadFile("example-card-view-all-response.json"), CardReaderViewAllResponse.class);
        assertEquals(conf.getTotalPages(), 11);
        assertEquals(conf.getResults().size(), 2);
    }

    @Test
    public void personQueue202Test() throws IOException {
        PersonResponse r = mapper.readValue(Utils.loadFile("example-person-queue-response.json"), PersonResponse.class);
        assertEquals(202, r.getStatus());
        assertTrue(r.getMessage().contains("Queued for search"));
    }

    @Test
    public void person404Test() throws IOException {
        PersonResponse r = mapper.readValue(Utils.loadFile("example-person-404-response.json"), PersonResponse.class);
        assertEquals(404, r.getStatus());
        assertTrue(r.getMessage().contains("No results found"));
    }

    @Test
    //does a client successfully reroute a 404 person response into an empty payload instead of an exception?
    public void person404routingTest() throws IOException {
        //TODO
    }
}
