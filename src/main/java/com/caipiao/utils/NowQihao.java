
//    NowQihao.java

package com.caipiao.utils;

import com.caipiao.entity.Bc_lottery;
import com.caipiao.intface.Bc_lotteryIntface;
import com.caipiao.intfaceImpl.LotteryIntfaceImpl;

import java.util.List;

// Referenced classes of package com.caipiao.utils:
//			SystemSet, TryStatic, TimeUtil, LotSale

public class NowQihao
{

	static Bc_lotteryIntface dao = new LotteryIntfaceImpl();
	static int time = 90;

	public NowQihao()
	{
	}

	public static boolean CheckBuy(String lot, String qihaos[])
	{
		String qihao = RetuMin(qihaos);
		return CheckBuy(lot, qihao);
	}

	public static boolean CheckBuy(String lot, String qihao)
	{
		time = TryStatic.StrToInt(SystemSet.crawler.getProperty((new StringBuilder(String.valueOf(lot))).append("_ago").toString()), time);
		Bc_lottery find = dao.find(lot, qihao);
		long etime = TimeUtil.StringToLong(find.getLot_etime(), "yyyy-MM-dd HH:mm:ss");
		long ntime = System.currentTimeMillis();
		return ntime + (long)(time * 1000) < etime;
	}

	public static String RetuMin(String qihao[])
	{
		long nqtemp = -1L;
		String as[];
		int j = (as = qihao).length;
		for (int i = 0; i < j; i++)
		{
			String str = as[i];
			long toInt = TryStatic.StrToLong(str);
			if (-1L != nqtemp)
			{
				if (toInt < nqtemp)
					nqtemp = toInt;
			} else
			{
				nqtemp = toInt;
			}
		}

		return String.valueOf(nqtemp);
	}

	public static String getNowQihao(String lot)
	{
		time = TryStatic.StrToInt(SystemSet.crawler.getProperty((new StringBuilder(String.valueOf(lot))).append("_ago").toString()), time);
		String timestr = TimeUtil.LongToString(System.currentTimeMillis() + (long)time * 1000L, "yyyy-MM-dd HH:mm:ss");
		Bc_lottery findByNowTime = dao.findByNowTime(lot, timestr);
		if (findByNowTime != null)
			return findByNowTime.getLot_qihao();
		else
			return null;
	}

	public static String getNowTime(String lot)
	{
		String result = null;
		time = TryStatic.StrToInt(SystemSet.crawler.getProperty((new StringBuilder(String.valueOf(lot))).append("_ago").toString()), time);
		String timestr = TimeUtil.LongToString(System.currentTimeMillis() + (long)time * 1000L, "yyyy-MM-dd HH:mm:ss");
		Bc_lottery findByNowTime = dao.findByNowTime(lot, timestr);
		if (findByNowTime != null)
		{
			int isopen = LotSale.getLotSale(lot);
			String etime = findByNowTime.getLot_etime();
			String qihao = findByNowTime.getLot_qihao();
			String ommit = "no";
			String btime = findByNowTime.getLot_btime();
			Bc_lottery findByEtime = dao.findByEtime(lot, btime);
			if (findByEtime != null)
				if (findByEtime.getLot_ommit().length() > 0)
				{
					ommit = findByEtime.getLot_ommit();
				} else
				{
					Bc_lottery findByEtime2 = dao.findByEtime(lot, findByEtime.getLot_btime());
					if (findByEtime2 != null)
					{
						String lot_ommit = findByEtime2.getLot_ommit();
						if (lot_ommit.length() > 0)
							ommit = lot_ommit;
					}
				}
			result = (new StringBuilder(String.valueOf(qihao))).append("#").append(time).append("#").append(TimeUtil.getToday("yyyy-MM-dd HH:mm:ss")).append("#").append(etime).append("##").append(ommit).append("##").append(isopen).toString();
		}
		return result;
	}

	public static List getCutList(String lot)
	{
		time = TryStatic.StrToInt(SystemSet.crawler.getProperty((new StringBuilder(String.valueOf(lot))).append("_ago").toString()), time);
		String timestr = TimeUtil.LongToString(System.currentTimeMillis() + (long)time * 1000L, "yyyy-MM-dd HH:mm:ss");
		return dao.findNowAfter(timestr, lot, 0, 200);
	}

	public static List findOpenByLot(String lot)
	{
		return dao.findNewOpen(lot, 10);
	}

	public static Bc_lottery findQihaoId(Integer qihaoid)
	{
		return dao.find(qihaoid.intValue());
	}

	public static List findOpen()
	{
		return dao.findAllOpen();
	}

	public static List findOpenByDay(String lot)
	{
		return dao.findDay(lot, TimeUtil.getToday("yyyy-MM-dd"));
	}

}
