package com.sysbcjzh.mysql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Mysql
{
	CommonJdbcDaoWeb daos = CommonJdbcDaoWeb.getInstance();

	public static Mysql getInstance() { return new Mysql();
	}

	public boolean add(Object en)
	{
		try
		{
			int executeInsertQuery = MysqlReflect.executeInsertQuery(this.daos, en);
			if (executeInsertQuery == 1)
				return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(String sql, Object[] args)
	{
		return this.daos.execute(sql, args) > 0;
	}

	public <T> T find(String sql, Class<T> c, Object[] args)
	{
		try
		{
			List<T> fromList = MysqlReflect.fromList(c, this.daos.queryForList(sql, args));
			if ((fromList != null) && (fromList.size() > 0))
				return fromList.get(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> List<T> finds(String sql, Class<T> c, Object[] args)
	{
		try
		{
			List queryForList = this.daos.queryForList(sql, args);
			if (queryForList != null)
				return MysqlReflect.fromList(c, this.daos.queryForList(sql, args));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getCount(String sql, Object[] args)
	{
		try
		{
			return this.daos.queryForInt(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean updateMap(String sqltable, String sqlwhere, Map<String, Object> map, Object[] arg)
	{
		StringBuilder sb = new StringBuilder(sqltable);
		List param = new ArrayList();
		Set<Entry<String, Object>> entries = map.entrySet();
//		Set entrySet = map.entrySet();
		for (Entry<String, Object> entry : entries) {
//		for (Map.Entry entry : entrySet) {
			String key = entry.getKey();
			sb.append(key + "=?,");
			Object value = entry.getValue();
			param.add(value);
		}
		String sql = sb.toString();
		if (sql.endsWith(",")) {
			sql = sql.substring(0, sql.length() - 1);
		}
		sql = sql.concat(sqlwhere);
		Object[] arrayOfObject;
		int value = (arrayOfObject = arg).length;
		for (int key = 0; key < value; key++) {
			Object ars = arrayOfObject[key];
			param.add(ars);
		}
		try
		{
			int execute = this.daos.execute(sql, param.toArray());
			return execute > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(String sql, Object[] args)
	{
		try
		{
			int execute = this.daos.execute(sql, args);
			return execute > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isExist(String sql, Object[] args)
	{
		try
		{
			int queryForInt = this.daos.queryForInt(sql, args);
			return queryForInt > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void excuteBatchs(Collection<JDBCBatchBean> batchBeans)
	{
		this.daos.excuteBatchs(batchBeans);
	}
}