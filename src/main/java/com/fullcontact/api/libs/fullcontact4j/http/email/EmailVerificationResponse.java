package com.fullcontact.api.libs.fullcontact4j.http.email;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

import java.util.*;

public class EmailVerificationResponse extends FCResponse {
    private String requestId;
    private Map<String, EmailVerificationData> emails;
    private String message;
    private List<String> failedEmails;
    private List<String> unknownEmails;

    private EmailVerificationResponse() {
        this.emails = new HashMap<String, EmailVerificationData>();
        this.failedEmails = new ArrayList<String>();
        this.unknownEmails = new ArrayList<String>();
    }

    public String getRequestId() {
        return requestId;
    }

    public Map<String, EmailVerificationData> getEmails() {
        return emails;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getFailedEmails() {
        return failedEmails;
    }

    public List<String> getUnknownEmails() {
        return unknownEmails;
    }
}