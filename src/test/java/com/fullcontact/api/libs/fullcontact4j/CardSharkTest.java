package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.cardshark.UploadResponse;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationEnrichmentEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationNormalizerEntity;
import com.fullcontact.api.libs.fullcontact4j.handlers.CardSharkHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CardSharkTest extends AbstractApiTest {

    public void test_upload_card() throws IOException, FullContactException {
        //String json = loadJson("cardshark.upload.post.request.json");
        //LocationNormalizerEntity entity = new FullContact("fake_api_key").getLocationHandler().parseJsonResponse(json);
        //assertNotNull(entity);
    }

    public void test_real() throws IOException, FullContactException {
        CardSharkHandler cardSharkHandler = new FullContact("put_your_key").getCardSharkHandler();
        InputStream fis = new FileInputStream("/tmp/card.jpeg");
        UploadResponse response = cardSharkHandler.uploadCardImage(fis, "http://requestb.in/14qwmle1");
    }


}
