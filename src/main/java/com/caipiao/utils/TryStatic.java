
//    TryStatic.java

package com.caipiao.utils;


// Referenced classes of package com.caipiao.utils:
//			TimeUtil

public class TryStatic
{

	public TryStatic()
	{
	}

	public static double StrToDouble(String str)
	{
		return StrToDouble(str, 0.0D);
	}

	public static double StrToDouble(String str, double def)
	{
		double r = def;
		try
		{
			r = Double.valueOf(str).doubleValue();
		}
		catch (Exception exception) { }
		return r;
	}

	public static double StrToDoubleABS(String str, double def)
	{
		double r = def;
		try
		{
			r = Math.abs(Double.valueOf(str).doubleValue());
		}
		catch (Exception exception) { }
		return r;
	}

	public static double StrToDoubleABS(String str, double def, double max)
	{
		double r = def;
		try
		{
			r = Math.abs(Double.valueOf(str).doubleValue());
		}
		catch (Exception exception) { }
		r = r <= max ? r : max;
		return r;
	}

	public static int StrToInt(String str)
	{
		int r = 0;
		try
		{
			r = Integer.valueOf(str).intValue();
		}
		catch (Exception exception) { }
		return r;
	}

	public static int StrToInt(String str, int def)
	{
		int r = def;
		try
		{
			r = Integer.valueOf(str).intValue();
		}
		catch (Exception exception) { }
		return r;
	}

	public static long StrToLong(String str)
	{
		long r = 0L;
		try
		{
			r = Long.valueOf(str).longValue();
		}
		catch (Exception exception) { }
		return r;
	}

	public static long StrToLong(String str, long def)
	{
		long r = def;
		try
		{
			r = Long.valueOf(str).longValue();
		}
		catch (Exception exception) { }
		return r;
	}

	public static String getAgo(int date)
	{
		String btime = null;
		if (1 == date)
			btime = TimeUtil.getDayAgo("yyyy-MM-dd", -30);
		else
		if (2 == date)
			btime = TimeUtil.getDayAgo("yyyy-MM-dd", -90);
		else
			btime = TimeUtil.getDayAgo("yyyy-MM-dd", -7);
		return btime;
	}
}
