package com.caipiao.utils;

public class AppUtils {
    public static String DetailType(Integer type) {
        String result = "";
        if (type.intValue() == 0)
            result = "购彩派奖";
        else if (type.intValue() == 1)
            result = "购买彩票";
        else if (type.intValue() == 2)
            result = "用户充值";
        else if (type.intValue() == 3)
            result = "用户提款";
        else if (type.intValue() == 4)
            result = "取消提款";
        else if (type.intValue() == 5)
            result = "积分兑换";
        else if (type.intValue() == 6)
            result = "方案保底";
        else if (type.intValue() == 7)
            result = "保底返还";
        else if (type.intValue() == 8)
            result = "方案撤单";
        else if (type.intValue() == 9) {
            result = "用户返利";
        }
        return result;
    }

    public static String LotImg(String lot) {
        String result = "http://101.1.16.80/app/";
        return result + lot + ".jpg";
    }

    public static String LotInfo(String lot) {
        String result = "";
        if ("Cqssc".equals(lot))
            result = "白天72期，10分钟开奖，夜间48期，5分钟开奖";
        else if ("Jxssc".equals(lot))
            result = "10分钟开奖，每天84期，返奖率59%";
        else if (("Sd11x5".equals(lot)) || ("Jx11x5".equals(lot)) || ("Gd11x5".equals(lot)))
            result = "10分钟一期，返奖率59%";
        else if ("Pl3".equals(lot))
            result = "天天开奖，奖不停";
        else if ("Fc3d".equals(lot))
            result = "2元赢取1800元";
        else if ("Jsk3".equals(lot)) {
            result = "好玩易中";
        }
        return result;
    }

    public static String LotInfo2(String lot) {
        String result = "";
        if ("Cqssc".equals(lot))
            result = "销售时间：10:00～02:00";
        else if ("Jxssc".equals(lot))
            result = "销售时间：9:00~23:00";
        else if ("Sd11x5".equals(lot))
            result = "每天08:55-21:55销售";
        else if ("Jx11x5".equals(lot))
            result = "每天09:00-22:00销售";
        else if ("Gd11x5".equals(lot))
            result = "每天08:55-23:00销售";
        else if (("Pl3".equals(lot)) || ("Fc3d".equals(lot)))
            result = "每日 20:30开奖";
        else if ("Jsk".equals(lot)) {
            result = "新玩法，易中奖";
        }
        return result;
    }
}