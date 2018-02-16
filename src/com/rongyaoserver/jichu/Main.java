package com.rongyaoserver.jichu;

import com.rongyaoserver.jichu.entity.Home;
import com.rongyaoserver.jichu.util.DBHelper;

import java.util.List;

public class Main {
    public static void main(String[] args){
        try {
            Object[] obj = new Object[]{"admin"};
            List<Home> homeList = new DBHelper().executeQuery("select * from u_home where h_user = ?",obj,Home.class);
            for (Home home : homeList) {
                System.out.println(home.getH_name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
