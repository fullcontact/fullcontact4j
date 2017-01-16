package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties("status")
public class CardReaderViewAllResponse extends FCResponse {
    private int currentPage;
    private int totalPages;
    private int totalRecords;
    private int count;
    private List<CardReaderFullResponse> results = Collections.emptyList();
}
