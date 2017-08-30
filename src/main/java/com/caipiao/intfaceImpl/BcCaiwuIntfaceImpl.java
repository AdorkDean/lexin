package com.caipiao.intfaceImpl;

import com.caipiao.entity.out.Achievement;
import com.caipiao.intface.BcCaiwuIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liujie on 2017/8/21.
 */
public class BcCaiwuIntfaceImpl implements BcCaiwuIntface {
    private Mysql dao = Mysql.getInstance();

    public BcCaiwuIntfaceImpl() {
    }

    public List<String> findsAllAgent() {
        String sql = "select u.user_name agent from bc_user u where u.User_type = ?";
        ArrayList retList = new ArrayList();
        List list = this.dao.finds(sql, Achievement.class, new Object[]{Integer.valueOf(1)});
        if(list != null) {
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                Achievement achievement = (Achievement)var5.next();
                retList.add(achievement.getAgent());
            }
        }

        return retList;
    }

    public List<Achievement> findsAchievementByPage(String username, String agent, String btime, String etime, int current, int pageSize) {
        ArrayList list = new ArrayList();
        String sql = "select ut.up_user_name agent, ut.user_name username,  ut.user_money money, IFNULL(rt.rech_money, 0) rech,";
        sql = sql + " IFNULL(rt.give_money, 0) give, IFNULL(dt.draw_money, 0) draw, (IFNULL(rt.rech_money, 0) - IFNULL(dt.draw_money, 0)) profit,";
        sql = sql + " (IFNULL(rt.rech_money, 0) - IFNULL(dt.draw_money, 0) - ut.user_money) netProfit";
        sql = sql + " from (select u.user_name, u.User_money, (select t.user_name from bc_user t where t.User_id = u.User_upid) up_user_name";
        sql = sql + " from bc_user u where u.User_type = 0) ut left join";
        sql = sql + " (select r.user_name, sum(r.Rech_money) rech_money, sum(r.Rech_give) give_money from bc_rech r";
        sql = sql + " where r.Rech_status = 1 ";
        if(StringUtils.isNotEmpty(btime)) {
            sql = sql + " and r.rech_time >= ?";
            list.add(btime);
        }

        if(StringUtils.isNotEmpty(etime)) {
            sql = sql + " and r.rech_time <= ?";
            list.add(etime);
        }

        sql = sql + " group by r.User_name) rt on ut.user_name = rt.user_name";
        sql = sql + " left join (select d.user_name, sum(d.draw_money) draw_money from bc_draw d where d.Draw_status = 2";
        if(StringUtils.isNotEmpty(btime)) {
            sql = sql + " and d.draw_time >= ?";
            list.add(btime);
        }

        if(StringUtils.isNotEmpty(etime)) {
            sql = sql + " and d.draw_time <= ?";
            list.add(etime);
        }

        sql = sql + " group by d.User_name) dt on ut.user_name = dt.user_name where 1 = 1 ";
        if(StringUtils.isNotEmpty(username)) {
            sql = sql + " and ut.user_name like ?";
            list.add(username + "%");
        }

        if(StringUtils.isNotEmpty(agent)) {
            sql = sql + " and ut.up_user_name = ?";
            list.add(agent);
        }

        sql = sql + " limit " + current + "," + pageSize;
        return this.dao.finds(sql, Achievement.class, list.toArray());
    }

    public int findsAchievementByPageCount(String username, String agent, String btime, String etime) {
        ArrayList list = new ArrayList();
        String sql = "select count(1)";
        sql = sql + " from (select u.user_name, u.User_money, (select t.user_name from bc_user t where t.User_id = u.User_upid) up_user_name";
        sql = sql + " from bc_user u where u.User_type = 0) ut left join";
        sql = sql + " (select r.user_name, sum(r.Rech_money) rech_money, sum(r.Rech_give) give_money from bc_rech r";
        sql = sql + " where r.Rech_status = 1 ";
        if(StringUtils.isNotEmpty(btime)) {
            sql = sql + " and r.rech_time >= ?";
            list.add(btime);
        }

        if(StringUtils.isNotEmpty(etime)) {
            sql = sql + " and r.rech_time <= ?";
            list.add(etime);
        }

        sql = sql + " group by r.User_name) rt on ut.user_name = rt.user_name";
        sql = sql + " left join (select d.user_name, sum(d.draw_money) draw_money from bc_draw d where d.Draw_status = 2";
        if(StringUtils.isNotEmpty(btime)) {
            sql = sql + " and d.draw_time >= ?";
            list.add(btime);
        }

        if(StringUtils.isNotEmpty(etime)) {
            sql = sql + " and d.draw_time <= ?";
            list.add(etime);
        }

        sql = sql + " group by d.User_name) dt on ut.user_name = dt.user_name where 1 = 1 ";
        if(StringUtils.isNotEmpty(username)) {
            sql = sql + " and ut.user_name like ?";
            list.add(username + "%");
        }

        if(StringUtils.isNotEmpty(agent)) {
            sql = sql + " and ut.up_user_name = ?";
            list.add(agent);
        }

        return this.dao.getCount(sql, list.toArray());
    }

    public Achievement findsAchievementByTotal(String username, String agent, String btime, String etime) {
        ArrayList list = new ArrayList();
        String sql = "select sum(ut.user_money) money, sum(IFNULL(rt.rech_money, 0)) rech,";
        sql = sql + " sum(IFNULL(rt.give_money, 0)) give, sum(IFNULL(dt.draw_money, 0)) draw,";
        sql = sql + " (sum(IFNULL(rt.rech_money, 0)) - sum(IFNULL(dt.draw_money, 0))) profit,";
        sql = sql + " (sum(IFNULL(rt.rech_money, 0)) - sum(IFNULL(dt.draw_money, 0)) - sum(ut.user_money)) netProfit";
        sql = sql + " from (select u.user_name, u.User_money, (select t.user_name from bc_user t where t.User_id = u.User_upid) up_user_name";
        sql = sql + " from bc_user u where u.User_type = 0) ut left join";
        sql = sql + " (select r.user_name, sum(r.Rech_money) rech_money, sum(r.Rech_give) give_money from bc_rech r";
        sql = sql + " where r.Rech_status = 1 ";
        if(StringUtils.isNotEmpty(btime)) {
            sql = sql + " and r.rech_time >= ?";
            list.add(btime);
        }

        if(StringUtils.isNotEmpty(etime)) {
            sql = sql + " and r.rech_time <= ?";
            list.add(etime);
        }

        sql = sql + " group by r.User_name) rt on ut.user_name = rt.user_name";
        sql = sql + " left join (select d.user_name, sum(d.draw_money) draw_money from bc_draw d where d.Draw_status = 2";
        if(StringUtils.isNotEmpty(btime)) {
            sql = sql + " and d.draw_time >= ?";
            list.add(btime);
        }

        if(StringUtils.isNotEmpty(etime)) {
            sql = sql + " and d.draw_time <= ?";
            list.add(etime);
        }

        sql = sql + " group by d.User_name) dt on ut.user_name = dt.user_name where 1 = 1 ";
        if(StringUtils.isNotEmpty(username)) {
            sql = sql + " and ut.user_name like ?";
            list.add(username + "%");
        }

        if(StringUtils.isNotEmpty(agent)) {
            sql = sql + " and ut.up_user_name = ?";
            list.add(agent);
        }

        return (Achievement)this.dao.find(sql, Achievement.class, list.toArray());
    }
}
