package com.fullcontact.api.libs.fullcontact4j.http.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DisposableEmailResponse{");
        sb.append("usernameSubAddressingStatus='").append(usernameSubAddressingStatus).append('\'');
        sb.append(", disposableEmailDomainStatus='").append(disposableEmailDomainStatus).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
