package com.fullcontact.api.libs.fullcontact4j.entity.person;

import com.google.gson.annotations.SerializedName;

public class Organizations {

	@SerializedName("name")
	private String organizationName;

	@SerializedName("startDate")
	private String organizationStartDate;

	@SerializedName("title")
	private String organizationTitle;

	@SerializedName("isPrimary")
	private boolean primary;

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationTitle() {
		return organizationTitle;
	}

	public void setOrganizationTitle(String organizationTitle) {
		this.organizationTitle = organizationTitle;
	}

	public String getOrganizationStartDate() {
		return organizationStartDate;
	}

	public void setOrganizationStartDate(String organizationStartDate) {
		this.organizationStartDate = organizationStartDate;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
}
