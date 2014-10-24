package com.fullcontact.api.libs.fullcontact4j.entity.name;

public class NameParserEntity extends BaseEntity{


    private NameParserInfo nameInfo;


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
