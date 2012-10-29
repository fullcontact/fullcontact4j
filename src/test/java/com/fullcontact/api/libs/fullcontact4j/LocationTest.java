package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationEnrichmentEntity;
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
    }

    public void test_location_enrichment() throws IOException, FullContactException {
        String json = loadJson("location.enrichment.json");
        LocationEnrichmentEntity entity = new FullContact("fake_api_key").getLocationHandler().parseEnrichmentJsonResponse(json);
        assertNotNull(entity);
        assertEquals(200, entity.getStatusCode());
        assertEquals("Denver", entity.getLocations().get(0).getCity());
        assertEquals("Denver", entity.getLocations().get(0).getCounty());
        assertEquals("Colorado", entity.getLocations().get(0).getState().getName());
        assertEquals("CO", entity.getLocations().get(0).getState().getCode());
        assertEquals("United States", entity.getLocations().get(0).getCountry().getName());
        assertEquals("US", entity.getLocations().get(0).getCountry().getCode());
        assertEquals("North America", entity.getLocations().get(0).getContinent());
        assertEquals("Denver, Colorado, United States", entity.getLocations().get(0).getNormalizedLocation());
        assertEquals(600158, entity.getLocations().get(0).getPopulation());
    }

}
