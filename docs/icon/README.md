#Fullcontact4j: Icon API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get IconHandler (handler for Icon API)

        IconHandler iconHandler = fullContact.getIconHandler();

## Request the list of all valid icon types

* Getting the list response

        IconListResponse response = iconHandler.getValidIconTypes();

* Getting the response status code

        System.out.println("Status Code: " + response.getStatusCode());

* Getting the list of (valid icon) typeIds

        List<String> iconTypes = response.getValidIconTypes();

## Request an icon

* Getting the icon (InputStream) by icon type

        String typeId = "facebook";
        InputStream iconInputStream = getIcon(typeId);

* Getting the icon (InputStream) by icon type and size

        String typeId = "facebook";
        int size = 16;
        InputStream iconInputStream = getIcon(typeId, size);

* Getting the icon (InputStream) by icon type, size and style

        String typeId = "facebook";
        int size = 16;
        String style = "default";
        InputStream iconInputStream = getIcon(typeId, size, style);


##Other API

* [Person API](/fullcontact/fullcontact4j/tree/master/docs/person/)
* [Name API](/fullcontact/fullcontact4j/tree/master/docs/name/)
* [Location API](/fullcontact/fullcontact4j/tree/master/docs/location/)
* [CardReader API](/fullcontact/fullcontact4j/tree/master/docs/cardReader/)
* [Batch API](/fullcontact/fullcontact4j/tree/master/docs/batch/)
* [Email API](/fullcontact/fullcontact4j/tree/master/docs/email/)
