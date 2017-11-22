package com.caipiao.service.my;

import com.caipiao.entity.Bc_point;
import com.caipiao.entity.Bc_user;
import com.caipiao.intface.Bc_pointIntface;
import com.caipiao.intfaceImpl.PointIntfaceImpl;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.StaticItem;

import java.util.List;

public class MyPointService {
    Bc_pointIntface dao = new PointIntfaceImpl();

    public List<Bc_point> findPoint(int userid, String btime, String etime, int type, int subadd, int start, int limit) {
        return this.dao.finds(userid, btime, etime, type, subadd, start, limit);
    }

    public int findPointcount(int userid, String btime, String etime, int type, int subadd) {
        return this.dao.findscount(userid, btime, etime, type, subadd);
    }

    public String changePoint(Bc_user user, int jf) {
        String result = "-1";
        int point = user.getUser_point();
        if ((point >= jf) && (jf % 100 == 0)) {
            double money = jf / 100;
            String item = StaticItem.GetPointitem();
            boolean addMoney = UserStatic.AddMoney(user, money, -jf, item, 5, "积分兑换彩金", 0.0D);
            if (addMoney) {
                result = "0";
            }
        }
        return result;
    }
}