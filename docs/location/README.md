#Fullcontact4j: Location API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get LocationHandler (handler for Location API)

        LocationHandler locationHandler = fullContact.getLocationHandler();


## Location Normalization:

* Normalize a location string

        LocationNormalizerEntity entity = locationHandler.getLocationNormalizationInfo("denver");

* Normalize a location string also include places with zero population

        boolean includeZeroPopulation = true;
        LocationNormalizerEntity entity = locationHandler.getLocationNormalizationInfo("denver", includeZeroPopulation);

* Normalize a location string and tell casing style

        boolean includeZeroPopulation = false;
        String casingStyle = "uppercase";
        LocationNormalizerEntity entity = locationHandler.getLocationNormalizationInfo("denver", casingStyle, includeZeroPopulation);

* Getting the status code of the request

        System.out.println("Status Code: " + entity.getStatusCode());

* Getting the likelihood

        System.out.println("Likelihood: " + entity.getLikelihood());

* Getting the request id

        System.out.println("Request Id: " + entity.getRequestId());

* Getting the locationInfo object

        LocationInfo locationInfo = entity.getLocationInfo();


## Location Enrichment: getLocationEnrichmentInfo

* Get enriched location info

        LocationEnrichmentEntity entity = locationHandler.getLocationEnrichmentInfo("denver");

* Get enriched location info, also include places with zero population

        boolean includeZeroPopulation = true;
        LocationEnrichmentEntity entity = locationHandler.getLocationEnrichmentInfo("denver", includeZeroPopulation);

* Get enriched location info and tell casing style

        boolean includeZeroPopulation = false;
        String casingStyle = "uppercase";
        LocationEnrichmentEntity entity = locationHandler.getLocationEnrichmentInfo("denver", casingStyle, includeZeroPopulation);

* Getting the status code of the request

        System.out.println("Status Code: " + entity.getStatusCode());

* Getting the request id

        System.out.println("Request Id: " + entity.getRequestId());

* Getting the list of locations with enriched information

        List<LocationInfo> locations = entity.getLocations();

###Getting the location information (from LocationInfo object)

* Getting the city name

        System.out.println("City: " + locationInfo.getCity());

* Getting the county name

        System.out.println("County: " + locationInfo.getCounty());

* Getting the state name

        System.out.println("State Name: " + locationInfo.getState().getName());

* Getting the state code

        System.out.println("State Code: " + locationInfo.getState().getCode());

* Getting the country name

        System.out.println("Country Name: " + locationInfo.getCountry().getName());

* Getting the country code

        System.out.println("Country Code: " + locationInfo.getCountry().getCode());

* Getting the continent name

        System.out.println("Continent: " + locationInfo.getContinent());

* Getting the population

        System.out.println("Population: " + locationInfo.getPopulation());

* Getting the location normalized string

        System.out.println("Normalized Location: " + locationInfo.getNormalizedLocation());


##Other API

* [Person API](/fullcontact/fullcontact4j/tree/refactoring/docs/person/)
* [Person Enhanced Data API](/fullcontact/fullcontact4j/tree/refactoring/docs/enhancedData/)
* [Name API](/fullcontact/fullcontact4j/tree/refactoring/docs/name/)
