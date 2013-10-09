package com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1277016352898379358L;

	@SerializedName("name")
	private String name;

    @SerializedName("title")
    private String title;

    @SerializedName("isPrimary")
    private boolean isPrimary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}


