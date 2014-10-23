package com.fullcontact.api.libs.fullcontact4j.response;

public class DisposableResponse extends FCResponse {

    private String usernameSubAddressingStatus;
    private String disposableEmailDomainStatus;
    private String message;

    public String getUsernameSubAddressingStatus() {
        return usernameSubAddressingStatus;
    }

    public String getDisposableEmailDomainStatus() {
        return disposableEmailDomainStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
