package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_banks;
import com.caipiao.intface.Bc_banksIntface;
import com.sysbcjzh.mysql.Mysql;
import java.util.List;
import java.util.Map;

public class BanksIntfaceImpl
		implements Bc_banksIntface
{
	Mysql dao = Mysql.getInstance();

	public boolean add(Bc_banks en) {
		return this.dao.add(en);
	}

	public boolean delete(int Banks_id)
	{
		return this.dao.delete("delete from Bc_banks where Banks_id=?", new Object[] { Integer.valueOf(Banks_id) });
	}

	public Bc_banks find(int Banks_id)
	{
		return (Bc_banks)this.dao.find("select * from Bc_banks where Banks_id=?", Bc_banks.class, new Object[] { Integer.valueOf(Banks_id) });
	}

	public boolean update(int Banks_id, Map<String, Object> map)
	{
		return this.dao.updateMap("update Bc_banks set ", " where Banks_id=?", map, new Object[] { Integer.valueOf(Banks_id) });
	}

	public List<Bc_banks> finds(int User_id)
	{
		return this.dao.finds("select * from Bc_banks where User_id=?", Bc_banks.class, new Object[] { Integer.valueOf(User_id) });
	}

	public int findCountByUser(int User_id)
	{
		return this.dao.getCount("select count(Banks_id) from Bc_banks where User_id=?", new Object[] { Integer.valueOf(User_id) });
	}
}