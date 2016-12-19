package com.fullcontact.api.libs.fullcontact4j.http.location;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LocationEnrichmentResponse extends FCResponse {
    private List<Location> locations = Collections.emptyList();

    public List<Location> getPossibleLocations() {
        return locations;
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class Location {
        private String city;
        private String county;
        private LocationCodeInfo state = new LocationCodeInfo();
        private LocationCodeInfo country = new LocationCodeInfo();
        private String continent;
        private int population;
        private String normalizedLocation;

    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class LocationCodeInfo {
        private String name;
        private String code;

    }
}
