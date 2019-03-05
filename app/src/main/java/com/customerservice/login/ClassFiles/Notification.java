package com.customerservice.login.ClassFiles;

import java.io.Serializable;

public class Notification implements Serializable {

    String notification_id,user_id,notification_title,notification_description,description_date_time;

    public String getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNotification_title() {
        return notification_title;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
    }

    public String getNotification_description() {
        return notification_description;
    }

    public void setNotification_description(String notification_description) {
        this.notification_description = notification_description;
    }

    public String getDescription_date_time() {
        return description_date_time;
    }

    public void setDescription_date_time(String description_date_time) {
        this.description_date_time = description_date_time;
    }

}
