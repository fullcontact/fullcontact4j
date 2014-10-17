package com.fullcontact.api.libs.fullcontact4j;
import org.junit.Assert;
import org.junit.Test;

public class FullContactClientTests {

    @Test
    public void basicConfigurationTest() {
        FullContact client = FullContact.withApiKey("example-key").build();
        Assert.assertTrue(client.httpInterface.apiKey.equals("example-key"));
    }
}
