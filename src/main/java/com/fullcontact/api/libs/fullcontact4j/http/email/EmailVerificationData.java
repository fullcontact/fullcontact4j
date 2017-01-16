package com.fullcontact.api.libs.fullcontact4j.http.email;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class EmailVerificationData {
    private String message;
    private String address;
    private String username;
    private String domain;
    private boolean corrected;
    private EmailVerificationAttributes attributes = new EmailVerificationAttributes();
    private Boolean cached;
    private String person;
    private String company;
    private boolean sendSafely;

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class EmailVerificationAttributes {
        private boolean validSyntax;
        private boolean deliverable;
        private boolean catchall;
        private boolean risky;
        private boolean disposable;
    }
}