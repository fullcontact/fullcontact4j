package com.fullcontact.api.libs.fullcontact4j.http.email;

public class EmailVerificationData {
    private String message;
    private String address;
    private String username;
    private String domain;
    private boolean corrected;
    private EmailVerificationAttributes attributes;
    private Boolean cached;
    private String person;
    private String company;
    private boolean sendSafely;

    private EmailVerificationData() {
        attributes = new EmailVerificationAttributes();
    }

    public String getMessage() {
        return message;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getDomain() {
        return domain;
    }

    public boolean isCorrected() {
        return corrected;
    }

    public EmailVerificationAttributes getAttributes() {
        return attributes;
    }

    public Boolean getCached() {
        return cached;
    }

    public String getPerson() {
        return person;
    }

    public String getCompany() {
        return company;
    }

    public boolean isSendSafely() {
        return sendSafely;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmailVerificationData{");
        sb.append("message='").append(message).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", domain='").append(domain).append('\'');
        sb.append(", corrected=").append(corrected);
        sb.append(", attributes=").append(attributes);
        sb.append(", cached=").append(cached);
        sb.append(", person='").append(person).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", sendSafely=").append(sendSafely);
        sb.append('}');
        return sb.toString();
    }

    public static class EmailVerificationAttributes {
        private boolean validSyntax;
        private boolean deliverable;
        private boolean catchall;
        private boolean risky;
        private boolean disposable;

        private EmailVerificationAttributes() {}

        public boolean isValidSyntax() {
            return validSyntax;
        }

        public boolean isDeliverable() {
            return deliverable;
        }

        public boolean isCatchall() {
            return catchall;
        }

        public boolean isRisky() {
            return risky;
        }

        public boolean isDisposable() {
            return disposable;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("EmailVerificationAttributes{");
            sb.append("validSyntax=").append(validSyntax);
            sb.append(", deliverable=").append(deliverable);
            sb.append(", catchall=").append(catchall);
            sb.append(", risky=").append(risky);
            sb.append(", disposable=").append(disposable);
            sb.append('}');
            return sb.toString();
        }
    }
}