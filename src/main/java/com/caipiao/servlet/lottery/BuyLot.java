package com.caipiao.servlet.lottery;

import com.caipiao.entity.Bc_user;
import com.caipiao.entity.out.BuyOneOut;
import com.caipiao.service.lottery.BuyLotService;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.StringUtils;
import com.sysbcjzh.utils.VelocityHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  订单详情 页面
 *
 *  spm  为bc_buy 中的buy_item 字段
 *
 *
 */
public class BuyLot extends IndexAction
{

	private static final long serialVersionUID = 1L;
	private BuyLotService service;

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public BuyLot()
	{
		service = new BuyLotService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String spm = request.getParameter("spm");
		BuyOneOut findBuy = service.findBuy(spm);
		if (findBuy != null)
		{
			VelocityHelper velo = new VelocityHelper();
			String user = UserSession.getUser(request);
			if (user != null)
			{
				Bc_user find = UserStatic.find(user);
				velo.Put("user", find);
			}
			String item = findBuy.getBuy_item();
			List findsBuyLot = service.findsBuyLot(item);
			int buy_ishm = findBuy.getBuy_ishm();
			velo.Put("buy", findBuy);
			velo.Put("lot", findsBuyLot);
			if (1 == buy_ishm)
			{
				List findsBuyUser = service.findsBuyUser(item);
				velo.Put("buyuser", findsBuyUser);
				velo.init("my/itemhm.vm", out);
			} else
			{
				String admin = UserSession.getAdmin(request);
				if (findBuy.getUser_name().equals(user) || StringUtils.isNotBlank(admin))
					velo.init("my/itemzg.vm", out);
				else
					out.print("<script>alert('该订单您无权查看。');location.href='/index.html';</script>");
			}
		} else
		{
			System.out.println("订单不存在！");
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		String result = "no";
		if (user != null)
		{
			String lot = request.getParameter("lot");
			String moneys = request.getParameter("money");
			String code = request.getParameter("code");
			String ishms = request.getParameter("ishm");
			String qihaolist = request.getParameter("qihao");
			String beishulist = request.getParameter("beishu");
			String isconts = request.getParameter("iscont");//是否追号
			String isopens = request.getParameter("isopen");//
			String buymons = request.getParameter("buymon");//
			String baos = request.getParameter("bao");//保底金额  不能为负数 否则后面的逻辑会产生判断错误
			String takes = request.getParameter("take");

			System.out.println(format.format(new Date()) + "用户user={"+user+"}购买彩票参数：lot={"+lot+"},money={"+moneys+"}" +
					",code={"+code+"},ishm={"+ishms+"},qihao={"+qihaolist+"},beishu={"+beishulist+"}" +
					",iscont={"+isconts+"},isopen={"+isopens+"},buymon={"+buymons+"},bao={"+baos+"},take={"+takes+"}");

			if (StringUtils.isNotEmptyAll(new String[] {lot, moneys, code, ishms, qihaolist, beishulist}))
			{
				String qihao[] = qihaolist.split(",");
				String beishustr[] = beishulist.split(",");
				if (qihao.length != beishustr.length)
				{
					result = "err";
				} else {
					int beishu[] = new int[beishustr.length];
					for (int i = 0; i < beishustr.length; i++)
						beishu[i] = TryStatic.StrToInt(beishustr[i], 1);

					Double money = Double.valueOf(TryStatic.StrToDouble(moneys, 0.0D));
					Double buymon = Double.valueOf(TryStatic.StrToDouble(buymons, 0.0D));
					Double bao = Double.valueOf(TryStatic.StrToDouble(baos, 0.0D));

					if(money.doubleValue() < 0 || buymon.doubleValue() < 0 || bao.doubleValue() < 0){
						result = "err";

					}else{
						int ishm = 0;
						if (buymon.doubleValue() > 0.0D){
							ishm = TryStatic.StrToInt(ishms, 0);
						}
						int take = TryStatic.StrToInt(takes, 0);
						take = take <= 10 ? take : 10;
						int isopen = TryStatic.StrToInt(isopens, 0);
						int iscont = TryStatic.StrToInt(isconts, 0);
						result = service.Buy(UserStatic.find(user), lot, money, buymon, bao, code, ishm, take, isopen, qihao, beishu, iscont);
					}


				}
			} else {
				result = "err";
			}
		}
		out.print(result);
		out.flush();
		out.close();
	}

	public void AddHMPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		String result = "no";
		if (user != null)
		{
			Bc_user find = UserStatic.find(user);
			if (find != null)
			{
				String item = request.getParameter("item");
				String lot = request.getParameter("lot");
				String fqh = request.getParameter("fqh");
				String mon = request.getParameter("mon");
				if (StringUtils.isNotEmptyAll(new String[] {
	item, lot, fqh, mon
}))
				{
					double buymon = TryStatic.StrToDouble(mon, 0.0D);
					result = service.BuyHM(find, item, lot, fqh, Math.ceil(buymon), "");
				} else
				{
					result = "err";
				}
			} else
			{
				result = "err";
			}
		}
		out.print(result);
		out.flush();
		out.close();
	}

	public void CheDanPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		String user = UserSession.getUser(request);
		String result = "nologin";
		if (user != null)
		{
			Bc_user find = UserStatic.find(user);
			if (find != null)
			{
				String idsstr = request.getParameter("ids");
				int ids = TryStatic.StrToInt(idsstr);
				if (ids > 0)
					result = service.CheDan(find, ids);
				else
					result = "1";
			} else
			{
				result = "err";
			}
		}
		out.print(result);
		out.flush();
		out.close();
	}


}
