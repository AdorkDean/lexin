
package com.caipiao.admin;

import com.caipiao.admin.service.AdminLotService;
import com.caipiao.data.open.MethodHemai;
import com.caipiao.data.open.MethodOpen;
import com.caipiao.data.open.MethodOut;
import com.caipiao.data.open.OpenThread;
import com.caipiao.entity.Bc_buy;
import com.caipiao.entity.Bc_lottery;
import com.caipiao.entity.Bc_user;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.LotEmun;
import com.caipiao.utils.TimeUtil;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.PageUtils;
import com.sysbcjzh.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 彩票
 */
public class AdminLot extends IndexAction
{

	private static final long serialVersionUID = 1L;
	AdminLotService service;

	public AdminLot()
	{
		service = new AdminLotService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String name = request.getParameter("name");
			String item = request.getParameter("item");
			String lot = request.getParameter("lot");
			String statusstr = request.getParameter("status");
			String ishmstr = request.getParameter("ishm");
			String btime = request.getParameter("btime");
			String etime = request.getParameter("etime");
			String fqh = request.getParameter("fqh");
			String pstr = request.getParameter("p");
			int status = TryStatic.StrToInt(statusstr, -2);
			int ishm = TryStatic.StrToInt(ishmstr, -1);
			int page = TryStatic.StrToInt(pstr, 1);
			int limit = 30;
			int count = service.findsBuyCount(name, item, lot, status, ishm, fqh, btime, etime);
			String url = (new StringBuilder("/admin/AdminLot.jzh?status=")).append(status).append("&ishm=").append(ishm).append("&").toString();
			if (StringUtils.isNotBlank(name))
			{
				url = (new StringBuilder(String.valueOf(url))).append("name=").append(name).append("&").toString();
				request.setAttribute("name", name);
			}
			if (StringUtils.isNotBlank(lot))
			{
				url = (new StringBuilder(String.valueOf(url))).append("lot=").append(lot).append("&").toString();
				request.setAttribute("lot", lot);
			}
			if (StringUtils.isNotEmptyAll(new String[] {
					btime, etime
			}))
			{
				url = (new StringBuilder(String.valueOf(url))).append("btime=").append(btime).append("&etime=").append(etime).append("&").toString();
				request.setAttribute("btime", btime);
				request.setAttribute("etime", etime);
			}
			if (StringUtils.isNotBlank(fqh))
			{
				url = (new StringBuilder(String.valueOf(url))).append("fqh=").append(fqh).append("&").toString();
				request.setAttribute("fqh", fqh);
			}
			String pagehtml = PageUtils.Page(count, page, limit, url);
			List findsBuy = service.findsBuy(name, item, lot, status, ishm, fqh, btime, etime, limit * (page - 1), limit);
			if (findsBuy != null)
			{
				request.setAttribute("find", findsBuy);
				request.setAttribute("page", pagehtml);
			}
			request.setAttribute("status", Integer.valueOf(status));
			request.setAttribute("ishm", Integer.valueOf(ishm));
			request.getRequestDispatcher("/adminsqwe/LotBuyList.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String i = request.getParameter("i");
			int ids = TryStatic.StrToInt(i);
			if (ids > 0)
			{
				Bc_lottery f = service.findLot(ids);
				if (f.getLot_isopen() == 0)
				{
					OpenThread openThread = new OpenThread(f.getLot_name(), f.getLot_qihao(), f.getLot_haoma());
					openThread.start();
					out.write("0");
				} else
				{
					out.write("1");
				}
			} else
			{
				out.write("err");
			}
		} else
		{
			out.write("nologin");
		}
		out.flush();
		out.close();
	}

	public void LotPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String i = request.getParameter("i");
			String haoma = request.getParameter("h");
			int ids = TryStatic.StrToInt(i);
			if (ids > 0 && StringUtils.isNotBlank(haoma))
			{
				boolean upHmLot = service.UpHmLot(ids, haoma);
				if (upHmLot)
					out.write("0");
				else
					out.write("1");
			} else
			{
				out.write("err");
			}
		} else
		{
			out.write("nologin");
		}
		out.flush();
		out.close();
	}

	public void PeopleDoPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String _ids = request.getParameter("ids");
			String mt = request.getParameter("mt");
			int ids = TryStatic.StrToInt(_ids);
			boolean dos = false;
			if (ids > 0)
			{
				if ("kaij".equals(mt))
					dos = (new MethodOpen()).Open(ids);
				else
				if ("chup".equals(mt))
					dos = (new MethodOut()).OutOne(ids);
				else
				if ("oneche".equals(mt))
					dos = (new MethodOut()).CheOen(ids);
				else
				if ("allche".equals(mt))
				{
					(new MethodOut()).AllChe(ids);
					dos = true;
				} else
				if ("isOk".equals(mt))
					dos = (new MethodHemai()).HeimaiOne(ids);
				if (dos)
					out.write("0");
				else
					out.write("1");
			} else
			{
				out.write("err");
			}
		} else
		{
			out.write("nologin");
		}
		out.flush();
		out.close();
	}

	public void ItemGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String i = request.getParameter("i");
			String t = request.getParameter("t");
			Bc_buy find = null;
			int id = TryStatic.StrToInt(i);
			if (id > 0)
				find = service.find(id);
			else
				find = service.find(t);
			if (find != null)
			{
				String buy_item = find.getBuy_item();
				List findItemLot = service.findItemLot(buy_item);
				List findItemUser = service.findItemUser(buy_item);
				request.setAttribute("buy", find);
				request.setAttribute("blot", findItemLot);
				request.setAttribute("buser", findItemUser);
				request.getRequestDispatcher("/adminsqwe/LotItem.jsp").forward(request, response);
			} else
			{
				response.sendRedirect("/errorPage.html");
			}
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void QihaoGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String lot = request.getParameter("l");
		String p = request.getParameter("p");
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String lotstr = lot == null ? "Cqssc" : lot;
			int page = TryStatic.StrToInt(p, 1);
			int limit = 30;
			int count = service.findsLotCount(lotstr);
			String pagehtml = PageUtils.Page(count, page, limit, (new StringBuilder("/admin/AdminLot!Qihao.jzh?l=")).append(lotstr).append("&").toString());
			List findsLot = service.findsLot(lotstr, limit * (page - 1), limit);
			request.setAttribute("find", findsLot);
			request.setAttribute("page", pagehtml);
			request.setAttribute("lot", lotstr);
			request.getRequestDispatcher("/adminsqwe/LotAddQihao.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void QihaoPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String lots = request.getParameter("lot");
		String riqi = request.getParameter("riqi");
		String nums = request.getParameter("num");
		String qihaos = request.getParameter("qihao");
		PrintWriter out = response.getWriter();
		String systemAdmin = UserSession.getAdmin(request);
		int adminLevel = UserSession.getAdminLevel(request);
		if (systemAdmin != null && adminLevel != 7)
		{
			if (StringUtils.isNotBlank(lots) && StringUtils.isNotBlank(riqi))
				try
				{
					lots = LotEmun.valueOf(lots).name;
					int days = TryStatic.StrToInt(nums, 10);
					service.AddQihao(lots, days, riqi, qihaos);
					out.print("0");
				}
				catch (Exception e)
				{
					out.print("err");
				}
			else
				out.print("err");
		} else
		{
			out.print(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void UpQihaoGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String lot = request.getParameter("lot");
		String id = request.getParameter("id");
		String systemAdmin = UserSession.getAdmin(request);
		if (systemAdmin != null)
		{
			int ids = TryStatic.StrToInt(id, 0);
			if (StringUtils.isNotBlank(lot) && ids != 0)
			{
				Bc_lottery findLot = service.findLot(ids);
				if (findLot != null)
				{
					request.setAttribute("find", findLot);
					request.setAttribute("lot", lot);
					request.getRequestDispatcher("/adminsqwe/LotUpQihao.jsp").forward(request, response);
				} else
				{
					out.write("<script>alert('该期号不存在!');history.back();</script>");
				}
			} else
			{
				out.write("<script>alert('参数错误!');history.back();</script>");
			}
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void UpQihaoPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String ids = request.getParameter("ids");
		String btime = request.getParameter("btime");
		String etime = request.getParameter("etime");
		PrintWriter out = response.getWriter();
		String systemAdmin = UserSession.getAdmin(request);
		if (systemAdmin != null)
		{
			int id = TryStatic.StrToInt(ids, 0);
			if (StringUtils.isNotEmptyAll(new String[] {
					btime, etime
			}) && id != 0)
			{
				boolean upLot = service.UpLot(id, btime, etime);
				if (upLot)
					out.print("0");
				else
					out.print("1");
			} else
			{
				out.print("err");
			}
		} else
		{
			out.print(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void BuyLotGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String item = request.getParameter("item");
			String lot = request.getParameter("lot");
			String qihao = request.getParameter("qihao");
			String statusstr = request.getParameter("status");
			String pstr = request.getParameter("p");
			int status = TryStatic.StrToInt(statusstr, -2);
			int page = TryStatic.StrToInt(pstr, 1);
			int limit = 30;
			int count = service.findsBuyLotCount(item, lot, qihao, status);
			String url = (new StringBuilder("/admin/AdminLot!BuyLot.jzh?status=")).append(status).append("&").toString();
			request.setAttribute("status", Integer.valueOf(status));
			if (StringUtils.isNotBlank(item))
			{
				url = (new StringBuilder(String.valueOf(url))).append("item=").append(item).append("&").toString();
				request.setAttribute("item", item);
			}
			if (StringUtils.isNotBlank(lot))
			{
				url = (new StringBuilder(String.valueOf(url))).append("lot=").append(lot).append("&").toString();
				request.setAttribute("lot", lot);
			}
			if (StringUtils.isNotBlank(qihao))
			{
				url = (new StringBuilder(String.valueOf(url))).append("qihao=").append(qihao).append("&").toString();
				request.setAttribute("qihao", qihao);
			}
			String pagehtml = PageUtils.Page(count, page, limit, url);
			List finds = service.findsBuyLot(item, lot, qihao, status, limit * (page - 1), limit);
			if (finds != null)
			{
				request.setAttribute("find", finds);
				request.setAttribute("page", pagehtml);
			}
			request.getRequestDispatcher("/adminsqwe/LotBuyLotList.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void LotListGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String lot = request.getParameter("lot");
			String qihao = request.getParameter("qihao");
			String havehmstr = request.getParameter("havehm");
			String isopenstr = request.getParameter("isopen");
			String pstr = request.getParameter("p");
			int havehm = TryStatic.StrToInt(havehmstr, -1);
			int isopen = TryStatic.StrToInt(isopenstr, -1);
			int page = TryStatic.StrToInt(pstr, 1);
			int limit = 30;
			String nowtime = TimeUtil.getToday("yyyy-MM-dd HH:mm:ss");
			int count = service.findsLotCount(lot, qihao, havehm, isopen, nowtime);
			String url = (new StringBuilder("/admin/AdminLot!LotList.jzh?havehm=")).append(havehm).append("&isopen=").append(isopen).append("&").toString();
			request.setAttribute("havehm", Integer.valueOf(havehm));
			request.setAttribute("isopen", Integer.valueOf(isopen));
			if (StringUtils.isNotBlank(lot))
			{
				url = (new StringBuilder(String.valueOf(url))).append("lot=").append(lot).append("&").toString();
				request.setAttribute("lot", lot);
			}
			if (StringUtils.isNotBlank(qihao))
			{
				url = (new StringBuilder(String.valueOf(url))).append("qihao=").append(qihao).append("&").toString();
				request.setAttribute("qihao", qihao);
			}
			String pagehtml = PageUtils.Page(count, page, limit, url);
			List finds = service.findsLot(lot, qihao, havehm, isopen, nowtime, limit * (page - 1), limit);
			if (finds != null)
			{
				request.setAttribute("find", finds);
				request.setAttribute("page", pagehtml);
			}
			request.getRequestDispatcher("/adminsqwe/LotList.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void UserBuyGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String user = request.getParameter("user");
			String lot = request.getParameter("lot");
			String statusstr = request.getParameter("status");
			String ishmstr = request.getParameter("ishm");
			String btime = request.getParameter("btime");
			String etime = request.getParameter("etime");
			String pstr = request.getParameter("p");
			int status = TryStatic.StrToInt(statusstr, -2);
			int ishm = TryStatic.StrToInt(ishmstr, -1);
			int page = TryStatic.StrToInt(pstr, 1);
			int limit = 30;
			int userid = -1;
			String url = (new StringBuilder("/admin/AdminLot!UserBuy.jzh?status=")).append(status).append("&ishm=").append(ishm).append("&").toString();
			request.setAttribute("status", Integer.valueOf(status));
			request.setAttribute("ishm", Integer.valueOf(ishm));
			if (StringUtils.isNotBlank(user))
			{
				Bc_user find = UserStatic.find(user);
				if (find != null)
				{
					userid = find.getUser_id();
					request.setAttribute("user", user);
					url = (new StringBuilder(String.valueOf(url))).append("user=").append(user).append("&").toString();
				}
			}
			if (StringUtils.isNotBlank(lot))
			{
				url = (new StringBuilder(String.valueOf(url))).append("lot=").append(lot).append("&").toString();
				request.setAttribute("lot", lot);
			}
			if (StringUtils.isNotEmptyAll(new String[] {
					btime, etime
			}))
			{
				url = (new StringBuilder(String.valueOf(url))).append("btime=").append(btime).append("&etime=").append(etime).append("&").toString();
				request.setAttribute("btime", btime);
				request.setAttribute("etime", etime);
			}
			int count = service.findBuyCount(userid, btime, etime, lot, status, ishm);
			String pagehtml = PageUtils.Page(count, page, limit, url);
			List list = service.findBuy(userid, btime, etime, lot, status, ishm, limit * (page - 1), limit);
			if (list != null)
			{
				request.setAttribute("find", list);
				request.setAttribute("page", pagehtml);
			}
			request.getRequestDispatcher("/adminsqwe/LotUserBuy.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}
}
