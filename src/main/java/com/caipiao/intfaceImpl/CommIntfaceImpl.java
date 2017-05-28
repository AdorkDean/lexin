package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_comm;
import com.caipiao.intface.Bc_commIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommIntfaceImpl
		implements Bc_commIntface
{
	Mysql dao = Mysql.getInstance();

	public boolean add(Bc_comm en) {
		return this.dao.add(en);
	}

	public boolean delete(int Comm_id)
	{
		return false;
	}

	public Bc_comm find(int Comm_id)
	{
		return (Bc_comm)this.dao.find("select * from Bc_comm where Comm_id=?", Bc_comm.class, new Object[] { Integer.valueOf(Comm_id) });
	}

	public Bc_comm findById(int user_id)
	{
		return (Bc_comm)this.dao.find("select * from Bc_comm where User_id=?", Bc_comm.class, new Object[] { Integer.valueOf(user_id) });
	}

	public boolean update(int userid, Map<String, Object> map)
	{
		return this.dao.updateMap("update Bc_comm set ", " where User_id=?", map, new Object[] { Integer.valueOf(userid) });
	}

	public List<Bc_comm> finds(String user, int start, int limit)
	{
		String sql = "select * from Bc_comm";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(user)) {
			sql = sql + " where User_name like ?"; list.add("%" + user + "%");
		}
		sql = sql + " order by Comm_id desc limit ?,?"; list.add(Integer.valueOf(start)); list.add(Integer.valueOf(limit));
		return this.dao.finds(sql, Bc_comm.class, list.toArray());
	}

	public int findsCount(String user)
	{
		String sql = "select count(Comm_id) from Bc_comm";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(user)) {
			sql = sql + " where User_name like ?"; list.add("%" + user + "%");
		}
		return this.dao.getCount(sql, list.toArray());
	}
}