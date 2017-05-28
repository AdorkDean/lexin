package com.caipiao.activity;

import java.util.List;


public class ActivityService
{

	IActivity dao;

	public ActivityService()
	{
		dao = new IActivityImpl();
	} 

	public List finds(int status)
	{
		return dao.finds(status);
	}

	public Activity find(String Acr_type)
	{
		return dao.find(Acr_type);
	}
}
