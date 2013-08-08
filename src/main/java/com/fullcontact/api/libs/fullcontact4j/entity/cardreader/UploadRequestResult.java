package com.fullcontact.api.libs.fullcontact4j.entity.cardreader;

import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderVerification;
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

    @SerializedName("submitted")
    private String submitted;

    @SerializedName("lastWebhookAttempt")
    private String lastWebhookAttempt;

    @SerializedName("webhookAttempts")
    private Integer webhookAttempts;

    @SerializedName("quality")
    private CardReaderVerification quality;

    @SerializedName("webhookUrl")
    private String webhookUrl;

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

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getLastWebhookAttempt() {
        return lastWebhookAttempt;
    }

    public void setLastWebhookAttempt(String lastWebhookAttempt) {
        this.lastWebhookAttempt = lastWebhookAttempt;
    }

    public Integer getWebhookAttempts() {
        return webhookAttempts;
    }

    public void setWebhookAttempts(Integer webhookAttempts) {
        this.webhookAttempts = webhookAttempts;
    }

    public CardReaderVerification getQuality() {
        return quality;
    }

    public void setQuality(CardReaderVerification quality) {
        this.quality = quality;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }
}
