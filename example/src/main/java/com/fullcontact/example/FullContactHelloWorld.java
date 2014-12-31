package com.fullcontact.example;

import com.fullcontact.api.libs.fullcontact4j.FullContact;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.enums.Casing;
import com.fullcontact.api.libs.fullcontact4j.http.location.LocationEnrichmentRequest;
import com.fullcontact.api.libs.fullcontact4j.http.location.LocationEnrichmentResponse;
import com.fullcontact.api.libs.fullcontact4j.http.name.NameDeduceRequest;
import com.fullcontact.api.libs.fullcontact4j.http.name.NameResponse;
import com.fullcontact.api.libs.fullcontact4j.http.person.PersonRequest;
import com.fullcontact.api.libs.fullcontact4j.http.person.PersonResponse;
import com.fullcontact.api.libs.fullcontact4j.http.person.model.SocialProfile;

/**
 * An example of how to use the FullContact client.
 * Here, lets take a business email (like, john.smith@business.com)
 * and generate some interesting details about that person and where they might live.
 */
public class FullContactHelloWorld {

    public static FullContact client;

    //the first argument should be a business email with somebody's name (like bart@fullcontact.com!)
    public static void main(String[] args) {
        // create a client builder, set the user agent, and build the client
        client = FullContact.withApiKey("your-api-key-here")
                .setUserAgent("FC HelloWorld/1.0")
                .build();
        if(args.length == 0) {
            System.out.println("Please include a business email as the first parameter.");
            return;
        }
        String email = args[0];

        getNameFromEmail(email);
        String potentialLocation = lookupTwitterAndLocation(email);
        lookupPopulation(potentialLocation);

        // ALWAYS shutdown your client after you use it, or you could have a small memory leak.
        client.shutdown();
    }

    /**
     * Given an email, let's use FullContact to get a full name
     * (This only works with emails with the name inside, like john.smith@business.com)
     */
    public static void getNameFromEmail(String email) {

        // First, let's build a request to the name deducer. It takes an email or a username parameter...
        // we have email, and want it formatted TitleCase (fancy!)
        NameDeduceRequest nameFromEmailRequest = client.buildNameDeduceRequest().email(email).casing(Casing.TITLECASE)
                .build();

        try {
            //actually send the request. this method will return when the request comes back
            NameResponse response = client.sendRequest(nameFromEmailRequest);

            // Get the full name from the response object
            String name = response.getNameDetails().getFullName();
            System.out.println("That person's name is probably " + name + "!");
        } catch(FullContactException e) {

            // We got a response code that wasn't in the 200s from FullContact...let's see why
            // (check out the API documentation for the potential error codes)
            Integer errorCode = e.getErrorCode();

            if(errorCode == 422 || errorCode == 400 || errorCode == 404) {
                System.out.println("Error: Your email was not a valid format or didn't contain a name.");
            } else {
                System.out.println("Error: Some unknown error occurred. Here's the message:");
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Given an email, let's use FullContact to try to find an associated Twitter account and location.
     */
    public static String lookupTwitterAndLocation(String email) {

        PersonRequest personLookupRequest = client.buildPersonRequest().email(email).build();

        try {
            PersonResponse response = client.sendRequest(personLookupRequest);

            if(response.getStatus() == 200) {
                // 200- success! Let's get some info from the response
                SocialProfile twitter = response.getSocialProfile("twitter");
                if(twitter != null) {
                    System.out.println("Their twitter handle is probably " + twitter.getUsername() +
                            "and they have " + twitter.getFollowers() + "followers!");
                } else {
                    System.out.println("This person doesn't seem to have a twitter.");
                }
                return response.getDemographics().getLocationGeneral();
            }

            if(response.getStatus() == 202) {
                // 202 - FullContact's "we are searching for this person" response. We can try again later
                System.out.println("FullContact is searching for this person. Re-run this again in a few minutes.");
            }

        } catch(FullContactException e) {

            if(e.getErrorCode() == 404) {
                System.out.println("Can't find this person. Sorry :(");
            } else {
                System.out.println("Unknown Error: " + e.getMessage());
            }
        }
        return ""; //
    }

    /**
     * If we have some string that represents a location, let's ask FullContact for the population of that location.
     */
    public static void lookupPopulation(String location) {
        if(location.isEmpty()) { //location is ""
            System.out.println("This person did not have a location in their profile...");
            return;
        }
        LocationEnrichmentRequest locationRequest = client.buildLocationEnrichmentRequest(location).build();

        try {
            LocationEnrichmentResponse locationResponse = client.sendRequest(locationRequest);

            if(locationResponse.getPossibleLocations().size() == 0) {
                System.out.println(location + " didn't seem to correlate to a place.");
                return;
            }

            System.out.println("This location has a population of " +
                    locationResponse.getPossibleLocations().get(0).getPopulation());

        } catch(FullContactException e) {
            System.out.println("Error Occurred: " + e.getMessage());
        }
    }
}
