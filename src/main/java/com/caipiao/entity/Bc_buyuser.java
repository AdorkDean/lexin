//    Bc_buyuser.java

package com.caipiao.entity;

import com.caipiao.utils.UserSession;

public class Bc_buyuser {

    private int Buyuser_id;
    private String Buyuser_time;
    private int User_id;
    private String User_name;
    private String Buy_item;
    private double Buyuser_money;
    private double Buyuser_win;
    private String Auto_item;

    public Bc_buyuser() {
    }

    public double getBuyuser_win() {
        return Buyuser_win;
    }

    public void setBuyuser_win(double buyuser_win) {
        Buyuser_win = buyuser_win;
    }

    public String getUser_name() {
        return User_name;
    }

    public String getUser_nameDis() {
        return UserSession.DisUser(User_name);
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public int getBuyuser_id() {
        return Buyuser_id;
    }

    public void setBuyuser_id(int buyuser_id) {
        Buyuser_id = buyuser_id;
    }

    public String getBuyuser_time() {
        return Buyuser_time;
    }

    public void setBuyuser_time(String buyuser_time) {
        Buyuser_time = buyuser_time;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getBuy_item() {
        return Buy_item;
    }

    public void setBuy_item(String buy_item) {
        Buy_item = buy_item;
    }

    public double getBuyuser_money() {
        return Buyuser_money;
    }

    public void setBuyuser_money(double buyuser_money) {
        Buyuser_money = buyuser_money;
    }

    public String getAuto_item() {
        return Auto_item;
    }

    public void setAuto_item(String auto_item) {
        Auto_item = auto_item;
    }
}
