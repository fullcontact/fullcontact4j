package com.fullcontact.api.libs.fullcontact4j.http.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class Organization {
	private String name;
	private String startDate;
    private boolean current;
    private String endDate;
	private String title;
	@JsonProperty("isPrimary")
	private boolean isPrimary;
}
