package com.fullcontact.api.libs.fullcontact4j.entity.enhanced;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Organizations {

	@SerializedName("name")
	private String organizationName;

    @SerializedName("title")
    private String organizationTitle;

    @SerializedName("revenueRange")
    private String revenueRange;

    @SerializedName("employeeCountRange")
    private String employeeCountRange;

	@SerializedName("isPrimary")
	private boolean primary;

    @SerializedName("positionFunctions")
    private List<String> positionFunctions;

    @SerializedName("domains")
    private List<String> domains;

    @SerializedName("industries")
    private List<String> industries;

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

    public String getRevenueRange() {
        return revenueRange;
    }

    public void setRevenueRange(String revenueRange) {
        this.revenueRange = revenueRange;
    }

    public String getEmployeeCountRange() {
        return employeeCountRange;
    }

    public void setEmployeeCountRange(String employeeCountRange) {
        this.employeeCountRange = employeeCountRange;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public List<String> getPositionFunctions() {
        return positionFunctions;
    }

    public void setPositionFunctions(List<String> positionFunctions) {
        this.positionFunctions = positionFunctions;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<String> getIndustries() {
        return industries;
    }

    public void setIndustries(List<String> industries) {
        this.industries = industries;
    }
}
