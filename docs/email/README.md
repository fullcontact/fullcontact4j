#Fullcontact4j: Email API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get EmailHandler (handler for Batch API)

        EmailHandler emailHandler = fullContact.getEmailHandler();

## Detect Disposable Email Addresses:

* Getting the disposable (email domain) information

        DisposableResponse response = emailHandler.getDisposableInfo("joe+tag@sharklasers.com");

* Getting the response status code

        System.out.println("Status Code: " + response.getStatusCode());

* Getting the disposable (email domain status) information

        System.out.println("Disposable Email Domain status: " + response.getDisposableEmailDomainStatus());

* Getting the username sub-addressing status information

        System.out.println("Username SubAddressing status: " + response.getUsernameSubAddressingStatus());

* Getting the response message

        System.out.println("Message: " + response.getMessage());


##Other API

* [Person API](/fullcontact/fullcontact4j/tree/refactoring/docs/person/)
* [Person Enhanced Data API](/fullcontact/fullcontact4j/tree/refactoring/docs/enhancedData/)
* [Name API](/fullcontact/fullcontact4j/tree/refactoring/docs/name/)
* [Location API](/fullcontact/fullcontact4j/tree/refactoring/docs/location/)
* [CardShark API](/fullcontact/fullcontact4j/tree/refactoring/docs/cardShark/)
