#Fullcontact4j: CardShark API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get CardSharkHandler (handler for CardShark API)

        CardSharkHandler cardSharkHandler = fullContact.getCardSharkHandler();


## Upload card methods:

* Upload card with only front image (of scanned business card) as InputStreams

        InputStream frontImageStream = new FileInputStream("/path/to/the/front-image-file.png");
        String webhookUrl = "http://example.com/webhookurl";
        UploadResponse uploadResponse = cardSharkHandler.uploadCardImage(frontImageStream, webhookUrl)

* Upload card with front and back images (of scanned business card) as InputStreams

        UploadResponse uploadResponse = cardSharkHandler.uploadCardImage(frontImageStream, backImageStream, webhookUrl)

* Getting the upload response status code

        System.out.println("Response Status: " + uploadResponse.getStatusCode());

* Getting the request id from the upload response

        System.out.println("Request Id: " + uploadResponse.getRequestId());

* Getting the request queued status from the upload response

        System.out.println("is Queued: " + uploadResponse.isQueued());

### Handling the webhook response

* Parsing the json response received on webhook url.

        UploadRequestResult requestResult = cardSharkHandler.parseUploadWebhookJsonResponse(jsonResponseAsString);

* Getting the requestId

        System.out.println("Request Id: " + requestResult.getRequestId());

* Getting the vCard url

        System.out.println("VCard URL: " + requestResult.getvCardUrl());

* Getting the ContactInfo object

        ContactInfo contactInfo = requestResult.getContact();

### Retrieving the contact information

* Getting the Name object

        Name name = requestResult.getContact().getName();

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


## View requests

* Getting the requests information (for the first page only)

        ViewRequestsEntity viewRequestsEntity = cardSharkHandler.viewRequestResults()

* Getting the requests information using pagination

        ViewRequestsEntity viewRequestsEntity = cardSharkHandler.viewRequestResults(pageNumber)

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
        ViewRequestEntity viewRequestsEntity = cardSharkHandler.viewRequestResults(requestId)

* Getting the single request status

        System.out.println("Status: " + viewRequestEntity.getStatus());

* Getting the request (contact) result

        UploadRequestResult requestResult = viewRequestEntity.getResult();

## Accept or Reject results

* Accepting the result

        AcceptResultResponse acceptResultResponse = acceptResult("your-request-id");

* Getting the status code (from the AcceptResultResponse)

        System.out.println("Status: " + acceptResultResponse.getStatusCode());

* Checking if accepted?

        System.out.println("Is Accepted: " + acceptResultResponse.isAccepted());

* Rejecting the result

        RejectResultResponse rejectResultResponse = rejectResult("your-request-id");

* Getting the status code (from the RejectResultResponse)

        System.out.println("Status: " + rejectResultResponse.getStatusCode());

* Checking if rejected?

        System.out.println("Is Rejected: " + rejectResultResponse.isRejected());

* Getting the new request id (if it's rerun case)

        System.out.println("New Request Id: " + rejectResultResponse.getRequestId());


##Other API

 * [Person API](/fullcontact/fullcontact4j/tree/refactoring/docs/person/)
 * [Person Enhanced Data API](/fullcontact/fullcontact4j/tree/refactoring/docs/enhancedData/)
 * [Name API](/fullcontact/fullcontact4j/tree/refactoring/docs/name/)
 * [Location API](/fullcontact/fullcontact4j/tree/refactoring/docs/location/)
 * [Batch API](/fullcontact/fullcontact4j/tree/refactoring/docs/batch/)
