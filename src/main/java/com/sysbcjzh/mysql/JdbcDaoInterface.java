package com.sysbcjzh.mysql;

import org.apache.commons.beanutils.DynaBean;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract interface JdbcDaoInterface {
    public abstract List<DynaBean> queryDynaBeans(String paramString, Object[] paramArrayOfObject);

    public abstract int execute(String paramString, Object[] paramArrayOfObject);

    public abstract <T> T query(String paramString, ResultSetExtractor<T> paramResultSetExtractor);

    public abstract void query(RowCallbackHandler paramRowCallbackHandler, int paramInt, String paramString, Object[] paramArrayOfObject);

    public abstract void query(RowCallbackHandler paramRowCallbackHandler, String paramString, Object[] paramArrayOfObject);

    public abstract long queryForLong(String paramString, Object[] paramArrayOfObject);

    public abstract int queryForInt(String paramString, Object[] paramArrayOfObject);

    public abstract List<Map<String, Object>> queryForList(String paramString, Object[] paramArrayOfObject);

    public abstract Map<String, Object> queryForMap(String paramString, Object[] paramArrayOfObject);

    public abstract long getCount(String paramString, Object[] paramArrayOfObject);

    public abstract long getCount(JDBCQueryBean paramJDBCQueryBean);

    public abstract void excuteBatch(String paramString, List<Object[]> paramList);

    public abstract void excuteBatch(JDBCBatchBean paramJDBCBatchBean);

    public abstract void query(JDBCQueryBean paramJDBCQueryBean, RowCallbackHandler paramRowCallbackHandler);

    public abstract void excuteBatchs(Collection<JDBCBatchBean> paramCollection);

    public abstract void excuteBatch(JDBCBatchUpdateBean paramJDBCBatchUpdateBean);
}