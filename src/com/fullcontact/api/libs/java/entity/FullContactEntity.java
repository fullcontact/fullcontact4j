package com.fullcontact.api.libs.java.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FullContactEntity {

	@SerializedName("contactInfo")
	private ContactInfo contactInfo;

	@SerializedName("organizations")
	private List<Organizations> organizations;

	@SerializedName("photos")
	private List<Photos> photos;

	@SerializedName("socialProfiles")
	private List<SocialProfiles> socialProfiles;

	@SerializedName("status")
	private int statusCode;

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

	public List<Organizations> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organizations> organizations) {
		this.organizations = organizations;
	}

	public List<SocialProfiles> getSocialProfiles() {
		return socialProfiles;
	}

	public void setSocialProfiles(List<SocialProfiles> socialProfiles) {
		this.socialProfiles = socialProfiles;
	}

	public List<Photos> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}
}
