package com.fullcontact.api.libs.fullcontact4j.http.person.model;

public class Organization {

	private String name;
	private String startDate;
    private boolean current;
    private String endDate;
	private String title;
	private boolean isPrimary;

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public boolean isCurrent() {
        return current;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsPrimary() {
        return isPrimary;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Organization{");
        sb.append("name='").append(name).append('\'');
        sb.append(", current=").append(current);
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
