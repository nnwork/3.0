package com.customerservice.login.ClassFiles;

import java.io.Serializable;

public class Building implements Serializable
{
    String BuildingId;
    String BuildingName;

    public String getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(String buildingId) {
        BuildingId = buildingId;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }



}
