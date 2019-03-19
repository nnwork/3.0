package com.customerservice.login.ClassFiles;

import java.io.Serializable;

public class ComplainClassFile implements Serializable {

    String complain_id,complain_user_id,complain_hcat_id,complain_problem,complain_img_uri,complain_date,complain_status,complain_v_date,complain_v_time,complain_date_time;

    public String getComplain_problem() {
        return complain_problem;
    }

    public String getComplain_id() {
        return complain_id;
    }

    public void setComplain_id(String complain_id) {
        this.complain_id = complain_id;
    }

    public String getComplain_user_id() {
        return complain_user_id;
    }

    public void setComplain_user_id(String complain_user_id) {
        this.complain_user_id = complain_user_id;
    }

    public String getComplain_hcat_id() {
        return complain_hcat_id;
    }

    public void setComplain_hcat_id(String complain_hcat_id) {
        this.complain_hcat_id = complain_hcat_id;
    }

    public String getComplain_img_uri() {
        return complain_img_uri;
    }

    public void setComplain_img_uri(String complain_img_uri) {
        this.complain_img_uri = complain_img_uri;
    }

    public String getComplain_date() {
        return complain_date;
    }

    public void setComplain_date(String complain_date) {
        this.complain_date = complain_date;
    }

    public String getComplain_status() {
        return complain_status;
    }

    public void setComplain_status(String complain_status) {
        this.complain_status = complain_status;
    }

    public String getComplain_v_date() {
        return complain_v_date;
    }

    public void setComplain_v_date(String complain_v_date) {
        this.complain_v_date = complain_v_date;
    }

    public String getComplain_v_time() {
        return complain_v_time;
    }

    public void setComplain_v_time(String complain_v_time) {
        this.complain_v_time = complain_v_time;
    }

    public String getComplain_date_time() {
        return complain_date_time;
    }

    public void setComplain_date_time(String complain_date_time) {
        this.complain_date_time = complain_date_time;
    }

    public void setComplain_problem(String complain_problem) {
        this.complain_problem = complain_problem;
    }
}
