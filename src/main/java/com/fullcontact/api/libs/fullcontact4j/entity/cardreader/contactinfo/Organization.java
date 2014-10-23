package com.fullcontact.api.libs.fullcontact4j.entity.cardreader.contactinfo;

import java.io.Serializable;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1277016352898379358L;


	private String name;


    private String title;


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

    public boolean getIsPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}


