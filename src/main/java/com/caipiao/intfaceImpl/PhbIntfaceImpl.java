package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_phb;
import com.caipiao.entity.out.Phb;
import com.caipiao.intface.Bc_phbIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhbIntfaceImpl
        implements Bc_phbIntface {
    Mysql dao = Mysql.getInstance();

    public boolean add(Bc_phb en) {
        return this.dao.add(en);
    }

    public boolean delete(int Phb_id) {
        return this.dao.delete("delete from Bc_phb where Phb_id=?", new Object[]{Integer.valueOf(Phb_id)});
    }

    public Bc_phb find(int Phb_id) {
        return (Bc_phb) this.dao.find("select * from Bc_phb where Phb_id=?", Bc_phb.class, new Object[]{Integer.valueOf(Phb_id)});
    }

    public Bc_phb findByUser(int user_id) {
        return (Bc_phb) this.dao.find("select * from Bc_phb where user_id=? and Phb_type=?", Bc_phb.class, new Object[]{Integer.valueOf(user_id), "all"});
    }

    public boolean update(int Phb_id, Map<String, Object> map) {
        return this.dao.updateMap("update Bc_phb set ", " where Phb_id=?", map, new Object[]{Integer.valueOf(Phb_id)});
    }

    public List<Phb> finds(int User_id, String select, String lot, int start, int limit) {
        String sql = "select Phb_id,User_name," + select + " as Phb_value from Bc_phb where Phb_type=? and " + select + ">0";
        ArrayList list = new ArrayList();
        list.add(lot);
        if (-1 != User_id) {
            sql = sql + " and User_id=?";
            list.add(Integer.valueOf(User_id));
        }
        if ("*".equals(select))
            sql = sql + " order by Phb_all desc limit ?,?";
        else {
            sql = sql + " order by " + select + " desc limit ?,?";
        }
        list.add(Integer.valueOf(start));
        list.add(Integer.valueOf(limit));
        return this.dao.finds(sql, Phb.class, list.toArray());
    }

    public boolean update(int userid, String lot, double win, double xiaofei, int type) {
        ArrayList list = new ArrayList();
        list.add(Double.valueOf(xiaofei));
        list.add(Double.valueOf(xiaofei));
        list.add(Double.valueOf(xiaofei));
        list.add(Double.valueOf(xiaofei));
        this.dao.update("update Bc_user set User_show=IF(User_show-?>0,User_show-?,0) where User_id=?", new Object[]{Double.valueOf(xiaofei), Double.valueOf(xiaofei), Integer.valueOf(userid)});
        if (type == 0) {
            String sql = "update Bc_phb set Phb_total_c=Phb_total_c+?,Phb_all_c=Phb_all_c+?,Phb_month_c=Phb_month_c+?,Phb_day_c=Phb_day_c+?";
            if (win > 0.0D) {
                sql = sql + ",Phb_total=Phb_total+?,Phb_all=Phb_all+?,Phb_month=Phb_month+?,Phb_day=Phb_day+?";
                list.add(Double.valueOf(win));
                list.add(Double.valueOf(win));
                list.add(Double.valueOf(win));
                list.add(Double.valueOf(win));
            }
            sql = sql + " where Phb_type in(?,'all') and User_id=?";
            list.add(lot);
            list.add(Integer.valueOf(userid));
            return this.dao.update(sql, list.toArray());
        }
        String sql = "update Bc_phb set Phb_total_c=Phb_total_c+?,Phb_hmall_c=Phb_hmall_c+?,Phb_hmmonth_c=Phb_hmmonth_c+?,Phb_hmday_c=Phb_hmday_c+?";
        if (win > 0.0D) {
            sql = sql + ",Phb_total=Phb_total+?,Phb_hmall=Phb_hmall+?,Phb_hmmonth=Phb_hmmonth+?,Phb_hmday=Phb_hmday+?";
            list.add(Double.valueOf(win));
            list.add(Double.valueOf(win));
            list.add(Double.valueOf(win));
            list.add(Double.valueOf(win));
        }
        sql = sql + " where Phb_type in(?,'all') and User_id=?";
        list.add(lot);
        list.add(Integer.valueOf(userid));
        return this.dao.update(sql, list.toArray());
    }

    public List<Bc_phb> findsPHB(int Userid, String type, int start, int limit) {
        String sql = "select * from Bc_phb where";
        ArrayList list = new ArrayList();
        if (-1 != Userid) {
            sql = sql + " User_id=?";
            list.add(Integer.valueOf(Userid));
        }
        if (StringUtils.isNotBlank(type)) {
            sql = sql + " and Phb_type=?";
            list.add(type);
        }
        sql = sql + " order by Phb_total desc limit ?,?";
        list.add(Integer.valueOf(start));
        list.add(Integer.valueOf(limit));
        sql = sql.replace("where and", "where");
        sql = sql.replace("where order", "order");
        return this.dao.finds(sql, Bc_phb.class, list.toArray());
    }

    public int findsPHBCount(int Userid, String type) {
        String sql = "select count(Phb_id) from Bc_phb where";
        ArrayList list = new ArrayList();
        if (-1 != Userid) {
            sql = sql + " User_id=?";
            list.add(Integer.valueOf(Userid));
        }
        if (StringUtils.isNotBlank(type)) {
            sql = sql + " and Phb_type=?";
            list.add(type);
        }
        sql = sql.replace("where and", "where");
        if (sql.endsWith("where")) {
            sql = sql.replace("where", "");
        }
        return this.dao.getCount(sql, list.toArray());
    }

    public boolean updateinit(int type) {
        if (1 == type) {
            return this.dao.update("update Bc_phb set Phb_month_c=0,Phb_hmmonth_c=0,Phb_month=0,Phb_hmmonth=0", new Object[0]);
        }
        return this.dao.update("update Bc_phb set Phb_day_c=0,Phb_hmday_c=0,Phb_day=0,Phb_hmday=0", new Object[0]);
    }
}