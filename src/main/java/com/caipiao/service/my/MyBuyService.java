
//    MyBuyService.java

package com.caipiao.service.my;

import com.caipiao.entity.Bc_phb;
import com.caipiao.intface.Bc_buyIntface;
import com.caipiao.intface.Bc_phbIntface;
import com.caipiao.intfaceImpl.BuyIntfaceImpl;
import com.caipiao.intfaceImpl.PhbIntfaceImpl;
import java.util.List;

public class MyBuyService
{

	Bc_buyIntface dao;
	Bc_phbIntface phbdao;

	public MyBuyService()
	{
		dao = new BuyIntfaceImpl();
		phbdao = new PhbIntfaceImpl();
	}

	public List findBuy(int userid)
	{
		return dao.findBuy(userid, null, null, null, -2, -1, 2, 10);
	}

	public Bc_phb findphb(int user_id)
	{
		return phbdao.findByUser(user_id);
	}

	public List findBuy(int userid, String btime, String etime, String lottery, int status, int ishm, int start, 
			int limit)
	{
		return dao.findBuy(userid, btime, etime, lottery, status, ishm, start, limit);
	}

	public int findBuyCount(int userid, String btime, String etime, String lottery, int status, int ishm)
	{
		return dao.findBuyCount(userid, btime, etime, lottery, status, ishm);
	}
}
