package com.caipiao.activity;


public class SignEntity {

    private int Id;
    private String Time;
    private int User_id;
    private int SignAll;
    private int BigSign;

    public SignEntity() {
    }

    public int getBigSign() {
        return BigSign;
    }

    public void setBigSign(int bigSign) {
        BigSign = bigSign;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public int getSignAll() {
        return SignAll;
    }

    public void setSignAll(int signAll) {
        SignAll = signAll;
    }
}
