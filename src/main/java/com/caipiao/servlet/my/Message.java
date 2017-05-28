
//    Message.java

package com.caipiao.servlet.my;

import com.caipiao.entity.Bc_user;
import com.caipiao.service.my.MyMessageService;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.TimeUtil;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.PageUtils;
import com.sysbcjzh.utils.VelocityHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Message extends IndexAction
{

	private static final long serialVersionUID = 0xe9a7fa211a3eb9e9L;
	MyMessageService service;

	public Message()
	{
		service = new MyMessageService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		if (user != null)
		{
			Bc_user find = UserStatic.find(user);
			String type = request.getParameter("type");
			String status = request.getParameter("status");
			String datestr = request.getParameter("date");
			String p = request.getParameter("p");
			int date = TryStatic.StrToInt(datestr, 0);
			String btime = TryStatic.getAgo(date);
			String etime = TimeUtil.getToday("yyyy-MM-dd");
			int userid = find.getUser_id();
			int page = TryStatic.StrToInt(p, 1);
			int limit = 12;
			int types = TryStatic.StrToInt(type, -1);
			int statu = TryStatic.StrToInt(status, -1);
			int findcount = service.findcount(userid, btime, etime, types, statu);
			String ajaxPage = PageUtils.Page163(findcount, page, limit, (new StringBuilder("/my/Point.jzh?status=")).append(statu).append("&date=").append(date).append("&type=").append(types).append("&").toString());
			java.util.List finds = service.finds(userid, btime, etime, types, statu, (page - 1) * limit, limit);
			VelocityHelper velo = new VelocityHelper();
			velo.Put("user", find);
			velo.Put("find", finds);
			velo.Put("page", ajaxPage);
			velo.Put("date", Integer.valueOf(date));
			velo.Put("status", Integer.valueOf(statu));
			velo.Put("type", Integer.valueOf(types));
			velo.init("my/message.vm", out);
		} else
		{
			out.print(UserSession.loginstr);
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		if (user != null)
		{
			String ids = request.getParameter("ids");
			int id = TryStatic.StrToInt(ids, -1);
			if (-1 != id)
			{
				Bc_user find = UserStatic.find(user);
				boolean delete = service.Delete(id, find.getUser_id());
				if (delete)
					out.print("0");
				else
					out.print("-1");
			} else
			{
				out.print("-1");
			}
		} else
		{
			out.print(UserSession.loginstr);
		}
		out.flush();
		out.close();
	}

	public void UpReadPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		String user = UserSession.getUser(request);
		if (user != null)
		{
			String ids = request.getParameter("ids");
			int id = TryStatic.StrToInt(ids, -1);
			if (-1 != id)
			{
				Bc_user find = UserStatic.find(user);
				service.UpRead(id, find.getUser_id());
			}
		}
	}
}
