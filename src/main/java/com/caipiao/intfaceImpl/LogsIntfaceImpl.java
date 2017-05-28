
//    LogsIntfaceImpl.java

package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_logs;
import com.caipiao.intface.Bc_logsIntface;
import com.caipiao.utils.TimeUtil;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class LogsIntfaceImpl
	implements Bc_logsIntface
{

	Mysql dao;

	public LogsIntfaceImpl()
	{
		dao = Mysql.getInstance();
	}

	public boolean add(Bc_logs en)
	{
		return dao.add(en);
	}

	public List finds(int userid, String btime, String etime, int type, int level, int start, int limit)
	{
		String sql = "select * from Bc_logs where";
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
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Logs_time>? and Logs_time<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		if (-1 != type)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Logs_type=?").toString();
			list.add(Integer.valueOf(type));
		}
		if (-1 != level)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Logs_level=?").toString();
			list.add(Integer.valueOf(level));
		}
		sql = (new StringBuilder(String.valueOf(sql))).append(" order by Logs_time desc limit ?,?").toString();
		list.add(Integer.valueOf(start));
		list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return dao.finds(sql, Bc_logs.class, list.toArray());
	}

	public int findscount(int userid, String btime, String etime, int type, int level)
	{
		String sql = "select count(Logs_id) from Bc_logs where";
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
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Logs_time>? and Logs_time<?").toString();
			list.add((new StringBuilder(String.valueOf(btime))).append(" 00:00:00").toString());
			list.add((new StringBuilder(String.valueOf(etime))).append(" 24:00:00").toString());
		}
		if (-1 != type)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Logs_type=?").toString();
			list.add(Integer.valueOf(type));
		}
		if (-1 != level)
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and Logs_level=?").toString();
			list.add(Integer.valueOf(level));
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where"))
			sql = sql.replace("where", "");
		return dao.getCount(sql, list.toArray());
	}

	public int findError(int userid, int level)
	{
		long longtime = System.currentTimeMillis() - 0x1b7740L;
		String time = TimeUtil.LongToString(longtime, "yyyy-MM-dd HH:mm:ss");
		return dao.getCount("select count(Logs_id) from Bc_logs where User_id=? and Logs_type=2 and Logs_level=? and Logs_time>?", new Object[] {
			Integer.valueOf(userid), Integer.valueOf(level), time
		});
	}
}
