package com.caipiao.activity;

import java.util.HashMap;

/**
 * 标记业务类
 */
public class SignEntityService
{

	ISignEntity dao;

	public SignEntityService()
	{
		dao = new ISignEntityImpl();
	}

	public String SignService(int userid, String today)
	{
		String result = "1";
		SignEntity find = dao.find(userid, today);
		if (find == null)
		{
			int findsur = dao.findsur(userid);
			if (findsur >= 999)//todo  9999
				result = "0";
			else
				result = "-1";
		}
		return result;
	}

	public SignEntity find(int userid, String today)
	{
		return dao.find(userid, today);
	}

	public boolean addData(int userid, String date, int number)
	{
		SignEntity en = new SignEntity();
		en.setTime(date);
		en.setUser_id(userid);
		en.setSignAll(number);
		int big = 0;
		if (number == 7 || number == 15)//todo number == 3 || number == 18
			big = 1;
		en.setBigSign(big);
		return dao.add(en);
	}

	public boolean updateBig(int signid)
	{
		HashMap map = new HashMap();
		map.put("BigSign", Integer.valueOf(2));
		return dao.update(signid, map);
	}
}
