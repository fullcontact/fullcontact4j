package com.fullcontact.api.libs.fullcontact4j.entity.person.socialprofiles;

import com.google.gson.annotations.SerializedName;

public class SocialProfile {

    @SerializedName("typeId")
    private String profileTypeId;

    @SerializedName("typeName")
    private String profileTypeName;

	@SerializedName("url")
	private String profileUrl;

	@SerializedName("id")
	private String profileId;

	@SerializedName("username")
	private String profileUsername;

    @SerializedName("bio")
    private String bio;

//    @SerializedName("birthday")
//    private String profileBday;
//
//	@SerializedName("headline")
//	private String headline;
//
//	@SerializedName("connections")
//	private int connections;
//
//	@SerializedName("currentStatus")
//	private String currentStatus;
//
//	@SerializedName("currentStatusTimestamp")
//	private String currentStatusTimestamp;

	public String getProfileTypeId() {
		return profileTypeId;
	}

	public void setProfileTypeId(String profileTypeId) {
		this.profileTypeId = profileTypeId;
	}

    public String getProfileTypeName() {
        return profileTypeName;
    }

    public void setProfileTypeName(String profileTypeName) {
        this.profileTypeName = profileTypeName;
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

	public String getProfileUsername() {
		return profileUsername;
	}

	public void setProfileUsername(String profileUsername) {
		this.profileUsername = profileUsername;
	}

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

//    public String getProfileBday() {
//        return profileBday;
//    }
//
//    public void setProfileBday(String profileBday) {
//        this.profileBday = profileBday;
//    }
//
//	public void setHeadline(String headline) {
//		this.headline = headline;
//	}
//
//	public String getHeadline() {
//		return headline;
//	}
//
//	public void setConnections(int connections) {
//		this.connections = connections;
//	}
//
//	public int getConnections() {
//		return connections;
//	}
//
//	public void setCurrentStatus(String currentStatus) {
//		this.currentStatus = currentStatus;
//	}
//
//	public String getCurrentStatus() {
//		return currentStatus;
//	}
//
//	public void setCurrentStatusTimestamp(String currentStatusTimestamp) {
//		this.currentStatusTimestamp = currentStatusTimestamp;
//	}
//
//	public String getCurrentStatusTimestamp() {
//		return currentStatusTimestamp;
//	}

}
