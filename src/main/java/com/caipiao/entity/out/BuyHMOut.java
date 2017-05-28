

package com.caipiao.entity.out;

import com.caipiao.utils.UserSession;

public class BuyHMOut
{

	private int Buy_id;
	private String Buy_item;
	private int Buy_isopen;
	private String User_name;
	private int User_level;
	private String Buy_lot;
	private double Buy_money;
	private double Buy_baodi;
	private double Buy_have;
	private int Buy_hmsort;
	private int Buy_take;
	private int Buy_status;
	private String Buy_fqihao;

	public BuyHMOut()
	{
	}

	public int getBuy_status()
	{
		return Buy_status;
	}

	public void setBuy_status(int buy_status)
	{
		Buy_status = buy_status;
	}

	public int getBao()
	{
		return (int)Math.floor((Buy_baodi * 100D) / Buy_money);
	}

	public int getJindu()
	{
		return (int)Math.floor(((Buy_money - Buy_have) * 100D) / Buy_money);
	}

	public String getBuy_fqihao()
	{
		return Buy_fqihao;
	}

	public void setBuy_fqihao(String buy_fqihao)
	{
		Buy_fqihao = buy_fqihao;
	}

	public int getBuy_id()
	{
		return Buy_id;
	}

	public void setBuy_id(int buy_id)
	{
		Buy_id = buy_id;
	}

	public String getBuy_item()
	{
		return Buy_item;
	}

	public void setBuy_item(String buy_item)
	{
		Buy_item = buy_item;
	}

	public int getBuy_isopen()
	{
		return Buy_isopen;
	}

	public void setBuy_isopen(int buy_isopen)
	{
		Buy_isopen = buy_isopen;
	}

	public String getUser_name()
	{
		return User_name;
	}

	public String getUser_nameDis()
	{
		return UserSession.DisUser(User_name);
	}

	public void setUser_name(String user_name)
	{
		User_name = user_name;
	}

	public int getUser_level()
	{
		return UserSession.getLevel(User_level);
	}

	public void setUser_level(int user_level)
	{
		User_level = user_level;
	}

	public String getBuy_lot()
	{
		return Buy_lot;
	}

	public void setBuy_lot(String buy_lot)
	{
		Buy_lot = buy_lot;
	}

	public double getBuy_money()
	{
		return Buy_money;
	}

	public void setBuy_money(double buy_money)
	{
		Buy_money = buy_money;
	}

	public double getBuy_baodi()
	{
		return Buy_baodi;
	}

	public void setBuy_baodi(double buy_baodi)
	{
		Buy_baodi = buy_baodi;
	}

	public double getBuy_have()
	{
		return Buy_have;
	}

	public void setBuy_have(double buy_have)
	{
		Buy_have = buy_have;
	}

	public int getBuy_hmsort()
	{
		return Buy_hmsort;
	}

	public void setBuy_hmsort(int buy_hmsort)
	{
		Buy_hmsort = buy_hmsort;
	}

	public int getBuy_take()
	{
		return Buy_take;
	}

	public void setBuy_take(int buy_take)
	{
		Buy_take = buy_take;
	}
}
