
//    DetailIntfaceImpl.java

package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_detail;
import com.caipiao.intface.Bc_detailIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class DetailIntfaceImpl
	implements Bc_detailIntface
{

	Mysql dao;

	public DetailIntfaceImpl()
	{
		dao = Mysql.getInstance();
	}

	public List finds(int userid, String btime, String etime, int type, int status, int start, int limit)
	{
		String sql = "select * from Bc_detail where User_id=?";
		ArrayList list = new ArrayList();
		list.add(Integer.valueOf(userid));
		if (StringUtils.isNotEmptyAll(new String[] {
	btime, etime
}))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Detail_time>? and Detail_time<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		if (-1 != type)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Detail_type=?").toString();
			list.add(Integer.valueOf(type));
		}
		if (-1 != status)
			if (status == 0)
				sql = (new StringBuilder(String.valueOf(sql))).append(" and Detail_addsub>0").toString();
			else
				sql = (new StringBuilder(String.valueOf(sql))).append(" and Detail_addsub<0").toString();
		sql = (new StringBuilder(String.valueOf(sql))).append(" order by Detail_id desc limit ?,?").toString();
		list.add(Integer.valueOf(start));
		list.add(Integer.valueOf(limit));
		return dao.finds(sql, Bc_detail.class, list.toArray());
	}

	public int findcount(int userid, String btime, String etime, int type, int status)
	{
		String sql = "select count(Detail_id) from Bc_detail where User_id=?";
		ArrayList list = new ArrayList();
		list.add(Integer.valueOf(userid));
		if (StringUtils.isNotEmptyAll(new String[] {
	btime, etime
}))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Detail_time>? and Detail_time<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		if (-1 != type)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Detail_type=?").toString();
			list.add(Integer.valueOf(type));
		}
		if (-1 != status)
			if (status == 0)
				sql = (new StringBuilder(String.valueOf(sql))).append(" and Detail_addsub>0").toString();
			else
				sql = (new StringBuilder(String.valueOf(sql))).append(" and Detail_addsub<0").toString();
		return dao.getCount(sql, list.toArray());
	}
}
