package com.fullcontact.api.libs.fullcontact4j.entity.location;

import com.google.gson.annotations.SerializedName;

public class LocationNameCode {

    @SerializedName("name")
    private String name;

    @SerializedName("code")
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
