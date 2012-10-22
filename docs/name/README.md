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
        System.out.println("Full Name: " + nameInfo.getFullName());
        System.out.println("First Name: " + nameInfo.getGivenName());
        System.out.println("Last Name: " + nameInfo.getFamilyName());
        System.out.println("Middle Names: " + nameInfo.getMiddleNames());
        System.out.println("Prefixes: " + nameInfo.getPrefixes());
        System.out.println("Suffixes: " + nameInfo.getSuffixes());
        System.out.println("Nicknames: " + nameInfo.getNicknames());


##Other API

* [Person API](/fullcontact/fullcontact4j/tree/refactoring/docs/person/)
* [Person Enhanced Data API](/fullcontact/fullcontact4j/tree/refactoring/docs/enhancedData/)
