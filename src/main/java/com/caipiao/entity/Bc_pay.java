//    Bc_pay.java

package com.caipiao.entity;

import com.caipiao.utils.ShowStatic;

public class Bc_pay {

    private int Pay_id;
    private int User_id;
    private String Pay_name;
    private String Pay_user;
    private String Pay_type;

    public Bc_pay() {
    }

    public int getPay_id() {
        return Pay_id;
    }

    public void setPay_id(int pay_id) {
        Pay_id = pay_id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getPay_name() {
        return Pay_name;
    }

    public void setPay_name(String pay_name) {
        Pay_name = pay_name;
    }

    public String getPay_user() {
        return Pay_user;
    }

    public void setPay_user(String pay_user) {
        Pay_user = pay_user;
    }

    public String getPay_type() {
        return Pay_type;
    }

    public String getPay_class() {
        return ShowStatic.BankLogoClass(Pay_type);
    }

    public void setPay_type(String pay_type) {
        Pay_type = pay_type;
    }

    public String getPayName() {
        String re = "--";
        if ("0".equals(Pay_type))
            re = "支付宝";
        else if ("1".equals(Pay_type))
            re = "财付通";
        return re;
    }
}
