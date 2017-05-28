
//    XSSRequestWrapper.java

package com.sysbcjzh.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

public class XSSRequestWrapper extends HttpServletRequestWrapper
{

	public XSSRequestWrapper(HttpServletRequest request)
	{
		super(request);
	}

	public String[] getParameterValues(String parameter)
	{
		String values[] = super.getParameterValues(parameter);
		if (values == null)
			return null;
		int count = values.length;
		String encodedValues[] = new String[count];
		for (int i = 0; i < count; i++)
			encodedValues[i] = stripXSS(values[i]);

		return encodedValues;
	}

	public String getParameter(String parameter)
	{
		String value = super.getParameter(parameter);
		return stripXSS(value);
	}

	public String getHeader(String name)
	{
		String value = super.getHeader(name);
		return stripXSS(value);
	}

	private String stripXSS(String value)
	{
		if (value != null)
		{
			value = value.replaceAll("", "");
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", 2);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\'(.*?)\\'", 42);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", 42);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("</script>", 2);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("<script(.*?)>", 42);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("eval\\((.*?)\\)", 42);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("e\255xpression\\((.*?)\\)", 42);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("javascript:", 2);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("vbscript:", 2);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("onload(.*?)=", 42);
			value = scriptPattern.matcher(value).replaceAll("");
		}
		return value;
	}
}
