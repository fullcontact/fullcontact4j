package com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BasicContactInfo implements Serializable {
    private static final long serialVersionUID = 3848544145278546449L;

	@SerializedName("type")
	private String type;

    @SerializedName("value")
    private String value;

    @SerializedName("primary")
    private boolean primary;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}


