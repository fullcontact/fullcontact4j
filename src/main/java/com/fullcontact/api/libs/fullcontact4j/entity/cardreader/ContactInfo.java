package com.fullcontact.api.libs.fullcontact4j.entity.cardreader;

import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo.*;
import com.google.gson.annotations.SerializedName;

import javax.security.auth.login.AccountException;
import java.io.Serializable;
import java.util.List;

public class ContactInfo implements Serializable {
    private static final long serialVersionUID = 7385077336995988963L;

    @SerializedName("name")
    private Name name;

    @SerializedName("emails")
    private List<BasicContactInfo> emails;

    @SerializedName("phoneNumbers")
    private List<BasicContactInfo> phoneNumbers;

    @SerializedName("photos")
    private List<BasicContactInfo> photos;

    @SerializedName("urls")
    private List<BasicContactInfo> urls;

    @SerializedName("organizations")
    private List<Organization> organizations;

    // Twitter, Facebook, etc...
    @SerializedName("accounts")
    private List<Account> accounts;

    // Skype, etc..
    @SerializedName("ims")
    private List<BasicContactInfo> ims;

    @SerializedName("addresses")
    private List<Address> addresses;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<BasicContactInfo> getEmails() {
        return emails;
    }

    public void setEmails(List<BasicContactInfo> emails) {
        this.emails = emails;
    }

    public List<BasicContactInfo> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<BasicContactInfo> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<BasicContactInfo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<BasicContactInfo> photos) {
        this.photos = photos;
    }

    public List<BasicContactInfo> getUrls() {
        return urls;
    }

    public void setUrls(List<BasicContactInfo> urls) {
        this.urls = urls;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<BasicContactInfo> getIms() {
        return ims;
    }

    public void setIms(List<BasicContactInfo> ims) {
        this.ims = ims;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}


