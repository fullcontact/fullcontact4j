package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.FullContactEntity;
import junit.framework.TestCase;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: danlynn
 * Date: 2/7/12
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class FullContactTest extends TestCase {
    public static String newline = System.getProperty("line.separator");

    public void test_parse_person_json_response() throws IOException {
        String json = loadJson("lorangb@gmail.com.json");

        FullContactEntity entity = new FullContact("fake_api_key").parsePersonJsonResponse(json);

        assertEquals("Lorang", entity.getContactInfo().getFamilyName());
        assertEquals("Bart", entity.getContactInfo().getGivenName());
        assertEquals("Bart Lorang", entity.getContactInfo().getFullName());
        assertEquals(1, entity.getContactInfo().getWebsites().size());
        assertEquals("http://fullcontact.com", entity.getContactInfo().getWebsites().get(0).getUrl());

        assertNotNull(entity.getDemographics());
        assertEquals("Male", entity.getDemographics().getGender());
        assertEquals("32", entity.getDemographics().getAge());
        assertEquals("25-34", entity.getDemographics().getAgeRange());
        assertEquals("Boulder, Colorado", entity.getDemographics().getLocationGeneral());

        assertNotNull(entity.getOrganizations());
        assertEquals(2, entity.getOrganizations().size());
        assertEquals("FullContact", entity.getOrganizations().get(0).getOrganizationName());

        assertNotNull(entity.getDigitalFootprint());
        assertEquals(5, entity.getDigitalFootprint().getTopics().size());
        assertEquals(4, entity.getDigitalFootprint().getScores().size());

        assertNotNull(entity.getPhotos());
        assertEquals(7, entity.getPhotos().size());
        assertEquals("http://a0.twimg.com/profile_images/1364842224/Bart_Profile_1_normal.jpg", entity.getPhotos().get(0).getPhotoUrl());
        assertEquals("twitter", entity.getPhotos().get(0).getPhotoTypeId());

        assertNotNull(entity.getSocialProfiles());
        assertEquals(14, entity.getSocialProfiles().getAllSocialProfiles().size());
        assertEquals(631, entity.getSocialProfiles().getTwitter().getFollowers());
        assertEquals(485, entity.getSocialProfiles().getTwitter().getFollowing());
        assertEquals("http://www.twitter.com/lorangb", entity.getSocialProfiles().getTwitter().getProfileUrl());
        assertEquals("http://twitter.com/statuses/user_timeline/lorangb.rss", entity.getSocialProfiles().getTwitter().getRss());

        assertEquals("http://api.flickr.com/services/feeds/photos_public.gne?id=39267654@N00", entity.getSocialProfiles().getFlickr().getRss());
        assertEquals("Co-Founder & CEO at FullContact", entity.getSocialProfiles().getLinkedIn().getBio());
        assertEquals("lorangb", entity.getSocialProfiles().getYouTube().getProfileUsername());
        assertEquals("114426306375480734745", entity.getSocialProfiles().getPicasa().getProfileId());

    }

    private String loadJson(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/" + fileName))));

        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append(newline);
        }

        return sb.toString();
    }
}
