//    BankName.java

package com.caipiao.pay.chinabank;


public class BankName {

    public BankName() {
    }

    public static String GetName(String ids) {
        String result = ids;
        if ("1025".equals(ids) || "1023".equals(ids) || "3272".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#中国工商银行").toString();
        else if ("103".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#中国农业银行").toString();
        else if ("104".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#中国银行").toString();
        else if ("105".equals(ids) || "1054".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#中国建设银行").toString();
        else if ("301".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#交通银行").toString();
        else if ("302".equals(ids) || "303".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#宁波银行").toString();
        else if ("306".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#广东发展银行").toString();
        else if ("307".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#平安银行").toString();
        else if ("3080".equals(ids) || "308".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#招商银行").toString();
        else if ("309".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#兴业银行").toString();
        else if ("311".equals(ids) || "3112".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#华夏银行").toString();
        else if ("312".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#光大银行").toString();
        else if ("313".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#中信银行").toString();
        else if ("314".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#上海浦东发展银行").toString();
        else if ("324".equals(ids) || "3241".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#杭州银行").toString();
        else if ("327".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#中国银联").toString();
        else if ("3230".equals(ids) || "3231".equals(ids))
            result = (new StringBuilder(String.valueOf(result))).append("#邮政储蓄银行").toString();
        return result;
    }

    public static String GetNameY(String ids) {
        String result = ids;
        return result;
    }
}
