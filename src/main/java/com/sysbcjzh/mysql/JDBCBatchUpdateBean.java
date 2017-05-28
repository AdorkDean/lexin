package com.sysbcjzh.mysql;

import java.util.Collection;
import java.util.LinkedHashMap;

public class JDBCBatchUpdateBean
{
	private final LinkedHashMap<String, JDBCBatchBean> batchBeansMap = new LinkedHashMap();

	public final void addJDBCBatch(String sql, Object[] params) {
		if (!this.batchBeansMap.containsKey(sql)) {
			this.batchBeansMap.put(sql, new JDBCBatchBean(sql));
		}
		((JDBCBatchBean)this.batchBeansMap.get(sql)).addBatchParameter(params);
	}

	public final Collection<JDBCBatchBean> getJDBCBatchs() {
		return this.batchBeansMap.values();
	}
}