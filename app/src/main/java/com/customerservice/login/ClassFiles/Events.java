package com.customerservice.login.ClassFiles;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Events implements Serializable {
    String 	event_id,cat_id,event_title,event_desc,event_venue;
    String event_address,event_landmark,pincode,event_date,event_time,event_speciality,added_datetime;

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_venue() {
        return event_venue;
    }

    public void setEvent_venue(String event_venue) {
        this.event_venue = event_venue;
    }

    public String getEvent_address() {
        return event_address;
    }

    public void setEvent_address(String event_address) {
        this.event_address = event_address;
    }

    public String getEvent_landmark() {
        return event_landmark;
    }

    public void setEvent_landmark(String event_landmark) {
        this.event_landmark = event_landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getEvent_speciality() {
        return event_speciality;
    }

    public void setEvent_speciality(String event_speciality) {
        this.event_speciality = event_speciality;
    }

    public String getAdded_datetime() {
        return added_datetime;
    }

    public void setAdded_datetime(String added_datetime) {
        this.added_datetime = added_datetime;
    }
}
