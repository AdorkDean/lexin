package com.caipiao.service.lottery;

import com.caipiao.data.open.MethodHemai;
import com.caipiao.data.open.MethodOut;
import com.caipiao.data.service.CountMoney;
import com.caipiao.entity.Bc_buy;
import com.caipiao.entity.Bc_buylot;
import com.caipiao.entity.Bc_buyuser;
import com.caipiao.entity.Bc_user;
import com.caipiao.entity.out.BuyOneOut;
import com.caipiao.entity.out.OutEntity;
import com.caipiao.intface.Bc_buyIntface;
import com.caipiao.intface.Bc_buylotIntface;
import com.caipiao.intface.Bc_buyuserIntface;
import com.caipiao.intfaceImpl.BuyIntfaceImpl;
import com.caipiao.intfaceImpl.BuylotIntfaceImpl;
import com.caipiao.intfaceImpl.BuyuserIntfaceImpl;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.*;
import org.apache.commons.lang.ArrayUtils;

import java.util.List;

/**
 * 购买彩票 关键类
 */
public class BuyLotService {
    Bc_buyIntface buy = new BuyIntfaceImpl();
    Bc_buyuserIntface buyuser = new BuyuserIntfaceImpl();
    Bc_buylotIntface buylot = new BuylotIntfaceImpl();

    public String Buy(Bc_user useren, String lot, Double money, Double buymon, Double bao, String code, int ishm, int take, int isopen, String[] qihao, int[] beishu, int iscont) {
        String result = "0";
        boolean checkBuy = NowQihao.CheckBuy(lot, qihao);
        if (!checkBuy) {
            return "-1";
        }
        //验证胆拖的数据
        if(!verifyDanTuo(code)){
            return "-1";
        }


        String times = TimeUtil.getToday("yyyy-MM-dd HH:mm:ss");
        int zhushu = CountMoney.getAllZhushu(code, lot);
        Double allMoney = CountMoney.getAllMoney(zhushu, beishu);

        if ((money.equals(allMoney)) && (allMoney.doubleValue() > 0.0D)) {
            double showpay = 0.0D;
            int status = 0;
            if (ishm == 0) {
                showpay = allMoney.doubleValue();
                isopen = 3;
            } else {
                showpay = buymon.doubleValue() + bao.doubleValue();
                status = -1;
            }
            if (qihao.length <= 1) {
                iscont = -1;
            }
            double user_money = useren.getUser_money();
            int user_id = useren.getUser_id();
            if (user_money >= showpay) {
                String getBuyItem = StaticItem.GetBuyItem(lot, qihao[0]);
                boolean monToDong = UserStatic.MonToDong(useren, showpay, getBuyItem, 1, "购彩冻结");
                if (monToDong) {
                    Bc_buy en = new Bc_buy();
                    en.setUser_id(user_id);
                    en.setUser_name(useren.getUser_name());
                    en.setBuy_money(allMoney.doubleValue());
                    en.setBuy_ishm(ishm);
                    en.setBuy_isopen(isopen);
                    en.setBuy_code(code);
                    en.setBuy_iscont(iscont);
                    en.setBuy_item(getBuyItem);
                    en.setBuy_lot(lot);
                    en.setBuy_status(status);
                    en.setBuy_zhushu(zhushu);
                    en.setBuy_fqihao(qihao[0]);
                    if (1 == ishm) {
                        en.setBuy_baodi(bao.doubleValue());
                        en.setBuy_have(allMoney.doubleValue() - buymon.doubleValue());
                        en.setBuy_take(take > 10 ? 10 : take);
                    }
                    en.setBuy_time(times);
                    boolean add = this.buy.add(en);
                    if (add) {
                        for (int i = 0; i < qihao.length; i++) {
                            Bc_buylot enlot = new Bc_buylot();
                            enlot.setBuylot_lot(lot);
                            enlot.setBuy_item(getBuyItem);
                            enlot.setBuylot_money(zhushu * 2 * beishu[i]);
                            enlot.setBuylot_multiple(beishu[i]);
                            enlot.setBuylot_qihao(qihao[i]);
                            enlot.setBuylot_status(ishm == 1 ? -1 : 0);
                            this.buylot.add(enlot);
                        }

                        Bc_buyuser enu = new Bc_buyuser();
                        if (ishm == 0)
                            enu.setBuyuser_money(allMoney.doubleValue());
                        else {
                            enu.setBuyuser_money(buymon.doubleValue());
                        }
                        enu.setBuy_item(getBuyItem);
                        enu.setBuyuser_time(times);
                        enu.setUser_id(user_id);
                        enu.setUser_name(useren.getUser_name());
                        this.buyuser.add(enu);
                    }
                } else {
                    result = "2";
                }
            } else {
                result = "2";
            }
        } else {
            result = "1";
        }
        return result;
    }

    private boolean verifyDanTuo(String code) {
        String[] split = code.split("#");
        for (String str : split) {
            String type = str.split(":")[0];
            if(ArrayUtils.contains(PlayType._11x5_DanTuo, type) ||
                    ArrayUtils.contains(PlayType._Def_DanTuo, type) ||
                    ArrayUtils.contains(PlayType._Ssc_DanTuo, type)
                    ){

                String[] $s = str.split("\\$");


                String[] split1 = $s[0].split(":");
                String[] split2 = $s[1].split(":");

                String dan = split1[1];
                String tuo = split2[0];
                if(tuo.contains(dan)){
                    System.out.println("胆拖数据有误： code = { "+code+"}");
                    return false;
                }
            }

        }
        return true;
    }

    public String BuyHM(Bc_user useren, String item, String lot, String fqh, double buymon, String aotu_item) {
        String result = "4";
        if (buymon <= 0.0D) {
            return result;
        }
        boolean checkBuy = NowQihao.CheckBuy(lot, fqh);
        if (!checkBuy) {
            return "-1";
        }
        String times = TimeUtil.getToday("yyyy-MM-dd HH:mm:ss");
        double usermon = useren.getUser_money();
        if (usermon < buymon) {
            return "2";
        }
        Bc_buy bc_buy = this.buy.findBuyOne(item);
        int buy_ishm = bc_buy.getBuy_ishm();
        int status = bc_buy.getBuy_status();
        if ((bc_buy != null) && (buy_ishm != 0) && (-1 == status)) {
            double buy_have = bc_buy.getBuy_have();
            if (buy_have < buymon) {
                return "1";
            }
            boolean monToDong = UserStatic.MonToDong(useren, buymon, item, 1, "购彩冻结");
            if (monToDong) {
                UpdateHave(item, buymon);
                Bc_buyuser enu = new Bc_buyuser();
                enu.setBuyuser_time(times);
                enu.setUser_id(useren.getUser_id());
                enu.setUser_name(useren.getUser_name());
                enu.setBuy_item(item);
                enu.setBuyuser_money(buymon);
                enu.setAuto_item(aotu_item);
                this.buyuser.add(enu);
                if (buy_have == buymon) {
                    new MethodHemai().HeimaiOne(bc_buy.getBuy_id());
                }
                result = "0";
            }
        } else {
            result = "3";
        }
        return result;
    }

    public String CheDan(Bc_user en, int ids) {
        String result = "-1";
        OutEntity one = this.buylot.findOutEntityOne(ids);
        boolean checkBuy = NowQihao.CheckBuy(one.getBuy_lot(), one.getBuylot_qihao());
        if (!checkBuy) {
            return "-1";
        }
        int userid = en.getUser_id();
        if (one != null) {
            String buy_item = one.getBuy_item();
            if (LockList.itemlock.contains(buy_item)) {
                return "-1";
            }
            LockList.itemlock.add(buy_item);
            Bc_buy BuyOne = this.buy.findBuyOne(buy_item);
            if (BuyOne != null) {
                int user_id = BuyOne.getUser_id();
                if (userid == user_id) {
                    boolean cheOen = false;
                    try {
                        cheOen = new MethodOut().CheOen(one);
                    } finally {
                        LockList.itemlock.remove(buy_item);
                    }
                    if (cheOen)
                        result = "0";
                } else {
                    result = "2";
                }
            } else {
                result = "1";
            }
        } else {
            result = "1";
        }
        return result;
    }

    public BuyOneOut findBuy(String item) {
        return this.buy.find(item);
    }

    public List<Bc_buyuser> findsBuyUser(String item) {
        return this.buyuser.findByItem(item);
    }

    public List<Bc_buylot> findsBuyLot(String item) {
        return this.buylot.findByItem(item);
    }

    public boolean UpdateHave(String item, double have) {
        return this.buy.updatehave(item, have);
    }
}