package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_auto;
import com.caipiao.intface.Bc_autoIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutoIntfaceImpl
        implements Bc_autoIntface {
    Mysql dao = Mysql.getInstance();

    public boolean add(Bc_auto en) {
        return this.dao.add(en);
    }

    public boolean delete(int auto_id) {
        return this.dao.delete("delete from Bc_auto where Auto_id=?", new Object[]{Integer.valueOf(auto_id)});
    }

    public Bc_auto find(int auto_id) {
        return (Bc_auto) this.dao.find("select * from Bc_auto where Auto_id=?", Bc_auto.class, new Object[]{Integer.valueOf(auto_id)});
    }

    public boolean update(int auto_id, Map<String, Object> map) {
        return this.dao.updateMap("update Bc_auto set ", " where Auto_id=?", map, new Object[]{Integer.valueOf(auto_id)});
    }

    public List<Bc_auto> finds(String user, String lot, int status, String btime, String etime, int start, int limit) {
        String sql = "select * from Bc_auto where";
        ArrayList list = new ArrayList();
        if (StringUtils.isNotBlank(user)) {
            sql = sql + " User_name like ?";
            list.add(user);
        }
        if (StringUtils.isNotBlank(lot)) {
            sql = sql + " Auto_lot=?";
            list.add(lot);
        }
        if (StringUtils.isNotEmptyAll(new String[]{btime, etime})) {
            sql = sql + " and Auto_time>? and Auto_time<?";
            list.add(btime + " 00:00:00");
            list.add(etime + " 24:00:00");
        }
        if (-1 != status) {
            sql = sql + " and Auto_status=?";
            list.add(Integer.valueOf(status));
        }
        sql = sql + " order by Auto_time desc limit ?,?";
        list.add(Integer.valueOf(start));
        list.add(Integer.valueOf(limit));
        sql = sql.replace("where and", "where");
        sql = sql.replace("where order", "order");
        return this.dao.finds(sql, Bc_auto.class, list.toArray());
    }

    public int findsCount(String user, String lot, int status, String btime, String etime) {
        String sql = "select count(Auto_id) from Bc_auto where";
        ArrayList list = new ArrayList();
        if (StringUtils.isNotBlank(user)) {
            sql = sql + " User_name like ?";
            list.add(user);
        }
        if (StringUtils.isNotBlank(lot)) {
            sql = sql + " Auto_lot=?";
            list.add(lot);
        }
        if (StringUtils.isNotEmptyAll(new String[]{btime, etime})) {
            sql = sql + " and Auto_time>? and Auto_time<?";
            list.add(btime + " 00:00:00");
            list.add(etime + " 24:00:00");
        }
        if (-1 != status) {
            sql = sql + " and Auto_status=?";
            list.add(Integer.valueOf(status));
        }
        sql = sql.replace("where and", "where");
        if (sql.endsWith("where")) {
            sql = sql.replace("where", "");
        }
        return this.dao.getCount(sql, list.toArray());
    }
}