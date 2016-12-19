package com.fullcontact.api.libs.fullcontact4j.http.person.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
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
}
