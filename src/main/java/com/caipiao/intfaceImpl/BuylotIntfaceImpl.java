package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_buylot;
import com.caipiao.entity.out.OpenEntity;
import com.caipiao.entity.out.OutEntity;
import com.caipiao.intface.Bc_buylotIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuylotIntfaceImpl
		implements Bc_buylotIntface
{
	Mysql dao = Mysql.getInstance();

	public boolean add(Bc_buylot en) {
		return this.dao.add(en);
	}

	public boolean delete(int Buylot_id) {
		return this.dao.delete("delete from Bc_buylot where Buylot_id=?", new Object[] { Integer.valueOf(Buylot_id) });
	}




	public boolean update(int Buylot_id, Map<String, Object> map) {
		return this.dao.updateMap("update Bc_buylot set ", " where Buylot_id=?", map, new Object[] { Integer.valueOf(Buylot_id) });
	}

	public boolean update(String item, Map<String, Object> map) {
		return this.dao.updateMap("update Bc_buylot set ", " where Buy_item=?", map, new Object[] { item });
	}

	public Bc_buylot findByItemqh(String item, String qh) {
		return (Bc_buylot)this.dao.find("select * from Bc_buylot where Buy_item=? and Buylot_qihao=?", Bc_buylot.class, new Object[] { item, qh });
	}

	public List<OpenEntity> findEntityList(String lot, String qihao, int status) {
		String sql = "select a.Buylot_id,a.Buylot_money,a.Buy_item,a.Buylot_lot,a.Buylot_qihao,a.Buylot_haoma,a.Buylot_multiple,a.Buylot_status,b.Buy_money,b.Buy_code,b.Buy_iscont,b.Buy_ishm";
		sql = sql + " from Bc_buylot a left join Bc_buy b on a.Buy_item=b.Buy_item where LENGTH(a.Buylot_haoma)>0 and a.Buylot_lot=? and a.Buylot_qihao=? and a.Buylot_status=?";
		return this.dao.finds(sql, OpenEntity.class, new Object[] { lot, qihao, Integer.valueOf(status) });
	}

	public OpenEntity findEntityOne(int Buylot_id) {
		String sql = "select a.Buylot_id,a.Buylot_money,a.Buy_item,a.Buylot_lot,a.Buylot_qihao,a.Buylot_haoma,a.Buylot_multiple,a.Buylot_status,b.Buy_money,b.Buy_code,b.Buy_iscont,b.Buy_ishm";
		sql = sql + " from Bc_buylot a left join Bc_buy b on a.Buy_item=b.Buy_item where LENGTH(a.Buylot_haoma)>0 and a.Buylot_id=?";
		return (OpenEntity)this.dao.find(sql, OpenEntity.class, new Object[] { Integer.valueOf(Buylot_id) });
	}

	public OutEntity findEntityOne(String item, String qihao) {
		String sql = "SELECT b.Buy_item,b.Buy_money,a.Buylot_qihao,a.Buylot_id,a.Buylot_status,a.Buylot_money,b.Buy_ishm,b.Buy_lot,c.Lot_etime";
		sql = sql + " FROM Bc_buylot a LEFT JOIN Bc_buy b ON a.Buy_item=b.Buy_item LEFT JOIN Bc_lottery c ON a.Buylot_lot=c.Lot_name AND a.Buylot_qihao=c.Lot_qihao where a.Buy_item=? and a.Buylot_qihao=?";
		return (OutEntity)this.dao.find(sql, OutEntity.class, new Object[] { item, qihao });
	}

	public OutEntity findOutEntityOne(int lot_id) {
		String sql = "SELECT b.Buy_item,b.Buy_money,a.Buylot_qihao,a.Buylot_id,a.Buylot_status,a.Buylot_money,b.Buy_ishm,b.Buy_lot,c.Lot_etime";
		sql = sql + " FROM Bc_buylot a LEFT JOIN Bc_buy b ON a.Buy_item=b.Buy_item LEFT JOIN Bc_lottery c ON a.Buylot_lot=c.Lot_name AND a.Buylot_qihao=c.Lot_qihao where a.Buylot_id=?";
		return (OutEntity)this.dao.find(sql, OutEntity.class, new Object[] { Integer.valueOf(lot_id) });
	}

	public List<OutEntity> findOutList(int status, String time) {
		String sql = "SELECT b.Buy_item,b.Buy_money,a.Buylot_qihao,a.Buylot_id,a.Buylot_status,a.Buylot_money,b.Buy_ishm,b.Buy_lot,c.Lot_etime";
		sql = sql + " FROM Bc_buylot a LEFT JOIN Bc_buy b ON a.Buy_item=b.Buy_item LEFT JOIN Bc_lottery c ON a.Buylot_lot=c.Lot_name AND a.Buylot_qihao=c.Lot_qihao WHERE a.Buylot_status=? AND c.Lot_btime<? AND c.Lot_etime>?";
		return this.dao.finds(sql, OutEntity.class, new Object[] { Integer.valueOf(status), time, time });
	}

	public List<OutEntity> findOutList(String item, int status) {
		String sql = "SELECT b.Buy_item,b.Buy_money,a.Buylot_qihao,a.Buylot_id,a.Buylot_status,a.Buylot_money,b.Buy_ishm,b.Buy_lot,c.Lot_etime";
		sql = sql + " FROM Bc_buylot a LEFT JOIN Bc_buy b ON a.Buy_item=b.Buy_item LEFT JOIN Bc_lottery c ON a.Buylot_lot=c.Lot_name AND a.Buylot_qihao=c.Lot_qihao WHERE a.Buy_item=? AND a.Buylot_status=?";
		return this.dao.finds(sql, OutEntity.class, new Object[] { item, Integer.valueOf(status) });
	}

	public List<OutEntity> findOutList(int buy_id, int status) {
		String sql = "SELECT b.Buy_item,b.Buy_money,a.Buylot_qihao,a.Buylot_id,a.Buylot_status,a.Buylot_money,b.Buy_ishm,b.Buy_lot,c.Lot_etime";
		sql = sql + " FROM Bc_buylot a LEFT JOIN Bc_buy b ON a.Buy_item=b.Buy_item LEFT JOIN Bc_lottery c ON a.Buylot_lot=c.Lot_name AND a.Buylot_qihao=c.Lot_qihao WHERE a.Buylot_status=? AND b.Buy_id=?";
		return this.dao.finds(sql, OutEntity.class, new Object[] { Integer.valueOf(status), Integer.valueOf(buy_id) });
	}

	public int findItemNotOpen(String item) {
		return this.dao.getCount("SELECT COUNT(Buylot_id) FROM bc_buylot WHERE (Buylot_status=0 or Buylot_status=1) AND Buy_item=?", new Object[] { item });
	}

	public List<Bc_buylot> finds(String item, String lot, String qihao, int status, int start, int limit) {
		String sql = "select * from Bc_buylot where";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(item)) {
			sql = sql + " Buy_item like ?"; list.add("%" + item + "%");
		}
		if (StringUtils.isNotBlank(lot)) {
			sql = sql + " and Buylot_lot=?"; list.add(lot);
		}
		if (StringUtils.isNotBlank(qihao)) {
			sql = sql + " and Buylot_qihao=?"; list.add(qihao);
		}
		if (-2 != status) {
			sql = sql + " and Buylot_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql + " order by Buylot_id desc limit ?,?"; list.add(Integer.valueOf(start)); list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return this.dao.finds(sql, Bc_buylot.class, list.toArray());
	}

	public int findsCount(String item, String lot, String qihao, int status) {
		String sql = "select count(Buylot_id) from Bc_buylot where";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(item)) {
			sql = sql + " Buy_item like ?"; list.add("%" + item + "%");
		}
		if (StringUtils.isNotBlank(lot)) {
			sql = sql + " and Buylot_lot=?"; list.add(lot);
		}
		if (StringUtils.isNotBlank(qihao)) {
			sql = sql + " and Buylot_qihao=?"; list.add(qihao);
		}
		if (-2 != status) {
			sql = sql + " and Buylot_status=?"; list.add(Integer.valueOf(status));
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where")) {
			sql = sql.replace("where", "");
		}
		return this.dao.getCount(sql, list.toArray());
	}

	public List<Bc_buylot> findByItem(String item) {
		return this.dao.finds("select * from Bc_buylot where Buy_item=?", Bc_buylot.class, new Object[] { item });
	}

	public int findItemOpenNum(String item) {
		return this.dao.getCount("SELECT COUNT(Buylot_id) FROM bc_buylot WHERE Buylot_status=1 AND Buy_item=?", new Object[] { item });
	}
}