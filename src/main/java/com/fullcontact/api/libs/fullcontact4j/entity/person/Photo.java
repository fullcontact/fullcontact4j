package com.fullcontact.api.libs.fullcontact4j.entity.person;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect
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
