package com.fullcontact.api.libs.fullcontact4j.entity.name;

import com.fullcontact.api.libs.fullcontact4j.entity.name.stats.NameStatsInfo;

public class NameStatsEntity extends BaseEntity{


    private NameStatsInfo nameStatsInfo;

    public NameStatsInfo getNameStatsInfo() {
        return nameStatsInfo;
    }

    public void setNameStatsInfo(NameStatsInfo nameStatsInfo) {
        this.nameStatsInfo = nameStatsInfo;
    }

}
