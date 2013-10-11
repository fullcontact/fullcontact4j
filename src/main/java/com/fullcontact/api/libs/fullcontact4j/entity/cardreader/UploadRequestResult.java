package com.fullcontact.api.libs.fullcontact4j.entity.cardreader;

import com.fullcontact.api.libs.fullcontact4j.enums.CardReaderVerification;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashMap;

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
    private Date submitted;

    @SerializedName("lastWebhookAttempt")
    private Date lastWebhookAttempt;

    @SerializedName("webhookAttempts")
    private Integer webhookAttempts;

    @SerializedName("quality")
    private CardReaderVerification quality;

    @SerializedName("webhookUrl")
    private String webhookUrl;

    // Custom param support. These are pass-through values
    @SerializedName("params")
    private HashMap<String,String> params;

    @SerializedName("clientServerResponseCode")
    private Integer clientServerResponseCode;

    // First 512 characters of the response body client server responds with
    @SerializedName("clientServerResponseBody")
    private String clientServerResponseBody;

    @SerializedName("URID")
    private String URID;

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

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getLastWebhookAttempt() {
        return lastWebhookAttempt;
    }

    public void setLastWebhookAttempt(Date lastWebhookAttempt) {
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

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public Integer getClientServerResponseCode() {
        return clientServerResponseCode;
    }

    public void setClientServerResponseCode(Integer clientServerResponseCode) {
        this.clientServerResponseCode = clientServerResponseCode;
    }

    public String getClientServerResponseBody() {
        return clientServerResponseBody;
    }

    public void setClientServerResponseBody(String clientServerResponseBody) {
        this.clientServerResponseBody = clientServerResponseBody;
    }

    public String getURID() {
        return URID;
    }

    public void setURID(String URID) {
        this.URID = URID;
    }

}
