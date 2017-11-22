//    MyOrderOut.java

package com.caipiao.entity.out;

import com.caipiao.utils.LotEmun;
import com.caipiao.utils.ShowStatic;
import com.caipiao.utils.UserSession;

public class MyOrderOut {

    private String Buyuser_time;
    private double Buyuser_money;
    private double Buyuser_win;
    private String Buy_fqihao;
    private String Buy_lot;
    private String User_name;
    private double Buy_money;
    private String Buy_item;
    private String Buy_status;

    public MyOrderOut() {
    }

    public String getBuyuser_time() {
        return Buyuser_time;
    }

    public void setBuyuser_time(String buyuser_time) {
        Buyuser_time = buyuser_time;
    }

    public double getBuyuser_money() {
        return Buyuser_money;
    }

    public void setBuyuser_money(double buyuser_money) {
        Buyuser_money = buyuser_money;
    }

    public double getBuyuser_win() {
        return Buyuser_win;
    }

    public void setBuyuser_win(double buyuser_win) {
        Buyuser_win = buyuser_win;
    }

    public String getBuy_fqihao() {
        return Buy_fqihao.substring(2);
    }

    public void setBuy_fqihao(String buy_fqihao) {
        Buy_fqihao = buy_fqihao;
    }

    public String getBuy_lot() {
        return LotEmun.valueOf(Buy_lot).namestr;
    }

    public void setBuy_lot(String buy_lot) {
        Buy_lot = buy_lot;
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

    public double getBuy_money() {
        return Buy_money;
    }

    public void setBuy_money(double buy_money) {
        Buy_money = buy_money;
    }

    public String getBuy_item() {
        return Buy_item;
    }

    public void setBuy_item(String buy_item) {
        Buy_item = buy_item;
    }

    public String getBuy_status() {
        return ShowStatic.ShowBuyStatus(Buy_status);
    }

    public void setBuy_status(String buy_status) {
        Buy_status = buy_status;
    }
}
