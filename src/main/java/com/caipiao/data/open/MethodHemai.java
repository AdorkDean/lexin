package com.caipiao.data.open;

import com.caipiao.entity.Bc_buyuser;
import com.caipiao.entity.Bc_user;
import com.caipiao.entity.out.HemaiEntity;
import com.caipiao.entity.out.OutEntity;
import com.caipiao.intface.Bc_buyIntface;
import com.caipiao.intface.Bc_buylotIntface;
import com.caipiao.intface.Bc_buyuserIntface;
import com.caipiao.intfaceImpl.BuyIntfaceImpl;
import com.caipiao.intfaceImpl.BuylotIntfaceImpl;
import com.caipiao.intfaceImpl.BuyuserIntfaceImpl;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.LockList;
import com.caipiao.utils.Log;
import com.caipiao.utils.TimeUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodHemai
{
	static final int hemai = -1;
	static final int hemai_ok = 0;
	static final int hemai_no = 3;
	static final int lot_out = 0;
	static final int lot_che = 4;
	static Bc_buyIntface dao = new BuyIntfaceImpl();
	static Bc_buylotIntface lotdao = new BuylotIntfaceImpl();
	static Bc_buyuserIntface userdao = new BuyuserIntfaceImpl();

	public void Instance()
	{
		if (LockList.hemailock == 0) {
			LockList.hemailock = 1;
			try {
				long nowlong = System.currentTimeMillis() + 90000L;
				String longToString = TimeUtil.LongToString(nowlong, "yyyy-MM-dd HH:mm:ss");
				List<HemaiEntity> list = dao.findTheHemaiList(-1, longToString);
				if (list != null)
					for (HemaiEntity hm : list) {
						Log.ShowInfo("订单" + hm.getBuy_item() + "正在执行合买验证。");
						HeimaiOne(hm);
					}
			}
			finally {
				LockList.hemailock = 0;
			}
		}
	}

	public boolean HeimaiOne(int buy_id)
	{
		HemaiEntity find = dao.findTheHemai(buy_id);
		return HeimaiOne(find);
	}

	public boolean HeimaiOne(String buy_item)
	{
		HemaiEntity find = dao.findTheHemai(buy_item);
		return HeimaiOne(find);
	}

	public boolean HeimaiOne(HemaiEntity en)
	{
		boolean result = false;
		int buy_status = en.getBuy_status();
		if (-1 == buy_status) {
			String buy_item = en.getBuy_item();
			int ids = en.getBuy_id();
			int user_id = en.getUser_id();
			Bc_user find = UserStatic.find(user_id);
			double baodi = en.getBuy_baodi();
			double have = en.getBuy_have();
			if (baodi >= have) {
				updateBuyStatus(ids, 0);
				updateLotStatus(buy_item, 0);
				if (baodi - have > 0.0D) {
					UserStatic.DongToMon(find, baodi - have, buy_item, 7, "保底返还");
				}
				if (have > 0.0D)
					AddHemai(buy_item, user_id, find.getUser_name(), have, "");
			}
			else {
				updateBuyStatus(ids, 3);
				updateLotStatus(buy_item, 4);
				if (baodi > 0.0D) {
					UserStatic.DongToMon(find, baodi, buy_item, 7, "保底返还");
				}
				List<Bc_buyuser> findUser = userdao.findByItem(buy_item);
				if (findUser != null) {
					for (Bc_buyuser u : findUser) {
						int userid = u.getUser_id();
						double buyuser_money = u.getBuyuser_money();
						Bc_user findu = UserStatic.find(userid);
						UserStatic.DongToMon(findu, buyuser_money, buy_item, 8, "未满撤单");
					}
				}
			}

			OutEntity fqh = lotdao.findEntityOne(buy_item, en.getBuy_fqihao());
			if (fqh != null) {
				int buylot_status = fqh.getBuylot_status();
				if (buylot_status == 0) {
					new MethodOut().OutOne(fqh);
				}
			}
			result = true;
		}
		return result;
	}

	public boolean AddHemai(String buy_item, int user_id, String user_name, double buymoney, String auto_item)
	{
		Bc_buyuser en = new Bc_buyuser();
		en.setAuto_item(auto_item);
		en.setBuy_item(buy_item);
		en.setBuyuser_money(buymoney);
		en.setBuyuser_time(TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		en.setUser_id(user_id);
		en.setUser_name(user_name);
		return userdao.add(en);
	}

	private boolean updateBuyStatus(int buy_id, int status)
	{
		Map map = new HashMap();
		map.put("Buy_status", Integer.valueOf(status));
		if (status == 0) {
			map.put("Buy_have", Integer.valueOf(0));
		}
		return dao.update(buy_id, map);
	}

	private boolean updateLotStatus(String item, int status)
	{
		Map map = new HashMap();
		map.put("Buylot_status", Integer.valueOf(status));
		return lotdao.update(item, map);
	}
}