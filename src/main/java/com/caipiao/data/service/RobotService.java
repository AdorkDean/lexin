package com.caipiao.data.service;

import com.caipiao.entity.Bc_buyuser;
import com.caipiao.entity.Bc_user;
import com.caipiao.intface.Bc_buyIntface;
import com.caipiao.intface.Bc_buyuserIntface;
import com.caipiao.intface.Bc_userIntface;
import com.caipiao.intfaceImpl.BuyIntfaceImpl;
import com.caipiao.intfaceImpl.BuyuserIntfaceImpl;
import com.caipiao.intfaceImpl.UserIntfaceImpl;
import com.caipiao.service.lottery.BuyLotService;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.TimeUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

/**
 * Created by nicholas on 2017/7/20.
 */
public class RobotService {


    static Bc_buyuserIntface buyuserIntface = new BuyuserIntfaceImpl();

    private Bc_userIntface userIntface = new UserIntfaceImpl();

    /**
     * 机器人购买
     *
     * @param buy_item 购买序号
     * @param have 合买剩余金额
     */
    public boolean robotBuy(String buy_item, double have){


        List<Bc_user> listForTestUser = userIntface.findListForTestUser();
        if(listForTestUser == null || listForTestUser.size() <= 0){
            System.out.println("没有配备机器人，请在后台配备测试用户");
            return false;
        }

        Bc_user robot_user = getRobot_user(have, listForTestUser);
        if (robot_user == null ){
            System.out.println("请为机器人充值足够的金额，便于购买彩票");
            return false;
        }


        boolean addHavaHemai = addHaveHemai(buy_item,robot_user.getUser_id(),robot_user.getUser_name(),have,"robot buy");

        if(addHavaHemai){
            System.out.println("机器人"+robot_user.getUser_name()+"购买单号"+buy_item+"金额"+have+"成功成功");
            boolean monToDong = UserStatic.MonToDong(robot_user, have, buy_item, 1, "购彩冻结");
            if(monToDong){
                System.out.println("机器人"+robot_user.getUser_name()+"扣除单号"+buy_item+"金额"+have+"成功成功");
            }else{
                System.out.println("机器人"+robot_user.getUser_name()+"扣除单号"+buy_item+"金额"+have+"失败失败");
            }
        }else{
            System.out.println("机器人"+robot_user.getUser_name()+"购买单号"+buy_item+"金额"+have+"失败失败失败");
            return false;
        }

        return true;
    }



    private boolean addHaveHemai(String buy_item, int user_id, String user_name, double buymoney, String auto_item)
    {
        Bc_buyuser en = new Bc_buyuser();
        en.setAuto_item(auto_item);
        en.setBuy_item(buy_item);
        en.setBuyuser_money(buymoney);
        en.setBuyuser_time(TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
        en.setUser_id(user_id);
        en.setUser_name(user_name);
        return buyuserIntface.add(en);
    }

    private Bc_user getRobot_user(double have, List<Bc_user> listForTestUser) {
        Bc_user robot_user = null;

        for (int i = 0; i < listForTestUser.size(); i++) {

            robot_user  = listForTestUser.get(getRandomInt(listForTestUser.size()));

            if(robot_user.getUser_money() > have){
                break;
            }
            robot_user = null;
        }
        return robot_user;
    }

    private int getRandomInt(int max) {
        return new Random().nextInt(max);
    }




}
