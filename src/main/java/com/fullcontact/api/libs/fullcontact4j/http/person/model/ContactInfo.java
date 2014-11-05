package com.fullcontact.api.libs.fullcontact4j.http.person.model;
import java.util.Collections;
import java.util.List;

public class ContactInfo {
	private String familyName;
	private String fullName;
	private String givenName;
    private List<String> middleNames = Collections.emptyList();
    private List<Website> websites = Collections.emptyList();
    private List<Chat> chats = Collections.emptyList();

    public String getFamilyName() {
        return familyName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGivenName() {
        return givenName;
    }

    public List<String> getMiddleNames() {
        return middleNames;
    }

    public List<Website> getWebsites() {
        return websites;
    }

    public List<Chat> getChats() {
        return chats;
    }


    public static class Chat {

        private String client;
        private String handle;

        public String getClient() {
            return client;
        }

        public String getHandle() {
            return handle;
        }
    }

    public static class Website {

        private String url;
        public String getUrl() {
            return url;
        }
    }

}