package com.fullcontact.api.libs.fullcontact4j.http.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
}
