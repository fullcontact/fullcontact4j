package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;

import java.util.List;

public class CardReaderViewAllResponse extends FCResponse {
    private int currentPage;
    private int totalPages;
    private int totalRecords;
    private int count;
    private List<CardReaderFullResponse> results;

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
}
