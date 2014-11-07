package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

import java.util.Collections;
import java.util.List;

public class NameResponse extends FCResponse {

    private String region;
    private double likelihood;
    private NameDetails nameDetails = new NameDetails();

    public NameDetails getNameDetails() {
        return nameDetails;
    }

    public double getLikelihood() { return likelihood; }

    public String getRegion() {
        return region;
    }

    public static class NameDetails {
        private String givenName;
        private String familyName;
        private List<String> middleNames = Collections.emptyList();
        private List<String> prefixes = Collections.emptyList();
        private List<String> suffixes = Collections.emptyList();
        private List<String> nicknames = Collections.emptyList();
        private String fullName;

        public String getFullName() {
            return fullName;
        }

        public List<String> getNicknames() {
            return nicknames;
        }

        public List<String> getPrefixes() { return prefixes; }

        public List<String> getSuffixes() {
            return suffixes;
        }

        public List<String> getMiddleNames() {
            return middleNames;
        }

        public String getFamilyName() {
            return familyName;
        }

        public String getGivenName() {
            return givenName;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("NameDetails{");
            sb.append("fullName='").append(fullName).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NameResponse{");
        sb.append("nameDetails=").append(nameDetails);
        sb.append('}');
        return sb.toString();
    }
}
