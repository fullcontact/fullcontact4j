package com.fullcontact.api.libs.fullcontact4j.entity.email;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DisposableResponse {


    private int statusCode;


    private String usernameSubAddressingStatus;


    private String disposableEmailDomainStatus;


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
