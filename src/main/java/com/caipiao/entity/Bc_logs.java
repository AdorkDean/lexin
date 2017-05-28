
//    Bc_logs.java

package com.caipiao.entity;


public class Bc_logs
{

	private int Logs_id;
	private int User_id;
	private String User_name;
	private String Logs_desc;
	private int Logs_type;
	private int Logs_level;
	private String Logs_time;
	private String Logs_ip;

	public Bc_logs()
	{
	}

	public String getUser_name()
	{
		return User_name;
	}

	public void setUser_name(String user_name)
	{
		User_name = user_name;
	}

	public int getLogs_level()
	{
		return Logs_level;
	}

	public void setLogs_level(int logs_level)
	{
		Logs_level = logs_level;
	}

	public int getLogs_id()
	{
		return Logs_id;
	}

	public void setLogs_id(int logs_id)
	{
		Logs_id = logs_id;
	}

	public int getUser_id()
	{
		return User_id;
	}

	public void setUser_id(int user_id)
	{
		User_id = user_id;
	}

	public String getLogs_desc()
	{
		return Logs_desc;
	}

	public void setLogs_desc(String logs_desc)
	{
		Logs_desc = logs_desc;
	}

	public int getLogs_type()
	{
		return Logs_type;
	}

	public void setLogs_type(int logs_type)
	{
		Logs_type = logs_type;
	}

	public String getLogs_time()
	{
		return Logs_time;
	}

	public void setLogs_time(String logs_time)
	{
		Logs_time = logs_time;
	}

	public String getLogs_ip()
	{
		return Logs_ip;
	}

	public void setLogs_ip(String logs_ip)
	{
		Logs_ip = logs_ip;
	}
}
