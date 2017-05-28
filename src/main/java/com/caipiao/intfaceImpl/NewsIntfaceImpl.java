package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_news;
import com.caipiao.intface.Bc_newsIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewsIntfaceImpl
		implements Bc_newsIntface
{
	Mysql dao = Mysql.getInstance();

	public boolean add(Bc_news en) {
		return this.dao.add(en);
	}

	public boolean delete(int News_id)
	{
		return this.dao.delete("delete from Bc_news where News_id=?", new Object[] { Integer.valueOf(News_id) });
	}

	public Bc_news find(int News_id)
	{
		Bc_news find = (Bc_news)this.dao.find("select * from Bc_news where News_id=?", Bc_news.class, new Object[] { Integer.valueOf(News_id) });
		if (find != null) {
			this.dao.update("update Bc_news set News_point=News_point+1 where News_id=?", new Object[] { Integer.valueOf(News_id) });
		}
		return find;
	}

	public boolean update(int News_id, Map<String, Object> map)
	{
		return this.dao.updateMap("update Bc_news set ", " where News_id=?", map, new Object[] { Integer.valueOf(News_id) });
	}

	public List<Bc_news> findByType(String auther, String title, String btime, String etime, String soures, int type, int type2, int status, int strat, int limit)
	{
		String sql = "select News_id,News_auther,News_title,News_time,News_etime,News_soures,News_status,News_sort,News_image,News_point,News_type from Bc_news where";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(auther)) {
			sql = sql + " News_auther like ?"; list.add("%" + auther + "%");
		}
		if (StringUtils.isNotBlank(title)) {
			sql = sql + " and News_title like ?"; list.add("%" + title + "%");
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and News_time>? and News_time<?"; list.add(btime); list.add(etime);
		}
		if (StringUtils.isNotBlank(soures)) {
			sql = sql + " and News_soures like ?"; list.add("%" + soures + "%");
		}
		if ((-1 != type) && (-1 != type2)) {
			if (type != type2) {
				sql = sql + " and News_type>? and News_type<?"; list.add(Integer.valueOf(type)); list.add(Integer.valueOf(type2));
			} else {
				sql = sql + " and News_type=?"; list.add(Integer.valueOf(type));
			}
		}
		if (-1 != status) {
			sql = sql + " and News_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql + " order by News_time desc limit ?,?"; list.add(Integer.valueOf(strat)); list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return this.dao.finds(sql, Bc_news.class, list.toArray());
	}

	public int findByTypeCount(String auther, String title, String btime, String etime, String soures, int type, int type2, int status)
	{
		String sql = "select count(News_id) from Bc_news where";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(auther)) {
			sql = sql + " News_auther like ?"; list.add("%" + auther + "%");
		}
		if (StringUtils.isNotBlank(title)) {
			sql = sql + " and News_title like ?"; list.add("%" + title + "%");
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and News_time>? and News_time<?"; list.add(btime); list.add(etime);
		}
		if (StringUtils.isNotBlank(soures)) {
			sql = sql + " and News_soures like ?"; list.add("%" + soures + "%");
		}
		if ((-1 != type) && (-1 != type2)) {
			if (type != type2) {
				sql = sql + " and News_type>? and News_type<?"; list.add(Integer.valueOf(type)); list.add(Integer.valueOf(type2));
			} else {
				sql = sql + " and News_type=?"; list.add(Integer.valueOf(type));
			}
		}
		if (-1 != status) {
			sql = sql + " and News_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where")) {
			sql = sql.replace("where", "");
		}
		return this.dao.getCount(sql, list.toArray());
	}
}