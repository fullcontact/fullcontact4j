package com.fullcontact.api.libs.fullcontact4j.entity.icon;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class IconListResponse {


    private int statusCode;


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
