package com.fullcontact.api.libs.fullcontact4j.entity.person;

import java.util.List;

import com.fullcontact.api.libs.fullcontact4j.entity.person.contactinfo.Chat;
import com.fullcontact.api.libs.fullcontact4j.entity.person.contactinfo.Website;
import com.google.gson.annotations.SerializedName;

public class ContactInfo {

	@SerializedName("familyName")
	private String familyName;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("givenName")
	private String givenName;

    @SerializedName("websites")
    private List<Website> websites;

    @SerializedName("chats")
    private List<Chat> chats;

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

	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}

	public List<Chat> getChats() {
		return chats;
	}

    public void setWebsites(List<Website> websites){
        this.websites = websites;
    }

    public List<Website> getWebsites(){
        return websites;
    }

}
