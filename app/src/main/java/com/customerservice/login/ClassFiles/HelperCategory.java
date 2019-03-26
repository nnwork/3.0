package com.customerservice.login.ClassFiles;

import java.io.Serializable;

public class HelperCategory implements Serializable {
    String hcat_id,	hcat_name,hcat_icon;

    public String getHcat_id() {
        return hcat_id;
    }

    public void setHcat_id(String hcat_id) {
        this.hcat_id = hcat_id;
    }

    public String getHcat_name() {
        return hcat_name;
    }

    public void setHcat_name(String hcat_name) {
        this.hcat_name = hcat_name;
    }

    public String getHcat_icon() {
        return hcat_icon;
    }

    public void setHcat_icon(String hcat_icon) {
        this.hcat_icon = hcat_icon;
    }
}
