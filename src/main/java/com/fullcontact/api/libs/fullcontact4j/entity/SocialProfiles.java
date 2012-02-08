package com.fullcontact.api.libs.fullcontact4j.entity;

import com.google.gson.annotations.SerializedName;

public class SocialProfiles {

	@SerializedName("type")
	private String profileType;

	@SerializedName("url")
	private String profileUrl;

	@SerializedName("id")
	private String profileId;

	@SerializedName("birthday")
	private String profileBday;

	@SerializedName("username")
	private String profileUsername;

	@SerializedName("headline")
	private String headline;

	@SerializedName("connections")
	private int connections;

	@SerializedName("currentStatus")
	private String currentStatus;

	@SerializedName("currentStatusTimestamp")
	private String currentStatusTimestamp;

	@SerializedName("bio")
	private String bio;

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfileBday() {
		return profileBday;
	}

	public void setProfileBday(String profileBday) {
		this.profileBday = profileBday;
	}

	public String getProfileUsername() {
		return profileUsername;
	}

	public void setProfileUsername(String profileUsername) {
		this.profileUsername = profileUsername;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getHeadline() {
		return headline;
	}

	public void setConnections(int connections) {
		this.connections = connections;
	}

	public int getConnections() {
		return connections;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatusTimestamp(String currentStatusTimestamp) {
		this.currentStatusTimestamp = currentStatusTimestamp;
	}

	public String getCurrentStatusTimestamp() {
		return currentStatusTimestamp;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getBio() {
		return bio;
	}

}
