package com.fullcontact.api.libs.fullcontact4j.http.location;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LocationNormalizationResponse extends FCResponse {

    private String city;
    private String county;
    private String continent;
    private double likelihood;
    private String normalizedLocation;
    private LocationCodeInfo state = new LocationCodeInfo();
    private LocationCodeInfo country = new LocationCodeInfo();

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
