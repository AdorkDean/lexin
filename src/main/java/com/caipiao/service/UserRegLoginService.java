
//    UserRegLoginService.java

package com.caipiao.service;

import com.caipiao.entity.*;
import com.caipiao.intface.Bc_userIntface;
import com.caipiao.intfaceImpl.UserIntfaceImpl;
import com.caipiao.utils.*;
import com.sysbcjzh.utils.*;
import javax.servlet.http.HttpServletRequest;

public class UserRegLoginService
{

	Bc_userIntface dao;

	public UserRegLoginService()
	{
		dao = new UserIntfaceImpl();
	}

	public boolean IsEmailExist(String email)
	{
		return dao.EmailIsExist(email);
	}

	public boolean IsNameExist(String name)
	{
		return dao.NameIsExist(name);
	}

	public void SendEmail(String email, int up, String code)
	{
		EmailUtils.SendEmail(email, "测试标题", (new StringBuilder("<a href='http://127.0.0.1/RegAction.jzh?name=")).append(email).append("&up=").append(up).append("&code=").append(code).append("'>正文内容验证</a>").toString());
	}

	public boolean Reg(int type, String name, String pass, String qq, int upid, HttpServletRequest request)
	{
		String md5String = StringUtils.md5String(pass);
		String time = TimeUtil.getToday("yyyy-MM-dd HH:mm:ss");
		Bc_user en = new Bc_user();
		en.setUser_name(name);
		en.setUser_pass(md5String);
		en.setUser_paypass(md5String);
		en.setUser_dong(0.0D);
		en.setUser_money(0.0D);
		en.setUser_red(0.0D);
		en.setUser_point(0);
		en.setUser_level(0);
		en.setUser_regtime(time);
		en.setUser_lgtime(time);
		en.setUser_lgip(IPUtils.GetIP(request));
		en.setUser_phonecheck(0);
		en.setUser_zipcheck(0);
		en.setUser_qq(qq);
		if (1 == type)
		{
			en.setUser_email(name);
			en.setUser_emailcheck(1);
		} else
		{
			en.setUser_emailcheck(0);
		}
		en.setUser_type(0);
		en.setUser_status(0);
		if (upid > 0)
			en.setUser_upid(upid);
		boolean add = dao.add(en);
		if (add)
		{
			Bc_user find = dao.find(name);
			if (find != null)
			{
				int user_id = find.getUser_id();
				String user_name = find.getUser_name();
				Bc_comm comm = new Bc_comm();
				comm.setAll(0.0D);
				comm.setUser_id(user_id);
				comm.setUser_name(user_name);
				dao.add(comm);
				Bc_phb phb = new Bc_phb();
				phb.setAll(0.0D);
				phb.setUser_id(user_id);
				phb.setUser_name(user_name);
				phb.setPhb_type("all");
				dao.add(phb);
				LotEmun alotemun[];
				int j = (alotemun = LotEmun.values()).length;
				for (int i = 2; i < j; i++)
				{
					LotEmun l = alotemun[i];
					Bc_phb p = new Bc_phb();
					p.setAll(0.0D);
					p.setUser_id(user_id);
					p.setUser_name(user_name);
					p.setPhb_type(l.name);
					dao.add(p);
				}

			}
			UserSession.userLogin(en, pass, request);
		}
		return add;
	}
}
