package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

import java.util.Collections;
import java.util.List;

public class CardReaderViewAllResponse extends FCResponse {
    private int currentPage;
    private int totalPages;
    private int totalRecords;
    private int count;
    private List<CardReaderFullResponse> results = Collections.emptyList();

    public List<CardReaderFullResponse> getResults() {
        return results;
    }

    public int getCount() {
        return count;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CardReaderViewAllResponse{");
        sb.append("results=").append(results.size());
        sb.append('}');
        return sb.toString();
    }
}
