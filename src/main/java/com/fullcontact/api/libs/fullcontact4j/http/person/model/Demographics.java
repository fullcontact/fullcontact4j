package com.fullcontact.api.libs.fullcontact4j.http.person.model;

public class Demographics {
    private String age;
    private String ageRange;
    private String children;
    private String education;
    private String gender;
    private String homeOwnerStatus;
    private String householdIncome;
    private String influencerScore;
    private String locationGeneral;
    private String maritalStatus;
    private DeducedLocation locationDeduced = new DeducedLocation();
    public DeducedLocation getLocationDeduced() {
        return locationDeduced;
    }
    public String getAge() {
        return age;
    }
    public String getAgeRange() {
        return ageRange;
    }
    public String getChildren() {
        return children;
    }
    public String getEducation() {
        return education;
    }
    public String getGender() {
        return gender;
    }
    public String getHomeOwnerStatus() {
        return homeOwnerStatus;
    }
    public String getHouseholdIncome() {
        return householdIncome;
    }
    public String getInfluencerScore() {
        return influencerScore;
    }
    public String getLocationGeneral() {
        return locationGeneral;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Demographics{");
        sb.append("age='").append(age).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", locationGeneral='").append(locationGeneral).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
