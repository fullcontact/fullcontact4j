package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.batch.BatchResponse;
import com.fullcontact.api.libs.fullcontact4j.entity.enhanced.PersonEnhancedEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationEnrichmentEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationNormalizerEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameParserEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameSimilarityEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameStatsEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.person.PersonEntity;

import java.io.IOException;
import java.util.List;

public class BatchTest extends AbstractApiTest {

    public void test_batch_process_parsing() throws IOException, FullContactException {
        String json = loadJson("batch.process.response.json");
        BatchResponse response = new FullContact("fake_api_key").getBatchHandler().parseJsonResponse(json);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        assertEquals(1, response.getResults().size());
        assertNotNull(response.getResults().get("https://api.fullcontact.com/v2/name/normalizer.json?q=dan+lynn"));
    }

    public void test_batch_process_getters() throws IOException, FullContactException {
        String json = loadJson("batch.process.whole.response.json");
        BatchResponse response = new FullContact("fake_api_key").getBatchHandler().parseJsonResponse(json);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode());
        assertEquals(13, response.getResults().size());

        List<NameEntity> nameNormalizerResults = response.getNameNormalizerResults();

        assertEquals("USA", nameNormalizerResults.get(0).getRegion());
        assertEquals("Johnny", nameNormalizerResults.get(0).getNameInfo().getNicknames().get(0));
        assertEquals(2, nameNormalizerResults.get(0).getNameInfo().getSuffixes().size());
        assertEquals(0.774, nameNormalizerResults.get(0).getLikelihood());

        List<NameEntity> nameDeducerResults = response.getNameDeducerResults();

        assertEquals(0.665, nameDeducerResults.get(0).getLikelihood());
        assertEquals(200, nameDeducerResults.get(0).getStatusCode());
        assertEquals("USA", nameDeducerResults.get(0).getRegion());

        List<NameParserEntity> nameParserResults = response.getNameParserResults();

        assertEquals(1.0, nameParserResults.get(0).getLikelihood());
        assertEquals(200, nameParserResults.get(0).getStatusCode());
        assertEquals("USA", nameParserResults.get(0).getRegion());
        assertEquals("John", nameParserResults.get(0).getNameInfo().getGivenName());

        List<NameStatsEntity> nameStatsResults = response.getNameStatsResults();

        assertEquals(0.008, nameStatsResults.get(0).getNameStatsInfo().getFamilyNameStats().getLikelihood());
        assertEquals(200, nameStatsResults.get(0).getStatusCode());
        assertEquals("USA", nameStatsResults.get(0).getRegion());

        List<NameSimilarityEntity> nameSimilarityResults = response.getNameSimilarityResults();

        assertEquals(0.888888888888889, nameSimilarityResults.get(0).getSecondStringData().getJaroWinklerInfo().getSimilarity());
        assertEquals(200, nameSimilarityResults.get(0).getStatusCode());

        List<PersonEntity> personResults = response.getPersonResults();

        assertEquals(0.9, personResults.get(0).getLikelihood());
        assertEquals(200, personResults.get(0).getStatusCode());

        List<PersonEnhancedEntity> enhancedResults = response.getEnhancedDataResults();

        assertEquals("Dan", enhancedResults.get(0).getContactInfo().getGivenName());
        assertEquals(200, enhancedResults.get(0).getStatusCode());

        List<LocationNormalizerEntity> locationNormalizerResults = response.getLocationNormalizerResults();

        assertEquals(1.0, locationNormalizerResults.get(0).getLikelihood());
        assertEquals(200, locationNormalizerResults.get(0).getStatusCode());

        List<LocationEnrichmentEntity> locationEnrichmentResults = response.getLocationEnrichmentResults();

        assertEquals(1, locationEnrichmentResults.size());
        assertEquals(7, locationEnrichmentResults.get(0).getLocations().size());
        assertEquals(600158, locationEnrichmentResults.get(0).getLocations().get(0).getPopulation());
        assertEquals(200, locationEnrichmentResults.get(0).getStatusCode());

        List<com.fullcontact.api.libs.fullcontact4j.entity.cardshark.ViewRequestsEntity> cardSharkRequestsResults = response.getCardSharkRequestsResults();

        assertEquals(1, cardSharkRequestsResults.size());
        assertEquals(2, cardSharkRequestsResults.get(0).getResults().size());

        //TODO: DEA getters -- https://api.fullcontact.com/v2/email/disposable.json?email=joe%2Btag%40sharklasers.com
    }



}
