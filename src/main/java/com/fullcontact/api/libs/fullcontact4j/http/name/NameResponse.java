package com.fullcontact.api.libs.fullcontact4j.http.name;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NameResponse extends FCResponse {
    private String region;
    private double likelihood;
    private NameDetails nameDetails = new NameDetails();

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class NameDetails {
        private String givenName;
        private String familyName;
        private List<String> middleNames = Collections.emptyList();
        private List<String> prefixes = Collections.emptyList();
        private List<String> suffixes = Collections.emptyList();
        private List<String> nicknames = Collections.emptyList();
        private String fullName;

    }
}
