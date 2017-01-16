package com.fullcontact.api.libs.fullcontact4j.http.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class Photo {
    private String typeId;
    private String typeName;
    private String type;
	private String url;
	@JsonProperty("isPrimary")
    private boolean isPrimary;
}
