package com.fullcontact.api.libs.fullcontact4j.http.person.model;

import lombok.*;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Macromeasures {
    private List<Interest> interests = Collections.emptyList();

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Interest {
        private String name;
        private String id;
        private Double score;
        private List<String> parents = Collections.emptyList();
        private String category;
    }
}
