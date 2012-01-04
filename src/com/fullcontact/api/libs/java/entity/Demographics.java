package com.fullcontact.api.libs.java.entity;

import com.google.gson.annotations.SerializedName;

public class Demographics {

    /** Field description */
    @SerializedName("age")
    private String age;

    /** Field description */
    @SerializedName("children")
    private String children;

    /** Field description */
    @SerializedName("education")
    private String education;

    /** Field description */
    @SerializedName("gender")
    private String gender;

    /** Field description */
    @SerializedName("homeOwnerStatus")
    private String homeOwnerStatus;

    /** Field description */
    @SerializedName("householdIncome")
    private String householdIncome;

    /** Field description */
    @SerializedName("influencerScore")
    private String influencerScore;

    /** Field description */
    @SerializedName("locationGeneral")
    private String locationGeneral;

    /** Field description */
    @SerializedName("maritalStatus")
    private String maritalStatus;

    /**
     * Method description
     *
     *
     * @return
     */
    public String getInfluencerScore() {
        return influencerScore;
    }

    /**
     * Method description
     *
     *
     * @param influencerScore
     */
    public void setInfluencerScore(String influencerScore) {
        this.influencerScore = influencerScore;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getHouseholdIncome() {
        return householdIncome;
    }

    /**
     * Method description
     *
     *
     * @param householdIncome
     */
    public void setHouseholdIncome(String householdIncome) {
        this.householdIncome = householdIncome;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getAge() {
        return age;
    }

    /**
     * Method description
     *
     *
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getHomeOwnerStatus() {
        return homeOwnerStatus;
    }

    /**
     * Method description
     *
     *
     * @param homeOwnerStatus
     */
    public void setHomeOwnerStatus(String homeOwnerStatus) {
        this.homeOwnerStatus = homeOwnerStatus;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getLocationGeneral() {
        return locationGeneral;
    }

    /**
     * Method description
     *
     *
     * @param locationGeneral
     */
    public void setLocationGeneral(String locationGeneral) {
        this.locationGeneral = locationGeneral;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getChildren() {
        return children;
    }

    /**
     * Method description
     *
     *
     * @param children
     */
    public void setChildren(String children) {
        this.children = children;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * Method description
     *
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Method description
     *
     *
     * @param maritalStatus
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * Method description
     *
     *
     * @param education
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getEducation() {
        return education;
    }
}
