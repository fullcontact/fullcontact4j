package com.fullcontact.api.libs.fullcontact4j.http.company.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class CompanyIndustry {
    private String type;
    private String code;
    private String name;
}
