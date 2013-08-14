#Fullcontact4j: Person Enhanced Data API

##How to use ?

* Declare an instance of FullContact

        String apiKey = "Your api key goes here";
        FullContact fullContact = new FullContact(apiKey);

* Get PersonEnhancedHandler (handler for Enhanced data API)

        PersonEnhancedHandler enhancedHandler = fullContact.getPersonEnhancedHandler();

* Getting the enhanced (person) information

        PersonEnhancedEntity entity = enhancedHandler.getPersonEnhancedInfo("dan@fullcontact.com");

* Getting the request status

        System.out.println("Request Status Code: " + entity.getStatusCode());

* Getting the contact information

        ContactInfo contactInfo = entity.getContactInfo();

* Getting the full name

        System.out.println("Full Name: " + contactInfo.getFullName());

* Getting the given name

        System.out.println("Given Name: " + contactInfo.getGivenName());

* Getting the family name

        System.out.println("Family Name: " + contactInfo.getFamilyName());

* Getting the phone numbers count

        System.out.println("Phone Numbers Count: " + contactInfo.getPhoneNumbers().size());

* Getting the addresses count

        System.out.println("Street Addresses Count: " + contactInfo.getStreetAddresses().size());

* Getting the emails count

        System.out.println("Emails Count: " + contactInfo.getEmailAddress().size());

* Getting the email address

        System.out.println("Email Address: " + contactInfo.getEmailAddress().get(0));

* Getting the phone number information

        PhoneNumber phoneNumber = contactInfo.getPhoneNumbers().get(0);

* Getting the phone number

        System.out.println("Phone Number: " + phoneNumber.getNumber());

* Getting the phone number provider

        System.out.println("Phone Number Provider: " + phoneNumber.getProvider());

* Getting the confidence value for the phone number

        System.out.println("Phone Number Confidence: " + phoneNumber.getConfidence());

* Getting the access type for the phone number

        System.out.println("Phone Number Access Type: " + phoneNumber.getAccessType());

* Getting the contact address information

        StreetAddress streetAddress = contactInfo.getStreetAddresses().get(0);

* Getting the street address

        System.out.println("Street Address: " + streetAddress.getAddress());

* Getting the city

        System.out.println("City: " + streetAddress.getCity());

* Getting the state

        System.out.println("State: " + streetAddress.getState());

* Getting the country

        System.out.println("Country: " + streetAddress.getCountry());

* Getting the address postal code

        System.out.println("Postal Code: " + streetAddress.getPostalCode());

* Getting the formatted address

        System.out.println("Formatted Address: " + streetAddress.getFormattedAddress());

* Getting the address type

        System.out.println("Type Id: " + streetAddress.getTypeId());

* Getting the demographics information

        Demographics demographics = entity.getDemographics();

* Getting the demographics - country code

        System.out.println("Country Code: " + demographics.getCountryCode());

* Getting the demographics - postal code

        System.out.println("Postal Code: " + demographics.getPostalCode());

* Getting the demographics - location

        System.out.println("Location: " + demographics.getLocationGeneral());

* Getting the Organizations list

        List<Organization> organizations = entity.getOrganizations();

* Getting the organization information

        Organization organization = organizations.get(0);

* Getting the organization name

        System.out.println("Organization Name: " + organization.getName());

* Getting the organization job title

        System.out.println("Job Title: " + organization.getTitle());

* Check if organization is primary

        System.out.println("Is Primary Organization: " + organization.isPrimary());

* Getting the organization employee count range

        System.out.println("Organization Employee Count Range: " + organization.getEmployeeCountRange());

* Getting the organization revenue range

        System.out.println("Organization Revenue Range: " + organization.getRevenueRange());

* Getting the organization domains

        System.out.println("Organization Domains: " + organization.getDomains());

* Getting the organization industries

        System.out.println("Organization Industries: " + organization.getIndustries());

* Getting the organization position functions

        System.out.println("Organization Position Functions: " + organization.getPositionFunctions());


##Other API

 * [Person API](/fullcontact/fullcontact4j/tree/master/docs/person/)
 * [Name API](/fullcontact/fullcontact4j/tree/master/docs/name/)
 * [Location API](/fullcontact/fullcontact4j/tree/master/docs/location/)
 * [CardReader API](/fullcontact/fullcontact4j/tree/master/docs/cardReader/)
 * [Batch API](/fullcontact/fullcontact4j/tree/master/docs/batch/)
 * [Email API](/fullcontact/fullcontact4j/tree/master/docs/email/)
 * [Icon API](/fullcontact/fullcontact4j/tree/master/docs/icon/)
