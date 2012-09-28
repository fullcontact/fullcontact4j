package com.fullcontact.api.libs.fullcontact4j.entity;

import com.fullcontact.api.libs.fullcontact4j.entity.enhanced.*;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonEnhancedEntity {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("contactInfo")
    private ContactInfo contactInfo;

    @SerializedName("demographics")
    private Demographics demographics;

    @SerializedName("organizations")
    private List<Organizations> organizations;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Demographics getDemographics() {
        return demographics;
    }

    public void setDemographics(Demographics demographics) {
        this.demographics = demographics;
    }

    public List<Organizations> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organizations> organizations) {
        this.organizations = organizations;
    }
}
