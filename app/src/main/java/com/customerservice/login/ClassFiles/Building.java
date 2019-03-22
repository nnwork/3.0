package com.customerservice.login.ClassFiles;

import java.io.Serializable;

public class Building implements Serializable
{

    String BuildingId;

    String BuildingName;

    public String getBuildingImage() {
        return BuildingImage;
    }

    public void setBuildingImage(String buildingImage) {
        BuildingImage = buildingImage;
    }

    String BuildingImage;

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
