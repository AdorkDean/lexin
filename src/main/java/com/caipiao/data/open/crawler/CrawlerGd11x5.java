
package com.caipiao.data.open.crawler;

import com.caipiao.utils.*;
import java.util.HashMap;

// Referenced classes of package com.caipiao.data.open.crawler:
//			OpenCrawler, GetOpenNumber

public class CrawlerGd11x5 extends OpenCrawler
{

	public CrawlerGd11x5()
	{
		super.lot = LotEmun.Gd11x5.name;
	}

	public HashMap getHaoma(int i)
	{
		return GetOpenNumber.GetGd11x5(i);
	}

	public String getOldQihao(String qihao)
	{
		String result = "";
		String day = qihao.substring(0, 8);
		String nums = qihao.substring(8);
		int num = 1;
		try
		{
			num = Integer.valueOf(nums).intValue();
		}
		catch (Exception exception) { }
		if (--num >= 10)
			result = (new StringBuilder(String.valueOf(day))).append(num).toString();
		else
		if (num > 0 && num < 10)
		{
			result = (new StringBuilder(String.valueOf(day))).append("0").append(num).toString();
		} else
		{
			long stringToLong = TimeUtil.StringToLong(day, "yyyyMMdd") - 0x5265c00L;
			String oldday = TimeUtil.LongToString(stringToLong, "yyyyMMdd");
			result = (new StringBuilder(String.valueOf(oldday))).append("84").toString();
		}
		return result;
	}

	public String getOmmit(String oldommit, String haoma)
	{
		return OmmitUtil._11x5(oldommit, haoma);
	}
}
