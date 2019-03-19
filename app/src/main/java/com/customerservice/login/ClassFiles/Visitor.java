package com.customerservice.login.ClassFiles;

import java.io.Serializable;

public class Visitor implements Serializable {

    String  visitors_id,visitors_name,visitors_contect,visitors_flat_id,visitors_photo,visitors_watchman_id,visitors_v_date,visitors_v_time,visitors_flat_approve;
    String 	visitors_app_date_time,visitors_exit_date_time,BuildingName,BuildingId,FlatNumber,FlatId,user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

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

    public String getFlatNumber() {
        return FlatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        FlatNumber = flatNumber;
    }

    public String getFlatId() {
        return FlatId;
    }

    public void setFlatId(String flatId) {
        FlatId = flatId;
    }

    public String getVisitors_id() {
        return visitors_id;
    }

    public void setVisitors_id(String visitors_id) {
        this.visitors_id = visitors_id;
    }

    public String getVisitors_name() {
        return visitors_name;
    }

    public void setVisitors_name(String visitors_name) {
        this.visitors_name = visitors_name;
    }

    public String getVisitors_contect() {
        return visitors_contect;
    }

    public void setVisitors_contect(String visitors_contect) {
        this.visitors_contect = visitors_contect;
    }

    public String getVisitors_flat_id() {
        return visitors_flat_id;
    }

    public void setVisitors_flat_id(String visitors_flat_id) {
        this.visitors_flat_id = visitors_flat_id;
    }

    public String getVisitors_photo() {
        return visitors_photo;
    }

    public void setVisitors_photo(String visitors_photo) {
        this.visitors_photo = visitors_photo;
    }

    public String getVisitors_watchman_id() {
        return visitors_watchman_id;
    }

    public void setVisitors_watchman_id(String visitors_watchman_id) {
        this.visitors_watchman_id = visitors_watchman_id;
    }

    public String getVisitors_v_date() {
        return visitors_v_date;
    }

    public void setVisitors_v_date(String visitors_v_date) {
        this.visitors_v_date = visitors_v_date;
    }

    public String getVisitors_v_time() {
        return visitors_v_time;
    }

    public void setVisitors_v_time(String visitors_v_time) {
        this.visitors_v_time = visitors_v_time;
    }

    public String getVisitors_flat_approve() {
        return visitors_flat_approve;
    }

    public void setVisitors_flat_approve(String visitors_flat_approve) {
        this.visitors_flat_approve = visitors_flat_approve;
    }

    public String getVisitors_app_date_time() {
        return visitors_app_date_time;
    }

    public void setVisitors_app_date_time(String visitors_app_date_time) {
        this.visitors_app_date_time = visitors_app_date_time;
    }

    public String getVisitors_exit_date_time() {
        return visitors_exit_date_time;
    }

    public void setVisitors_exit_date_time(String visitors_exit_date_time) {
        this.visitors_exit_date_time = visitors_exit_date_time;
    }
}
