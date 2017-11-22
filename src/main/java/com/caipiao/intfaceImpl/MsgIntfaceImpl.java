//    MsgIntfaceImpl.java

package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_msg;
import com.caipiao.intface.Bc_msgIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MsgIntfaceImpl
        implements Bc_msgIntface {

    Mysql dao;

    public MsgIntfaceImpl() {
        dao = Mysql.getInstance();
    }

    public boolean add(Bc_msg en) {
        return dao.add(en);
    }

    public boolean delete(int Msg_id) {
        return dao.delete("delete from Bc_msg where Msg_id=?", new Object[]{
                Integer.valueOf(Msg_id)
        });
    }

    public boolean delete(int Msg_id, int User_id) {
        return dao.delete("delete from Bc_msg where Msg_id=? and User_id=?", new Object[]{
                Integer.valueOf(Msg_id), Integer.valueOf(User_id)
        });
    }

    public Bc_msg find(int Msg_id) {
        return (Bc_msg) dao.find("select * from Bc_msg where Msg_id=?", Bc_msg.class, new Object[]{
                Integer.valueOf(Msg_id)
        });
    }

    public boolean update(int Msg_id, Map map) {
        return dao.updateMap("update Bc_msg set ", " where Msg_id=?", map, new Object[]{
                Integer.valueOf(Msg_id)
        });
    }

    public List finds(int User_id, String btime, String etime, int type, int status, int start, int limit) {
        String sql = "select * from Bc_msg where";
        ArrayList list = new ArrayList();
        if (-1 != User_id) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" User_id=?").toString();
            list.add(Integer.valueOf(User_id));
        }
        if (StringUtils.isNotEmptyAll(new String[]{
                btime, etime
        })) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Msg_time>? and Msg_time<?").toString();
            list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
            list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
        }
        if (-1 != type) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Msg_type=?").toString();
            list.add(Integer.valueOf(type));
        }
        if (-1 != status) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Msg_status=?").toString();
            list.add(Integer.valueOf(status));
        }
        sql = (new StringBuilder(String.valueOf(sql))).append(" order by Msg_time desc limit ?,?").toString();
        list.add(Integer.valueOf(start));
        list.add(Integer.valueOf(limit));
        sql = sql.replace("where and", "where");
        sql = sql.replace("where order", "order");
        return dao.finds(sql, Bc_msg.class, list.toArray());
    }

    public int findscount(int User_id, String btime, String etime, int type, int status) {
        String sql = "select count(Msg_id) from Bc_msg where";
        ArrayList list = new ArrayList();
        if (-1 != User_id) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" User_id=?").toString();
            list.add(Integer.valueOf(User_id));
        }
        if (StringUtils.isNotEmptyAll(new String[]{
                btime, etime
        })) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Msg_time>? and Msg_time<?").toString();
            list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
            list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
        }
        if (-1 != type) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Msg_type=?").toString();
            list.add(Integer.valueOf(type));
        }
        if (-1 != status) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Msg_status=?").toString();
            list.add(Integer.valueOf(status));
        }
        sql = sql.replace("where and", "where");
        if (sql.endsWith("where"))
            sql = sql.replace("where", "");
        return dao.getCount(sql, list.toArray());
    }

    public boolean update(int Msg_id, int User_id) {
        return dao.update("update Bc_msg set Msg_status=1 where Msg_id=? and User_id=?", new Object[]{
                Integer.valueOf(Msg_id), Integer.valueOf(User_id)
        });
    }
}
