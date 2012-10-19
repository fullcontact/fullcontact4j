package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.name.*;
import com.fullcontact.api.libs.fullcontact4j.entity.name.stats.*;

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

    public void test_name_similarity() throws IOException, FullContactException {
        String json = loadJson("name.similarity.json");
        NameSimilarityEntity entity = new FullContact("fake_api_key").getNameHandler().parseSimilarityJsonResponse(json);
        assertNotNull(entity);
        assertEquals(200, entity.getStatusCode());
        assertEquals(0.8888889, entity.getSimMetricsData().getJaroWinklerInfo().getSimilarity());
        assertEquals(0.44444442, entity.getSimMetricsData().getLevenshteinInfo().getSimilarity());
        assertEquals(0.888888888888889, entity.getSecondStringData().getJaroWinklerInfo().getSimilarity());
        assertEquals(0.4444444444444444, entity.getSecondStringData().getLevenshteinInfo().getSimilarity());
        assertEquals(0.888888888888889, entity.getSecondStringData().getLevel2jaroWinklerInfo().getSimilarity());
        assertEquals(0.4444444444444444, entity.getSecondStringData().getLevel2levenshteinInfo().getSimilarity());
        assertEquals(0.5454545455, entity.getBiagramData().getDiceInfo().getSimilarity());
        assertEquals("24 ms", entity.getBiagramData().getDiceInfo().getTimeTaken());

        System.out.println("Status: " + entity.getStatusCode());
        System.out.println("SimMetrics JaroWinkler Similarity: " + entity.getSimMetricsData().getJaroWinklerInfo().getSimilarity());
        System.out.println("SimMetrics Levenshtein Similarity: " + entity.getSimMetricsData().getLevenshteinInfo().getSimilarity());
        System.out.println("SecondString JaroWinkler Similarity: " + entity.getSecondStringData().getJaroWinklerInfo().getSimilarity());
        System.out.println("SecondString Levenshtein Similarity: " + entity.getSecondStringData().getLevenshteinInfo().getSimilarity());
        System.out.println("SecondString Level2 JaroWinkler Similarity: " + entity.getSecondStringData().getLevel2jaroWinklerInfo().getSimilarity());
        System.out.println("SecondString Level2 Levenshtein Similarity: " + entity.getSecondStringData().getLevel2levenshteinInfo().getSimilarity());
        System.out.println("Biagram Dice Similarity: " + entity.getBiagramData().getDiceInfo().getSimilarity());
    }

    public void test_name_stats_name() throws IOException, FullContactException {
        String json = loadJson("name.stats.name.json");
        NameStatsEntity entity = new FullContact("fake_api_key").getNameHandler().parseStatsJsonResponse(json);
        assertNotNull(entity);
        assertEquals(200, entity.getStatusCode());
        assertEquals("e8beadff-10b5-46f6-9b99-2124bbf0a408", entity.getRequestId());
        assertEquals("USA", entity.getRegion());
        assertNotNull(entity.getNameStatsInfo());
        assertEquals("John", entity.getNameStatsInfo().getValue());
        assertEquals(3499150, entity.getNameStatsInfo().getGivenNameStats().getCount());
        assertEquals(3, entity.getNameStatsInfo().getGivenNameStats().getRank());
        assertEquals(0.992, entity.getNameStatsInfo().getGivenNameStats().getLikelihood());
        assertNull(entity.getNameStatsInfo().getGivenNameStats().getFrequencyRatio());

        // given name female stats
        GenderStats femaleNameStats = entity.getNameStatsInfo().getGivenNameStats().getFemaleStats();
        assertNotNull(femaleNameStats);
        assertEquals("0.00011386", femaleNameStats.getFrequencyRatio());
        assertEquals(15015, femaleNameStats.getCount());
        assertEquals(0.004, femaleNameStats.getLikelihood());
        assertEquals(928, femaleNameStats.getRank());

        // given name male age stats
        AgeDensityCurveStats maleAgeNameStats = entity.getNameStatsInfo()
                .getGivenNameStats().getMaleStats().getAgeStats().getDensityCurve();

        assertNotNull(maleAgeNameStats);
        assertEquals(51.2, maleAgeNameStats.getMeanAge());
        assertEquals(77174, maleAgeNameStats.getMode().getCount());
        assertEquals(47, maleAgeNameStats.getMode().getModeAge().get(0).intValue());
        assertEquals(35.3, maleAgeNameStats.getQuartiles().getQuartile1());

        System.out.println("Request Status: " + entity.getStatusCode());
        System.out.println("Request Id: " + entity.getRequestId());
        System.out.println("Region: " + entity.getRegion());

        // Name Stats info
        NameStatsInfo statsInfo = entity.getNameStatsInfo();
        System.out.println("Name value: " + statsInfo.getValue());

        //Name Stats - basic info (common as Given name and family name)
        BasicNameStats familyNameStats = statsInfo.getFamilyNameStats();
        System.out.println("Name Count (familyName wise): " + familyNameStats.getCount());
        System.out.println("Name Rank  (familyName wise): " + familyNameStats.getRank());
        System.out.println("Name Likelihood (familyName wise): " + familyNameStats.getLikelihood());
        System.out.println("Name frequency ratio (familyName wise): " + familyNameStats.getFrequencyRatio());

        // Name Stats on Gender basis
        GenderStats maleNameStats = statsInfo.getGivenNameStats().getMaleStats();
        System.out.println("Name Count (as male): " + maleNameStats.getCount());
        System.out.println("Name Rank  (as male): " + maleNameStats.getRank());
        System.out.println("Name Likelihood (as male): " + maleNameStats.getLikelihood());
        System.out.println("Name frequency ratio (as male): " + maleNameStats.getFrequencyRatio());

        // Name Stats on the basis of Age Density Curve
        AgeDensityCurveStats femaleAgeNameStats = statsInfo.getGivenNameStats().getFemaleStats().getAgeStats().getDensityCurve();
        System.out.println("Mean age: " + femaleAgeNameStats.getMeanAge());
        System.out.println("Quartile 1: " + femaleAgeNameStats.getQuartiles().getQuartile1());
        System.out.println("Age mode count: " + femaleAgeNameStats.getMode().getCount());
        System.out.println("Mode age: " + femaleAgeNameStats.getMode().getModeAge());
    }

}
