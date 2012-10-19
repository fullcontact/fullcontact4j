package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.fullcontact.api.libs.fullcontact4j.entity.name.stats.*;
import com.google.gson.annotations.SerializedName;

public class NameStatsEntity extends BaseEntity{

    @SerializedName("name")
    private NameStatsInfo nameStatsInfo;

    public NameStatsInfo getNameStatsInfo() {
        return nameStatsInfo;
    }

    public void setNameStatsInfo(NameStatsInfo nameStatsInfo) {
        this.nameStatsInfo = nameStatsInfo;
    }

}
