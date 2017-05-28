
package com.caipiao.data.open.crawler;

import com.caipiao.entity.Bc_lottery;
import com.caipiao.intface.Bc_lotteryIntface;
import com.caipiao.intfaceImpl.LotteryIntfaceImpl;
import com.caipiao.utils.Log;
import com.caipiao.utils.LotEmun;
import com.caipiao.utils.SystemSet;
import com.caipiao.utils.TimeUtil;
import com.sysbcjzh.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public abstract class OpenCrawler
{

	protected static final Properties times;
	protected static final Bc_lotteryIntface dao = new LotteryIntfaceImpl();
	protected String lot;

	public OpenCrawler()
	{
	}

	protected List findNoAgoTen()
	{
		long time = System.currentTimeMillis();
		try
		{
			time -= Integer.valueOf(times.getProperty(lot)).intValue() * 1000;
		}
		catch (Exception exception) { }
		return dao.findNowAgo(TimeUtil.LongToString(time, "yyyy-MM-dd HH:mm:ss"), lot, 0, 10);
	}

	protected boolean update(String lot_qh, String haoma, String ommit)
	{
		HashMap map = new HashMap();
		map.put("Lot_haoma", haoma);
		map.put("Lot_ommit", ommit);
		return dao.update(lot, lot_qh, map);
	}

	protected Bc_lottery findone(String qihao)
	{
		return dao.find(lot, qihao);
	}

	public abstract HashMap getHaoma(int i);

	public abstract String getOmmit(String s, String s1);

	public abstract String getOldQihao(String s);

	protected boolean SaveHaoma(int cs)
	{
		boolean result = false;
		List findNoOpen = findNoAgoTen();//查出10分钟以前的未开奖彩票
		HashMap haoma = getHaoma(cs);
		for (int i = findNoOpen.size() - 1; i >= 0; i--)
		{
			Bc_lottery loten = (Bc_lottery)findNoOpen.get(i);
			String qh = loten.getLot_qihao();
			String hm = loten.getLot_haoma();
			if (StringUtils.isNotBlank(hm))
			{
				if (i == 0)
					result = true;
			} else
			{
				String open = (String)haoma.get(qh);
				if (open != null)
				{
					Log.ShowInfo((new StringBuilder(String.valueOf(LotEmun.valueOf(lot).namestr))).append(" 第").append(qh).append(" 开奖号码抓取中...").append(open).toString());
					String oldommit = "";
					try
					{
						oldommit = ((Bc_lottery)findNoOpen.get(i + 1)).getLot_ommit();
						if (oldommit == null || oldommit.length() == 0)
						{
							Bc_lottery findone = findone(getOldQihao(qh));
							oldommit = findone.getLot_ommit();
						}
					}
					catch (Exception exception) { }
					update(qh, open, getOmmit(oldommit, open));
					if (i == 0)
						result = true;
				}
			}
		}

		return result;
	}

	public void Instance()
	{
		System.out.println((new StringBuilder(String.valueOf(LotEmun.valueOf(lot).namestr))).append("  开奖号码抓取中...").toString());
		for (int i = 0; i < 4; i++)
		{
			boolean saveHaoma = SaveHaoma(i);
			if (saveHaoma)
				break;
			try
			{
				Thread.sleep(2000L);
			}
			catch (InterruptedException interruptedexception) { }
		}

	}

	static 
	{
		times = SystemSet.crawler;
	}
}
