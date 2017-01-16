package com.fullcontact.api.libs.fullcontact4j.http.email;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmailVerificationResponse extends FCResponse {
    private String requestId;
    private Map<String, EmailVerificationData> emails = Collections.emptyMap();
    private String message;
    private List<String> failedEmails = Collections.emptyList();
    private List<String> unknownEmails = Collections.emptyList();
}