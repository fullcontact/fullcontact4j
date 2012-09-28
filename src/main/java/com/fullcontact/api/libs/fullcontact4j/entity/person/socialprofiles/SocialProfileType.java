package com.fullcontact.api.libs.fullcontact4j.entity.person.socialprofiles;

/**
 * Created by IntelliJ IDEA.
 * User: salil
 * Date: 2/10/12
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public enum SocialProfileType {
    angellist("angellist", "Angel List"),
    facebook("facebook", "Facebook"),
    linkedin("linkedin", "Linkedin"),
    gravatar("gravatar", "Gravatar"),
    myspace("myspace", "Myspace"),
    plaxo("plaxo", "Plaxo"),
    twitter("twitter", "Twitter"),
    friendster("friendster", "Friendster"),
    quora("quora", "Quora"),
    aboutme("aboutme", "About Me"),
    picasa("picasa", "Picasa"),
    flickr("flickr", "Flickr"),
    youtube("youtube", "Youtube"),
    livejournal("livejournal", "Live Journal"),
    posterous("posterous", "Posterous"),
    skype("skype", "Skype"),
    bebo("bebo", "Bebo"),
    blogger("blogger", "Blogger"),
    academiaedu("academiaedu", "Academia Edu"),
    delicious("delicious", "Delicious"),
    digg("digg", "Digg"),
    disqus("disqus", "Disqus"),
    foursquare("foursquare", "Foursquare"),
    googleprofile("googleprofile", "Google Profile"),
    googlereader("googlereader", "Google Reader"),
    googleplus("googleplus", "Google Plus"),
    googleusercontent("googleplus", "Google Plus"),
    github("github", "Github"),
    hackernews("hackernews", "Hacker News"),
    hiim("hiim", "Hi Im"),
    klout("klout", "Klout"),
    lanyrd("lanyrd", "Lanyrd"),
    lastfm("lastfm", "Last Fm"),
    other("other", "Other"),
    multiply("multiply", "Multiply"),
    friendfeed("friendfeed", "Friendfeed"),
    plancast("plancast", "Plancast"),
    slideshare("slideshare", "Slideshare"),
    soundcloud("soundcloud", "Sound Cloud"),
    stackexchange("stackexchange", "Stack Exchange"),
    stackoverflow("stackoverflow", "Stack Overflow"),
    steam("steam", "Steam"),
    tripit("tripit", "Tripit"),
    tumblr("tumblr", "Tumblr"),
    tungleme("tungleme", "Tungle Me"),
    typepad("typepad", "Typepad"),
    vimeo("vimeo", "Vimeo"),
    wordpress("wordpress", "Wordpress"),
    wordpresscom("wordpresscom", "Wordpress Com"),
    yahoo("yahoo", "yahoo"),
    peerindex("peerindex", "peerindex");
    // rainmaker("rainmaker", "Rainmaker")

    public final String typeId;
    public final String typeName;

    SocialProfileType(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public static SocialProfileType fromString(String profileType) {
        if (profileType != null) {
            return Enum.valueOf(SocialProfileType.class, profileType);
        }else{
            return null;
        }
    }


}
