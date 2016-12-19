package com.fullcontact.api.libs.fullcontact4j.http.person.model;
import lombok.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ContactInfo {
	private String familyName;
	private String fullName;
	private String givenName;
    private List<String> middleNames = Collections.emptyList();
    private List<Website> websites = Collections.emptyList();
    private List<Chat> chats = Collections.emptyList();

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class Chat {
        private String client;
        private String handle;
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class Website {
        private String url;
    }
}
