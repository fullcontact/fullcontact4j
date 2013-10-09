package com.fullcontact.api.libs.fullcontact4j.entity.cardreader;

import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo.BasicContactInfo;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo.Name;
import com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo.Organization;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ContactInfo {
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
}


