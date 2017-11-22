package com.caipiao.activity;

import com.sysbcjzh.mysql.Mysql;

import java.util.ArrayList;
import java.util.List;


public class IActivityImpl
        implements IActivity {

    Mysql dao;

    public IActivityImpl() {
        dao = Mysql.getInstance();
    }

    public List finds(int status) {
        String sql = "select * from Activity";
        ArrayList list = new ArrayList();
        if (-1 != status) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" where Act_status=?").toString();
            list.add(Integer.valueOf(status));
        }
        return dao.finds(sql, Activity.class, list.toArray());
    }

    public Activity find(String activity) {
        return (Activity) dao.find("select * from Activity where Acr_type=?", Activity.class, new Object[]{
                activity
        });
    }
}
