package com.fullcontact.api.libs.fullcontact4j.http.person.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Demographics {
    private String age;
    private String ageRange;
    private String children;
    private String education;
    private String gender;
    private String homeOwnerStatus;
    private String householdIncome;
    private String influencerScore;
    private String locationGeneral;
    private String maritalStatus;
    private DeducedLocation locationDeduced = new DeducedLocation();
}
