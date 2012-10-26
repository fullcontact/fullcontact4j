package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationInfo;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationNormalizerEntity;

import java.io.IOException;

public class LocationTest extends AbstractApiTest {

    public void test_location_normalization() throws IOException, FullContactException {
        String json = loadJson("location.normalization.json");
        LocationNormalizerEntity entity = new FullContact("fake_api_key").getLocationHandler().parseJsonResponse(json);
        assertNotNull(entity);
        assertEquals(200, entity.getStatusCode());
        assertEquals(0.875, entity.getLikelihood());
        assertEquals("c1fd1383-ea3a-4249-be72-3c04b6cfe2e6", entity.getRequestId());
        assertEquals("Denver", entity.getLocationInfo().getCity());
        assertEquals("Colorado", entity.getLocationInfo().getState().getName());
        assertEquals("CO", entity.getLocationInfo().getState().getCode());
        assertEquals("United States", entity.getLocationInfo().getCountry().getName());
        assertEquals("US", entity.getLocationInfo().getCountry().getCode());
        assertEquals("North America", entity.getLocationInfo().getContinent());
        assertEquals("Denver, Colorado, United States", entity.getLocationInfo().getNormalizedLocation());

        System.out.println("Status Code: " + entity.getStatusCode());
        System.out.println("Likelihood: " + entity.getLikelihood());
        System.out.println("Request Id: " + entity.getRequestId());
        LocationInfo locationInfo = entity.getLocationInfo();
        System.out.println("City: " + locationInfo.getCity());
        System.out.println("County: " + locationInfo.getCounty());
        System.out.println("State Name: " + locationInfo.getState().getName());
        System.out.println("State Code: " + locationInfo.getState().getCode());
        System.out.println("Country Name: " + locationInfo.getCountry().getName());
        System.out.println("Continent: " + locationInfo.getContinent());
        System.out.println("Normalized Location: " + locationInfo.getNormalizedLocation());
    }



}
