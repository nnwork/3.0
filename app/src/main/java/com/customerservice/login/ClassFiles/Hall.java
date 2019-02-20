package com.customerservice.login.ClassFiles;

import java.io.Serializable;

public class Hall implements Serializable {
    String hall_id,hall_title,hall_capacity,hall_img_1,	hall_img_2;

    public String getHall_id() {
        return hall_id;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public String getHall_title() {
        return hall_title;
    }

    public void setHall_title(String hall_title) {
        this.hall_title = hall_title;
    }

    public String getHall_capacity() {
        return hall_capacity;
    }

    public void setHall_capacity(String hall_capacity) {
        this.hall_capacity = hall_capacity;
    }

    public String getHall_img_1() {
        return hall_img_1;
    }

    public void setHall_img_1(String hall_img_1) {
        this.hall_img_1 = hall_img_1;
    }

    public String getHall_img_2() {
        return hall_img_2;
    }

    public void setHall_img_2(String hall_img_2) {
        this.hall_img_2 = hall_img_2;
    }
}
