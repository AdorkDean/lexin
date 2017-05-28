
//    NewsService.java

package com.caipiao.service;

import com.caipiao.entity.Bc_news;
import com.caipiao.intface.Bc_newsIntface;
import com.caipiao.intfaceImpl.NewsIntfaceImpl;
import java.util.List;

public class NewsService
{

	Bc_newsIntface newsdao;

	public NewsService()
	{
		newsdao = new NewsIntfaceImpl();
	}

	public List finds(int type)
	{
		return newsdao.findByType(null, null, null, null, null, type, type, 0, 3, 6);
	}

	public Bc_news find(int ids)
	{
		return newsdao.find(ids);
	}

	public List findAll()
	{
		return newsdao.findByType(null, null, null, null, null, -1, -1, 0, 0, 25);
	}
}
