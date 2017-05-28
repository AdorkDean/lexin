
//    SignEntityServlet.java

package com.caipiao.activity.servlet;

import com.caipiao.activity.Activity;
import com.caipiao.activity.ActivityService;
import com.caipiao.activity.SignEntity;
import com.caipiao.activity.SignEntityService;
import com.caipiao.entity.Bc_user;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.TimeUtil;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * 标记实体
 */
public class SignEntityServlet extends IndexAction
{

	private static final long serialVersionUID = 1L;
	SignEntityService service;
	ActivityService services;

	public SignEntityServlet()
	{
		service = new SignEntityService();
		services = new ActivityService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		String today = TimeUtil.getToday("yyyy-MM-dd");
		if (user != null)
		{
			Activity activity = services.find("SignEntity");
			if (1 == activity.getAct_status())
			{
				Bc_user find = UserStatic.find(user);
				int user_id = find.getUser_id();
				String signService = service.SignService(user_id, today);
				System.out.println((new StringBuilder("--------------")).append(signService).toString());
				if ("-1".equals(signService))
					out.print("<script>alert('未达到投注金额！');location.href='/activity/sigin.jsp';</script>");
				else
				if ("0".equals(signService)){
					
					int moneys[] = {3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 10};
					int number = (new Random()).nextInt(10) + 1;//todo 这里之前为9
					int money = moneys[number];
					int muber = 1;
					SignEntity sign = service.find(user_id, TimeUtil.getYesterday("yyyy-MM-dd"));
					if (sign != null)
					{
						int signAll = sign.getSignAll();
						if (15 == signAll)
							muber = 1;
						else
							muber = sign.getSignAll() + 1;
					}
					boolean addData = service.addData(find.getUser_id(), today, muber);
					if (addData)
					{
						UserStatic.AddMoney(find, money, 0, user, 10, (new StringBuilder(String.valueOf(today))).append("签到赠送").toString(), 0.0D);
						out.print((new StringBuilder("<script>alert('签到成功！领取")).append(money).append("元。');location.href='/activity/sigin.jsp';</script>").toString());
					} else
					{
						out.print("<script>alert('签到错误！');location.href='/activity/sigin.jsp';</script>");
					}
				} else
				if ("1".equals(signService))
					out.print("<script>alert('您已签到！');location.href='/activity/sigin.jsp';</script>");
				else
					out.print("<script>alert('签到错误！');location.href='/activity/sigin.jsp';</script>");
			} else
			{
				out.print("<script>alert('签到活动未开启！');location.href='/activity/sigin.jsp';</script>");
			}
		} else
		{
			out.print(UserSession.loginstr);
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws ServletException, IOException
	{
	}

	public void BigGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		String today = TimeUtil.getToday("yyyy-MM-dd");
		if (user != null)
		{
			Activity activity = services.find("SignEntity");
			if (1 == activity.getAct_status())
			{
				Bc_user find = UserStatic.find(user);
				int user_id = find.getUser_id();
				SignEntity findsign = service.find(user_id, today);
				if (findsign != null)
				{
					int bigSign = findsign.getBigSign();
					if (bigSign == 0)
						out.print("<script>alert('您签到未满7天或者15天！');location.href='/activity/sigin.jsp';</script>");
					else
					if (2 == bigSign)
						out.print("<script>alert('您已领取过大礼包！');location.href='/activity/sigin.jsp';</script>");
					else
					if (1 == bigSign)
					{
						boolean updateBig = service.updateBig(findsign.getId());
						int signAll = findsign.getSignAll();
						if (updateBig)
						{
							int mon = 0;
							if (signAll == 7)
								mon = 38;
							else
							if (signAll == 15)
								mon = 58;
							UserStatic.AddMoney(find, mon, 0, user, 10, (new StringBuilder(String.valueOf(today))).append("签到大礼包赠送").toString(), 0.0D);
							out.print((new StringBuilder("<script>alert('签到成功！领取")).append(mon).append("元大礼包。');location.href='/activity/sigin.jsp';</script>").toString());
						}
					} else
					{
						out.print("<script>alert('领取大礼包错误！');location.href='/activity/sigin.jsp';</script>");
					}
				} else
				{
					out.print("<script>alert('您今日还未签到！');location.href='/activity/sigin.jsp';</script>");
				}
			} else
			{
				out.print("<script>alert('签到活动未开启！');location.href='/activity/sigin.jsp';</script>");
			}
		} else
		{
			out.print(UserSession.loginstr);
		}
		out.flush();
		out.close();
	}
}
