package com.fullcontact.api.libs.fullcontact4j.entity.person.socialprofiles;

import com.google.gson.annotations.SerializedName;

public class LinkedInProfile extends SocialProfile{

    @SerializedName("followers")
    private long followers;

    @SerializedName("following")
    private long following;

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
