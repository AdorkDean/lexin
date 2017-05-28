package com.sysbcjzh.mysql;

import com.caipiao.utils.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CommonJdbcDaoWeb extends JdbcDaoSupport
		implements JdbcDaoInterface
{
	private static CommonJdbcDaoWeb INSTANCE = new CommonJdbcDaoWeb();

	public static CommonJdbcDaoWeb getInstance() { return INSTANCE; }

	private CommonJdbcDaoWeb() {
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			String fileName = "mysql.xml";
			InputStream inputStream = CommonJdbcDaoWeb.class.getClassLoader().getResourceAsStream(fileName);
			if (inputStream == null) {
				inputStream = new FileInputStream(new File(fileName));
			}
			JAXPConfigurator.configure(new InputStreamReader(inputStream), false);
			ProxoolDataSource proxoolDataSource = new ProxoolDataSource("dbname");
			setDataSource(proxoolDataSource);
		}
		catch (ProxoolException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<DynaBean> queryDynaBeans(String sql, Object[] args)
	{
		return (List)getJdbcTemplate().query(sql, args, new ResultSetExtractor()
		{
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException
			{
				RowSetDynaClass rsdc = new RowSetDynaClass(rs);
				return rsdc.getRows();
			} } );
	}

	public int execute(String sql, Object[] args) {
		return getJdbcTemplate().update(sql, args);
	}

	public <T> T query(String sql, ResultSetExtractor<T> rch) {
		return getJdbcTemplate().query(sql, rch);
	}

	public void query(RowCallbackHandler rowCallbackHandler, int resultSetType, String sql, Object[] args)
	{
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql);
		factory.setResultSetType(resultSetType);

		PreparedStatementCreator creator = factory.newPreparedStatementCreator(sql, args);
		getJdbcTemplate().query(creator, rowCallbackHandler);
	}

	public void query(RowCallbackHandler rowCallbackHandler, String sql, Object[] args)
	{
		if (args == null)
			getJdbcTemplate().query(sql, rowCallbackHandler);
		else
			getJdbcTemplate().query(sql, args, rowCallbackHandler);
	}

	public void query(JDBCQueryBean queryBean, RowCallbackHandler rowCallbackHandler)
	{
		query(rowCallbackHandler, queryBean.getSql(), queryBean.getParameters());
	}

	public long queryForLong(String sql, Object[] args)
	{
		return getJdbcTemplate().queryForLong(sql, args);
	}

	public long getCount(String sql, Object[] args)
	{
		sql = "select count(*) from (" + sql + ")";
		long count = queryForLong(sql, args);
		return count;
	}

	public long getCount(JDBCQueryBean queryBean)
	{
		return getCount(queryBean.getSql(), queryBean.getParameters());
	}

	public int queryForInt(String sql, Object[] args)
	{
		return getJdbcTemplate().queryForInt(sql, args);
	}

	public List<Map<String, Object>> queryForList(String sql, Object[] args)
	{
		return getJdbcTemplate().queryForList(sql, args);
	}

	public Map<String, Object> queryForMap(String sql, Object[] args)
	{
		return getJdbcTemplate().queryForMap(sql, args);
	}

	public void excuteBatch(String sql,final List<Object[]> updateParameters)
	{
		getJdbcTemplate().execute(sql, new PreparedStatementCallback()
		{
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
			{
				if (updateParameters != null) {
					Log.ShowInfo("更新数据量：" + updateParameters.size());
					for (Object[] objects : updateParameters) {
						if (objects == null)
							continue;
						for (int i = 1; i < objects.length + 1; i++)
						{
							StatementCreatorUtils.setParameterValue(ps, i, -2147483648, objects[(i - 1)]);
						}
						ps.addBatch();
					}

					ps.executeBatch();
				}

				return null;
			}
		});
	}

	public void excuteBatch(JDBCBatchBean batchbean)
	{
		excuteBatch(batchbean.getSql(), batchbean.getBatchParams());
	}

	public void excuteBatchs(Collection<JDBCBatchBean> batchBeans)
	{
		for (JDBCBatchBean jdbcBatchBean : batchBeans)
			excuteBatch(jdbcBatchBean);
	}

	public void excuteBatch(JDBCBatchUpdateBean bean)
	{
		Collection jdbcBatchs = bean.getJDBCBatchs();
		JDBCBatchBean jdbcBatchBean;
		for (Iterator iterator = jdbcBatchs.iterator(); iterator.hasNext(); excuteBatch(jdbcBatchBean))
			jdbcBatchBean = (JDBCBatchBean)iterator.next();
	}
}