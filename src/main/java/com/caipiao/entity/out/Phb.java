
//    Phb.java

package com.caipiao.entity.out;

import com.caipiao.utils.UserSession;

/**
 * 排行榜
 */
public class Phb
{

	private int Phb_id;
	private int User_id;
	private String User_name;
	private double Phb_value;

	public Phb()
	{
	}

	public int getPhb_id()
	{
		return Phb_id;
	}

	public void setPhb_id(int phb_id)
	{
		Phb_id = phb_id;
	}

	public int getUser_id()
	{
		return User_id;
	}

	public void setUser_id(int user_id)
	{
		User_id = user_id;
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

	public double getPhb_value()
	{
		return (0.0D + (double)(int)(Phb_value * 100D)) / 100D;
	}

	public void setPhb_value(double phb_value)
	{
		Phb_value = phb_value;
	}
}
