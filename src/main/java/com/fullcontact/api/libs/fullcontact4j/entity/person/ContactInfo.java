package com.fullcontact.api.libs.fullcontact4j.entity.person;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;
@JsonAutoDetect
public class ContactInfo {
	private String familyName;
	private String fullName;
	private String givenName;
    private List<String> middleNames;
    private List<Website> websites;
    private List<Chat> chats;

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
}
