package com.fullcontact.api.libs.fullcontact4j.http.cardreader.model;

public class Name {

    private String familyName;
    private String givenName;
    private String middleName;
    private String honorificPrefix;
    private String honorificSuffix;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getHonorificPrefix() {
        return honorificPrefix;
    }

    public void setHonorificPrefix(String honorificPrefix) {
        this.honorificPrefix = honorificPrefix;
    }

    public String getHonorificSuffix() {
        return honorificSuffix;
    }

    public void setHonorificSuffix(String honorificSuffix) {
        this.honorificSuffix = honorificSuffix;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append((honorificPrefix==null?"":honorificPrefix + " "));
        sb.append((givenName==null?"":givenName + " "));
        sb.append((middleName==null?"":middleName + " "));
        sb.append((familyName==null?"":familyName + " "));
        sb.append((honorificSuffix==null?"":honorificSuffix + " "));
        return sb.toString();
    }
}
