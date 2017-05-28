package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_buy;
import com.caipiao.entity.out.BuyHMOut;
import com.caipiao.entity.out.BuyOneOut;
import com.caipiao.entity.out.HemaiEntity;
import com.caipiao.entity.out.MyOrderOut;
import com.caipiao.intface.Bc_buyIntface;
import com.sysbcjzh.mysql.Mysql;
import com.sysbcjzh.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuyIntfaceImpl
		implements Bc_buyIntface
{
	Mysql dao = Mysql.getInstance();

	public boolean add(Bc_buy en) {
		return this.dao.add(en);
	}

	public boolean delete(int buy_id) {
		return this.dao.delete("delete from Bc_buy where Buy_id=?", new Object[] { Integer.valueOf(buy_id) });
	}

	public Bc_buy find(int buy_id)
	{
		return (Bc_buy)this.dao.find("select * from Bc_buy where Buy_id=?", Bc_buy.class, new Object[] { Integer.valueOf(buy_id) });
	}

	public Bc_buy find(int buy_id, int status)
	{
		return (Bc_buy)this.dao.find("select * from Bc_buy where Buy_id=? and Buy_status=?", Bc_buy.class, new Object[] { Integer.valueOf(buy_id), Integer.valueOf(status) });
	}

	public BuyOneOut find(String buy_item)
	{
		return (BuyOneOut)this.dao.find("select a.Buy_id,a.User_id,a.User_name,a.Buy_time,a.Buy_item,a.Buy_lot,a.Buy_money,a.Buy_baodi,a.Buy_have,a.Buy_winmoney,a.Buy_code,a.Buy_zhushu,a.Buy_status,a.Buy_ishm,a.Buy_hmsort,a.Buy_take,a.Buy_isopen,a.Buy_iscont,a.Buy_fqihao,b.User_word,b.User_level from Bc_buy a left join Bc_user b on a.User_id=b.User_id where Buy_item=?", BuyOneOut.class, new Object[] { buy_item });
	}

	public Bc_buy findBuyOne(String buy_item) {
		return (Bc_buy)this.dao.find("select * from Bc_buy where Buy_item=?", Bc_buy.class, new Object[] { buy_item });
	}

	public boolean update(int buy_id, Map<String, Object> map) {
		return this.dao.updateMap("update Bc_buy set ", " where buy_id=?", map, new Object[] { Integer.valueOf(buy_id) });
	}

	public boolean update(String item, Map<String, Object> map)
	{
		return this.dao.updateMap("update Bc_buy set ", " where Buy_item=?", map, new Object[] { item });
	}

	public List<MyOrderOut> findBuy(int userid, String btime, String etime, String lottery, int status, int ishm, int start, int limit)
	{
		String sql = "select a.Buyuser_time,a.Buyuser_money,a.Buyuser_win,a.Buy_item,b.Buy_lot,b.Buy_fqihao,b.User_name,b.Buy_money,b.Buy_status from Bc_buyuser a left join Bc_buy b on a.Buy_item=b.Buy_item where";
		ArrayList list = new ArrayList();
		if (-1 != userid) {
			sql = sql + " a.User_id=?"; list.add(Integer.valueOf(userid));
		}
		if (-2 != status) {
			sql = sql + " and b.Buy_status=?"; list.add(Integer.valueOf(status));
		}
		if (StringUtils.isNotBlank(lottery)) {
			sql = sql + " and b.Buy_lot=?"; list.add(lottery);
		}
		if (btime != null) {
			sql = sql + " and a.Buyuser_time>?"; list.add(btime + " 00:00:00");
		}
		if (etime != null) {
			sql = sql + " and a.Buyuser_time<?"; list.add(etime + " 24:00:00");
		}
		if (-1 != ishm) {
			sql = sql + " and b.Buy_ishm=?"; list.add(Integer.valueOf(ishm));
		}
		sql = sql + " order by a.Buyuser_time desc limit ?,?"; list.add(Integer.valueOf(start)); list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return this.dao.finds(sql, MyOrderOut.class, list.toArray());
	}

	public int findBuyCount(int userid, String btime, String etime, String lottery, int status, int ishm)
	{
		String sql = "select count(a.Buyuser_id) from Bc_buyuser a left join Bc_buy b on a.Buy_item=b.Buy_item where";
		ArrayList list = new ArrayList();
		if (-1 != userid) {
			sql = sql + " a.User_id=?"; list.add(Integer.valueOf(userid));
		}
		if (-2 != status) {
			sql = sql + " and b.Buy_status=?"; list.add(Integer.valueOf(status));
		}
		if (StringUtils.isNotBlank(lottery)) {
			sql = sql + " and b.Buy_lot=?"; list.add(lottery);
		}
		if (btime != null) {
			sql = sql + " and a.Buyuser_time>?"; list.add(btime + " 00:00:00");
		}
		if (etime != null) {
			sql = sql + " and a.Buyuser_time<?"; list.add(etime + " 24:00:00");
		}
		if (-1 != ishm) {
			sql = sql + " and b.Buy_ishm=?"; list.add(Integer.valueOf(ishm));
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where")) {
			sql = sql.replace("where", "");
		}
		return this.dao.getCount(sql, list.toArray());
	}

	public List<HemaiEntity> findTheHemaiList(int status, String time)
	{
		String sql = "select a.Buy_id,a.User_id,a.Buy_item,a.Buy_money,a.Buy_baodi,a.Buy_have,a.Buy_fqihao,a.Buy_lot,a.Buy_status";
		sql = sql + " from Bc_buy a left join Bc_lottery b on a.Buy_lot=b.Lot_name and a.Buy_fqihao=b.Lot_qihao where a.Buy_status=? and b.Lot_etime<?";
		return this.dao.finds(sql, HemaiEntity.class, new Object[] { Integer.valueOf(status), time });
	}

	public HemaiEntity findTheHemai(String item)
	{
		String sql = "select a.Buy_id,a.User_id,a.Buy_item,a.Buy_money,a.Buy_baodi,a.Buy_have,a.Buy_fqihao,a.Buy_lot,a.Buy_status";
		sql = sql + " from Bc_buy a left join Bc_lottery b on a.Buy_lot=b.Lot_name and a.Buy_fqihao=b.Lot_qihao where a.Buy_item=?";
		return (HemaiEntity)this.dao.find(sql, HemaiEntity.class, new Object[] { item });
	}

	public HemaiEntity findTheHemai(int ids)
	{
		String sql = "select a.Buy_id,a.User_id,a.Buy_item,a.Buy_money,a.Buy_baodi,a.Buy_have,a.Buy_fqihao,a.Buy_lot,a.Buy_status";
		sql = sql + " from Bc_buy a left join Bc_lottery b on a.Buy_lot=b.Lot_name and a.Buy_fqihao=b.Lot_qihao where a.Buy_id=?";
		return (HemaiEntity)this.dao.find(sql, HemaiEntity.class, new Object[] { Integer.valueOf(ids) });
	}

	public boolean itemadd(String item, double money)
	{
		return this.dao.update("update Bc_buy set Buy_winmoney=Buy_winmoney+? where Buy_item=?", new Object[] { Double.valueOf(money), item });
	}

	public boolean updatehave(String item, double money)
	{
		return this.dao.update("update Bc_buy set Buy_have=Buy_have-? where Buy_item=?", new Object[] { Double.valueOf(money), item });
	}

	public List<Bc_buy> findsBuy(String name, String item, String lot, int status, int ishm, String fqihao, String btime, String etime, int start, int limit)
	{
		String sql = "select * from Bc_buy where";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(name)) {
			sql = sql + " User_name like ?"; list.add("%" + name + "%");
		}
		if (StringUtils.isNotBlank(item)) {
			sql = sql + " and Buy_item like ?"; list.add("%" + item + "%");
		}
		if (StringUtils.isNotBlank(lot)) {
			sql = sql + " and Buy_lot=?"; list.add(lot);
		}
		if (-2 != status) {
			sql = sql + " and Buy_status=?"; list.add(Integer.valueOf(status));
		}
		if (-1 != ishm) {
			sql = sql + " and Buy_ishm=?"; list.add(Integer.valueOf(ishm));
		}
		if (StringUtils.isNotBlank(fqihao)) {
			sql = sql + " and Buy_fqihao=?"; list.add(fqihao);
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and Buy_time>? and Buy_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		sql = sql + " order by Buy_time desc limit ?,?"; list.add(Integer.valueOf(start)); list.add(Integer.valueOf(limit));
		sql = sql.replace("where and", "where");
		sql = sql.replace("where order", "order");
		return this.dao.finds(sql, Bc_buy.class, list.toArray());
	}

	public int findsBuyCount(String name, String item, String lot, int status, int ishm, String fqihao, String btime, String etime)
	{
		String sql = "select count(Buy_id) from Bc_buy where";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(name)) {
			sql = sql + " User_name like ?"; list.add("%" + name + "%");
		}
		if (StringUtils.isNotBlank(item)) {
			sql = sql + " and Buy_item like ?"; list.add("%" + item + "%");
		}
		if (StringUtils.isNotBlank(lot)) {
			sql = sql + " and Buy_lot=?"; list.add(lot);
		}
		if (-2 != status) {
			sql = sql + " and Buy_status=?"; list.add(Integer.valueOf(status));
		}
		if (-1 != ishm) {
			sql = sql + " and Buy_ishm=?"; list.add(Integer.valueOf(ishm));
		}
		if (StringUtils.isNotBlank(fqihao)) {
			sql = sql + " and Buy_fqihao=?"; list.add(fqihao);
		}
		if (StringUtils.isNotEmptyAll(new String[] { btime, etime })) {
			sql = sql + " and Buy_time>? and Buy_time<?"; list.add(btime + " 00:00:00"); list.add(etime + " 24:00:00");
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where")) {
			sql = sql.replace("where", "");
		}
		return this.dao.getCount(sql, list.toArray());
	}

	public List<BuyHMOut> findsBuyForHM(String name, String lot, int isbao, int istake, int status, int start, int limit) {
		String sql = "select a.Buy_id,a.Buy_item,a.Buy_isopen,a.User_name,b.User_level,a.Buy_lot,a.Buy_money,a.Buy_baodi,a.Buy_have,a.Buy_hmsort,a.Buy_take,a.Buy_fqihao,a.Buy_status from Bc_buy a left join Bc_user b on a.User_id=b.User_id where a.Buy_ishm=1";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(name)) {
			sql = sql + " and a.User_name like ?"; list.add("%" + name + "%");
		}
		if (StringUtils.isNotBlank(lot)) {
			sql = sql + " and a.Buy_lot=?"; list.add(lot);
		}
		if (-1 != isbao) {
			if (isbao == 0) sql = sql + " and a.Buy_baodi>0"; else sql = sql + " and a.Buy_baodi=0";
		}
		if (-1 != istake) {
			if (istake == 0) sql = sql + " and a.Buy_take>0"; else sql = sql + " and a.Buy_take=0";
		}
		if (-2 != status) {
			sql = sql + " and a.Buy_status=?"; list.add(Integer.valueOf(status));
		}

		sql = sql + " order by a.Buy_status asc,a.Buy_time desc limit ?,?"; list.add(Integer.valueOf(start)); list.add(Integer.valueOf(limit));
		return this.dao.finds(sql, BuyHMOut.class, list.toArray());
	}

	public int findsBuyForHMCount(String name, String lot, int isbao, int istake, int status)
	{
		String sql = "select count(a.Buy_id) from Bc_buy a left join Bc_user b on a.User_id=b.User_id where a.Buy_ishm=1";
		ArrayList list = new ArrayList();
		if (StringUtils.isNotBlank(name)) {
			sql = sql + " and a.User_name like ?"; list.add("%" + name + "%");
		}
		if (StringUtils.isNotBlank(lot)) {
			sql = sql + " and a.Buy_lot=?"; list.add(lot);
		}
		if (-1 != isbao) {
			if (isbao == 0) sql = sql + " and a.Buy_baodi>0"; else sql = sql + " and a.Buy_baodi=0";
		}
		if (-1 != istake) {
			if (istake == 0) sql = sql + " and a.Buy_take>0"; else sql = sql + " and a.Buy_take=0";
		}
		if (-2 != status) {
			sql = sql + " and a.Buy_status=?"; list.add(Integer.valueOf(status));
		}
		return this.dao.getCount(sql, list.toArray());
	}
}