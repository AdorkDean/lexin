package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_point;
import com.caipiao.intface.Bc_pointIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PointIntfaceImpl
		implements Bc_pointIntface
{
	Mysql dao = Mysql.getInstance();

	public boolean delete(int Point_id)
	{
		return this.dao.delete("delete from Bc_point where Point_id=?", new Object[] { Integer.valueOf(Point_id) });
	}

	public Bc_point find(int Point_id)
	{
		return (Bc_point)this.dao.find("select * from Bc_point where Point_id=?", Bc_point.class, new Object[] { Integer.valueOf(Point_id) });
	}

	public boolean update(int Point_id, Map<String, Object> map)
	{
		return this.dao.updateMap("update Bc_point set ", " where Point_id=?", map, new Object[] { Integer.valueOf(Point_id) });
	}

	public List<Bc_point> finds(int userid, String btime, String etime, int type, int subadd, int start, int limit)
	{
		String sql = "select * from Bc_point where";
		ArrayList list = new ArrayList();
		if (-1 != userid) {
			sql = sql + " User_id=?"; list.add(Integer.valueOf(userid));
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and Point_time>? and Point_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		if (-1 != type) {
			sql = sql + " and Point_type=?"; list.add(Integer.valueOf(type));
		}
		if (-1 != subadd) {
			if (subadd == 0)
				sql = sql + " and Point_addsub>0";
			else {
				sql = sql + " and Point_addsub<0";
			}
		}
		sql = sql + " order by Point_time desc limit ?,?"; list.add(Integer.valueOf(start)); list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return this.dao.finds(sql, Bc_point.class, list.toArray());
	}

	public int findscount(int userid, String btime, String etime, int type, int subadd)
	{
		String sql = "select count(Point_id) from Bc_point where";
		ArrayList list = new ArrayList();
		if (-1 != userid) {
			sql = sql + " User_id=?"; list.add(Integer.valueOf(userid));
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and Point_time>? and Point_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		if (-1 != type) {
			sql = sql + " and Point_type=?"; list.add(Integer.valueOf(type));
		}
		if (-1 != subadd) {
			if (subadd == 0)
				sql = sql + " and Point_addsub>0";
			else {
				sql = sql + " and Point_addsub<0";
			}
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where")) {
			sql = sql.replace("where", "");
		}
		return this.dao.getCount(sql, list.toArray());
	}
}