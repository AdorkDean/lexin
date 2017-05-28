
//    RedsIntfaceImpl.java

package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_reds;
import com.caipiao.intface.Bc_redsIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.*;

public class RedsIntfaceImpl
	implements Bc_redsIntface
{

	Mysql dao;

	public RedsIntfaceImpl()
	{
		dao = Mysql.getInstance();
	}

	public boolean add(Bc_reds en)
	{
		return dao.add(en);
	}

	public boolean delete(int Reds_id)
	{
		return dao.delete("delete from Bc_reds where Reds_id=?", new Object[] {
			Integer.valueOf(Reds_id)
		});
	}

	public Bc_reds find(int Reds_id)
	{
		return (Bc_reds)dao.find("select * from Bc_reds where Reds_id=?", Bc_reds.class, new Object[] {
			Integer.valueOf(Reds_id)
		});
	}

	public boolean update(int Reds_id, Map map)
	{
		return dao.updateMap("update Bc_reds set ", " where Reds_id=?", map, new Object[] {
			Integer.valueOf(Reds_id)
		});
	}

	public List finds(int userid, String btime, String etime, int type, int subadd, int start, int limit)
	{
		String sql = "select * from Bc_reds where";
		ArrayList list = new ArrayList();
		if (-1 != userid)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" User_id=?").toString();
			list.add(Integer.valueOf(userid));
		}
		if (StringUtils.isNotEmptyAll(new String[] {
	btime, etime
}))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Reds_time>? and Reds_time<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		if (-1 != type)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Reds_type=?").toString();
			list.add(Integer.valueOf(type));
		}
		if (-1 != subadd)
			if (subadd == 0)
				sql = (new StringBuilder(String.valueOf(sql))).append(" and Reds_addsub>0").toString();
			else
				sql = (new StringBuilder(String.valueOf(sql))).append(" and Reds_addsub<0").toString();
		sql = (new StringBuilder(String.valueOf(sql))).append(" order by Reds_time desc limit ?,?").toString();
		list.add(Integer.valueOf(start));
		list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return dao.finds(sql, Bc_reds.class, list.toArray());
	}

	public int findscount(int userid, String btime, String etime, int type, int subadd)
	{
		String sql = "select count(Reds_id) from Bc_reds where";
		ArrayList list = new ArrayList();
		if (-1 != userid)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" User_id=?").toString();
			list.add(Integer.valueOf(userid));
		}
		if (StringUtils.isNotEmptyAll(new String[] {
	btime, etime
}))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Reds_time>? and Reds_time<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		if (-1 != type)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Reds_type=?").toString();
			list.add(Integer.valueOf(type));
		}
		if (-1 != subadd)
			if (subadd == 0)
				sql = (new StringBuilder(String.valueOf(sql))).append(" and Reds_addsub>0").toString();
			else
				sql = (new StringBuilder(String.valueOf(sql))).append(" and Reds_addsub<0").toString();
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where"))
			sql = sql.replace("where", "");
		return dao.getCount(sql, list.toArray());
	}
}
