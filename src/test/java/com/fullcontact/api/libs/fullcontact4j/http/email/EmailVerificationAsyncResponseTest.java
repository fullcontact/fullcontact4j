package com.fullcontact.api.libs.fullcontact4j.http.email;

import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmailVerificationAsyncResponseTest {

    private final String json = "{\"batchId\":\"65a1bd21-e4cf-4c43-adf6-7279ae567ad3-vs\",\"webhookUrl\":null," +
        "\"response\":{\"status\":200,\"emails\":{\"paris2@fullcontact.com\":{\"message\":\"Email address does not " +
        "exist\",\"address\":\"paris2@fullcontact.com\",\"username\":\"paris2\",\"domain\":\"fullcontact.com\"," +
        "\"corrected\":false,\"attributes\":{\"validSyntax\":true,\"deliverable\":false,\"catchall\":false," +
        "\"risky\":false,\"disposable\":false},\"sendSafely\":false},\"pmit20934203a@gmail.com\":{\"message\":\"Email" +
        " address does not exist\",\"address\":\"pmit20934203a@gmail.com\",\"username\":\"pmit20934203a\"," +
        "\"domain\":\"gmail.com\",\"corrected\":false,\"attributes\":{\"validSyntax\":true,\"deliverable\":false," +
        "\"catchall\":false,\"risky\":false,\"disposable\":false},\"sendSafely\":false}}},\"completed\":true}";
    @Test
    public void fromJsonTest() throws FullContactException {
        EmailVerificationAsyncResponse r = EmailVerificationAsyncResponse.fromJson(json);

        assertEquals(2, r.getResponse().getEmails().size());
        assertEquals(0, r.getResponse().getUnknownEmails().size());

        EmailVerificationData data = r.getResponse().getEmails().values().iterator().next();

        assertEquals("paris2@fullcontact.com", data.getAddress());
        assertEquals(false, data.isSendSafely());
    }
}