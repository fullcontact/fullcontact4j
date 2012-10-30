package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationEnrichmentEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationNormalizerEntity;

import java.io.IOException;

public class CardSharkTest extends AbstractApiTest {

    public void test_upload_card() throws IOException, FullContactException {
        String json = loadJson("cardshark.upload.post.request.json");
        //LocationNormalizerEntity entity = new FullContact("fake_api_key").getLocationHandler().parseJsonResponse(json);
        //assertNotNull(entity);
    }


}
