#Fullcontact4j: Name API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get NameHandler (handler for Name API)

        NameHandler nameHandler = fullContact.getNameHandler();


## Name Normalization:

* Normalize a name string

        NameEntity entity = nameHandler.getNameNormalizationInfo("mr%20john%20(johnny)%20michael%20smith%20jr%20mba");

## Name Deducer:

* Deduce name from email

        NameEntity entity = nameHandler.getNameDeducerInfoByEmail("johndsmith79@business.com");

* Deduce name from username

        NameEntity entity = nameHandler.getNameDeducerInfoByUsername("johndsmith79");

###NameEntity information
* Status Code of the Request

		System.out.println("Status Code : " + entity.getStatusCode());

* Getting the likelihood

		System.out.println("Likelihood : " + entity.getLikelihood());

* Getting the request id

        System.out.println("RequestId: " + entity.getRequestId());

* Getting the region

        System.out.println("Region: " + entity.getRegion());

* Getting the (normalized) name information

        NameInfo nameInfo = entity.getNameInfo();

* Getting the full name

        System.out.println("Full Name: " + nameInfo.getFullName());

* Getting the first name

        System.out.println("First Name: " + nameInfo.getGivenName());

* Getting the last name

        System.out.println("Last Name: " + nameInfo.getFamilyName());

* Getting the middle name

        System.out.println("Middle Names: " + nameInfo.getMiddleNames());

* Getting the name prefixes

        System.out.println("Prefixes: " + nameInfo.getPrefixes());

* Getting the name suffixes

        System.out.println("Suffixes: " + nameInfo.getSuffixes());

* Getting the nick-name

        System.out.println("Nicknames: " + nameInfo.getNicknames());


##Name Similarity:

* Check similarity between two names

        NameSimilarityEntity entity = nameHandler.getNameSimilarityInfo("john", "johnathan");

* Getting the request status

        System.out.println("Status: " + entity.getStatusCode());

* Getting the SimMetrics similarity info

        SimilarityData similarityData = entity.getSimMetricsData();

* Getting the SimMetrics JaroWinkler Similarity

        System.out.println("SimMetrics JaroWinkler Similarity: " + similarityData.getJaroWinklerInfo().getSimilarity());

* Getting the SimMetrics Levenshtein Similarity

        System.out.println("SimMetrics Levenshtein Similarity: " + similarityData.getLevenshteinInfo().getSimilarity());

* Getting the SecondString info

        SimilarityData similarityData = entity.getSecondStringData();

* Getting the SecondString JaroWinkler Similarity

        System.out.println("SecondString JaroWinkler Similarity: " + similarityData.getJaroWinklerInfo().getSimilarity());

* Getting the SecondString Levenshtein Similarity

        System.out.println("SecondString Levenshtein Similarity: " + similarityData.getLevenshteinInfo().getSimilarity());

* Getting the SecondString Level2 JaroWinkler Similarity

        System.out.println("SecondString Level2 JaroWinkler Similarity: " + similarityData.getLevel2jaroWinklerInfo().getSimilarity());

* Getting the SecondString Level2 Levenshtein Similarity

        System.out.println("SecondString Level2 Levenshtein Similarity: " + similarityData.getLevel2levenshteinInfo().getSimilarity());

* Getting the BiagramData Dice Similarity

        System.out.println("Biagram Dice Similarity: " + entity.getBiagramData().getDiceInfo().getSimilarity());


##Name Stats:

* Get stats for a name

        NameStatsEntity entity = nameHandler.getNameStatsByName("john");

* Get stats for a given name

        NameStatsEntity entity = nameHandler.getNameStatsByGivenName("john");

* Get stats for a family name

        NameStatsEntity entity = nameHandler.getNameStatsByFamilyName("smith");

* Get stats for a given name and family name

        NameStatsEntity entity = nameHandler.getNameStatsByGivenNameAndFamilyName("john", "smith");

###NameStatsEntity information

* Getting the request status

		System.out.println("Status Code : " + entity.getStatusCode());

* Getting the request id

        System.out.println("Request Id: " + entity.getRequestId());

* Getting the region

        System.out.println("Region: " + entity.getRegion());

* Getting the name stats info

        NameStatsInfo statsInfo = entity.getNameStatsInfo();

* Getting the familyName (or givenName) stats

        BasicNameStats familyNameStats = statsInfo.getFamilyNameStats();

* Getting the familyName count

        System.out.println("Name Count (familyName wise): " + familyNameStats.getCount());

* Getting the familyName rank

        System.out.println("Name Rank  (familyName wise): " + familyNameStats.getRank());

* Getting the familyName likelihood

        System.out.println("Name Likelihood (familyName wise): " + familyNameStats.getLikelihood());

* Getting the familyName frequency ratio

        System.out.println("Name frequency ratio (familyName wise): " + familyNameStats.getFrequencyRatio());

* Getting the gender (male/female) stats

        GenderStats maleNameStats = statsInfo.getGivenNameStats().getMaleStats();

* Getting the name count (as male)

        System.out.println("Name Count (as male): " + maleNameStats.getCount());

* Getting the name rank (as male)

        System.out.println("Name Rank  (as male): " + maleNameStats.getRank());

* Getting the name likelihood (as male)

        System.out.println("Name Likelihood (as male): " + maleNameStats.getLikelihood());

* Getting the name frequency ratio (as male)

        System.out.println("Name frequency ratio (as male): " + maleNameStats.getFrequencyRatio());

* Getting the name stats on the basis of age density curve

        AgeDensityCurveStats femaleAgeNameStats = statsInfo.getGivenNameStats().getFemaleStats().getAgeStats().getDensityCurve();

* Getting the mean age for the name (as per gender)

        System.out.println("Mean age: " + femaleAgeNameStats.getMeanAge());

* Getting the quartile info (as per gender)

        System.out.println("Quartile 1: " + femaleAgeNameStats.getQuartiles().getQuartile1());

* Getting the mode age count (as per gender)

        System.out.println("Age mode count: " + femaleAgeNameStats.getMode().getCount());

* Getting the mode age (as per gender)

        System.out.println("Mode age: " + femaleAgeNameStats.getMode().getModeAge());


##Name Parser:

* Get stats for a name

        NameParserEntity entity = nameHandler.getNameParserInfo("john");

* Status Code of the Request

		System.out.println("Status Code : " + entity.getStatusCode());

* Getting the likelihood

		System.out.println("Likelihood : " + entity.getLikelihood());

* Getting the request id

        System.out.println("RequestId: " + entity.getRequestId());

* Getting the region

        System.out.println("Region: " + entity.getRegion());

* Getting the ambiguous name

        System.out.println("AmbiguousName: " + entity.getAmbiguousName());

* Getting the givenName

        System.out.println("First Name: " + entity.getNameInfo().getGivenName());

* Getting the givenName

        System.out.println("Last Name: " + entity.getNameInfo().getFamilyName());


##Other API

* [Person API](/fullcontact/fullcontact4j/tree/refactoring/docs/person/)
* [Person Enhanced Data API](/fullcontact/fullcontact4j/tree/refactoring/docs/enhancedData/)
* [Location API](/fullcontact/fullcontact4j/tree/refactoring/docs/location/)
* [CardShark API](/fullcontact/fullcontact4j/tree/refactoring/docs/cardShark/)
