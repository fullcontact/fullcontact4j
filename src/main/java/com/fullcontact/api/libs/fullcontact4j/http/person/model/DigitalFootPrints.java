package com.fullcontact.api.libs.fullcontact4j.http.person.model;

import lombok.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@EqualsAndHashCode
@ToString
public class DigitalFootPrints {
    private List<DigitalFootPrintsTopic> topics = Collections.emptyList();
    private List<DigitalFootPrintsScore> scores = Collections.emptyList();

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class DigitalFootPrintsScore {
        private Float value;
        private String provider;
        private String type;
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class DigitalFootPrintsTopic {
        private String value;
        private String provider;
    }
}
