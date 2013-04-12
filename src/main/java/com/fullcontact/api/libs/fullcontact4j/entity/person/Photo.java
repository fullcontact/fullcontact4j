package com.fullcontact.api.libs.fullcontact4j.entity.person;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("typeId")
    private String photoTypeId;

    @SerializedName("typeName")
    private String photoTypeName;

	@SerializedName("url")
	private String photoUrl;

    @SerializedName("isPrimary")
    private Boolean photoIsPrimary = false;

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoTypeId() {
		return photoTypeId;
	}

	public void setPhotoTypeId(String photoTypeId) {
		this.photoTypeId = photoTypeId;
	}

    public String getPhotoTypeName() {
        return photoTypeName;
    }

    public void setPhotoTypeName(String photoTypeName) {
        this.photoTypeName = photoTypeName;
    }

    public void setPhotoIsPrimary(Boolean isPrimary){
        this.photoIsPrimary = isPrimary;
    }

    public Boolean getPhotoIsPrimary(){
        return this.photoIsPrimary;
    }

}
