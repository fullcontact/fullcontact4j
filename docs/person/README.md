#Fullcontact4j: Person API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get PersonHandler (handler for Person API)

        PersonHandler personHandler = fullContact.getPersonHandler()

* Finding a person information (requires an email as input parameter)

        PersonEntity entity = personHandler.getPersonInformation("lorangb@gmail.com");

* Finding a person information by email (same as above)

        PersonEntity entity = personHandler.getPersonInfoByEmail("lorangb@gmail.com");

* Finding a person information by email MD5 hash (case sensitive)

        PersonEntity entity = personHandler.getPersonInfoByEmailMD5("a4cb1b07d68a3436a190e1559586ae3c");

* Finding a person information by Twitter Id

        PersonEntity entity = personHandler.getPersonInfoByTwitter("lorangb");

* Finding a person information by Facebook Username

        PersonEntity entity = personHandler.getPersonInfoByFacebookUsername("bart.lorang");

* Finding a person using parameters Map

        Map<String, String> map = new HashMap<String, String>();
        map.put("email", "lorangb@gmail.com");
        PersonEntity entity = personHandler.getPersonInfoByParameters(map);

* Finding a person using WebHooks

        Find by email
        PersonEntity entity = personHandler.getPersonInfoByEmailUsingWebhook("lorangb@gmail.com", "http://domain.com/webhookUrl", "webhook-id-123");

        Find by Twitter id
        PersonEntity entity = personHandler.getPersonInfoByTwitterUsingWebhook("lorangb", "http://domain.com/webhookUrl", "webhook-id-123");

        Find by Facebook username
        PersonEntity entity = personHandler.getPersonInfoByFacebookUsername("bart.lorang", "http://domain.com/webhookUrl", "webhook-id-123");

* Status Code of the Request

		System.out.println("Status Code : " + entity.getStatusCode());		

* Getting the likelihood

		System.out.println("Likelihood : " + entity.getLikelihood());

* Getting the Contact Info

		ContactInfo contactInfo = entity.getContactInfo();

		System.out.println("Full name : " + contactInfo.getFullName());
		System.out.println("Given name : " + contactInfo.getGivenName());
		System.out.println("Family name : " + contactInfo.getFamilyName());
		
* List of Chat Clients & Handles

        List<Chat> chats = contactInfo.getChats();

        for (Chat chat : chats) {
            System.out.println("Client : " + chat.getChatClient());
            System.out.println("Handle : " + chat.getChatHandle());
            System.out.println();
        }

* List of Websites

        List<Website> websites = contactInfo.getWebsites();

		for (Website website : websites) {
			System.out.println("URL : " + website.getUrl());
			System.out.println();
		}

* Getting the Demographics Info

        Demographics demographics = entity.getDemographics();
        System.out.println("Gender: " + demographics.getGender());
        System.out.println("Age: " + demographics.getAge());
        System.out.println("Age Range: " + demographics.getAgeRange());
        System.out.println("Location: " + demographics.getLocationGeneral());

* Getting the DigitalFootPrint Scores

        DigitalFootPrints dfp = entity.getDigitalFootprint();
        List<DigitalFootPrintsScore> scores = dfp.getScores();

        for(DigitalFootPrintsScore score : scores){
            System.out.println("type: " + score.getType());
            System.out.println("value: " + score.getValue());
            System.out.println("provider: " + score.getProvider());
        }

* Getting the DigitalFootPrint Topics

        DigitalFootPrints dfp = entity.getDigitalFootprint();
        List<DigitalFootPrintsTopic> topics = dfp.getTopics();

        for(DigitalFootPrintsTopic topic : topics){
            System.out.println("value: " + topic.getValue());
            System.out.println("provider: " + topic.getProvider());
			System.out.println();
        }

* List of Organizations

		List<Organization> organizations = entity.getOrganizations();

        for (Organization org : organizations) {
            System.out.println("Organization Name: " + org.getName());
            System.out.println("Job Title: " + org.getTitle());
            System.out.println("Job Start Date: " + org.getStartDate());
			System.out.println();
		}

* List of Photos (e.g. Flickr, Picassa, etc)

        List<Photo> photos = entity.getPhotos();

        for (Photo photo : photos) {
            System.out.println("Type Id: " + photo.getPhotoTypeId());
            System.out.println("Type Name: " + photo.getPhotoTypeName());
            System.out.println("Url: " + photo.getPhotoUrl());
            System.out.println();
        }

* List of Social Profiles (e.g. Facebook, Twitter, etc)

        List<SocialProfile> profiles = entity.getSocialProfiles().getAllSocialProfiles();
        for (SocialProfile profile : profiles) {
            System.out.println("type : " + profile.getProfileTypeId());
            System.out.println("url :" + profile.getProfileUrl());
            System.out.println("id :" + profile.getProfileId());
            System.out.println("username : " + profile.getProfileUsername());
            System.out.println("bio : " + profile.getBio());
            System.out.println();
        }

* List of Enhanced Data entities

        List<EnhancedDataEntity> enhancedDataEntities = entity.getEnhancedData();
        for(EnhancedDataEntity dataEntity : enhancedDataEntities){
            System.out.println("Url: " + dataEntity.getUrl());
            System.out.println("isPrimary: " + dataEntity.isPrimary());
        }

##Other API

* [Person Enhanced Data API](/fullcontact/fullcontact4j/tree/refactoring/docs/enhancedData/)
* [Name API](/fullcontact/fullcontact4j/tree/refactoring/docs/name/)
* [Location API](/fullcontact/fullcontact4j/tree/refactoring/docs/location/)
* [CardShark API](/fullcontact/fullcontact4j/tree/refactoring/docs/cardShark/)
* [Batch API](/fullcontact/fullcontact4j/tree/refactoring/docs/batch/)
* [Email API](/fullcontact/fullcontact4j/tree/refactoring/docs/email/)
* [Icon API](/fullcontact/fullcontact4j/tree/refactoring/docs/icon/)
