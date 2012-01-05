#jFullContact

A Java wrapper for the [FullContact API](http://api.fullcontact.com/)

##Quick Usage
* You can either download the source code. Or just use jar files in the lib directory (i.e. jFullContact.jar and gson-2.1.jar)

##How to use ?
 
* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Finding a person information

		String email = "lorangb@gmail.com";	
		FullContactEntity entity = fullContact.getPersonInformation(email);

* Status Code of the Request

		System.out.println("Status Code : " + entity.getStatusCode());		

* Getting the Contact Info

		ContactInfo contactInfo = entity.getContactInfo();

		System.out.println("Full name : " + contactInfo.getFullName());
		System.out.println("Given name : " + contactInfo.getGivenName());
		System.out.println("Family name : " + contactInfo.getFamilyName());
		

* List of Chat Clients & Handles

		List<Chats> chats = contactInfo.getChats();
		
		for (Chats chat : chats) {
			System.out.println("Client : " + chat.getChatClient());
			System.out.println("Handle : " + chat.getChatHandle());
			System.out.println();
		}

* List of Phone Numbers

        List<String> phoneNumbers = contactInfo.getPhoneNumbers();

		for (String phoneNumber : phoneNumbers) {
			System.out.println(phoneNumber);
		}
		
* List of Organizations

		List<Organizations> organizations = entity.getOrganizations();

		for (Organizations org : organizations) {
			System.out.println(org.getOrganizationName());
			System.out.println(org.getOrganizationTitle());
			System.out.println(org.getOrganizationStartDate());
			System.out.println();
		}

* List of Photos (e.g. Flickr, Picassa, etc)

		List<Photos> photos = entity.getPhotos();

		for (Photos photo : photos) {
			System.out.println("type :" + photo.getPhotoType());
			System.out.println("url :" + photo.getPhotoUrl());
			System.out.println();
		}

* List of Social Profiles (e.g. Facebook, Twitter, etc)

		List<SocialProfiles> profiles = entity.getSocialProfiles();

		for (SocialProfiles profile : profiles) {
			System.out.println("type : " + profile.getProfileType());
			System.out.println("url :" + profile.getProfileUrl());
			System.out.println("id :" + profile.getProfileId());
			System.out.println("username : " + profile.getProfileUsername());
			System.out.println("currentStatus : " + profile.getCurrentStatus());
			System.out.println("currentStatusTimestamp : " + profile.getCurrentStatusTimestamp());
			System.out.println("connections : " + profile.getConnections());
			System.out.println("bio : " + profile.getBio());			
			
			System.out.println();
		}


##Dependencies
* Google GSON (http://code.google.com/p/google-gson/)
