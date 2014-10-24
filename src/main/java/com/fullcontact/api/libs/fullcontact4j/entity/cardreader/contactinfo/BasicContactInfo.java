package com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo;

import java.io.Serializable;

public class BasicContactInfo implements Serializable {
    private static final long serialVersionUID = 3848544145278546449L;


	private String type;


    private String value;


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


