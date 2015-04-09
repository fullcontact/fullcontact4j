package com.fullcontact.api.libs.fullcontact4j.http.company.model;

import java.util.Collections;
import java.util.List;

public class CompanyOrganization {

    private String name;
    private Integer approxEmployees;
    private String founded;
    private String overview;
    private CompanyContactInfo contactInfo;
    private List<KeyEmployee> keyEmployees = Collections.emptyList();
    private List<CompanyUrl> links = Collections.emptyList();
    private List<CompanyUrl> images = Collections.emptyList();
    private List<String> keywords = Collections.emptyList();


    public static class KeyEmployee {
        private String name;
        private String title;
        private String link;

        public String getName() {
            return name;
        }

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }
    }

    public static class CompanyUrl {
        private String url;
        private String label;

        public String getUrl() {
            return url;
        }

        public String getLabel() {
            return label;
        }
    }
    
    public static class CompanyContactInfo {
        private List<CompanyEmailAddress> emailAddresses;
        private List<CompanyPhoneNumber> phoneNumbers;
        private List<CompanyAddress> addresses;

        public List<CompanyEmailAddress> getEmailAddresses() {
            return emailAddresses;
        }

        public List<CompanyPhoneNumber> getPhoneNumbers() {
            return phoneNumbers;
        }

        public List<CompanyAddress> getAddresses() {
            return addresses;
        }
    }
    
    public static class CompanyPhoneNumber {
        private String number;
        private String label;

        public String getNumber() {
            return number;
        }

        public String getLabel() {
            return label;
        }
    }

    public static class CompanyEmailAddress {
        private String value;
        private String label;

        public String getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }
    }
    
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

        public String getAddressLine1() {
            return addressLine1;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public String getLocality() {
            return locality;
        }

        public String getCounty() {
            return county;
        }

        public CompanyAddressField getRegion() {
            return region;
        }

        public CompanyAddressField getCountry() {
            return country;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public String getTimezone() {
            return timezone;
        }

        public String getLabel() {
            return label;
        }
    }

    public static class CompanyAddressField {
        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }

    public String getName() {
        return name;
    }

    public Integer getApproxEmployees() {
        return approxEmployees;
    }

    public String getFounded() {
        return founded;
    }

    public String getOverview() {
        return overview;
    }

    public CompanyContactInfo getContactInfo() {
        return contactInfo;
    }

    public List<KeyEmployee> getKeyEmployees() {
        return keyEmployees;
    }

    public List<CompanyUrl> getLinks() {
        return links;
    }

    public List<CompanyUrl> getImages() {
        return images;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
