package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_rech;
import com.caipiao.entity.out.Rech;
import com.caipiao.intface.Bc_rechIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RechIntfaceImpl
		implements Bc_rechIntface
{
	Mysql dao = Mysql.getInstance();

	public boolean add(Bc_rech en) {
		return this.dao.add(en);
	}

	public boolean delete(int rech_id)
	{
		return this.dao.delete("delete from Bc_rech where Rech_id=?", new Object[] { Integer.valueOf(rech_id) });
	}


	public Bc_rech find(int rech_id)
	{
		return (Bc_rech)this.dao.find("select * from Bc_rech where Rech_id=?", Bc_rech.class, new Object[] { Integer.valueOf(rech_id) });
	}

	public Bc_rech find(String item, String name) {
		return (Bc_rech)this.dao.find("select * from Bc_rech where Rech_item=? and User_name=?", Bc_rech.class, new Object[] { item, name });
	}

	public boolean update(int rech_id, Map<String, Object> map) {
		return this.dao.updateMap("update Bc_rech set ", " where Rech_id=?", map, new Object[] { Integer.valueOf(rech_id) });
	}

	public List<Rech> finds(int user_id, String btime, String etime, int type, int utype, int status, int start, int limit)
	{
		String sql = "select a.Rech_id,a.User_id,a.User_name,a.Rech_item,a.Rech_time,a.Rech_money,a.Rech_give,a.Rech_type,a.Rech_status,a.Rech_desc,a.Rech_douser,a.Rech_dotime,b.User_type,c.User_name as User_upname";
		sql = sql + " from Bc_rech a LEFT JOIN Bc_user b ON a.User_id=b.User_id LEFT JOIN Bc_user c ON b.User_upid=c.User_id where";
		ArrayList list = new ArrayList();
		if (-1 != user_id) {
			sql = sql + " a.User_id=?"; list.add(Integer.valueOf(user_id));
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and a.Rech_time>? and a.Rech_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		if (-1 != type) {
			sql = sql + " and a.Rech_type=?"; list.add(Integer.valueOf(type));
		}
		if (-1 != utype) {
			sql = sql + " and b.User_type=?"; list.add(Integer.valueOf(utype));
		}
		if (-1 != status) {
			sql = sql + " and a.Rech_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql + " order by a.Rech_time desc limit ?,?"; list.add(Integer.valueOf(start)); list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return this.dao.finds(sql, Rech.class, list.toArray());
	}

	public int findscount(int user_id, String btime, String etime, int type, int utype, int status)
	{
		String sql = "select count(a.Rech_id) from Bc_rech a LEFT JOIN Bc_user b ON a.User_id=b.User_id where";
		ArrayList list = new ArrayList();
		if (-1 != user_id) {
			sql = sql + " a.User_id=?"; list.add(Integer.valueOf(user_id));
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and a.Rech_time>? and a.Rech_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		if (-1 != type) {
			sql = sql + " and a.Rech_type=?"; list.add(Integer.valueOf(type));
		}
		if (-1 != utype) {
			sql = sql + " and b.User_type=?"; list.add(Integer.valueOf(utype));
		}
		if (-1 != status) {
			sql = sql + " and a.Rech_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where")) {
			sql = sql.replace("where", "");
		}
		return this.dao.getCount(sql, list.toArray());
	}

	public List<Bc_rech> finds(int user_id, String btime, String etime, int type, int status, int start, int limit) {
		String sql = "select * from Bc_rech where";
		ArrayList list = new ArrayList();
		if (-1 != user_id) {
			sql = sql + " User_id=?"; list.add(Integer.valueOf(user_id));
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and Rech_time>? and Rech_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		if (-1 != type) {
			sql = sql + " and Rech_type=?"; list.add(Integer.valueOf(type));
		}
		if (-1 != status) {
			sql = sql + " and Rech_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql + " order by Rech_time desc limit ?,?"; list.add(Integer.valueOf(start)); list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return this.dao.finds(sql, Bc_rech.class, list.toArray());
	}

	public int findscount(int user_id, String btime, String etime, int type, int status)
	{
		String sql = "select count(Rech_id) from Bc_rech where";
		ArrayList list = new ArrayList();
		if (-1 != user_id) {
			sql = sql + " User_id=?"; list.add(Integer.valueOf(user_id));
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and Rech_time>? and Rech_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		if (-1 != type) {
			sql = sql + " and Rech_type=?"; list.add(Integer.valueOf(type));
		}
		if (-1 != status) {
			sql = sql + " and Rech_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where")) {
			sql = sql.replace("where", "");
		}
		return this.dao.getCount(sql, list.toArray());
	}

	public Bc_rech find(String rech_item)
	{
		return (Bc_rech)this.dao.find("select * from Bc_rech where Rech_item=?", Bc_rech.class, new Object[] { rech_item });
	}

	public int findsDayRechCount(int UserId) {
		return this.dao.getCount("select count(Rech_id) from Bc_rech where to_days(Rech_dotime)=to_days(now()) and Rech_status=1 and User_id=?", new Object[] { Integer.valueOf(UserId) });
	}

	public List<Bc_rech> findCpsRech(int upid, String btime, String etime, String name, int status, int start, int limit)
	{
		String sql = "select a.User_name,a.Rech_time,a.Rech_dotime,a.Rech_money,a.Rech_give,a.Rech_status,a.User_id";
		sql = sql + " from Bc_rech a INNER JOIN Bc_user b ON a.User_id=b.User_id and b.User_upid=? where";
		ArrayList list = new ArrayList();
		list.add(Integer.valueOf(upid));
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and a.Rech_time>? and a.Rech_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		if (StringUtils.isNotBlank(name)) {
			sql = sql + " and a.User_name=?"; list.add(name);
		}
		if (-1 != status) {
			sql = sql + " and a.Rech_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql + " order by a.Rech_time desc limit ?,?"; list.add(Integer.valueOf(start)); list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return this.dao.finds(sql, Bc_rech.class, list.toArray());
	}

	public int findCpsRechcount(int upid, String btime, String etime, String name, int status) {
		String sql = "select count(a.Rech_id) from Bc_rech a INNER JOIN Bc_user b ON a.User_id=b.User_id and b.User_upid=? where";
		ArrayList list = new ArrayList(); list.add(Integer.valueOf(upid));
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and a.Rech_time>? and a.Rech_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		if (StringUtils.isNotBlank(name)) {
			sql = sql + " and a.User_name=?"; list.add(name);
		}
		if (-1 != status) {
			sql = sql + " and a.Rech_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where")) {
			sql = sql.replace("where", "");
		}
		return this.dao.getCount(sql, list.toArray());
	}
}