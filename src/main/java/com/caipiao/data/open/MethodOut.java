package com.caipiao.data.open;

import com.caipiao.entity.Bc_buyuser;
import com.caipiao.entity.out.OutEntity;
import com.caipiao.intface.Bc_buylotIntface;
import com.caipiao.intface.Bc_buyuserIntface;
import com.caipiao.intfaceImpl.BuylotIntfaceImpl;
import com.caipiao.intfaceImpl.BuyuserIntfaceImpl;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.LockList;
import com.caipiao.utils.SystemSet;
import com.caipiao.utils.TimeUtil;
import com.caipiao.utils.TryStatic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 撤单业务
 */
public class MethodOut
{
	static final int no_out = 0;
	static final int ok_out = 1;
	static final int sys_che = 4;
	static Bc_buylotIntface dao = new BuylotIntfaceImpl();
	static Bc_buyuserIntface userdao = new BuyuserIntfaceImpl();

	public void Instance()
	{
		long timeMillis = System.currentTimeMillis();
		String nowtime = TimeUtil.LongToString(timeMillis - 90000L, "yyyy-MM-dd HH:mm:ss");
		List<OutEntity> list = dao.findOutList(0, nowtime);
		if (list != null)
			for (OutEntity out : list) {
				String buy_item = out.getBuy_item();
				if (!LockList.itemlock.contains(buy_item)) {
					LockList.itemlock.add(buy_item);
					try {
						int findItemOpenNum = dao.findItemOpenNum(buy_item);
						if (findItemOpenNum == 0) {
							OutOne(out);
						} else {
							String lot = out.getBuy_lot();
							String lot_etime = out.getLot_etime();
							int time = TryStatic.StrToInt(SystemSet.crawler.getProperty(lot + "_ago"), 90);
							long etime = TimeUtil.StringToLong(lot_etime, "yyyy-MM-dd HH:mm:ss");
							long ntime = System.currentTimeMillis();
							if (etime - ntime <= time * 1000)
								OutOne(out);
						}
					}
					finally {
						LockList.itemlock.remove(buy_item);
					}
				}
			}
	}

	public boolean OutOne(int lot_id)
	{
		OutEntity one = dao.findOutEntityOne(lot_id);
		if (one != null) {
			int buylot_status = one.getBuylot_status();
			if (buylot_status == 0) {
				return OutOne(one);
			}
		}
		return false;
	}

	public boolean CheOen(int lot_id)
	{
		OutEntity one = dao.findOutEntityOne(lot_id);
		if (one != null) {
			int bs = one.getBuylot_status();
			if ((bs == 0) || (1 == bs)) {
				return CheOen(one);
			}
		}
		return false;
	}

	public void AllChe(int buy_id)
	{
		List<OutEntity> list = dao.findOutList(buy_id, 0);
		if (list != null)
			for (OutEntity o : list)
				CheOen(o);
	}

	public boolean OutOne(OutEntity en)
	{
		boolean result = false;
		int buylot_status = en.getBuylot_status();
		if (buylot_status == 0) {
			int buylot_id = en.getBuylot_id();
			UpdateStatus(buylot_id, 1);

			String buy_item = en.getBuy_item();
			double allmoney = en.getBuy_money();
			double lotmoney = en.getBuylot_money();
			List<Bc_buyuser> buyuser = userdao.findByItem(buy_item);
			if (buyuser != null) {
				for (Bc_buyuser buy : buyuser) {
					int user_id = buy.getUser_id();
					double buymon = buy.getBuyuser_money();
					double mon = lotmoney * 100.0D / allmoney * buymon / 100.0D;
					UserStatic.DongSub(user_id, mon);
				}
			}
			result = true;
		}
		return result;
	}

	public boolean CheOen(OutEntity en)
	{
		boolean result = false;
		int lotstatus = en.getBuylot_status();
		int lotid = en.getBuylot_id();
		if ((lotstatus == 0) || (1 == lotstatus)) {
			UpdateStatus(lotid, 4);
			String buy_item = en.getBuy_item();
			double allmoney = en.getBuy_money();
			double lotmoney = en.getBuylot_money();
			List<Bc_buyuser> buyuser = userdao.findByItem(buy_item);
			if (buyuser != null) {
				for (Bc_buyuser buy : buyuser) {
					int user_id = buy.getUser_id();
					double buymon = buy.getBuyuser_money();
					double mon = lotmoney * 100.0D / allmoney * buymon / 100.0D;
					if (1 == lotstatus)
						UserStatic.AddMoney(UserStatic.find(user_id), mon, 0, buy_item, 8, "方案第" + en.getBuylot_qihao() + "撤单", 0.0D);
					else {
						UserStatic.DongToMon(UserStatic.find(user_id), mon, buy_item, 8, "方案第" + en.getBuylot_qihao() + "撤单");
					}
				}
			}
			new MethodOpen().CheckBuyStatus(buy_item, 0.0D);
			result = true;
		}
		return result;
	}

	private boolean UpdateStatus(int lotid, int status)
	{
		Map map = new HashMap();
		map.put("Buylot_status", Integer.valueOf(status));
		if (1 == status) {
			map.put("Buylot_outtime", TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		}
		return dao.update(lotid, map);
	}
}