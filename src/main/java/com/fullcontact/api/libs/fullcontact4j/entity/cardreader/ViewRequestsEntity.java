package com.fullcontact.api.libs.fullcontact4j.entity.cardreader;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewRequestsEntity {

    @SerializedName("count")
    private int count;

    @SerializedName("status")
    private int status;

    @SerializedName("totalRecords")
    private int totalRecords;

    @SerializedName("currentPage")
    private int currentPage;

    @SerializedName("totalPages")
    private int totalPages;

    @SerializedName("results")
    private List<UploadRequestResult> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<UploadRequestResult> getResults() {
        return results;
    }

    public void setResults(List<UploadRequestResult> results) {
        this.results = results;
    }
}
