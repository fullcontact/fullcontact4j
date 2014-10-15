package com.fullcontact.api.libs.fullcontact4j;
import com.squareup.okhttp.OkHttpClient;
import org.junit.Assert;
import org.junit.Test;
import retrofit.client.OkClient;

public class FullContactClientTests {

    @Test
    public void basicConfigurationTest() {
        FullContact client = FullContact.withApiKey("example-key").build();
        Assert.assertTrue(client.httpInterface.authString.equals("example-key"));
    }
}
