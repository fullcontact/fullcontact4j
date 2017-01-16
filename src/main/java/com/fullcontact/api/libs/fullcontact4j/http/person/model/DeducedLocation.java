package com.fullcontact.api.libs.fullcontact4j.http.person.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class DeducedLocation {
    private double likelihood;
    private String normalizedLocation;
    private String deducedLocation;
    private LocationEntry city = new LocationEntry();
    private LocationEntry state = new LocationEntry();
    private LocationEntry country = new LocationEntry();
    private LocationEntry continent = new LocationEntry();
    private LocationEntry county = new LocationEntry();

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class LocationEntry {
        private boolean deduced;
        private String name;
        private String code;

    }

}
