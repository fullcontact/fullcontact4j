package com.fullcontact.api.libs.fullcontact4j.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DisposableEmailResponse extends FCResponse {

    private String usernameSubAddressingStatus;
    private String disposableEmailDomainStatus;
    private String message;

    @JsonProperty("usernameSubAddressing")
    public String getUsernameSubAddressingStatus() {
        return usernameSubAddressingStatus;
    }

    @JsonProperty("disposableEmailDomain")
    public String getDisposableEmailDomainStatus() {
        return disposableEmailDomainStatus;
    }

    public String getMessage() {
        return message;
    }

}
