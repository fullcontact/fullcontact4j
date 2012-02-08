package com.fullcontact.api.libs.fullcontact4j.entity;

import java.util.List;

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

	@SerializedName("chats")
	private List<Chats> chats;

	@SerializedName("phoneNumbers")
	private List<String> phoneNumbers;

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

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

}
