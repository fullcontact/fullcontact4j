package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.batch.BatchResponse;

import java.io.IOException;

public class BatchTest extends AbstractApiTest {

    public void test_something() throws IOException, FullContactException {
        String json = loadJson("batch.process.response.json");
        BatchResponse response = new FullContact("fake_api_key").getBatchHandler().parseJsonResponse(json);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        assertEquals(1, response.getResults().size());
        assertNotNull(response.getResults().get("https://api.fullcontact.com/v2/name/normalizer.json?q=dan+lynn"));

        System.out.println("Status Code: " + response.getStatusCode());
    }

}
