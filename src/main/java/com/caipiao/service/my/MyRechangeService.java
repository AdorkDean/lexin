package com.caipiao.service.my;

import com.caipiao.entity.*;
import com.caipiao.intface.Bc_banksIntface;
import com.caipiao.intface.Bc_drawIntface;
import com.caipiao.intface.Bc_payIntface;
import com.caipiao.intface.Bc_rechIntface;
import com.caipiao.intfaceImpl.BanksIntfaceImpl;
import com.caipiao.intfaceImpl.DrawIntfaceImpl;
import com.caipiao.intfaceImpl.PayIntfaceImpl;
import com.caipiao.intfaceImpl.RechIntfaceImpl;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.LockList;
import com.caipiao.utils.StaticItem;
import com.caipiao.utils.TimeUtil;
import com.sysbcjzh.utils.StringUtils;

import java.util.HashMap;
import java.util.List;

public class MyRechangeService {
    Bc_banksIntface bankdao = new BanksIntfaceImpl();
    Bc_rechIntface rechdao = new RechIntfaceImpl();
    Bc_drawIntface drawdao = new DrawIntfaceImpl();
    Bc_payIntface paydao = new PayIntfaceImpl();

    public int findUserDrawCount(int Userid) {
        return this.drawdao.findUserDrawCount(Userid);
    }

    public int findUserRechCount(int Userid) {
        return this.rechdao.findsDayRechCount(Userid);
    }

    public boolean Rech(String user_name, int user_id, String rech_item, double money, double give, int type, String desc) {
        Bc_rech en = new Bc_rech();
        en.setRech_desc(desc);
        en.setRech_give(give);
        en.setRech_item(rech_item);
        en.setRech_money(money);
        en.setRech_status(1);//为了弥补对接第三方的问题 这里默认1成功，因为第三方对接只会在成功的时候才会插入
        en.setRech_time(TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
        en.setRech_type(type);
        en.setUser_id(user_id);
        en.setUser_name(user_name);
        return this.rechdao.add(en);
    }

    public Bc_rech find(String item, String name) {
        return this.rechdao.find(item, name);
    }

    public Bc_rech find(String item) {
        return this.rechdao.find(item);
    }

    public boolean updateRech(int rech_id, int status, double newgive) {
        HashMap map = new HashMap();
        map.put("Rech_status", Integer.valueOf(status));
        map.put("Rech_dotime", TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
        if (newgive > 0.0D) {
            map.put("Rech_give", Double.valueOf(newgive));
        }
        return this.rechdao.update(rech_id, map);
    }

    public List<Bc_banks> findBanks(int User_id) {
        return this.bankdao.finds(User_id);
    }

    public List<Bc_pay> findPays(int User_id) {
        return this.paydao.finds(User_id);
    }

    public Bc_banks findBank(int bank_id) {
        return this.bankdao.find(bank_id);
    }

    public Bc_pay findPay(int pay_id) {
        return this.paydao.find(pay_id);
    }

    public int findBankCount(int User_id) {
        return this.bankdao.findCountByUser(User_id);
    }

    public int findPayCount(int User_id) {
        return this.paydao.findCountByUser(User_id);
    }

    public void AddBanks(int User_id, String phone, String bank, String card, String add, String addname) {
        Bc_banks en = new Bc_banks();
        en.setUser_id(User_id);
        en.setBanks_bank(bank);
        en.setBanks_card(card);
        en.setBanks_add(add);
        en.setBanks_status(0);
        en.setBanks_phone(phone);
        en.setBanks_name(addname);
        this.bankdao.add(en);
    }

    public void AddPays(int User_id, String pay_user, String pay_type, String payname) {
        Bc_pay en = new Bc_pay();
        en.setPay_name(payname);
        en.setPay_type(pay_type);
        en.setPay_user(pay_user);
        en.setUser_id(User_id);
        this.paydao.add(en);
    }

    public List<Bc_rech> findsRech(int userid, String btime, String etime, int status, int start, int limit) {
        return this.rechdao.finds(userid, btime, etime, -1, status, start, limit);
    }

    public int findsRechcount(int userid, String btime, String etime, int status) {
        return this.rechdao.findscount(userid, btime, etime, -1, status);
    }

    public List<Bc_draw> findDraw(int userid, String btime, String etime, int status, int start, int limit) {
        return this.drawdao.finds(userid, btime, etime, -1, status, start, limit);
    }

    public int findDrawcount(int userid, String btime, String etime, int status) {
        return this.drawdao.findscount(userid, btime, etime, -1, status);
    }

    public String AddDraw(Bc_user user, int ids, String bank, String card, String adds, int type, double money, Double surg, String desc, String paypass) {
        String result = "1";
        double user_show = user.getUser_show();
        if (user_show > 0.0D) {
            return "3";
        }
        if (StringUtils.md5String(paypass).equals(user.getUser_paypass())) {
            if ((money + surg.doubleValue() <= user.getUser_money()) && (money > 0.0D)) {
                String getDrawitem = StaticItem.GetDrawitem();
                Bc_draw en = new Bc_draw();
                en.setDraw_desc(desc);
                en.setDraw_item(getDrawitem);
                en.setDraw_money(money);
                en.setDraw_status(0);
                en.setDraw_surgery(surg.doubleValue());
                en.setDraw_time(TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
                en.setPay_id(ids);
                en.setUser_id(user.getUser_id());
                en.setUser_name(user.getUser_name());
                en.setDraw_type(type);
                en.setBanks_bank(bank);
                en.setBanks_card(card);
                en.setUser_realname(user.getUser_realname());
                en.setBanks_add(adds);
                boolean add = this.drawdao.add(en);
                if (add) {
                    UserStatic.MonToDong(user, money, getDrawitem, 3, "提款冻结");
                    result = "0";
                } else {
                    result = "-1";
                }
            } else {
                result = "2";
            }
        }
        return result;
    }

    public String DrawQuxiao(Bc_user en, int draw_id) {
        String result = "-1";
        int user_id = en.getUser_id();
        Bc_draw find = this.drawdao.find(draw_id);
        if ((find != null) && (find.getUser_id() == user_id) && (find.getDraw_status() == 0)) {
            String draw_item = find.getDraw_item();
            try {
                LockList.drawlock.add(draw_item);
                HashMap map = new HashMap();
                map.put("Draw_status", Integer.valueOf(3));
                map.put("Draw_desc", "用户主动撤销");
                boolean update = this.drawdao.update(draw_id, map);
                double money = find.getDraw_money() + find.getDraw_surgery();
                if (update) {
                    result = "0";
                    UserStatic.DongToMon(en, money, draw_item, 4, "用户取消");
                }
            } finally {
                LockList.drawlock.remove(draw_item);
            }
        } else {
            result = "1";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(TimeUtil.StringToLong("2015-05-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
    }
}