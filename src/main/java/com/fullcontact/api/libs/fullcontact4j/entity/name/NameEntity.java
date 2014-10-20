package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameEntity extends BaseEntity {


    private NameInfo nameInfo;

    public NameInfo getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(NameInfo nameInfo) {
        this.nameInfo = nameInfo;
    }

}
