
//    CheckUtil.java

package com.sysbcjzh.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil
{

	public CheckUtil()
	{
	}

	public static boolean checkEmail(String email)
	{
		boolean flag = false;
		try
		{
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		}
		catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}

	public static boolean CheckMobile(String mobiles)
	{
		boolean flag = false;
		try
		{
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		}
		catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}

	public static boolean Regex(String reg, String body)
	{
		return Pattern.compile(reg).matcher(body).matches();
	}
}
