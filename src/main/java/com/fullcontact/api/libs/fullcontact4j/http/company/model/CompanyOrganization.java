package com.fullcontact.api.libs.fullcontact4j.http.company.model;

import lombok.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class CompanyOrganization {

    private String name;
    private Integer approxEmployees;
    private String founded;
    private String overview;
    private CompanyContactInfo contactInfo;
    private List<KeyPerson> keyPeople = Collections.emptyList();
    private List<CompanyUrl> links = Collections.emptyList();
    private List<CompanyUrl> images = Collections.emptyList();
    private List<String> keywords = Collections.emptyList();

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class CompanyUrl {
        private String url;
        private String label;
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class CompanyContactInfo {
        private List<CompanyEmailAddress> emailAddresses;
        private List<CompanyPhoneNumber> phoneNumbers;
        private List<CompanyAddress> addresses;
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class CompanyPhoneNumber {
        private String number;
        private String label;
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class CompanyEmailAddress {
        private String value;
        private String label;

    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class CompanyAddress {
        private String addressLine1;
        private String addressLine2;
        private String locality;
        private String county;
        private CompanyAddressField region;
        private CompanyAddressField country;
        private String latitude;
        private String longitude;
        private String postalCode;
        private String timezone;
        private String label;
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class CompanyAddressField {
        private String name;
        private String code;
    }
}
