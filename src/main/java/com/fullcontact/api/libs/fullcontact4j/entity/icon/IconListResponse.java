package com.fullcontact.api.libs.fullcontact4j.entity.icon;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class IconListResponse {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("valid_icons")
    List<String> validIconTypes = new ArrayList<String>();

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getValidIconTypes() {
        return validIconTypes;
    }

    public void setValidIconTypes(List<String> validIconTypes) {
        this.validIconTypes = validIconTypes;
    }
}
