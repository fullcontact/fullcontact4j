package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.name.NameEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameInfo;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameParserEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameParserInfo;

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

    public void test_name_deducer() throws IOException, FullContactException {
        String json = loadJson("name.deducer.json");
        NameEntity entity = new FullContact("fake_api_key").getNameHandler().parseDeducerJsonResponse(json);
        assertNotNull(entity);
        assertEquals(200, entity.getStatusCode());
        assertEquals(0.665, entity.getLikelihood());
        assertEquals("56ad905a-de64-4a28-9f8d-07c36b80aab0", entity.getRequestId());
        assertEquals("USA", entity.getRegion());
        assertEquals("John D. Smith", entity.getNameInfo().getFullName());
        assertEquals("John", entity.getNameInfo().getGivenName());
        assertEquals("Smith", entity.getNameInfo().getFamilyName());
        assertEquals(1, entity.getNameInfo().getMiddleNames().size());
        assertEquals("D.", entity.getNameInfo().getMiddleNames().get(0));

        System.out.println("Status: " + entity.getStatusCode());
        System.out.println("Likelihood: " + entity.getLikelihood());
        System.out.println("RequestId: " + entity.getRequestId());
        System.out.println("Region: " + entity.getRegion());
        NameInfo nameInfo = entity.getNameInfo();
        System.out.println("Full Name: " + nameInfo.getFullName());
        System.out.println("First Name: " + nameInfo.getGivenName());
        System.out.println("Last Name: " + nameInfo.getFamilyName());
        System.out.println("Middle Names: " + nameInfo.getMiddleNames());
    }

    public void test_name_parser() throws IOException, FullContactException {
        String json = loadJson("name.parser.json");
        NameParserEntity entity = new FullContact("fake_api_key").getNameHandler().parseParserJsonResponse(json);
        assertNotNull(entity);
        assertEquals(200, entity.getStatusCode());
        assertEquals(1.0, entity.getLikelihood());
        assertEquals("80a4772d-e970-44af-a695-31054a8f5d45", entity.getRequestId());
        assertEquals("USA", entity.getRegion());
        assertEquals("John Smith", entity.getAmbiguousName());
        assertEquals("John", entity.getNameInfo().getGivenName());
        assertEquals("Smith", entity.getNameInfo().getFamilyName());

        System.out.println("Status: " + entity.getStatusCode());
        System.out.println("Likelihood: " + entity.getLikelihood());
        System.out.println("RequestId: " + entity.getRequestId());
        System.out.println("Region: " + entity.getRegion());
        System.out.println("AmbiguousName: " + entity.getAmbiguousName());
        NameParserInfo nameInfo = entity.getNameInfo();
        System.out.println("First Name: " + nameInfo.getGivenName());
        System.out.println("Last Name: " + nameInfo.getFamilyName());
    }

}
