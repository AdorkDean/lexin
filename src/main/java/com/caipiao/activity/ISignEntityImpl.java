package com.caipiao.activity;

import com.caipiao.entity.Bc_phb;
import com.sysbcjzh.mysql.Mysql;

import java.util.Map;


public class ISignEntityImpl
        implements ISignEntity {

    Mysql dao;

    public ISignEntityImpl() {
        dao = Mysql.getInstance();
    }

    public SignEntity find(int userid, String date) {
        return (SignEntity) dao.find("select * from SignEntity where User_id=? and Time=?", SignEntity.class, new Object[]{
                Integer.valueOf(userid), date
        });
    }

    public boolean add(SignEntity en) {
        return dao.add(en);
    }

    public int findsur(int userid) {
        Bc_phb find = (Bc_phb) dao.find("select * from Bc_phb where Phb_type=? and User_id=?", Bc_phb.class, new Object[]{
                "all", Integer.valueOf(userid)
        });
        double result = find.getPhb_day_c() + find.getPhb_hmday_c();
        return (int) result;
    }

    public boolean update(int signid, Map map) {
        return dao.updateMap("update SignEntity set ", " where Id=?", map, new Object[]{
                Integer.valueOf(signid)
        });
    }
}
