
//    Cps.java

package com.caipiao.servlet.my;

import com.caipiao.entity.Bc_comm;
import com.caipiao.entity.Bc_user;
import com.caipiao.service.my.MyBuyService;
import com.caipiao.service.my.MyCpsService;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.LotEmun;
import com.caipiao.utils.TimeUtil;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.PageUtils;
import com.sysbcjzh.utils.StringUtils;
import com.sysbcjzh.utils.VelocityHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Cps extends IndexAction
{

	private static final long serialVersionUID = 1L;
	MyCpsService service;
	MyBuyService buyservice;

	public Cps()
	{
		service = new MyCpsService();
		buyservice = new MyBuyService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		if (user != null)
		{
			Bc_user find = UserStatic.find(user);
			int user_type = find.getUser_type();
			if (1 == user_type)
			{
				Bc_comm findById = service.findcomm(find.getUser_id());
				VelocityHelper velo = new VelocityHelper();
				velo.Put("user", find);
				velo.Put("comm", findById);
				velo.init("my/cps.vm", out);
			} else
			{
				out.print("<script>alert('您无权访问该页面！');location.href='/Index.jzh';</script>");
			}
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
			Bc_user find = UserStatic.find(user);
			int user_type = find.getUser_type();
			if (1 == user_type)
			{
				int downid = TryStatic.StrToInt(request.getParameter("downid"));
				Bc_user down = UserStatic.find(downid);
				if (down != null && find.getUser_id() == down.getUser_upid())
				{
					Bc_comm f = service.findcomm(find.getUser_id());
					double Ssq = TryStatic.StrToDoubleABS(request.getParameter("Ssq"), 0.0D, f.getSsq());
					double Dlt = TryStatic.StrToDoubleABS(request.getParameter("Dlt"), 0.0D, f.getDlt());
					double Pl5 = TryStatic.StrToDoubleABS(request.getParameter("Pl5"), 0.0D, f.getPl5());
					double Fc3d = TryStatic.StrToDoubleABS(request.getParameter("Fc3d"), 0.0D, f.getFc3d());
					double Pl3 = TryStatic.StrToDoubleABS(request.getParameter("Pl3"), 0.0D, f.getPl3());
					double Cqssc = TryStatic.StrToDoubleABS(request.getParameter("Cqssc"), 0.0D, f.getCqssc());
					double Jxssc = TryStatic.StrToDoubleABS(request.getParameter("Jxssc"), 0.0D, f.getJxssc());
					double Sd11x5 = TryStatic.StrToDoubleABS(request.getParameter("Sd11x5"), 0.0D, f.getSd11x5());
					double Jx11x5 = TryStatic.StrToDoubleABS(request.getParameter("Jx11x5"), 0.0D, f.getJx11x5());
					double Gd11x5 = TryStatic.StrToDoubleABS(request.getParameter("Gd11x5"), 0.0D, f.getGd11x5());
					double Cq11x5 = TryStatic.StrToDoubleABS(request.getParameter("Cq11x5"), 0.0D, f.getCq11x5());
					boolean upcomm = service.upcomm(down.getUser_id(), Ssq, Dlt, Pl5, Fc3d, Pl3, Cqssc, Jxssc, Sd11x5, Jx11x5, Gd11x5, Cq11x5);
					if (upcomm)
						out.print("0");
					else
						out.print("1");
				} else
				{
					out.print("2");
				}
			} else
			{
				out.print("2");
			}
		} else
		{
			out.print("nologin");
		}
		out.flush();
		out.close();
	}

	public void SetGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		if (user != null)
		{
			Bc_user find = UserStatic.find(user);
			int user_type = find.getUser_type();
			if (1 == user_type)
			{
				int downid = TryStatic.StrToInt(request.getParameter("downid"));
				Bc_user down = UserStatic.find(downid);
				if (down != null && find.getUser_id() == down.getUser_upid())
				{
					Bc_comm commfind = service.findcomm(find.getUser_id());
					Bc_comm commdown = service.findcomm(down.getUser_id());
					VelocityHelper velo = new VelocityHelper();
					velo.Put("user", find);
					velo.Put("commfind", commfind);
					velo.Put("commdown", commdown);
					velo.Put("down", down.getUser_name());
					velo.Put("downid", Integer.valueOf(down.getUser_id()));
					velo.init("my/cpsset.vm", out);
				} else
				{
					out.print("<script>alert('您无权访问该页面！');location.href='/Index.jzh';</script>");
				}
			} else
			{
				out.print("<script>alert('您无权访问该页面！');location.href='/Index.jzh';</script>");
			}
		} else
		{
			out.print(UserSession.loginstr);
		}
		out.flush();
		out.close();
	}

	public void ListGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		if (user != null)
		{
			Bc_user find = UserStatic.find(user);
			int user_type = find.getUser_type();
			if (1 == user_type)
			{
				int User_id = find.getUser_id();
				String datestr = request.getParameter("date");
				String cusstr = request.getParameter("cus");
				String p = request.getParameter("p");
				int date = TryStatic.StrToInt(datestr, -1);
				String btime = "";
				String etime = "";
				if (-1 != date)
				{
					btime = TryStatic.getAgo(date);
					etime = TimeUtil.getToday("yyyy-MM-dd");
				}
				int cus = TryStatic.StrToInt(cusstr, -1);
				int page = TryStatic.StrToInt(p, 1);
				int limit = 15;
				int count = service.findListCount(User_id, cus, btime, etime);
				String ajaxPage = PageUtils.Page163(count, page, limit, (new StringBuilder("/my/Cps!List.jzh?date=")).append(date).append("&cus=").append(cus).append("&").toString());
				List findList = service.findList(User_id, cus, btime, etime, (page - 1) * limit, limit);
				VelocityHelper velo = new VelocityHelper();
				velo.Put("user", find);
				velo.Put("list", findList);
				velo.Put("page", ajaxPage);
				velo.Put("date", Integer.valueOf(date));
				velo.Put("cus", Integer.valueOf(cus));
				velo.Put("count", Integer.valueOf(count));
				velo.init("my/cpslist.vm", out);
			} else
			{
				out.print("<script>alert('您无权访问该页面！');location.href='/Index.jzh';</script>");
			}
		} else
		{
			out.print(UserSession.loginstr);
		}
		out.flush();
		out.close();
	}

	public void ListBuyGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		if (user != null)
		{
			Bc_user find = UserStatic.find(user);
			int user_type = find.getUser_type();
			if (1 == user_type)
			{
				int downid = TryStatic.StrToInt(request.getParameter("downid"));
				Bc_user down = UserStatic.find(downid);
				if (down != null && find.getUser_id() == down.getUser_upid())
				{
					String datestr = request.getParameter("date");
					String lot = request.getParameter("lot");
					String status = request.getParameter("status");
					String ishm = request.getParameter("ishm");
					String p = request.getParameter("p");
					int userid = down.getUser_id();
					int date = TryStatic.StrToInt(datestr, 0);
					String btime = TryStatic.getAgo(date);
					String etime = TimeUtil.getToday("yyyy-MM-dd");
					int page = TryStatic.StrToInt(p, 1);
					int limit = 12;
					int statu = TryStatic.StrToInt(status, -2);
					int hm = TryStatic.StrToInt(ishm, -1);
					int count = buyservice.findBuyCount(userid, btime, etime, lot, statu, hm);
					String url = (new StringBuilder("/my/Cps!ListBuy.jzh?date=")).append(date).append("&status=").append(statu).append("&ishm=").append(hm).append("&downid=").append(downid).toString();
					if (StringUtils.isNotBlank(lot))
						url = (new StringBuilder(String.valueOf(url))).append("&lot=").append(lot).toString();
					String ajaxPage = PageUtils.Page163(count, page, limit, (new StringBuilder(String.valueOf(url))).append("&").toString());
					List findBuy = buyservice.findBuy(userid, btime, etime, lot, statu, hm, (page - 1) * limit, limit);
					VelocityHelper velo = new VelocityHelper();
					velo.Put("user", find);
					velo.Put("order", findBuy);
					velo.Put("page", ajaxPage);
					velo.Put("date", Integer.valueOf(date));
					if (StringUtils.isNotBlank(lot))
					{
						velo.Put("lot", lot);
						velo.Put("lotname", LotEmun.valueOf(lot).namestr);
					}
					velo.Put("status", Integer.valueOf(statu));
					velo.Put("ishm", Integer.valueOf(hm));
					velo.Put("down", down.getUser_name());
					velo.Put("downid", Integer.valueOf(userid));
					velo.init("my/cpsorder.vm", out);
				} else
				{
					out.print("<script>alert('您无权访问该页面！');location.href='/Index.jzh';</script>");
				}
			} else
			{
				out.print("<script>alert('您无权访问该页面！');location.href='/Index.jzh';</script>");
			}
		} else
		{
			out.print(UserSession.loginstr);
		}
		out.flush();
		out.close();
	}

	public void DrawGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		if (user != null)
		{
			Bc_user find = UserStatic.find(user);
			int user_type = find.getUser_type();
			if (1 == user_type)
			{
				int User_id = find.getUser_id();
				String datestr = request.getParameter("date");
				String cusstr = request.getParameter("cus");
				String p = request.getParameter("p");
				int date = TryStatic.StrToInt(datestr, -1);
				String btime = "";
				String etime = "";
				if (-1 != date)
				{
					btime = TryStatic.getAgo(date);
					etime = TimeUtil.getToday("yyyy-MM-dd");
				}
				int cus = TryStatic.StrToInt(cusstr, -1);
				int page = TryStatic.StrToInt(p, 1);
				int limit = 15;
				int count = service.findListCount(User_id, cus, btime, etime);
				String ajaxPage = PageUtils.Page163(count, page, limit, (new StringBuilder("/my/Cps!List.jzh?date=")).append(date).append("&cus=").append(cus).append("&").toString());
				List findList = service.findList(User_id, cus, btime, etime, (page - 1) * limit, limit);
				VelocityHelper velo = new VelocityHelper();
				velo.Put("user", find);
				velo.Put("list", findList);
				velo.Put("page", ajaxPage);
				velo.Put("date", Integer.valueOf(date));
				velo.Put("cus", Integer.valueOf(cus));
				velo.Put("count", Integer.valueOf(count));
				velo.init("my/cpslist.vm", out);
			} else
			{
				out.print("<script>alert('您无权访问该页面！');location.href='/Index.jzh';</script>");
			}
		} else
		{
			out.print(UserSession.loginstr);
		}
		out.flush();
		out.close();
	}

	public void RechGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		if (user != null)
		{
			Bc_user find = UserStatic.find(user);
			int user_type = find.getUser_type();
			if (1 == user_type)
			{
				int User_id = find.getUser_id();
				String datestr = request.getParameter("date");
				String cusstr = request.getParameter("status");
				String nametr = request.getParameter("name");
				String p = request.getParameter("p");
				int date = TryStatic.StrToInt(datestr, -1);
				String btime = "";
				String etime = "";
				if (-1 != date)
				{
					btime = TryStatic.getAgo(date);
					etime = TimeUtil.getToday("yyyy-MM-dd");
				}
				int cus = TryStatic.StrToInt(cusstr, -1);
				int page = TryStatic.StrToInt(p, 1);
				int limit = 15;
				int count = service.findsRechCount(User_id, btime, etime, nametr, cus);
				String ajaxPage = PageUtils.Page163(count, page, limit, (new StringBuilder("/my/Cps!Rech.jzh?date=")).append(date).append("&status=").append(cus).append("&").toString());
				List findList = service.findsRech(User_id, btime, etime, nametr, cus, (page - 1) * limit, limit);
				VelocityHelper velo = new VelocityHelper();
				velo.Put("user", find);
				velo.Put("list", findList);
				velo.Put("page", ajaxPage);
				velo.Put("date", Integer.valueOf(date));
				velo.Put("status", Integer.valueOf(cus));
				velo.Put("count", Integer.valueOf(count));
				velo.init("my/cpsrech.vm", out);
			} else
			{
				out.print("<script>alert('您无权访问该页面！');location.href='/Index.jzh';</script>");
			}
		} else
		{
			out.print(UserSession.loginstr);
		}
		out.flush();
		out.close();
	}
}
