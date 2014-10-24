package com.fullcontact.api.libs.fullcontact4j.entity.cardreader;

import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo.*;

import java.io.Serializable;
import java.util.List;
public class ContactInfo implements Serializable {
    private static final long serialVersionUID = 7385077336995988963L;
    private Name name;
    private List<BasicContactInfo> emails;
    private List<BasicContactInfo> phoneNumbers;
    private List<BasicContactInfo> photos;
    private List<BasicContactInfo> urls;
    private List<Organization> organizations;
    // Twitter, Facebook, etc...
    private List<Account> accounts;
    // Skype, etc..
    private List<BasicContactInfo> ims;
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

