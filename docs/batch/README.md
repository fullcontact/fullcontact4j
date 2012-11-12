#Fullcontact4j: Batch API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get BatchHandler (handler for Batch API)

        BatchHandler batchHandler = fullContact.getBatchHandler();

## Batch API:

* Process API queries (in batch manner).

        List<String> queries = new ArrayList<String>();
        queries.add("https://api.fullcontact.com/v2/name/normalizer.json?q=dan+lynn");
        queries.add("https://api.fullcontact.com/v2/person.json?email=bart@fullcontact.com");
        BatchResponse response = batchHandler.processApiRequests(queries);

* Getting the batch request status

        System.out.println("Status Code: " + response.getStatusCode());

* Getting the batch results count

        System.out.println("Batch results count: " + response.getResults().size());

* Getting a single result as String

        String nameNormalizerResultString = response.getResults().get("https://api.fullcontact.com/v2/name/normalizer.json?q=dan+lynn")

* Parse the (name normalizer) response manually, we can parse other API responses as well, in the same manner.

        NameEntity nameNormalizerResult = fullContact.getNameHandler().parseNormalizationJsonResponse(nameNormalizerResultString);

* Getting the name normalizer results

        List<NameEntity> nameNormalizerResults = response.getNameNormalizerResults();
        System.out.println("Name Normalizer results count: " + nameNormalizerResults.size());

* Getting the name deducer results

        List<NameEntity> nameDeducerResults = response.getNameDeducerResults();
        System.out.println("Name Deducer results count: " + nameDeducerResults.size());

* Getting the name parser results

        List<NameParserEntity> nameParserResults = response.getNameParserResults();
        System.out.println("Name Parser results count: " + nameParserResults.size());

* Getting the name stats results

        List<NameStatsEntity> nameStatsResults = response.getNameStatsResults();
        System.out.println("Name Stats results count: " + nameStatsResults.size());

* Getting the name similarity results

        List<NameSimilarityEntity> nameSimilarityResults = response.getNameSimilarityResults();
        System.out.println("Name Similarity results count: " + nameSimilarityResults.size());

* Getting the person results

        List<PersonEntity> personResults = response.getPersonResults();
        System.out.println("Person results count: " + personResults.size());

* Getting the person enhanced data results

        List<PersonEnhancedEntity> enhancedResults = response.getEnhancedDataResults();
        System.out.println("Enhanced Data results count: " + enhancedResults.size());

* Getting the location normalizer results

        List<LocationNormalizerEntity> locationNormalizerResults = response.getLocationNormalizerResults();
        System.out.println("Location Normalizer results count: " + enhancedResults.size());

* Getting the location enrichment results

        List<LocationEnrichmentEntity> locationEnrichmentResults = response.getLocationEnrichmentResults();
        System.out.println("Location Enrichment results count: " + enhancedResults.size());

* Getting the cardshark requests results

        List<ViewRequestsEntity> cardSharkRequestsResults = response.getCardSharkRequestsResults();
        System.out.println("CardSharkRequests results count: " + cardSharkRequestsResults.size());


##Other API

* [Person API](/fullcontact/fullcontact4j/tree/refactoring/docs/person/)
* [Person Enhanced Data API](/fullcontact/fullcontact4j/tree/refactoring/docs/enhancedData/)
* [Name API](/fullcontact/fullcontact4j/tree/refactoring/docs/name/)
* [Location API](/fullcontact/fullcontact4j/tree/refactoring/docs/location/)
* [CardShark API](/fullcontact/fullcontact4j/tree/refactoring/docs/cardShark/)
