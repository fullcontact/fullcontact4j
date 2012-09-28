package com.fullcontact.api.libs.fullcontact4j.entity;

import com.fullcontact.api.libs.fullcontact4j.entity.person.*;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonEntity {

    @SerializedName("contactInfo")
    private ContactInfo contactInfo;

    @SerializedName("demographics")
    private Demographics demographics;

    @SerializedName("digitalFootprint")
    private DigitalFootPrints digitalFootprint;

    @SerializedName("organizations")
    private List<Organizations> organizations;

	@SerializedName("photos")
	private List<Photo> photos;

	@SerializedName("status")
	private int statusCode;

    @SerializedName("likelihood")
    private double likelihood;

    @SerializedName("enhancedData")
    private List<EnhancedDataEntity> enhancedData;

    private transient SocialProfiles socialProfiles;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

    public double getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(double likelihood) {
        this.likelihood = likelihood;
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

    public DigitalFootPrints getDigitalFootprint() {
        return digitalFootprint;
    }

    public void setDigitalFootprint(DigitalFootPrints digitalFootprint) {
        this.digitalFootprint = digitalFootprint;
    }

	public SocialProfiles getSocialProfiles() {
		return socialProfiles;
	}

	public void setSocialProfiles(SocialProfiles socialProfiles) {
		this.socialProfiles = socialProfiles;
	}

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<EnhancedDataEntity> getEnhancedData() {
        return enhancedData;
    }

    public void setEnhancedData(List<EnhancedDataEntity> enhancedData) {
        this.enhancedData = enhancedData;
    }
}
