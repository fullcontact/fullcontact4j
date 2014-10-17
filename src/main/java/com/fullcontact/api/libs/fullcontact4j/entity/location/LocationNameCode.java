package com.fullcontact.api.libs.fullcontact4j.entity.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationNameCode {


    private String name;


    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
