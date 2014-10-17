package com.fullcontact.api.libs.fullcontact4j;
import com.fullcontact.api.libs.fullcontact4j.request.PersonRequest;
import com.fullcontact.api.libs.fullcontact4j.response.PersonResponse;
import org.junit.Assert;
import org.junit.Test;

public class FullContactClientTests {

    @Test
    public void basicConfigurationTest() {
        FullContact client = FullContact.withApiKey("example-key").build();
        Assert.assertTrue(client.httpInterface.apiKey.equals("example-key"));
    }

    @Test
    public void justAPersonalTest() throws Exception {
        FullContact client = FullContact.withApiKey("3a5316706b189050").build();
        PersonRequest pr = client.buildPersonRequest().email("bart@fullcontact.com").build();
        try {
            PersonResponse res = client.sendRequest(pr);
        } catch(FullContactException e) {
            e.printStackTrace();
        }
    }
}
