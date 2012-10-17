#Fullcontact4j: Name API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get NameHandler (handler for Name API)
        NameHandler nameHandler = fullContact.getNameHandler();
