
//    UserStatic.java

package com.caipiao.service.systeminit;

import com.caipiao.entity.*;
import com.caipiao.intface.Bc_userIntface;
import com.caipiao.intfaceImpl.UserIntfaceImpl;
import com.caipiao.utils.TimeUtil;

public class UserStatic
{

	private static double wucha = 0.01D;
	private static Bc_userIntface dao = new UserIntfaceImpl();

	public UserStatic()
	{
	}

	public static Bc_user find(String name)
	{
		return dao.find(name);
	}

	public static Bc_user find(int user_id)
	{
		return dao.find(user_id);
	}

	public static boolean upLgtimeandLgIp(int userid, String time, String ip)
	{
		return dao.updatetimeip(userid, time, ip);
	}

	public static boolean AddMoney(Bc_user en, double money, int point, String item, int type, String desc, double show)
	{
		boolean addMoney = false;
		if (type == 0)
			addMoney = dao.addMoney(en.getUser_id(), money, point);
		else
			addMoney = dao.addMoney(en.getUser_id(), money, 0, point, show);
		if (addMoney)
		{
			if (money != 0.0D)
				AddDetailDesc(money, en.getUser_money() + money, desc, item, type, en.getUser_id());
			if (point != 0)
				AddPointDesc(point, en.getUser_point() + point, 0, en.getUser_id(), item, desc);
		}
		return addMoney;
	}

	public static boolean MonToDong(Bc_user en, double money, String item, int type, String desc)
	{
		boolean dongjie = dao.MoneyToDongjie(en.getUser_id(), money);
		if (dongjie)
			AddDetailDesc(-money, en.getUser_money() - money, desc, item, type, en.getUser_id());
		return dongjie;
	}

	public static boolean DongToMon(Bc_user en, double money, String item, int type, String desc)
	{
		int user_id = en.getUser_id();
		double dong = en.getUser_dong();
		if (money > dong && money < dong + wucha)
			money = dong;
		boolean dongToMoney = dao.DongToMoney(user_id, money);
		if (dongToMoney)
			AddDetailDesc(money, en.getUser_money() + money, desc, item, type, user_id);
		return dongToMoney;
	}

	public static boolean DongSub(int user_id, double money)
	{
		Bc_user find = find(user_id);
		return DongSub(find, money);
	}

	public static boolean DongSub(Bc_user en, double money)
	{
		int user_id = en.getUser_id();
		double dong = en.getUser_dong();
		if (money < dong && money + wucha > dong || money > dong && money < dong + wucha)
			money = dong;
		return dao.DongSub(user_id, money);
	}

	public static void AddDetailDesc(double addsub, double bal, String des, String item, int type, int user_id)
	{
		Bc_detail en = new Bc_detail();
		en.setDetail_balance(bal);
		en.setDetail_desc(des);
		en.setDetail_item(item);
		en.setDetail_addsub(addsub);
		en.setDetail_time(TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		en.setDetail_type(type);
		en.setUser_id(user_id);
		dao.add(en);
	}

	public static void AddPointDesc(int addsub, int have, int type, int userid, String item, String desc)
	{
		Bc_point en = new Bc_point();
		en.setPoint_desc(desc);
		en.setPoint_have(have);
		en.setPoint_item(item);
		en.setPoint_addsub(addsub);
		en.setPoint_time(TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		en.setPoint_type(type);
		en.setUser_id(userid);
		dao.add(en);
	}

}
