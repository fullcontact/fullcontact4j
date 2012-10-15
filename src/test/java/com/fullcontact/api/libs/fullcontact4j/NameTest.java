package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.name.NameEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameInfo;

import java.io.IOException;

public class NameTest extends AbstractApiTest {

    public void test_name_normalization() throws IOException, FullContactException {
        String json = loadJson("name.normalization.json");
        NameEntity entity = new FullContact("fake_api_key").getNameHandler().parseJsonResponse(json);
        assertNotNull(entity);
        assertEquals(200, entity.getStatusCode());
        assertEquals(0.774, entity.getLikelihood());
        assertEquals("beacbb9f-fb28-4113-905b-461656229e51", entity.getRequestId());
        assertEquals("USA", entity.getRegion());

        assertEquals("John Michael Smith", entity.getNameInfo().getFullName());
        assertEquals("John", entity.getNameInfo().getGivenName());
        assertEquals("Smith", entity.getNameInfo().getFamilyName());
        assertEquals(1, entity.getNameInfo().getMiddleNames().size());
        assertEquals("Michael", entity.getNameInfo().getMiddleNames().get(0));
        assertEquals(1, entity.getNameInfo().getPrefixes().size());
        assertEquals("Mr.", entity.getNameInfo().getPrefixes().get(0));
        assertEquals(2, entity.getNameInfo().getSuffixes().size());
        assertEquals("MBA", entity.getNameInfo().getSuffixes().get(1));
        assertEquals(1, entity.getNameInfo().getNicknames().size());
        assertEquals("Johnny", entity.getNameInfo().getNicknames().get(0));

        System.out.println("Status: " + entity.getStatusCode());
        System.out.println("Likelihood: " + entity.getLikelihood());
        System.out.println("RequestId: " + entity.getRequestId());
        System.out.println("Region: " + entity.getRegion());
        NameInfo nameInfo = entity.getNameInfo();
        System.out.println("Full Name: " + nameInfo.getFullName());
        System.out.println("First Name: " + nameInfo.getGivenName());
        System.out.println("Last Name: " + nameInfo.getFamilyName());
        System.out.println("Middle Names: " + nameInfo.getMiddleNames());
        System.out.println("Prefixes: " + nameInfo.getPrefixes());
        System.out.println("Suffixes: " + nameInfo.getSuffixes());
        System.out.println("Nicknames: " + nameInfo.getNicknames());
    }

}
