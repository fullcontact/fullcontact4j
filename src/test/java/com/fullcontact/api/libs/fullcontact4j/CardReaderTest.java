package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.builders.CardReaderUploadRequestBuilder;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.UploadRequestResult;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.UploadResponse;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.ViewRequestEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.ViewRequestsEntity;
import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderCasing;
import com.fullcontact.api.libs.fullcontact4j.enums.ResponseFormat;
import com.fullcontact.api.libs.fullcontact4j.handlers.CardReaderHandler;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mattdelliott
 * Date: 8/7/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class CardReaderTest extends AbstractApiTest {

    public void test_upload_card() throws IOException, FullContactException {
        String json = loadJson("cardshark.upload.response.json");
        UploadResponse entity = new FullContact("fake_api_key").getCardReaderHandler().parseUploadJsonResponse(json);
        assertNotNull(entity);
        assertEquals(202, entity.getStatusCode());
        assertEquals("b23bb2dd-fe7c-4d83-ab8d-f23792b8e4cf", entity.getRequestId());
        assertEquals(true, entity.isQueued());
    }

    public void test_upload_card_webhook_response() throws IOException, FullContactException {
        String json = loadJson("cardshark.upload.webhook.response.json");
        UploadRequestResult requestResult = new FullContact("fake_api_key").getCardReaderHandler().parseUploadWebhookJsonResponse(json);
        assertNotNull(requestResult);
        assertEquals("b23bb2dd-fe7c-4d83-ab8d-f23792b8e4cf", requestResult.getRequestId());
        assertEquals("https://d1h3f0foa0xzdz.cloudfront.net/1/2I63W5XH0JPPYKTRWFGL0P75N9OEK7.vcf", requestResult.getvCardUrl());

        basicContactDataTests(requestResult);

        assertNull(requestResult.getParams());
    }

    public void test_upload_card_webhook_response_with_params() throws IOException, FullContactException {
        String json = loadJson("cardshark.upload.webhook.response.params.json");
        UploadRequestResult requestResult = new FullContact("fake_api_key").getCardReaderHandler().parseUploadWebhookJsonResponse(json);
        assertNotNull(requestResult);
        assertEquals("b23bb2dd-fe7c-4d83-ab8d-f23792b8e4cf", requestResult.getRequestId());
        assertEquals("https://d1h3f0foa0xzdz.cloudfront.net/1/2I63W5XH0JPPYKTRWFGL0P75N9OEK7.vcf", requestResult.getvCardUrl());

        basicContactDataTests(requestResult);

        assertEquals("sales-leads", requestResult.getParams().get("category"));
        assertEquals("12345", requestResult.getParams().get("APNS"));
    }

    private void basicContactDataTests(UploadRequestResult requestResult) {
        assertNotNull(requestResult.getContact().getName());
        assertEquals("Elliott", requestResult.getContact().getName().getFamilyName());
        assertEquals("Matt", requestResult.getContact().getName().getGivenName());
        assertNull(requestResult.getContact().getName().getMiddleName());

        assertEquals(1, requestResult.getContact().getEmails().size());
        assertEquals("matt@fullcontact.com", requestResult.getContact().getEmails().get(0).getValue());
        assertEquals("work", requestResult.getContact().getEmails().get(0).getType());

        assertEquals(1, requestResult.getContact().getOrganizations().size());
        assertEquals("Full contact", requestResult.getContact().getOrganizations().get(0).getName());
        assertEquals("Senior UI/UX Engineer", requestResult.getContact().getOrganizations().get(0).getTitle());
        assertEquals(true, requestResult.getContact().getOrganizations().get(0).isPrimary());

        assertEquals(1, requestResult.getContact().getPhoneNumbers().size());
        assertEquals("+1-720-334-2209", requestResult.getContact().getPhoneNumbers().get(0).getValue());
        assertEquals("work", requestResult.getContact().getPhoneNumbers().get(0).getType());

        assertEquals(1, requestResult.getContact().getPhotos().size());
        assertEquals("https://d1h3f0foa0xzdz.cloudfront.net/1/b23bb2dd-fe7c-4d83-ab8d-f23792b8e4cf-front.png", requestResult.getContact().getPhotos().get(0).getValue());
        assertEquals("BusinessCard", requestResult.getContact().getPhotos().get(0).getType());
        assertFalse(requestResult.getContact().getPhotos().get(0).isPrimary());

        assertEquals(1, requestResult.getContact().getUrls().size());
        assertEquals("www.fullcontact.com", requestResult.getContact().getUrls().get(0).getValue());
        assertEquals("other", requestResult.getContact().getUrls().get(0).getType());
    }

    public void test_view_requests() throws IOException, FullContactException {
        String json = loadJson("cardshark.view.requests.response.json");
        ViewRequestsEntity viewRequestsEntity = new FullContact("fake_api_key").getCardReaderHandler().parseViewRequestsJsonResponse(json);
        testBasicResponseData(viewRequestsEntity);
        assertNull(viewRequestsEntity.getResults().get(0).getParams());
    }

    public void test_view_requests_with_params() throws IOException, FullContactException {
        String json = loadJson("cardshark.view.requests.response.params.json");
        ViewRequestsEntity viewRequestsEntity = new FullContact("fake_api_key").getCardReaderHandler().parseViewRequestsJsonResponse(json);
        UploadRequestResult requestResult = testBasicResponseData(viewRequestsEntity);
        assertEquals("sales-leads", requestResult.getParams().get("category"));
        assertEquals("12345", requestResult.getParams().get("APNS"));
    }

    private UploadRequestResult testBasicResponseData(ViewRequestsEntity viewRequestsEntity) {
        assertNotNull(viewRequestsEntity);
        assertEquals(200, viewRequestsEntity.getStatus());
        assertEquals(1, viewRequestsEntity.getTotalPages());
        assertEquals(0, viewRequestsEntity.getCurrentPage());
        assertEquals(2, viewRequestsEntity.getTotalRecords());
        assertEquals(2, viewRequestsEntity.getCount());
        assertEquals(2, viewRequestsEntity.getResults().size());
        UploadRequestResult requestResult = viewRequestsEntity.getResults().get(0);
        assertNotNull(requestResult.getContact().getName());
        assertEquals("Elliott", requestResult.getContact().getName().getFamilyName());
        assertEquals(1, requestResult.getContact().getEmails().size());
        assertEquals(1, requestResult.getContact().getOrganizations().size());
        assertEquals("Senior UI/UX Engineer", requestResult.getContact().getOrganizations().get(0).getTitle());
        assertEquals(1, requestResult.getContact().getPhoneNumbers().size());
        assertEquals("work", requestResult.getContact().getPhoneNumbers().get(0).getType());
        assertEquals(1, requestResult.getContact().getPhotos().size());
        assertEquals("https://d1h3f0foa0xzdz.cloudfront.net/1/b23bb2dd-fe7c-4d83-ab8d-f23792b8e4cf-front.png", requestResult.getContact().getPhotos().get(0).getValue());
        assertEquals(1, requestResult.getContact().getUrls().size());
        assertEquals("www.fullcontact.com", requestResult.getContact().getUrls().get(0).getValue());
        return requestResult;
    }

    public void test_view_request() throws IOException, FullContactException {
        String json = loadJson("cardshark.view.request.response.json");
        ViewRequestEntity requestResult = new FullContact("fake_api_key").getCardReaderHandler().parseViewRequestJsonResponse(json);
        assertNotNull(requestResult);
        assertEquals("Elliott", requestResult.getContact().getName().getFamilyName());
        assertEquals(1, requestResult.getContact().getEmails().size());
        assertEquals(1, requestResult.getContact().getOrganizations().size());
        assertEquals("Senior UI/UX Engineer", requestResult.getContact().getOrganizations().get(0).getTitle());
        assertEquals(1, requestResult.getContact().getPhoneNumbers().size());
        assertEquals("work", requestResult.getContact().getPhoneNumbers().get(0).getType());
        assertEquals(1, requestResult.getContact().getPhotos().size());
        assertEquals("https://d1h3f0foa0xzdz.cloudfront.net/1/3c1759f1-820f-43ce-8a7a-24be8aa9d045-front.png", requestResult.getContact().getPhotos().get(0).getValue());
        assertEquals(1, requestResult.getContact().getUrls().size());
        assertEquals("www.fullcontact.com", requestResult.getContact().getUrls().get(0).getValue());
        assertEquals("sales-leads", requestResult.getParams().get("category"));
        assertEquals("12345", requestResult.getParams().get("APNS"));
    }

    public void test_parse_iso8601() {
        String json = "{ \"submitted\": \"2013-09-13T19:08:30.000Z\" }";
        CardReaderHandler handler = new CardReaderHandler("tester");
        ViewRequestEntity entity = handler.parseViewRequestJsonResponse(json);
        assert entity.getSubmitted() != null;
    }

    public void test_basic_usage_of_custom_params() {
        CardReaderUploadRequestBuilder builder = new CardReaderUploadRequestBuilder();
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("test","value");
        params.put("name","FullContact");
        params.put("apiKey","notAllowedHere");
        params.put("accessToken","notAllowedHere");
        CardReaderUploadRequestBuilder.CardReaderUploadRequest request =
                builder.setCustomParams(params)
                .setCasing(CardReaderCasing.TitleCase)
                .setFormat(ResponseFormat.JSON)
                .setWebhookUrl("fullcontact.com")
                .build();
        // Assert restrictions enforced
        assertFalse(params.containsKey("accessToken"));
        assertFalse(params.containsKey("apiKey"));
        HashMap<String,String> queryParams = null;
        try {
            queryParams = FullContactHttpRequest.generateQueryParams("testApiKey",request);
        } catch (FullContactException e) {
            fail("Exception occured processing query params");
        }
        assertFalse(queryParams.containsKey("accessToken"));
        assertEquals("testApiKey",queryParams.get("apiKey"));
        assertTrue(queryParams.containsKey("test"));
        assertEquals("value", queryParams.get("test"));
        assertTrue(queryParams.containsKey("name"));
        assertEquals("FullContact", queryParams.get("name"));
        assertEquals(CardReaderCasing.TitleCase.toString().toLowerCase(),
                queryParams.get("casing"));
        assertEquals(ResponseFormat.JSON.toString().toLowerCase(),
                queryParams.get("format"));
    }

}
