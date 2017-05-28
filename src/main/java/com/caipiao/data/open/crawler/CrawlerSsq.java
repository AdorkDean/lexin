
package com.caipiao.data.open.crawler;

import com.caipiao.utils.*;
import java.util.HashMap;

// Referenced classes of package com.caipiao.data.open.crawler:
//			OpenCrawler, GetOpenNumber

public class CrawlerSsq extends OpenCrawler
{

	public CrawlerSsq()
	{
		super.lot = LotEmun.Ssq.name;
	}

	public HashMap getHaoma(int i)
	{
		return GetOpenNumber.GetSsq(i);
	}

	public String getOldQihao(String qihao)
	{
		String result = "";
		String year = qihao.substring(0, 4);
		String nums = qihao.substring(4);
		int num = TryStatic.StrToInt(nums, 1);
		if (--num >= 100)
			result = (new StringBuilder(String.valueOf(year))).append(num).toString();
		else
		if (num >= 10 && num < 100)
			result = (new StringBuilder(String.valueOf(year))).append("0").append(num).toString();
		else
		if (num > 0 && num < 10)
			result = (new StringBuilder(String.valueOf(year))).append("00").append(num).toString();
		return result;
	}

	public String getOmmit(String oldommit, String haoma)
	{
		return OmmitUtil.Ssc(oldommit, haoma);
	}
}
