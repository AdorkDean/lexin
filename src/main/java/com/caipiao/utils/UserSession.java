package com.caipiao.utils;

import com.caipiao.entity.Bc_user;
import com.caipiao.service.systeminit.LogsStatic;
import com.caipiao.service.systeminit.UserStatic;
import com.sysbcjzh.utils.IPUtils;
import com.sysbcjzh.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class UserSession
{
	public static String loginstr = "<script>alert('您长时间未操作，请重新登录！');location.href='/login.html';</script>";
	public static String loginadminstr = "<script>alert('请登录!');parent.location.href='/adminsqwe/login.jsp';</script>";
	public static String bcjzhuser = "bcjzhuser";
	public static String adminuser = "adminuser";
	public static String adminlevel = "adminlevel";
	public static String rand = "rand";

	public static String userLogin(Bc_user useren, String pass, HttpServletRequest request)
	{
		String msg = "0";
		int user_id = useren.getUser_id();
		int findError = LogsStatic.FindError(user_id, 0);
		if ((StringUtils.md5String(pass).equals(useren.getUser_pass())) && (findError < 5)) {
			if (1 != useren.getUser_status()) {
				request.getSession().setAttribute(bcjzhuser, useren.getUser_name());

				LogsStatic.AddLogs(user_id, useren.getUser_name(), 0, 0, IPUtils.GetIP(request), "用户登录");
				UserStatic.upLgtimeandLgIp(user_id, TimeUtil.getToday("yyyy-MM-dd HH:mm:ss"), IPUtils.GetIP(request));
			} else {
				msg = "1";
			}
		}
		else if (findError >= 5) {
			msg = "密码错误次数过多，您的账户已被冻结，请半小时后登录。";
		} else {
			msg = "密码错误，您还有" + (4 - findError) + "次机会，否则将冻结账户半小时。";
			LogsStatic.AddLogs(user_id, useren.getUser_name(), 2, 0, IPUtils.GetIP(request), "用户登录密码错误");
		}

		return msg;
	}

	public static String adminLogin(Bc_user useren, String pass, HttpServletRequest request)
	{
		String msg = "0";
		int user_id = useren.getUser_id();
		int findError = LogsStatic.FindError(user_id, 1);
		if ((StringUtils.md5String(pass).equals(useren.getUser_pass())) && (findError < 5)) {
			if (1 != useren.getUser_status()) {
				int user_type = useren.getUser_type();
				if ((user_type == 7) || (user_type == 8) || (user_type == 9)) {
					request.getSession().setAttribute(adminuser, useren.getUser_name());
					request.getSession().setAttribute(adminlevel, Integer.valueOf(user_type));
					request.getSession().setAttribute(bcjzhuser, useren.getUser_name());

					LogsStatic.AddLogs(user_id, useren.getUser_name(), 0, 1, IPUtils.GetIP(request), "登录后台");
				} else {
					msg = "2";
				}
			} else {
				msg = "1";
			}
		}
		else if (findError >= 5) {
			msg = "密码错误次数过多，您的账户已被冻结，请半小时后登录。";
		} else {
			msg = "密码错误，您还有" + (4 - findError) + "次机会，否则将冻结账户半小时。";
			LogsStatic.AddLogs(user_id, useren.getUser_name(), 2, 1, IPUtils.GetIP(request), "登录后台密码错误");
		}

		return msg;
	}

	public static String getUser(HttpServletRequest request)
	{
		String user = null;
		Object attribute = request.getSession().getAttribute(bcjzhuser);
		if (attribute != null) {
			user = attribute.toString();
		}
		return user;
	}

	public static String getAdmin(HttpServletRequest request)
	{
		String user = null;
		Object attribute = request.getSession().getAttribute(adminuser);
		if (attribute != null) {
			user = attribute.toString();
		}
		return user;
	}

	public static int getAdminLevel(HttpServletRequest request)
	{
		int user = 0;
		Object attribute = request.getSession().getAttribute(adminlevel);
		if (attribute != null) {
			user = TryStatic.StrToInt(attribute.toString(), 0);
		}
		return user;
	}

	public static void userOut(HttpServletRequest request)
	{
		request.getSession().removeAttribute(bcjzhuser);
		request.getSession().invalidate();
	}

	public static void adminOut(HttpServletRequest request)
	{
		request.getSession().removeAttribute(adminuser);
		request.getSession().invalidate();
	}

	public static boolean CheackCodeIsRight(String InputCode, HttpServletRequest request)
	{
		boolean pd = false;
		Object attribute = request.getSession().getAttribute(rand);
		if ((InputCode != null) && (attribute != null) &&
				(InputCode.equals(attribute.toString()))) {
			pd = true;
		}

		return pd;
	}

	public static String DisUser(String user)
	{
		String disuser = "";
		int length = user.length();
		int temp = 2;
		if (length > temp) {
			disuser = user.substring(0, 2);
		} else {
			temp = 1;
			disuser = user.substring(0, 1);
		}
		for (int i = 0; i < length - temp; i++) {
			disuser = disuser + "*";
		}
		return disuser;
	}

	public static int getLevel(int level)
	{
		int result = 0;
		double temp = 0.0D;
		while (temp < level) {
			result++;
			temp += 100 + result * 5;
		}
		return result;
	}
}