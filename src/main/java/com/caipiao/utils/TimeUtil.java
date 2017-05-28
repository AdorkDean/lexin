
//    TimeUtil.java

package com.caipiao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil
{

	public static final String L_Y = "yyyy-MM-dd HH:mm:ss";
	public static final String S_Y = "yyyy-MM-dd";
	public static final String S_N = "yyyyMMdd";

	public TimeUtil()
	{
	}

	public static String getToday(String fomat)
	{
		Calendar tim = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(fomat);
		return format.format(tim.getTime());
	}

	public static String getTomorrow(String fomat)
	{
		return getDayAgo(fomat, 1);
	}

	public static String getYesterday(String fomat)
	{
		return getDayAgo(fomat, -1);
	}

	public static String getDayAgo(String fomat, int day)
	{
		Long currentTimeMillis = Long.valueOf(System.currentTimeMillis() + (long)day * 0x5265c00L);
		Date date = new Date(currentTimeMillis.longValue());
		SimpleDateFormat fmat = new SimpleDateFormat(fomat);
		return fmat.format(date);
	}

	public static int getNowS()
	{
		Calendar c = Calendar.getInstance();
		return c.get(11) * 60 * 60 + c.get(12) * 60 + c.get(13);
	}

	public static long StringToLong(String time, String format)
	{
		long result = 0L;
		SimpleDateFormat fmat = new SimpleDateFormat(format);
		try
		{
			Date parse = fmat.parse(time);
			result = parse.getTime() * 1L;
		}
		catch (Exception exception) { }
		return result;
	}

	public static String LongToString(long time, String format)
	{
		Date date = new Date(time);
		SimpleDateFormat fmat = new SimpleDateFormat(format);
		return fmat.format(date);
	}

	public static String StringToQuartz(String time, long yanshi)
	{
		long stringToLong = StringToLong(time, "yyyy-MM-dd HH:mm:ss") + yanshi;
		Date date = new Date(stringToLong);
		SimpleDateFormat fmt = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
		return fmt.format(date);
	}
}
