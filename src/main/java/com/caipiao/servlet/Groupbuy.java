
//    Groupbuy.java

package com.caipiao.servlet;

import com.caipiao.service.GroupBuyService;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.PageUtils;
import com.sysbcjzh.utils.StringUtils;
import com.sysbcjzh.utils.VelocityHelper;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Groupbuy extends IndexAction
{

	private static final long serialVersionUID = 1L;
	GroupBuyService service;

	public Groupbuy()
	{
		service = new GroupBuyService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		VelocityHelper velo = new VelocityHelper();
		String user = UserSession.getUser(request);
		if (user != null)
		{
			com.caipiao.entity.Bc_user find = UserStatic.find(user);
			velo.Put("user", find);
		}
		String name = request.getParameter("name");
		String lot = request.getParameter("lot");
		if ("all".equals(lot))
			lot = null;
		String isbao = request.getParameter("isbao");
		String istake = request.getParameter("istake");
		String statusstr = request.getParameter("status");
		String p = request.getParameter("p");
		int page = TryStatic.StrToInt(p, 1);
		int limit = 25;
		int bao = TryStatic.StrToInt(isbao, -1);
		int take = TryStatic.StrToInt(istake, -1);
		int status = TryStatic.StrToInt(statusstr, -2);
		int count = service.findsHMCount(name, lot, bao, take, status);
		String url = (new StringBuilder("/Groupbuy.jzh?isbao=")).append(bao).append("&istake=").append(take).append("&status=").append(status).append("&").toString();
		if (StringUtils.isNotBlank(name))
		{
			url = (new StringBuilder(String.valueOf(url))).append("name=").append(name).append("&").toString();
			velo.Put("name", name);
		}
		if (StringUtils.isNotBlank(lot))
		{
			url = (new StringBuilder(String.valueOf(url))).append("lot=").append(lot).append("&").toString();
			velo.Put("lot", lot);
		}
		String pagehtml = PageUtils.Page163(count, page, limit, url);
		List list = service.findsHM(name, lot, bao, take, status, (page - 1) * limit, limit);
		velo.Put("list", list);
		velo.Put("page", pagehtml);
		velo.Put("isbao", Integer.valueOf(bao));
		velo.Put("istake", Integer.valueOf(take));
		velo.Put("status", Integer.valueOf(status));
		velo.init("groupbuy.vm", out);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		String lot = request.getParameter("lot");
		if ("all".equals(lot))
			lot = null;
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		List findsHM = service.findsHM(null, lot, -1, -1, -2, 0, 20);
		if (findsHM != null)
			json.put("msg", findsHM);
		else
			json.put("msg", "no");
		out.print(json.toString());
		out.flush();
		out.close();
	}
}
