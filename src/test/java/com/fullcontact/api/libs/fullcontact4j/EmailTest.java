package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.email.DisposableResponse;

import java.io.IOException;

public class EmailTest extends AbstractApiTest {

    public void test_getDisposableInfo() throws IOException, FullContactException {
        String json = loadJson("email.dea.response.json");
        DisposableResponse response = new FullContact("fake_api_key").getEmailHandler().parseJsonResponse(json);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        assertEquals("true", response.getDisposableEmailDomainStatus());
        assertEquals("unknown", response.getUsernameSubAddressingStatus());
        assertEquals("Email username contains sub address characters but is unknown. Email's domain is likely assocated with dispoable email addresses.", response.getMessage());
    }

}
