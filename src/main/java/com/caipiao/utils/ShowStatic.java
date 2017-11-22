package com.caipiao.utils;

public class ShowStatic {
    public static String ShowLotStatus(String status) {
        String result = "";
        if ("-1".equals(status))
            result = "等待满员";
        else if ("0".equals(status))
            result = "等待出票";
        else if ("1".equals(status))
            result = "等待开奖";
        else if ("2".equals(status))
            result = "<font color=\"#ba2636\">已中奖</font>";
        else if ("3".equals(status))
            result = "未中奖";
        else if ("5".equals(status)) {
            result = "已撤单";
        }
        return result;
    }

    public static String ShowBuyStatus(String status) {
        String result = "已撤单";
        if ("0".equals(status))
            result = "<font color='#71B200'>进行中</font>";
        else if ("1".equals(status))
            result = "<font color='#CC0000'>已中奖</font>";
        else if ("-1".equals(status))
            result = "<font color='#1E50A2'>未满员</font>";
        else if ("2".equals(status)) {
            result = "未中奖";
        }
        return result;
    }

    public static String BankLogoClass(String bank) {
        String clazz = "";
        if ("工商银行".equals(bank))
            clazz = "b_0001";
        else if ("农业银行".equals(bank))
            clazz = "b_0002";
        else if ("0".equals(bank))
            clazz = "";
        else if ("1".equals(bank)) {
            clazz = "";
        }
        return clazz;
    }
}