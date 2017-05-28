
//    StaticItem.java

package com.caipiao.utils;

// Referenced classes of package com.caipiao.utils:
//			TimeUtil

public class StaticItem
{

	static int l_buy = 0;
	static int l_draw = 0;
	static int l_chong = 0;
	static int l_auto = 0;
	static int l_point = 0;

	public StaticItem()
	{
	}

	public static String GetBuyItem(String s, String q)
	{
		String itqh = q.substring(q.length() - 3);
		String format = String.format("%03d", new Object[] {
			Integer.valueOf(l_buy)
		});
		l_buy = l_buy != 999 ? l_buy + 1 : 0;
		return (new StringBuilder(String.valueOf(TimeUtil.getToday("MMddHHmmss")))).append(s).append(itqh).append(format).toString();
	}

	public static String GetAutoItem()
	{
		String format = String.format("%03d", new Object[] {
			Integer.valueOf(l_auto)
		});
		l_auto = l_auto != 999 ? l_auto + 1 : 0;
		return (new StringBuilder("A")).append(TimeUtil.getToday("MMddHHmmss")).append(format).toString();
	}

	public static String GetDrawitem()
	{
		String format = String.format("%03d", new Object[] {
			Integer.valueOf(l_draw)
		});
		l_draw = l_draw != 999 ? l_draw + 1 : 0;
		return (new StringBuilder("D")).append(TimeUtil.getToday("MMddHHmmss")).append(format).toString();
	}

	public static String GetRechitem()
	{
		String format = String.format("%03d", new Object[] {
			Integer.valueOf(l_chong)
		});
		l_chong = l_chong != 999 ? l_chong + 1 : 0;
		return (new StringBuilder("C")).append(TimeUtil.getToday("MMddHHmmss")).append(format).toString();
	}

	public static String GetPointitem()
	{
		String format = String.format("%03d", new Object[] {
			Integer.valueOf(l_point)
		});
		l_point = l_point != 999 ? l_point + 1 : 0;
		return (new StringBuilder("P")).append(TimeUtil.getToday("MMddHHmmss")).append(format).toString();
	}

	public static void main(String args[])
	{
		for (int i = 0; i < 20; i++)
			System.out.println(GetBuyItem("Cqssc", "123456789"));

	}

}
