package com.fullcontact.api.libs.fullcontact4j.http.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Organization {
	@Getter private String name;
	@Getter private String startDate;
    @Getter private boolean current;
    @Getter private String endDate;
	@Getter private String title;
	@JsonProperty("isPrimary")
	private boolean isPrimary;

	@JsonProperty("isPrimary")
	public boolean isPrimary() {
		return isPrimary;
	}
}
