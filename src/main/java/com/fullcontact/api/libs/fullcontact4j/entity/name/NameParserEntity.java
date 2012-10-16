package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.google.gson.annotations.SerializedName;

public class NameParserEntity extends BaseEntity{

    @SerializedName("result")
    private NameParserInfo nameInfo;

    @SerializedName("ambiguousName")
    private String ambiguousName;

    public NameParserInfo getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(NameParserInfo nameInfo) {
        this.nameInfo = nameInfo;
    }

    public String getAmbiguousName() {
        return ambiguousName;
    }

    public void setAmbiguousName(String ambiguousName) {
        this.ambiguousName = ambiguousName;
    }
}
