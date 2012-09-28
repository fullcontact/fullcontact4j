package com.fullcontact.api.libs.fullcontact4j.entity.person;

import java.util.List;

import com.fullcontact.api.libs.fullcontact4j.entity.person.contactinfo.Chats;
import com.fullcontact.api.libs.fullcontact4j.entity.person.contactinfo.Website;
import com.google.gson.annotations.SerializedName;

public class ContactInfo {

	@SerializedName("familyName")
	private String familyName;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("givenName")
	private String givenName;

	@SerializedName("emailAddresses")
	private List<String> emailAddress;

    @SerializedName("websites")
    private List<Website> websites;

    @SerializedName("chats")
    private List<Chats> chats;

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public List<String> getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(List<String> emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setChats(List<Chats> chats) {
		this.chats = chats;
	}

	public List<Chats> getChats() {
		return chats;
	}

    public void setWebsites(List<Website> websites){
        this.websites = websites;
    }

    public List<Website> getWebsites(){
        return websites;
    }

}
