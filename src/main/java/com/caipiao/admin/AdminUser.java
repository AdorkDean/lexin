
package com.caipiao.admin;

import com.caipiao.admin.service.AdminUserService;
import com.caipiao.entity.Bc_user;
import com.caipiao.service.systeminit.LogsStatic;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.caipiao.utils.XlsOutUtil;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.PageUtils;
import com.sysbcjzh.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * 用户
 */
public class AdminUser extends IndexAction
{

	private static final long serialVersionUID = 0x641b635211f3abafL;
	AdminUserService service;

	public AdminUser()
	{
		service = new AdminUserService();
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String t = request.getParameter("t");
			String p = request.getParameter("p");
			String u = request.getParameter("u");
			String m = request.getParameter("m");
			String s = request.getParameter("s");
			String bt = request.getParameter("bt");
			String et = request.getParameter("et");
			String uidstr = request.getParameter("uid");
			String rm = request.getParameter("rm");
			int type = TryStatic.StrToInt(t, -1);
			int pages = TryStatic.StrToInt(p, 1);
			int status = TryStatic.StrToInt(s, -1);
			int uid = TryStatic.StrToInt(uidstr, -1);
			double yue = TryStatic.StrToDouble(m, 0.0D);
			int limit = 30;
			int count = service.findsUserCount(u, rm, yue, type, status, uid, bt, et);
			String url = (new StringBuilder("/admin/AdminUser.jzh?t=")).append(type).append("&s=").append(status).append("&").toString();
			if (StringUtils.isNotBlank(u))
			{
				url = (new StringBuilder(String.valueOf(url))).append("u=").append(u).append("&").toString();
				request.setAttribute("user", u);
			}
			if (StringUtils.isNotBlank(rm))
			{
				url = (new StringBuilder(String.valueOf(url))).append("rm=").append(rm).append("&").toString();
				request.setAttribute("real", rm);
			}
			if (StringUtils.isNotEmptyAll(new String[] {
					bt, et
			}))
			{
				url = (new StringBuilder(String.valueOf(url))).append("bt=").append(bt).append("&et=").append(et).append("&").toString();
				request.setAttribute("bt", bt);
				request.setAttribute("et", et);
			}
			if (yue > 0.0D)
			{
				url = (new StringBuilder(String.valueOf(url))).append("m=").append(yue).append("&").toString();
				request.setAttribute("m", Double.valueOf(yue));
			}
			if (uid > 0)
			{
				url = (new StringBuilder(String.valueOf(url))).append("uid=").append(uid).append("&").toString();
				request.setAttribute("uid", Integer.valueOf(uid));
			}
			String page = PageUtils.Page(count, pages, limit, url);
			List list = service.findsUser(u, rm, yue, type, status, uid, bt, et, limit * (pages - 1), limit);
			request.setAttribute("find", list);
			request.setAttribute("page", page);
			request.setAttribute("s", Integer.valueOf(status));
			request.setAttribute("t", Integer.valueOf(type));
			request.getRequestDispatcher("/adminsqwe/UserList.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void DownUserGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		int adminLevel = UserSession.getAdminLevel(request);
		if (admin != null && adminLevel != 7)
		{
			int count = service.findsUserCount(null, null, 50D, 0, 0, 0, null, null);
			List finds = service.findsUser(null, null, 50D, 0, 0, 0, null, null, 0, count + 1);
			String filePath = XlsOutUtil.SaveUserXLS(finds);
			File f = new File(filePath);
			if (!f.exists())
			{
				response.sendError(404, "File not found!");
				return;
			}
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
			byte buf[] = new byte[1024];
			int len = 0;
			response.reset();
			URL u = new URL((new StringBuilder("file:///")).append(filePath).toString());
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition", (new StringBuilder("inline; filename=")).append(f.getName()).toString());
			OutputStream outf = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				outf.write(buf, 0, len);
			br.close();
			outf.close();
		} else
		{
			out.print(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		int adminLevel = UserSession.getAdminLevel(request);
		if (admin != null && adminLevel != 7)
		{
			String user = request.getParameter("user");
			String money = request.getParameter("money");
			String song = request.getParameter("song");
			String des = request.getParameter("des");
			String shows = request.getParameter("show");
			Double mon = Double.valueOf(TryStatic.StrToDouble(money, 0.0D));
			Double son = Double.valueOf(TryStatic.StrToDouble(song, 0.0D));
			Double show = Double.valueOf(TryStatic.StrToDouble(shows, 0.0D));
			if (StringUtils.isNotBlank(user) && mon.doubleValue() != 0.0D)
			{
				Bc_user find = UserStatic.find(user);
				if (find != null)
				{
					boolean addMoney = service.AddMoney(find, mon.doubleValue(), son.doubleValue(), des, admin, Math.abs(show.doubleValue()));
					if (addMoney)
						out.print("0");
					else
						out.print("1");
				} else
				{
					out.print("2");
				}
			} else
			{
				out.print("err");
			}
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void DescGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String id = request.getParameter("i");
			String user = request.getParameter("u");
			String types = request.getParameter("t");
			String bt = request.getParameter("b");
			String et = request.getParameter("e");
			String p = request.getParameter("p");
			int userid = TryStatic.StrToInt(id, 0);
			Bc_user find = UserStatic.find(user);
			if (userid != 0)
				find = UserStatic.find(userid);
			else
			if (StringUtils.isNotBlank(user))
				find = UserStatic.find(user);
			if (find != null)
			{
				request.setAttribute("name", find.getUser_name());
				userid = find.getUser_id();
				int page = TryStatic.StrToInt(p, 1);
				int limit = 30;
				int type = TryStatic.StrToInt(types, -1);
				String url = (new StringBuilder("/admin/AdminUser!Desc.jzh?i=")).append(userid).append("&t=").append(type).append("&").toString();
				if (StringUtils.isNotEmptyAll(new String[] {
						bt, et
				}))
				{
					url = (new StringBuilder(String.valueOf(url))).append("b=").append(bt).append("&e=").append(et).append("&").toString();
					request.setAttribute("bt", bt);
					request.setAttribute("et", et);
				}
				int count = service.findsDescCount(userid, bt, et, type);
				String Page = PageUtils.Page(count, page, limit, url);
				List finds = service.findsDesc(userid, bt, et, type, (page - 1) * limit, limit);
				if (finds != null)
				{
					request.setAttribute("find", finds);
					request.setAttribute("page", Page);
					request.setAttribute("t", Integer.valueOf(type));
					request.getRequestDispatcher("/adminsqwe/UserDesc.jsp").forward(request, response);
				} else
				{
					out.print("<script>alert('该用户暂无明细!');location.href='/adminsqwe/UserDesc.jsp';</script>");
				}
			} else
			{
				out.print("<script>alert('用户名不存在!');location.href='/adminsqwe/UserDesc.jsp';</script>");
			}
		} else
		{
			out.print(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void LoginPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String yzm = request.getParameter("yzm");
		String msg = "请填写完整！";
		if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(pass) && StringUtils.length(user) < 20 && StringUtils.length(pass) < 20)
		{
			boolean cheackCodeIsRight = UserSession.CheackCodeIsRight(yzm, request);
			if (cheackCodeIsRight)
			{
				Bc_user find = UserStatic.find(user);
				if (find != null)
				{
					String adminLogin = UserSession.adminLogin(find, pass, request);
					if ("1".equals(adminLogin))
						msg = "该用户已停用，请联系管理员！";
					else
					if ("2".equals(adminLogin))
						msg = "用户权限不够！";
					else
						msg = adminLogin;
				} else
				{
					msg = "用户名或密码错误！";
				}
			} else
			{
				msg = "验证码错误！";
			}
		}
		if ("0".equals(msg))
			response.sendRedirect("/adminsqwe/index.jsp");
		else
			out.write((new StringBuilder("<script>alert('")).append(msg).append("!');location.href='/adminsqwe/login.jsp'</script>").toString());
		out.flush();
		out.close();
	}

	public void LogsGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String user = request.getParameter("user");
			String typestr = request.getParameter("type");
			String levelstr = request.getParameter("level");
			String btime = request.getParameter("btime");
			String etime = request.getParameter("etime");
			String p = request.getParameter("p");
			int type = TryStatic.StrToInt(typestr, -1);
			int level = TryStatic.StrToInt(levelstr, -1);
			int page = TryStatic.StrToInt(p, 1);
			int limit = 30;
			int userid = -1;
			String url = (new StringBuilder("/admin/AdminUser!Logs.jzh?type=")).append(type).append("&level=").append(level).append("&").toString();
			request.setAttribute("type", Integer.valueOf(type));
			request.setAttribute("level", Integer.valueOf(level));
			if (StringUtils.isNotBlank(user))
			{
				Bc_user find = UserStatic.find(user);
				if (find != null)
				{
					userid = find.getUser_id();
					url = (new StringBuilder(String.valueOf(url))).append("user=").append(user).append("&").toString();
					request.setAttribute("user", user);
				}
			}
			if (StringUtils.isNotEmptyAll(new String[] {
					btime, etime
			}))
			{
				url = (new StringBuilder(String.valueOf(url))).append("btime=").append(btime).append("&etime=").append(etime).append("&").toString();
				request.setAttribute("btime", btime);
				request.setAttribute("etime", etime);
			}
			int count = LogsStatic.findsLogsCount(userid, btime, etime, type, level);
			String pagehtml = PageUtils.Page(count, page, limit, url);
			List list = LogsStatic.findsLogs(userid, btime, etime, type, level, limit * (page - 1), limit);
			if (list != null)
			{
				request.setAttribute("find", list);
				request.setAttribute("page", pagehtml);
			}
			request.getRequestDispatcher("/adminsqwe/LotLogsList.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void CommGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String user = request.getParameter("user");
			String p = request.getParameter("p");
			int page = TryStatic.StrToInt(p, 1);
			int limit = 30;
			String url = "/admin/AdminUser!Logs.jzh?";
			if (StringUtils.isNotBlank(user))
			{
				url = (new StringBuilder(String.valueOf(url))).append("user=").append(user).append("&").toString();
				request.setAttribute("user", user);
			}
			int count = service.findsCommCount(user);
			String pagehtml = PageUtils.Page(count, page, limit, url);
			List list = service.findsComm(user, limit * (page - 1), limit);
			if (list != null)
			{
				request.setAttribute("find", list);
				request.setAttribute("page", pagehtml);
			}
			request.getRequestDispatcher("/adminsqwe/UserCommList.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void AutoGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String user = request.getParameter("user");
			String lot = request.getParameter("lot");
			String statustr = request.getParameter("status");
			String btime = request.getParameter("btime");
			String etime = request.getParameter("etime");
			String p = request.getParameter("p");
			int status = TryStatic.StrToInt(statustr, -1);
			int page = TryStatic.StrToInt(p, 1);
			int limit = 30;
			String url = (new StringBuilder("/admin/AdminUser!Auto.jzh?status=")).append(status).append("&").toString();
			request.setAttribute("status", Integer.valueOf(status));
			if (StringUtils.isNotBlank(user))
			{
				url = (new StringBuilder(String.valueOf(url))).append("user=").append(user).append("&").toString();
				request.setAttribute("user", user);
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
			int count = service.findsAutoCount(user, lot, status, btime, etime);
			String pagehtml = PageUtils.Page(count, page, limit, url);
			List list = service.findsAuto(user, lot, status, btime, etime, limit * (page - 1), limit);
			if (list != null)
			{
				request.setAttribute("find", list);
				request.setAttribute("page", pagehtml);
			}
			request.getRequestDispatcher("/adminsqwe/UserAutoList.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void PointGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String user = request.getParameter("user");
			String typestr = request.getParameter("type");
			String subaddstr = request.getParameter("subadd");
			String btime = request.getParameter("btime");
			String etime = request.getParameter("etime");
			String p = request.getParameter("p");
			int type = TryStatic.StrToInt(typestr, -1);
			int subadd = TryStatic.StrToInt(subaddstr, -1);
			int page = TryStatic.StrToInt(p, 1);
			int limit = 30;
			String url = (new StringBuilder("/admin/AdminUser!Point.jzh?type=")).append(type).append("&subadd=").append(subadd).append("&").toString();
			request.setAttribute("type", Integer.valueOf(type));
			request.setAttribute("subadd", Integer.valueOf(subadd));
			int userid = -1;
			if (StringUtils.isNotBlank(user))
			{
				Bc_user find = UserStatic.find(user);
				if (find != null)
				{
					userid = find.getUser_id();
					url = (new StringBuilder(String.valueOf(url))).append("user=").append(user).append("&").toString();
					request.setAttribute("user", user);
				}
			}
			if (StringUtils.isNotEmptyAll(new String[] {
					btime, etime
			}))
			{
				url = (new StringBuilder(String.valueOf(url))).append("btime=").append(btime).append("&etime=").append(etime).append("&").toString();
				request.setAttribute("btime", btime);
				request.setAttribute("etime", etime);
			}
			int count = service.findsPointCount(userid, btime, etime, type, subadd);
			String pagehtml = PageUtils.Page(count, page, limit, url);
			List list = service.findsPoint(userid, btime, etime, type, subadd, limit * (page - 1), limit);
			if (list != null)
			{
				request.setAttribute("find", list);
				request.setAttribute("page", pagehtml);
			}
			request.getRequestDispatcher("/adminsqwe/UserPointList.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void RedsGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String user = request.getParameter("user");
			String typestr = request.getParameter("type");
			String subaddstr = request.getParameter("subadd");
			String btime = request.getParameter("btime");
			String etime = request.getParameter("etime");
			String p = request.getParameter("p");
			int type = TryStatic.StrToInt(typestr, -1);
			int subadd = TryStatic.StrToInt(subaddstr, -1);
			int page = TryStatic.StrToInt(p, 1);
			int limit = 30;
			String url = (new StringBuilder("/admin/AdminUser!Reds.jzh?type=")).append(type).append("&subadd=").append(subadd).append("&").toString();
			request.setAttribute("type", Integer.valueOf(type));
			request.setAttribute("subadd", Integer.valueOf(subadd));
			int userid = -1;
			if (StringUtils.isNotBlank(user))
			{
				Bc_user find = UserStatic.find(user);
				if (find != null)
				{
					userid = find.getUser_id();
					url = (new StringBuilder(String.valueOf(url))).append("user=").append(user).append("&").toString();
					request.setAttribute("user", user);
				}
			}
			if (StringUtils.isNotEmptyAll(new String[] {
					btime, etime
			}))
			{
				url = (new StringBuilder(String.valueOf(url))).append("btime=").append(btime).append("&etime=").append(etime).append("&").toString();
				request.setAttribute("btime", btime);
				request.setAttribute("etime", etime);
			}
			int count = service.findsRedsCount(userid, btime, etime, type, subadd);
			String pagehtml = PageUtils.Page(count, page, limit, url);
			List list = service.findsReds(userid, btime, etime, type, subadd, limit * (page - 1), limit);
			if (list != null)
			{
				request.setAttribute("find", list);
				request.setAttribute("page", pagehtml);
			}
			request.getRequestDispatcher("/adminsqwe/UserRedsList.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void OutGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		UserSession.adminOut(request);
		PrintWriter out = response.getWriter();
		out.write("<script>location.href='/adminsqwe/login.jsp'</script>");
		out.flush();
		out.close();
	}

	public void SetPassPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String idsstr = request.getParameter("ids");
			String pass = request.getParameter("pass");
			int ids = TryStatic.StrToInt(idsstr);
			if (ids > 0 && StringUtils.isNotBlank(pass))
			{
				Bc_user find = UserStatic.find(ids);
				if (find != null)
				{
					boolean updateUserPass = service.updateUserPass(find.getUser_id(), pass);
					if (updateUserPass)
						out.write("0");
					else
						out.write("1");
				} else
				{
					out.write("userNull");
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

	public void CommPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		int adminLevel = UserSession.getAdminLevel(request);
		if (admin != null && adminLevel == 9)
		{
			String idsstr = request.getParameter("ids");
			String _ssq = request.getParameter("ssq");
			String _dlt = request.getParameter("dlt");
			String _pl5 = request.getParameter("pl5");
			String _fc3d = request.getParameter("fc3d");
			String _pl3 = request.getParameter("pl3");
			String _cqssc = request.getParameter("cqssc");
			String _jxssc = request.getParameter("jxssc");
			String _jx11x5 = request.getParameter("jx11x5");
			String _sd11x5 = request.getParameter("sd11x5");
			String _gd11x5 = request.getParameter("gd11x5");
			String _cq11x5 = request.getParameter("cq11x5");
			String _jsk3 = request.getParameter("jsk3");
			String _hnssc = request.getParameter("hnssc");
			String _ynssc = request.getParameter("ynssc");
			int userid = TryStatic.StrToInt(idsstr);
			if (userid > 0)
			{
				Bc_user find = UserStatic.find(userid);
				if (find != null)
				{
					double ssq = TryStatic.StrToDouble(_ssq, 0.0D);
					double dlt = TryStatic.StrToDouble(_dlt, 0.0D);
					double pl5 = TryStatic.StrToDouble(_pl5, 0.0D);
					double fc3d = TryStatic.StrToDouble(_fc3d, 0.0D);
					double pl3 = TryStatic.StrToDouble(_pl3, 0.0D);
					double cqssc = TryStatic.StrToDouble(_cqssc, 0.0D);
					double jxssc = TryStatic.StrToDouble(_jxssc, 0.0D);
					double sd11x5 = TryStatic.StrToDouble(_sd11x5, 0.0D);
					double jx11x5 = TryStatic.StrToDouble(_jx11x5, 0.0D);
					double gd11x5 = TryStatic.StrToDouble(_gd11x5, 0.0D);
					double cq11x5 = TryStatic.StrToDouble(_cq11x5, 0.0D);
					double hnssc = TryStatic.StrToDouble(_hnssc, 0.0D);
					double ynssc = TryStatic.StrToDouble(_ynssc, 0.0D);
					double jsk3 = TryStatic.StrToDouble(_jsk3, 0.0D);
					boolean upadteUserComm = service.upadteUserComm(userid, cqssc, jxssc, hnssc , ynssc , jx11x5, sd11x5, cq11x5, gd11x5, jsk3, fc3d, dlt, ssq, pl5, pl3);
					if (upadteUserComm)
						out.write("0");
					else
						out.write("1");
				} else
				{
					out.write("userNull");
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

	public void UserEditGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		if (admin != null)
		{
			String uidstr = request.getParameter("uid");
			int uid = TryStatic.StrToInt(uidstr);
			if (uid > 0)
			{
				Bc_user find = UserStatic.find(uid);
				if (find != null)
					request.setAttribute("find", find);
				com.caipiao.entity.Bc_comm findcomm = service.findcomm(uid);
				if (findcomm != null)
					request.setAttribute("comm", findcomm);
			}
			request.getRequestDispatcher("/adminsqwe/UserEdit.jsp").forward(request, response);
		} else
		{
			out.write(UserSession.loginadminstr);
		}
		out.flush();
		out.close();
	}

	public void UserPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		int adminLevel = UserSession.getAdminLevel(request);
		if (admin != null && adminLevel == 9)
		{
			String idsstr = request.getParameter("ids");
			String _type = request.getParameter("type");
			String _status = request.getParameter("status");
			int ids = TryStatic.StrToInt(idsstr);
			if (ids > 0)
			{
				Bc_user find = UserStatic.find(ids);
				if (find != null)
				{
					int type = TryStatic.StrToInt(_type, -1);
					int status = TryStatic.StrToInt(_status, -1);
					if (type != -1 && status != -1)
					{
						boolean updateUserType = service.updateUserType(ids, type, status);
						if (updateUserType)
							out.write("0");
						else
							out.write("1");
					} else
					{
						out.write("err");
					}
				} else
				{
					out.write("err");
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

	public void ZhuanPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String admin = UserSession.getAdmin(request);
		int adminLevel = UserSession.getAdminLevel(request);
		if (admin != null && adminLevel == 9)
		{
			String _ids = request.getParameter("ids");
			String _user = request.getParameter("user");
			int id = TryStatic.StrToInt(_ids, -1);
			if (id > 0)
			{
				String changeUserUp = service.ChangeUserUp(id, admin, _user, request);
				out.write(changeUserUp);
			} else
			{
				out.write("-1");
			}
		} else
		{
			out.write("nologin");
		}
		out.flush();
		out.close();
	}
}
