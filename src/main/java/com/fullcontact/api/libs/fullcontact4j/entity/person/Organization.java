package com.fullcontact.api.libs.fullcontact4j.entity.person;

import com.google.gson.annotations.SerializedName;

public class Organization {

	@SerializedName("name")
	private String name;

	@SerializedName("startDate")
	private String startDate;

	@SerializedName("title")
	private String title;

	@SerializedName("isPrimary")
	private boolean primary;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
}
