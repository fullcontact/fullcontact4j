package com.fullcontact.api.libs.fullcontact4j.entity.cardshark;

import com.google.gson.annotations.SerializedName;

public class ViewRequestEntity {

    @SerializedName("status")
    private int status;

    @SerializedName("results")
    private UploadRequestResult result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UploadRequestResult getResult() {
        return result;
    }

    public void setResult(UploadRequestResult result) {
        this.result = result;
    }
}
