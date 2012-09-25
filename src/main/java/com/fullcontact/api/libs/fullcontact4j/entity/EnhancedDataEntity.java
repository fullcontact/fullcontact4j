package com.fullcontact.api.libs.fullcontact4j.entity;

import com.google.gson.annotations.SerializedName;

public class EnhancedDataEntity {

    @SerializedName("isPrimary")
    private boolean primary;

	@SerializedName("url")
	private String url;

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
