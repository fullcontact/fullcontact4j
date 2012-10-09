package com.fullcontact.api.libs.fullcontact4j.entity.enhanced;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Organization {

	@SerializedName("name")
	private String name;

    @SerializedName("title")
    private String title;

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
