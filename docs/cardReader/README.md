#Fullcontact4j: CardReader API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get CardReaderHandler (handler for CardShark API)

        CardReaderHandler cardReaderHandler = fullContact.getCardReaderHandler();


## Upload card method:

* Upload card with only front image (of scanned business card) as InputStreams

        InputStream frontImageStream = new FileInputStream("/path/to/the/front-image-file.png");
        String webhookUrl = "http://example.com/webhookurl";
        HashMap<String, String> customParams = new HashMap<String, String>();
        customParams.put("customNotes","Met at conference, was a nice fellow");
        UploadResponse uploadResponse = cardReaderHandler.uploadCard(
        		 new CardReaderUploadRequestBuilder()
        		 		.setFrontImage(frontImageStream)
        		 		.setWebhookUrl(webhookUrl)
        		 		.setBackImage(backImageStream)                              // optional
        		 		.setVerification(CardReaderVerification.Medium)             // optional
        		 		.setVerifiedOnly(true)                                      // optional
        		 		.setCasing(CardReaderCasing.TitleCase)                      // optional
        		 		.setFormat(ResponseFormat.JSON)                             // optional
        		 		.setCustomParams(customParams)                              // optional - advanced
        		 		.setURID(UUID.randomUUID())                                 // optional - advanced
        		 		.setSandbox(true)                                           // optional - for testing
        		 		.setSandboxStatus(CardReaderSandboxStatus.CALLBACK_MADE)    // optional - for testing
        		 		.build()
        );

To learn more about the optional, advanced, and testing parameters, [check out our docs page!](http://www.fullcontact.com/developer/docs/card-reader/)

* Getting the upload response status code

        System.out.println("Response Status: " + uploadResponse.getStatusCode());

* Getting the request id from the upload response

        System.out.println("Request Id: " + uploadResponse.getRequestId());

* Getting the request queued status from the upload response

        System.out.println("is Queued: " + uploadResponse.isQueued());

* Getting the estimated wait time:

		System.out.println("Estimated time: " + uploadResponse.getEstimatedWaitTimeMinutes());

### Handling the webhook response

* Parsing the json response received on webhook url.

        UploadRequestResult requestResult = cardReaderHandler.parseUploadWebhookJsonResponse(jsonResponseAsString);

* Getting the requestId

        System.out.println("Request Id: " + requestResult.getRequestId());

* Getting the vCard url

        System.out.println("VCard URL: " + requestResult.getvCardUrl());

* Getting the ContactInfo object

        ContactInfo contactInfo = requestResult.getContact();

### Retrieving the contact information

* Obtain the ContactInfo object (re-referenced below)

        ContactInfo contactInfo = requestResult.getContact();

* Getting the Name object

        Name name = contactInfo.getName();

* Getting the givenName

        System.out.println("Given Name: " + name.getGivenName());

* Getting the familyName

        System.out.println("Family Name: " + name.getFamilyName());

* Getting the middleName

        System.out.println("Middle Name: " + name.getMiddleName());

* Getting the honorificPrefix

        System.out.println("Name Prefix: " + name.getHonorificPrefix());

* Getting the honorificSuffix

        System.out.println("Name Suffix: " + name.getHonorificSuffix());

* Getting the emails count

        System.out.println("Emails count: " + contactInfo.getEmails().size());

* Getting the email address

        System.out.println("Email Address: " + contactInfo.getEmails().get(0).getValue());

* Getting the email type

        System.out.println("Email Type: " + contactInfo.getEmails().get(0).getType());

* Getting the phone numbers count

        System.out.println("Phone numbers count: " + contactInfo.getPhoneNumbers().size());

* Getting the phone number value

        System.out.println("Phone number: " + contactInfo.getPhoneNumbers().get(0).getValue());

* Getting the phone number type

        System.out.println("Phone number Type: " + contactInfo.getPhoneNumbers().get(0).getType());

* Getting the photos count

        System.out.println("Photos count: " + contactInfo.getPhotos().size());

* Getting the photo URL

        System.out.println("Photo Url: " + contactInfo.getPhotos().get(0).getValue());

* Getting the photo type

        System.out.println("Photo Type: " + contactInfo.getPhotos().get(0).getType());

* Checking if the photo is primary?

        System.out.println("Is photo primary: " + contactInfo.getPhotos().get(0).isPrimary());

* Getting the URLs count

        System.out.println("URLs count: " + contactInfo.getUrls().size());

* Getting the URL value

        System.out.println("URL: " + contactInfo.getUrls().get(0).getValue());

* Getting the URL type

        System.out.println("URL Type: " + contactInfo.getUrls().get(0).getType());

* Getting the organizations count

        System.out.println("Organizations count: " + contactInfo.getOrganizations().size());

* Getting the organization name

        System.out.println("Organization Name: " + contactInfo.getOrganizations().get(0).getName());

* Getting the organization title

        System.out.println("Organization Title: " + contactInfo.getOrganizations().get(0).getTitle());

* Checking if the organization is primary?

        System.out.println("Is primary organization: " + contactInfo.getOrganizations().get(0).isPrimary());

* Getting the addresses count (Currently always 1 or less)

        System.out.println("Address count: " + contactInfo.getAddresses().size());

* Getting the address country (if address is present! (Count > 0)

        System.out.println("Country is: " + contactInfo.getAddresses().get(0).getCountry());

* Getting the address type

        System.out.println("Address type is: " + contactInfo.getAddresses().get(0).getType());

* Getting the address locality

        System.out.println("Locality is: " + contactInfo.getAddresses().get(0).getLocality()); // City

* Getting the address region

        System.out.println("Region is: " + contactInfo.getAddresses().get(0).getRegion()); // State

* Getting the address postal code

        System.out.println("Postal code is: " + contactInfo.getAddresses().get(0).getPostalCode());

* Getting the address street address

        System.out.println("Street address is: " + contactInfo.getAddresses().get(0).getStreetAddress());

* Getting the Accounts size

        System.out.println("Accounts count: " + contactInfo.getAccounts().size());

* Getting the first account's domain

        System.out.println("Domain is: " + contactInfo.getAccounts().get(0).getDomain());

* Getting the first account's entire url

        System.out.println("URL is: " + contactInfo.getAccounts().get(0).getUrlString());

* Getting the first account's userId

        System.out.println("UserId is: " + contactInfo.getAccounts().get(0).getUserId());

* Getting the first account's username

        System.out.println("Domain is: " + contactInfo.getAccounts().get(0).getUserName());

* Getting the IMs size (Instant Messenger accounts like Skype)

        System.out.println("IMs count: " + contactInfo.getIms().size());

* Getting the first IM's value

        System.out.println("IM value is: " + contactInfo.getIms().get(0).getValue());  // joe.schmoe

* Getting the first IM's type

        System.out.println("IM type is: " + contactInfo.getIms().get(0).getType());  // Skype


## View requests

* Getting the requests information (for the first page only)

        ViewRequestsEntity viewRequestsEntity = cardReaderHandler.viewRequestResults()

* Getting the requests information using pagination

        ViewRequestsEntity viewRequestsEntity = cardReaderHandler.viewRequestResults(pageNumber)

* Getting the status for the request made

        System.out.println("Status: " + viewRequestsEntity.getStatus());

* Getting the requests count

        System.out.println("Count: " + viewRequestsEntity.getCount());

* Getting the total number of pages

        System.out.println("Total Pages: " + viewRequestsEntity.getTotalPages());

* Getting the current page

        System.out.println("Current Page: " + viewRequestsEntity.getCurrentPage());

* Getting the total number of requests

        System.out.println("Total Records: " + viewRequestsEntity.getTotalRecords());

* Getting a request result

        UploadRequestResult requestResult = viewRequestsEntity.getResults().get(0);

* Getting the (single) request information (by request id)

        String requestId = "your-request-id";
        ViewRequestEntity viewRequestsEntity = cardReaderHandler.viewRequestResults(requestId)

* Getting the single request status

        System.out.println("Status: " + viewRequestEntity.getStatus());

* Getting the request (contact) result

        UploadRequestResult requestResult = viewRequestEntity.getResult();

* More advanced get request (Specify other params from Card Reader docs View Requests feature)

        String requestId = "123-abc-def-456";
        CardReaderViewResultsRequest request = new CardReaderViewResultsRequestBuilder(requestId)
                                                    .setDiagnostics(true)
                                                    .setVerifiedOnly(true)
                                                    .build();
        cardReaderHandler.viewRequestResults(request)

##Other API

* [Person API](/fullcontact/fullcontact4j/tree/master/docs/person/)
* [Name API](/fullcontact/fullcontact4j/tree/master/docs/name/)
* [Location API](/fullcontact/fullcontact4j/tree/master/docs/location/)
* [Batch API](/fullcontact/fullcontact4j/tree/master/docs/batch/)
* [Email API](/fullcontact/fullcontact4j/tree/master/docs/email/)
* [Icon API](/fullcontact/fullcontact4j/tree/master/docs/icon/)
