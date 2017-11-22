package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_buyuser;
import com.caipiao.entity.out.MyOrderOut;
import com.caipiao.intface.Bc_buyuserIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BuyuserIntfaceImpl
        implements Bc_buyuserIntface {
    Mysql dao = Mysql.getInstance();

    public boolean add(Bc_buyuser en) {
        return this.dao.add(en);
    }

    public List<MyOrderOut> findBuy(int userid, String btime, String etime, String lottery, int status, int ishm, int start, int limit) {
        String sql = "select a.Buy_item,a.Buyuser_time,b.Buy_lot,b.Buy_qihao,a.User_name,b.Buy_money,a.Buyuser_money,b.Buy_status,a.Buyuser_win from Bc_buyuser a left join Bc_buy b on a.Buy_item=b.Buy_item where a.User_id=?";
        ArrayList list = new ArrayList();
        list.add(Integer.valueOf(userid));
        if (-3 != status) {
            sql = sql + " and b.Buy_status=?";
            list.add(Integer.valueOf(status));
        }
        if (StringUtils.isNotBlank(lottery)) {
            sql = sql + " and b.Buy_lot=?";
            list.add(lottery);
        }
        if (btime != null) {
            sql = sql + " and a.Buyuser_time>?";
            list.add(btime);
        }
        if (etime != null) {
            sql = sql + " and a.Buyuser_time<?";
            list.add(etime);
        }
        if (-1 != ishm) {
            sql = sql + " and b.Buy_ishm=?";
            list.add(Integer.valueOf(ishm));
        }
        sql = sql + " order by a.Buyuser_time desc limit ?,?";
        list.add(Integer.valueOf(start));
        list.add(Integer.valueOf(limit));
        return this.dao.finds(sql, MyOrderOut.class, list.toArray());
    }

    public int findBuyCount(int userid, String btime, String etime, String lottery, int status, int ishm) {
        String sql = "select count(a.Buyuser_id) from Bc_buyuser a left join Bc_buy b on a.Buy_item=b.Buy_item where a.User_id=?";
        ArrayList list = new ArrayList();
        list.add(Integer.valueOf(userid));
        if (-3 != status) {
            sql = sql + " and b.Buy_status=?";
            list.add(Integer.valueOf(status));
        }
        if (StringUtils.isNotBlank(lottery)) {
            sql = sql + " and b.Buy_lot=?";
            list.add(lottery);
        }
        if (btime != null) {
            sql = sql + " and a.Buyuser_time>?";
            list.add(btime);
        }
        if (etime != null) {
            sql = sql + " and a.Buyuser_time<?";
            list.add(etime);
        }
        if (-1 != ishm) {
            sql = sql + " and b.Buy_ishm=?";
            list.add(Integer.valueOf(ishm));
        }
        return this.dao.getCount(sql, list.toArray());
    }

    public List<Bc_buyuser> findNewWin(String lot, int num) {
        return this.dao.finds("select a.User_name,a.Buyuser_win from Bc_buyuser a left join Bc_buy b on a.Buy_item=b.Buy_item where b.Buy_lot=? and a.Buyuser_win>0 order by a.Buyuser_time desc limit 0,?", Bc_buyuser.class, new Object[]{lot, Integer.valueOf(num)});
    }

    public List<Bc_buyuser> findNewWin(int num) {
        return this.dao.finds("select a.User_name,a.Buyuser_win,b.Buy_lot as Buy_item from Bc_buyuser a left join Bc_buy b on a.Buy_item=b.Buy_item where a.Buyuser_win>0 order by a.Buyuser_time desc limit 0,?", Bc_buyuser.class, new Object[]{Integer.valueOf(num)});
    }

    public List<Bc_buyuser> findByItem(String item) {
        return this.dao.finds("select * from Bc_buyuser where Buy_item=?", Bc_buyuser.class, new Object[]{item});
    }

    public List<Bc_buyuser> findByItem(String item, int start, int limit) {
        return this.dao.finds("select * from Bc_buyuser where Buy_item=? order by a.Buyuser_time desc limit ?,?", Bc_buyuser.class, new Object[]{item, Integer.valueOf(start), Integer.valueOf(limit)});
    }

    public int findByItemCount(String item) {
        return this.dao.getCount("select count(Buyuser_id) from Bc_buyuser where Buy_item=?", new Object[]{item});
    }

    public boolean addWin(int Buyuser_id, double win) {
        return this.dao.update("update Bc_buyuser set Buyuser_win=Buyuser_win+? where Buyuser_id=?", new Object[]{Double.valueOf(win), Integer.valueOf(Buyuser_id)});
    }
}