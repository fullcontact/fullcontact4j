package com.fullcontact.api.libs.fullcontact4j.entity;

import com.google.gson.annotations.SerializedName;

public class DigitalFootPrintsTopic {

    @SerializedName("value")
    private String value;

    @SerializedName("provider")
    private String provider;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

    public String getProvider(){
        return provider;
    }

    public void setProvider(String provider){
        this.provider = provider;
    }

}
