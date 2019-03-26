package com.customerservice.login.ClassFiles;

import java.io.Serializable;

public class Flat implements Serializable {
    public String getFlatId() {
        return FlatId;
    }

    public void setFlatId(String flatId) {
        FlatId = flatId;
    }

    String FlatId;
    public String getBuldingName() {
        return BuldingName;
    }

    public void setBuldingName(String buldingName) {
        BuldingName = buldingName;
    }

    String FlatNumber,	BuildingId, BuldingName;

    public String getFlatNumber() {
        return FlatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        FlatNumber = flatNumber;
    }

    public String getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(String buildingId) {
        BuildingId = buildingId;
    }
}
