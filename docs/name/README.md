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

            NameEntity entity = nameHandler.getNameDeducerInfoByUsername("johndsmith79@business.com");

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


##Other API

* [Person API](/fullcontact/fullcontact4j/tree/refactoring/docs/person/)
* [Person Enhanced Data API](/fullcontact/fullcontact4j/tree/refactoring/docs/enhancedData/)
