package com.fullcontact.api.libs.fullcontact4j.entity.email;

import com.google.gson.annotations.SerializedName;

public class DisposableResponse {

    @SerializedName("status")
    private int statusCode;

    @SerializedName("usernameSubAddressing")
    private String usernameSubAddressingStatus;

    @SerializedName("disposableEmailDomain")
    private String disposableEmailDomainStatus;

    @SerializedName("message")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUsernameSubAddressingStatus() {
        return usernameSubAddressingStatus;
    }

    public void setUsernameSubAddressingStatus(String usernameSubAddressingStatus) {
        this.usernameSubAddressingStatus = usernameSubAddressingStatus;
    }

    public String getDisposableEmailDomainStatus() {
        return disposableEmailDomainStatus;
    }

    public void setDisposableEmailDomainStatus(String disposableEmailDomainStatus) {
        this.disposableEmailDomainStatus = disposableEmailDomainStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
