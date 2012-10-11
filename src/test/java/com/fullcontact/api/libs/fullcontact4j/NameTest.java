package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.name.NameEntity;

import java.io.IOException;

public class NameTest extends AbstractApiTest {

    public void test_name_normalization() throws IOException, FullContactException {
        String json = loadJson("name.normalization.json");
        NameEntity entity = new FullContact("fake_api_key").getNameHandler().parseJsonResponse(json);
        assertNotNull(entity);
        System.out.println("Likelihood: " + entity.getLikelihood());
        System.out.println("Status: " + entity.getStatusCode());
        System.out.println("Region: " + entity.getRegion());
        System.out.println("Given Name: " + entity.getNameInfo().getGivenName());
    }

}
