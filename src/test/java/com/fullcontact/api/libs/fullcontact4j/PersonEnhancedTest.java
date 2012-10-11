package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.PersonEnhancedEntity;
import java.io.*;

public class PersonEnhancedTest extends AbstractApiTest {

    public void test_parse_person_enhanced_data() throws IOException, FullContactException {
        String json = loadJson("enhanced.dan@fullcontact.com.json");
        PersonEnhancedEntity entity = new FullContact("fake_api_key").getPersonEnhancedHandler().parseJsonResponse(json);
        assertNotNull(entity);
        assertEquals("Dan Lynn", entity.getContactInfo().getFullName());
        assertEquals("Dan", entity.getContactInfo().getGivenName());
        assertEquals("Lynn", entity.getContactInfo().getFamilyName());
        assertEquals(2, entity.getContactInfo().getPhoneNumbers().size());
        assertEquals(38, entity.getContactInfo().getPhoneNumbers().get(0).getConfidence());
        assertEquals(1, entity.getContactInfo().getStreetAddresses().size());
        assertEquals(1, entity.getContactInfo().getEmailAddress().size());
        assertEquals("dan@fullcontact.com", entity.getContactInfo().getEmailAddress().get(0));
        assertEquals("80202-5995", entity.getDemographics().getPostalCode());
        assertEquals(1, entity.getOrganizations().size());
        assertEquals("FullContact", entity.getOrganizations().get(0).getName());
        assertEquals(true, entity.getOrganizations().get(0).isPrimary());
    }

}
