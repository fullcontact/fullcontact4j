package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NameParseResponse extends FCResponse {

    private String ambiguousName;
    private Result result = new Result();
    private String region;

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class Result {
        private String givenName;
        private String familyName;
        private double likelihood;

    }
}
