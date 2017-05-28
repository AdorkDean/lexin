
//    Bc_news.java

package com.caipiao.entity;

import com.caipiao.utils.UserSession;

public class Bc_news
{

	private int News_id;
	private String News_auther;
	private String News_title;
	private String News_time;
	private String News_etime;
	private String News_soures;
	private String News_text;
	private int News_status;
	private int News_sort;
	private String News_image;
	private int News_point;
	private int News_type;

	public Bc_news()
	{
	}

	public int getNews_id()
	{
		return News_id;
	}

	public void setNews_id(int news_id)
	{
		News_id = news_id;
	}

	public String getNews_auther()
	{
		return News_auther;
	}

	public String getNews_autherDis()
	{
		return UserSession.DisUser(News_auther);
	}

	public void setNews_auther(String news_auther)
	{
		News_auther = news_auther;
	}

	public String getNews_title()
	{
		return News_title;
	}

	public void setNews_title(String news_title)
	{
		News_title = news_title;
	}

	public String getNews_time()
	{
		return News_time;
	}

	public void setNews_time(String news_time)
	{
		News_time = news_time;
	}

	public String getNews_etime()
	{
		return News_etime;
	}

	public void setNews_etime(String news_etime)
	{
		News_etime = news_etime;
	}

	public String getNews_soures()
	{
		return News_soures;
	}

	public void setNews_soures(String news_soures)
	{
		News_soures = news_soures;
	}

	public String getNews_text()
	{
		return News_text;
	}

	public void setNews_text(String news_text)
	{
		News_text = news_text;
	}

	public int getNews_status()
	{
		return News_status;
	}

	public void setNews_status(int news_status)
	{
		News_status = news_status;
	}

	public int getNews_sort()
	{
		return News_sort;
	}

	public void setNews_sort(int news_sort)
	{
		News_sort = news_sort;
	}

	public String getNews_image()
	{
		return News_image;
	}

	public void setNews_image(String news_image)
	{
		News_image = news_image;
	}

	public int getNews_point()
	{
		return News_point;
	}

	public void setNews_point(int news_point)
	{
		News_point = news_point;
	}

	public int getNews_type()
	{
		return News_type;
	}

	public void setNews_type(int news_type)
	{
		News_type = news_type;
	}
}
