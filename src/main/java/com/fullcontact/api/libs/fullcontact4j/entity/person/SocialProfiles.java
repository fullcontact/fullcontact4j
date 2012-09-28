package com.fullcontact.api.libs.fullcontact4j.entity.person;

import com.fullcontact.api.libs.fullcontact4j.entity.person.socialprofiles.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.*;

public class SocialProfiles {

    private final static String FLICKR = SocialProfileType.flickr.typeId;
    private final static String LINKEDIN = SocialProfileType.linkedin.typeId;
    private final static String PICASA = SocialProfileType.picasa.typeId;
    private final static String TWITTER = SocialProfileType.twitter.typeId;
    private final static String YOUTUBE = SocialProfileType.youtube.typeId;

    private List<SocialProfile> profiles = new ArrayList<SocialProfile>();
    private Gson _gson;

    private SocialProfiles() {}

    public SocialProfiles(JsonElement socialProfilesJsonObject) {
        for (JsonElement profile : socialProfilesJsonObject.getAsJsonArray()){
            this.addProfileFromJson(profile);
        }
    }

    private Gson getGson() {
        if (_gson == null) {
            _gson = new Gson();
        }
        return _gson;
    }

    private SocialProfile extractSocialProfileFromJson(JsonElement jsonProfile) {
        SocialProfile socialProfile = null;
        String typeId = jsonProfile.getAsJsonObject().get("typeId").getAsString();

        if (typeId.equalsIgnoreCase(TWITTER)) {
            socialProfile = getGson().fromJson(jsonProfile, TwitterProfile.class);
        } else if (typeId.equalsIgnoreCase(LINKEDIN)) {
            socialProfile = getGson().fromJson(jsonProfile, LinkedInProfile.class);
        }  else if (typeId.equalsIgnoreCase(FLICKR)) {
            socialProfile = getGson().fromJson(jsonProfile, FlickrProfile.class);
        }  else if (typeId.equalsIgnoreCase(PICASA)) {
            socialProfile = getGson().fromJson(jsonProfile, PicasaProfile.class);
        }  else if (typeId.equalsIgnoreCase(YOUTUBE)) {
            socialProfile = getGson().fromJson(jsonProfile, YouTubeProfile.class);
        } else {
            socialProfile = getGson().fromJson(jsonProfile, SocialProfile.class);
        }
        return socialProfile;
    }

    private void addProfileFromJson(JsonElement jsonProfile) {
        profiles.add(extractSocialProfileFromJson(jsonProfile));
    }


    public List<SocialProfile> getAllSocialProfiles() {
        return profiles;
    }

    public TwitterProfile getTwitter() {
        return (TwitterProfile) getSocialProfile(SocialProfileType.twitter);
    }

    public LinkedInProfile getLinkedIn() {
        return (LinkedInProfile) getSocialProfile(SocialProfileType.linkedin);
    }

    public FlickrProfile getFlickr() {
        return (FlickrProfile) getSocialProfile(SocialProfileType.flickr);
    }

    public PicasaProfile getPicasa() {
        return (PicasaProfile) getSocialProfile(SocialProfileType.picasa);
    }

    public YouTubeProfile getYouTube() {
        return (YouTubeProfile) getSocialProfile(SocialProfileType.youtube);
    }

    public SocialProfile getSocialProfile(SocialProfileType profileType) {
        SocialProfile profile = null;
        for (SocialProfile socialProfile : profiles) {
            if (socialProfile.getProfileTypeId().equalsIgnoreCase(profileType.typeId)) {
                profile = socialProfile;
                break;
            }
        }
        return profile;
    }

}
