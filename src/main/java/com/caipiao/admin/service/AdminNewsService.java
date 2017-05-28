package com.caipiao.admin.service;

import com.caipiao.entity.Bc_news;
import com.caipiao.intface.Bc_newsIntface;
import com.caipiao.intfaceImpl.NewsIntfaceImpl;
import com.caipiao.utils.TimeUtil;
import com.sysbcjzh.utils.StringUtils;
import java.util.HashMap;
import java.util.List;

/**
 * 新闻管理
 */
public class AdminNewsService
{

	Bc_newsIntface newsdao;

	public AdminNewsService()
	{
		newsdao = new NewsIntfaceImpl();
	}

	public Bc_news find(int id)
	{
		return newsdao.find(id);
	}

	public boolean addNews(String auther, String title, String soures, String image, String text, int type)
	{
		String time = TimeUtil.getToday("yyyy-MM-dd HH:mm:ss");
		Bc_news en = new Bc_news();
		en.setNews_auther(auther);
		en.setNews_etime(time);
		if (StringUtils.isNotBlank(image))
			en.setNews_image(image);
		en.setNews_point(0);
		en.setNews_sort(0);
		en.setNews_soures(soures);
		en.setNews_status(0);
		en.setNews_text(text);
		en.setNews_time(time);
		en.setNews_title(title);
		en.setNews_type(type);
		return newsdao.add(en);
	}

	public boolean UpNews(int id, int status, String auther, String title, String soures, String image, String text, 
			int type)
	{
		String time = TimeUtil.getToday("yyyy-MM-dd HH:mm:ss");
		HashMap map = new HashMap();
		map.put("News_auther", auther);
		map.put("News_title", title);
		map.put("News_soures", soures);
		map.put("News_text", text);
		map.put("News_type", Integer.valueOf(type));
		map.put("News_etime", time);
		map.put("News_status", Integer.valueOf(status));
		if (StringUtils.isNotBlank(image))
			map.put("News_image", image);
		return newsdao.update(id, map);
	}

	public List findsNews(String auther, String title, String btime, String etime, String soures, int type, int type2, 
			int status, int strat, int limit)
	{
		return newsdao.findByType(auther, title, btime, etime, soures, type, type2, status, strat, limit);
	}

	public int findsNewsCount(String auther, String title, String btime, String etime, String soures, int type, int type2, 
			int status)
	{
		return newsdao.findByTypeCount(auther, title, btime, etime, soures, type, type2, status);
	}
}
