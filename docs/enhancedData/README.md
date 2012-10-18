#Fullcontact4j: Person Enhanced Data API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get PersonEnhancedHandler (handler for Enhanced data API)
        PersonEnhancedHandler enhancedHandler = fullContact.getPersonEnhancedHandler();
