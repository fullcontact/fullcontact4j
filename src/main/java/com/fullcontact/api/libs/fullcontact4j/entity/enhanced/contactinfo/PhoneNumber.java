package com.fullcontact.api.libs.fullcontact4j.entity.enhanced.contactinfo;

import com.google.gson.annotations.SerializedName;

public class PhoneNumber {

    @SerializedName("number")
    private String number;

    @SerializedName("accessType")
    private String accessType;

    @SerializedName("provider")
    private String provider;

	@SerializedName("confidence")
	private int confidence;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }
}
