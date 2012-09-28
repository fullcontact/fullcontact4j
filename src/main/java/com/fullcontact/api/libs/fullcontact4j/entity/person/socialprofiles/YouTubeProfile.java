package com.fullcontact.api.libs.fullcontact4j.entity.person.socialprofiles;

import com.google.gson.annotations.SerializedName;

public class YouTubeProfile extends SocialProfile{

    @SerializedName("rss")
    private String rss;

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

}
