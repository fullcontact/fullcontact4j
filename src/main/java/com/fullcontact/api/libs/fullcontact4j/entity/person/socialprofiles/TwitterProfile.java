package com.fullcontact.api.libs.fullcontact4j.entity.person.socialprofiles;

import com.google.gson.annotations.SerializedName;

public class TwitterProfile extends SocialProfile{

    @SerializedName("rss")
    private String rss;

    @SerializedName("followers")
    private long followers;

    @SerializedName("following")
    private long following;

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }
}
