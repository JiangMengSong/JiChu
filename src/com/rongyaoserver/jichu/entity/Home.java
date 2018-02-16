package com.rongyaoserver.jichu.entity;

import java.util.Date;

public class Home {
    private int h_id;
    private double h_x;
    private double h_y;
    private double h_z;
    private String h_world;
    private Date h_date;
    private String h_name;
    private String h_user;

    public int getH_id() {
        return h_id;
    }

    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    public double getH_x() {
        return h_x;
    }

    public void setH_x(double h_x) {
        this.h_x = h_x;
    }

    public double getH_y() {
        return h_y;
    }

    public void setH_y(double h_y) {
        this.h_y = h_y;
    }

    public double getH_z() {
        return h_z;
    }

    public void setH_z(double h_z) {
        this.h_z = h_z;
    }

    public String getH_world() {
        return h_world;
    }

    public void setH_world(String h_world) {
        this.h_world = h_world;
    }

    public Date getH_date() {
        return h_date;
    }

    public void setH_date(Date h_date) {
        this.h_date = h_date;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getH_user() {
        return h_user;
    }

    public void setH_user(String h_user) {
        this.h_user = h_user;
    }
}
