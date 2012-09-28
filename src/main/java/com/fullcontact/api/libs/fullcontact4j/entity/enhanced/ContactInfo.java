package com.fullcontact.api.libs.fullcontact4j.entity.enhanced;

import com.fullcontact.api.libs.fullcontact4j.entity.enhanced.contactinfo.PhoneNumber;
import com.fullcontact.api.libs.fullcontact4j.entity.enhanced.contactinfo.StreetAddress;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactInfo {

	@SerializedName("familyName")
	private String familyName;

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("givenName")
	private String givenName;

    @SerializedName("emailAddresses")
	private List<String> emailAddress;

    @SerializedName("phoneNumbers")
	private List<PhoneNumber> phoneNumbers;

    @SerializedName("streetAddresses")
	private List<StreetAddress> streetAddresses;

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

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<StreetAddress> getStreetAddresses() {
        return streetAddresses;
    }

    public void setStreetAddresses(List<StreetAddress> streetAddresses) {
        this.streetAddresses = streetAddresses;
    }
}


