package com.fullcontact.api.libs.fullcontact4j.response;

import com.fullcontact.api.libs.fullcontact4j.entity.person.*;
import com.fullcontact.api.libs.fullcontact4j.entity.person.SocialProfile;
import com.fullcontact.api.libs.fullcontact4j.entity.person.SocialProfileType;

import java.util.List;

public class PersonResponse extends FCResponse {

    private ContactInfo contactInfo;
    private Demographics demographics;
    private DigitalFootPrints digitalFootprint;
    private List<Organization> organizations;
    private List<Photo> photos;
    private int statusCode;
    private double likelihood;
    private List<SocialProfile> socialProfiles;
    private String requestId;

    /**
     * Gets the social profile of a certain type
     * @param type the social media website
     * @return the SocialProfile, or null if it doesn't exist.
     */
    public SocialProfile getSocialProfile(SocialProfileType type) {
        for(SocialProfile p : socialProfiles) {
            if(type.equals(SocialProfileType.fromString(p.getTypeId()))) {
                return p;
            }
        }
        return null;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public Demographics getDemographics() {
        return demographics;
    }

    public DigitalFootPrints getDigitalFootprint() {
        return digitalFootprint;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public double getLikelihood() {
        return likelihood;
    }

    public List<SocialProfile> getSocialProfiles() {
        return socialProfiles;
    }

    public String getRequestId() {
        return requestId;
    }
}


