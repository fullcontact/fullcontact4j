package com.fullcontact.api.libs.fullcontact4j.entity.cardshark;

import com.google.gson.annotations.SerializedName;

public class UploadRequestResult {

    @SerializedName("status")
    private String statusCode;

    @SerializedName("id")
    private String requestId;

    @SerializedName("message")
    private String message;

    @SerializedName("vCardUrl")
    private String vCardUrl;

    @SerializedName("contact")
    private ContactInfo contact;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getvCardUrl() {
        return vCardUrl;
    }

    public void setvCardUrl(String vCardUrl) {
        this.vCardUrl = vCardUrl;
    }

    public ContactInfo getContact() {
        return contact;
    }

    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }
}
