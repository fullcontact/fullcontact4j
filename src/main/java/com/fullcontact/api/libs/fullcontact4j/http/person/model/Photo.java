package com.fullcontact.api.libs.fullcontact4j.http.person.model;

public class Photo {
    private String typeId;
    private String typeName;
	private String url;
    private boolean isPrimary;

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }
}
