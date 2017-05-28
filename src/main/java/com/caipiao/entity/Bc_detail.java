
//    Bc_detail.java

package com.caipiao.entity;


public class Bc_detail
{

	private int Detail_id;
	private int User_id;
	private double Detail_balance;
	private double Detail_addsub;
	private int Detail_type;
	private String Detail_time;
	private String Detail_item;
	private String Detail_desc;

	public Bc_detail()
	{
	}

	public double getDetail_addsub()
	{
		return Detail_addsub;
	}

	public void setDetail_addsub(double detail_addsub)
	{
		Detail_addsub = detail_addsub;
	}

	public int getDetail_id()
	{
		return Detail_id;
	}

	public void setDetail_id(int detail_id)
	{
		Detail_id = detail_id;
	}

	public int getUser_id()
	{
		return User_id;
	}

	public void setUser_id(int user_id)
	{
		User_id = user_id;
	}

	public double getDetail_balance()
	{
		return Detail_balance;
	}

	public void setDetail_balance(double detail_balance)
	{
		Detail_balance = detail_balance;
	}

	public int getDetail_type()
	{
		return Detail_type;
	}

	public void setDetail_type(int detail_type)
	{
		Detail_type = detail_type;
	}

	public String getDetail_time()
	{
		return Detail_time;
	}

	public void setDetail_time(String detail_time)
	{
		Detail_time = detail_time;
	}

	public String getDetail_item()
	{
		return Detail_item;
	}

	public void setDetail_item(String detail_item)
	{
		Detail_item = detail_item;
	}

	public String getDetail_desc()
	{
		return Detail_desc;
	}

	public void setDetail_desc(String detail_desc)
	{
		Detail_desc = detail_desc;
	}
}
