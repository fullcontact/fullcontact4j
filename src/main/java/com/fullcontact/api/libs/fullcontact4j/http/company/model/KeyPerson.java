package com.fullcontact.api.libs.fullcontact4j.http.company.model;

import com.fullcontact.api.libs.fullcontact4j.http.person.PersonRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyPerson {
    private static final Pattern LOOKUP_REGEX = Pattern.compile("lookup=([^&]+)");
    private String name;
    private String title;
    private String link;

    //for testing
    public KeyPerson(String name, String title, String link) {
        this.name = name;
        this.title = title;
        this.link = link;
    }

    //for Jackson
    KeyPerson() {}

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    /**
     * Converts the `link` field, if present, into a Person API Request.
     * @return the person api request preloaded, or null if there is no link/the link is malformed.
     */
    public PersonRequest.Builder toPersonRequestOrNull() {
        if(link == null || link.isEmpty()) {
            return null;
        }
        try {
            Matcher lookupMatcher = LOOKUP_REGEX.matcher(link);
            if(!lookupMatcher.find()) {
                return null;
            }
            return new PersonRequest.Builder().lookup(lookupMatcher.group(1));
        } catch(IllegalStateException e) {
            return null;
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyPerson keyPerson = (KeyPerson) o;

        if (link != null ? !link.equals(keyPerson.link) : keyPerson.link != null) return false;
        if (name != null ? !name.equals(keyPerson.name) : keyPerson.name != null) return false;
        if (title != null ? !title.equals(keyPerson.title) : keyPerson.title != null) return false;

        return true;
    }
}
