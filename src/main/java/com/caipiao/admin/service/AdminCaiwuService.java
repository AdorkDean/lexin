package com.caipiao.admin.service;

import com.caipiao.entity.Bc_draw;
import com.caipiao.intface.Bc_drawIntface;
import com.caipiao.intface.Bc_rechIntface;
import com.caipiao.intfaceImpl.DrawIntfaceImpl;
import com.caipiao.intfaceImpl.RechIntfaceImpl;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.TimeUtil;
import java.util.HashMap;
import java.util.List;

/**
 * 管理员
 */
public class AdminCaiwuService
{

	Bc_rechIntface rechdao;
	Bc_drawIntface drawdao;

	public AdminCaiwuService()
	{
		rechdao = new RechIntfaceImpl();
		drawdao = new DrawIntfaceImpl();
	}

	public List findsRech(int user_id, String btime, String etime, int type, int utype, int status, int start,
						  int limit)
	{
		return rechdao.finds(user_id, btime, etime, type, utype, status, start, limit);
	}

	public int findsRechcount(int user_id, String btime, String etime, int type, int utype, int status)
	{
		return rechdao.findscount(user_id, btime, etime, type, utype, status);
	}

	public List findsDraw(int user_id, String btime, String etime, int type, int utype, int status, int start,
						  int limit)
	{
		return drawdao.finds(user_id, btime, etime, type, utype, status, start, limit);
	}

	public int findsDrawcount(int user_id, String btime, String etime, int type, int utype, int status)
	{
		return drawdao.findscount(user_id, btime, etime, type, utype, status);
	}

	public boolean DrawUpdate(int draw_id, String douser, int type, String msg)
	{
		boolean check = false;
		Bc_draw find = drawdao.find(draw_id);
		int draw_status = find.getDraw_status();
		HashMap map = new HashMap();
		map.put("Draw_douser", douser);
		map.put("Draw_dotime", TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		if (draw_status == 0 && type == 0)
		{
			check = true;
			map.put("Draw_desc", "提款已接受");
			map.put("Draw_status", Integer.valueOf(1));
		} else
		if (1 == type && (draw_status == 0 || 1 == draw_status))
		{
			check = true;
			map.put("Draw_desc", (new StringBuilder("原因：")).append(msg).toString());
			map.put("Draw_status", Integer.valueOf(4));
		} else
		if (2 == type && 1 == draw_status)
		{
			check = true;
			map.put("Draw_desc", (new StringBuilder("交易号：")).append(msg).toString());
			map.put("Draw_status", Integer.valueOf(2));
		}
		if (check)
		{
			boolean update = drawdao.update(draw_id, map);
			if (update)
			{
				int user_id = find.getUser_id();
				double money = find.getDraw_money() + find.getDraw_surgery();
				if (1 == type)
					UserStatic.DongToMon(UserStatic.find(user_id), money, find.getDraw_item(), 4, "拒绝提款");
				else
				if (2 == type)
					UserStatic.DongSub(user_id, money);
			}
			return update;
		} else
		{
			return false;
		}
	}

	public int findNowDraw()
	{
		return drawdao.findNowDraw();
	}
}
