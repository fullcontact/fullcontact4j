package com.fullcontact.api.libs.fullcontact4j.entity.person;

import com.google.gson.annotations.SerializedName;

public class DigitalFootPrintsScore {

    @SerializedName("value")
    private Float value;

    @SerializedName("provider")
    private String provider;

    @SerializedName("type")
    private String type;

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

    public String getProvider(){
        return provider;
    }

    public void setProvider(String provider){
        this.provider = provider;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

}
