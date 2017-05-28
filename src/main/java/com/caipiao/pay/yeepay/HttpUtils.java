package com.caipiao.pay.yeepay;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class HttpUtils
{
	private static final String URL_PARAM_CONNECT_FLAG = "&";
	private static final int SIZE = 1048576;
	private static Log log = LogFactory.getLog(HttpUtils.class);

	public static List URLGet(String strUrl, Map map)
			throws IOException
	{
		String strtTotalURL = "";
		List result = new ArrayList();
		if (strtTotalURL.indexOf("?") == -1)
			strtTotalURL = strUrl + "?" + getUrl(map);
		else {
			strtTotalURL = strUrl + "&" + getUrl(map);
		}
		log.debug("strtTotalURL:" + strtTotalURL);
		URL url = new URL(strtTotalURL);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setUseCaches(false);
		HttpURLConnection.setFollowRedirects(true);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()), 1048576);
		while (true) {
			String line = in.readLine();
			if (line == null)
			{
				break;
			}
			result.add(line);
		}

		in.close();
		return result;
	}

	public static List URLPost(String strUrl, Map map)
			throws IOException
	{
		String content = "";
		content = getUrl(map);
		String totalURL = null;
		if (strUrl.indexOf("?") == -1)
			totalURL = strUrl + "?" + content;
		else {
			totalURL = strUrl + "&" + content;
		}
		URL url = new URL(strUrl);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setAllowUserInteraction(false);
		con.setUseCaches(false);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		BufferedWriter bout = new BufferedWriter(
				new OutputStreamWriter(con
						.getOutputStream()));
		bout.write(content);
		bout.flush();
		bout.close();
		BufferedReader bin = new BufferedReader(
				new InputStreamReader(con
						.getInputStream()), 1048576);
		List result = new ArrayList();
		while (true) {
			String line = bin.readLine();
			if (line == null)
			{
				break;
			}
			result.add(line);
		}

		return result;
	}

	private static String getUrl(Map map)
	{
		if ((map == null) || (map.keySet().size() == 0)) {
			return "";
		}
		StringBuffer url = new StringBuffer();
		Set keys = map.keySet();
		for (Iterator i = keys.iterator(); i.hasNext(); ) {
			String key = String.valueOf(i.next());
			if (map.containsKey(key)) {
				Object val = map.get(key);
				String str = val != null ? val.toString() : "";
				try {
					str = URLEncoder.encode(str, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str)
						.append("&");
			}
		}
		String strURL = "";
		strURL = url.toString();
		if ("&".equals(strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}
		return strURL;
	}
}