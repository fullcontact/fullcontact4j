package com.fullcontact.api.libs.fullcontact4j.entity.person;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

public class SocialProfile {
    private String typeId;
    private String type;
    private String typeName;
	private String url;
	private String id;
	private String username;
    private String bio;
    private String rss;
    private int following;
    private int followers;

    public String getType() {
        return type;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getRss() {
        return rss;
    }

    public int getFollowing() {
        return following;
    }

    public int getFollowers() {
        return followers;
    }
}
