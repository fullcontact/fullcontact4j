package com.fullcontact.api.libs.fullcontact4j.http.company;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcontact.api.libs.fullcontact4j.FullContactException;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import com.fullcontact.api.libs.fullcontact4j.http.company.model.CompanyOrganization;
import com.fullcontact.api.libs.fullcontact4j.http.company.model.CompanyTraffic;
import com.fullcontact.api.libs.fullcontact4j.http.person.model.SocialProfile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CompanyResponse extends FCResponse {
    private static ObjectMapper mapper = new ObjectMapper();

    private String message;
    private String requestId;
    private List<CompanyCategory> category = Collections.emptyList();
    private String favicon;
    private String logo;
    private String redirectsTo;
    private String website;
    private String languageLocale;
    private String onlineSince;
    private CompanyOrganization organization;
    private List<SocialProfile> socialProfiles = Collections.emptyList();
    private CompanyTraffic traffic;

    /**
     * Factory method to create a webhook response from json.
     * @param json
     * @return a new PersonResponse represented by the Json string
     * @throws com.fullcontact.api.libs.fullcontact4j.FullContactException if there is a parsing/mapping error.
     */
    public static CompanyResponse fromJson(String json) throws FullContactException {
        //Properties not present in the POJO are ignored instead of throwing exceptions
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //An empty string ("") is interpreted as null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try {
            return mapper.readValue(json, CompanyResponse.class);
        } catch(JsonMappingException e) {
            throw new FullContactException("Failed to convert person json to a response", e);
        } catch(JsonParseException e) {
            throw new FullContactException("Json is not valid format", e);
        } catch(IOException e) {
            throw new FullContactException("Unexpected exception when parsing json", e);
        }
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestId() {
        return requestId;
    }

    public List<CompanyCategory> getCategory() {
        return category;
    }

    public String getFavicon() {
        return favicon;
    }

    public String getLogo() {
        return logo;
    }

    public String getRedirectsTo() {
        return redirectsTo;
    }

    public String getWebsite() {
        return website;
    }

    public String getLanguageLocale() {
        return languageLocale;
    }

    public String getOnlineSince() {
        return onlineSince;
    }

    public CompanyOrganization getOrganization() {
        return organization;
    }

    public List<SocialProfile> getSocialProfiles() {
        return socialProfiles;
    }

    public CompanyTraffic getTraffic() {
        return traffic;
    }

    public static class CompanyCategory {

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }
}
