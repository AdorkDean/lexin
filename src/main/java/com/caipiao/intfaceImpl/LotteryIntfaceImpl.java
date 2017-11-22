//    LotteryIntfaceImpl.java

package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_lottery;
import com.caipiao.intface.Bc_lotteryIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LotteryIntfaceImpl
        implements Bc_lotteryIntface {

    Mysql dao;

    public LotteryIntfaceImpl() {
        dao = Mysql.getInstance();
    }

    public boolean add(Bc_lottery en) {
        return dao.add(en);
    }

    public boolean delete(int lot_id) {
        return dao.delete("delete from Bc_lottery where Lot_id=?", new Object[]{
                Integer.valueOf(lot_id)
        });
    }

    public Bc_lottery find(String lot_name, String lot_qihao) {
        return (Bc_lottery) dao.find("select * from Bc_lottery where Lot_name=? and Lot_qihao=?", Bc_lottery.class, new Object[]{
                lot_name, lot_qihao
        });
    }

    public Bc_lottery findByNowTime(String lot_name, String nowtime) {
        return (Bc_lottery) dao.find("select * from Bc_lottery where Lot_name=? and Lot_btime<=? and Lot_etime>?", Bc_lottery.class, new Object[]{
                lot_name, nowtime, nowtime
        });
    }

    public Bc_lottery findByEtime(String lot_name, String etime) {
        return (Bc_lottery) dao.find("select * from Bc_lottery where Lot_name=? and Lot_etime=?", Bc_lottery.class, new Object[]{
                lot_name, etime
        });
    }

    public Bc_lottery find(int Lot_id) {
        return (Bc_lottery) dao.find("select * from Bc_lottery where Lot_id=?", Bc_lottery.class, new Object[]{
                Integer.valueOf(Lot_id)
        });
    }

    public boolean update(String lot_name, String lot_qihao, Map map) {
        if (map.containsKey("Lot_haoma"))
            dao.update("update Bc_buylot set Buylot_haoma=? where Buylot_lot=? and Buylot_qihao=?", new Object[]{
                    map.get("Lot_haoma"), lot_name, lot_qihao
            });
        return dao.updateMap("update Bc_lottery set ", " where Lot_name=? and Lot_qihao=?", map, new Object[]{
                lot_name, lot_qihao
        });
    }

    public boolean update(int lot_id, Map map) {
        return dao.updateMap("update Bc_lottery set ", " where Lot_id=?", map, new Object[]{
                Integer.valueOf(lot_id)
        });
    }

    public List findAllOpen() {
        return dao.finds("select * from (select * from Bc_lottery WHERE LENGTH(Lot_haoma)>0 order by Lot_etime desc) temp group by Lot_name", Bc_lottery.class, new Object[0]);
    }

    public List findNowAfter(String nowtime, String lot, int start, int limit) {
        return dao.finds("select * from Bc_lottery where Lot_name=? and Lot_etime>? order by Lot_etime asc limit ?,?", Bc_lottery.class, new Object[]{
                lot, nowtime, Integer.valueOf(start), Integer.valueOf(limit)
        });
    }

    public int findNowAfterCount(String nowtime, String lot) {
        return dao.getCount("select count(Lot_id) from Bc_lottery where Lot_name=? and Lot_etime>?", new Object[]{
                lot, nowtime
        });
    }

    public List findNotOpenByTime(String btime, String nowtime) {
        return dao.finds("select * from (select * from Bc_lottery WHERE LENGTH(Lot_haoma)<=0 and Lot_etime>=? and Lot_etime<=? order by Lot_etime desc) temp group by Lot_name", Bc_lottery.class, new Object[]{
                btime, nowtime
        });
    }

    public List findNowAgo(String nowtime, String lot, int start, int limit) {
        return dao.finds("select * from Bc_lottery where Lot_name=? and Lot_etime<? order by Lot_etime desc limit ?,?", Bc_lottery.class, new Object[]{
                lot, nowtime, Integer.valueOf(start), Integer.valueOf(limit)
        });
    }

    public List findHaveWithOpen(int open) {
        return dao.finds("select * from Bc_lottery where LENGTH(Lot_haoma)>0 and Lot_isopen=?", Bc_lottery.class, new Object[]{
                Integer.valueOf(open)
        });
    }

    public List findDay(String lot, String day) {
        return dao.finds("select Lot_qihao,Lot_haoma from Bc_lottery where Lot_name=? and Lot_etime like ?", Bc_lottery.class, new Object[]{
                lot, (new StringBuilder("%")).append(day).append("%").toString()
        });
    }

    public List findNewOpen(String lot, int num) {
        return dao.finds("select Lot_qihao,Lot_haoma,Lot_id from Bc_lottery WHERE LENGTH(Lot_haoma)>0 and Lot_name=? order by Lot_etime desc limit 0,?", Bc_lottery.class, new Object[]{
                lot, Integer.valueOf(num)
        });
    }

    public List finds(String lot, String qihao, int havehm, int isopen, String btime, String etime, int start,
                      int limit) {
        String sql = "select * from Bc_lottery where";
        ArrayList list = new ArrayList();
        if (StringUtils.isNotBlank(lot)) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" Lot_name=?").toString();
            list.add(lot);
        }
        if (StringUtils.isNotBlank(qihao)) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Lot_qihao=?").toString();
            list.add(qihao);
        }
        if (-1 != havehm)
            if (havehm == 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and LENGTH(Lot_haoma)=0").toString();
            else if (1 == havehm)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and LENGTH(Lot_haoma)>0").toString();
        if (-1 != isopen) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Lot_isopen=?").toString();
            list.add(Integer.valueOf(isopen));
        }
        if (StringUtils.isNotBlank(btime)) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Lot_btime>?").toString();
            list.add(btime);
        }
        if (StringUtils.isNotBlank(etime)) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Lot_etime<?").toString();
            list.add(etime);
        }
        sql = (new StringBuilder(String.valueOf(sql))).append(" order by Lot_id desc limit ?,?").toString();
        list.add(Integer.valueOf(start));
        list.add(Integer.valueOf(limit));
        sql = sql.replace("where and", "where");
        sql = sql.replace("where order", "order");
        return dao.finds(sql, Bc_lottery.class, list.toArray());
    }

    public int findsCount(String lot, String qihao, int havehm, int isopen, String btime, String etime) {
        String sql = "select count(Lot_id) from Bc_lottery where";
        ArrayList list = new ArrayList();
        if (StringUtils.isNotBlank(lot)) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" Lot_name=?").toString();
            list.add(lot);
        }
        if (StringUtils.isNotBlank(qihao)) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Lot_qihao=?").toString();
            list.add(qihao);
        }
        if (-1 != havehm)
            if (havehm == 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and LENGTH(Lot_haoma)=0").toString();
            else if (1 == havehm)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and LENGTH(Lot_haoma)>0").toString();
        if (-1 != isopen) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Lot_isopen=?").toString();
            list.add(Integer.valueOf(isopen));
        }
        if (StringUtils.isNotBlank(btime)) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Lot_btime>?").toString();
            list.add(btime);
        }
        if (StringUtils.isNotBlank(etime)) {
            sql = (new StringBuilder(String.valueOf(sql))).append(" and Lot_etime<?").toString();
            list.add(etime);
        }
        sql = sql.replace("where and", "where");
        if (sql.endsWith("where"))
            sql = sql.replace("where", "");
        return dao.getCount(sql, list.toArray());
    }
}
