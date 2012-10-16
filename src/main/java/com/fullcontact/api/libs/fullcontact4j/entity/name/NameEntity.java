package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.google.gson.annotations.SerializedName;

public class NameEntity extends BaseEntity {

    @SerializedName("nameDetails")
    private NameInfo nameInfo;

    public NameInfo getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(NameInfo nameInfo) {
        this.nameInfo = nameInfo;
    }

}
