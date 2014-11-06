package com.fullcontact.api.libs.fullcontact4j.http.cardreader.model;

import java.util.Collections;
import java.util.List;

/**
 * Conglomeration of internet accounts associated with contact information about a person.
 */
public class ContactInfo {

    private Name name;
    private List<BasicContactInfo> emails = Collections.emptyList();
    private List<BasicContactInfo> phoneNumbers = Collections.emptyList();
    private List<BasicContactInfo> photos = Collections.emptyList();
    private List<BasicContactInfo> urls = Collections.emptyList();
    private List<Organization> organizations = Collections.emptyList();
    // Twitter, Facebook, etc...
    private List<Account> accounts = Collections.emptyList();
    // Skype, etc..
    private List<BasicContactInfo> ims = Collections.emptyList();
    private List<Address> addresses = Collections.emptyList();

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactInfo{");
        sb.append("name=").append(name);
        sb.append(", emails:").append(emails.size());
        sb.append(", accounts:").append(accounts.size());
        sb.append(", photos:").append(photos.size());
        sb.append('}');
        return sb.toString();
    }
}

