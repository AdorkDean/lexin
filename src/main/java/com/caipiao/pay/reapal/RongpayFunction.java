
//    RongpayFunction.java

package com.caipiao.pay.reapal;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

// Referenced classes of package com.caipiao.pay.reapal:
//			Md5Encrypt, RongpayConfig

public class RongpayFunction
{

	public RongpayFunction()
	{
	}

	public static String BuildMysign(Map sArray, String key)
	{
		if (sArray != null && sArray.size() > 0)
		{
			StringBuilder prestr = CreateLinkString(sArray);
			return Md5Encrypt.md5(prestr.append(key).toString());
		} else
		{
			return null;
		}
	}

	public static StringBuilder CreateLinkString(Map params)
	{
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);
		StringBuilder prestr = new StringBuilder();
		String key = "";
		String value = "";
		for (int i = 0; i < keys.size(); i++)
		{
			key = (String)keys.get(i);
			value = (String)params.get(key);
			if (!"".equals(value) && value != null && !key.equalsIgnoreCase("sign") && !key.equalsIgnoreCase("sign_type"))
				prestr.append(key).append("=").append(value).append("&");
		}

		return prestr.deleteCharAt(prestr.length() - 1);
	}

	public static Map transformRequestMap(Map requestParams)
	{
		Map params = null;
		if (requestParams != null && requestParams.size() > 0)
		{
			params = new HashMap();
			String name = "";
			String values[] = (String[])null;
			String valueStr;
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); params.put(name, valueStr))
			{
				name = (String)iter.next();
				values = (String[])requestParams.get(name);
				valueStr = "";
				for (int i = 0; i < values.length; i++)
					valueStr = i != values.length - 1 ? (new StringBuilder(String.valueOf(valueStr))).append(values[i]).append(",").toString() : (new StringBuilder(String.valueOf(valueStr))).append(values[i]).toString();

			}

		}
		return params;
	}

	public static String Verify(String notify_id, String merchant_ID)
	{
		String transport = RongpayConfig.transport;
		StringBuilder veryfy_url = new StringBuilder();
		if (transport.equalsIgnoreCase("https"))
			veryfy_url.append("https://interface.rongpay.com.cn/verify/notify?");
		else
			veryfy_url.append(RongpayConfig.verify_url);
		veryfy_url.append("merchant_ID=").append(merchant_ID).append("&notify_id=").append(notify_id);
		String responseTxt = CheckUrl(veryfy_url.toString());
		return responseTxt;
	}

	private static String CheckUrl(String urlvalue)
	{
		String inputLine = "";
		try
		{
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			if (in != null)
				inputLine = in.readLine().toString();
			in.close();
			urlConnection.disconnect();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return inputLine;
	}

	public static HashMap GetMessage(String url)
	{
		SAXReader reader = new SAXReader();
		Document doc = null;
		HashMap hm = null;
		try
		{
			InputStream in = (new URL(url)).openStream();
			if (in != null)
			{
				doc = reader.read(in);
				hm = new HashMap();
				Element root = doc.getRootElement();
				for (Iterator it = root.elementIterator(); it.hasNext();)
				{
					Element e = (Element)it.next();
					if (e.nodeCount() > 1)
					{
						HashMap hm1 = new HashMap();
						Element e1;
						for (Iterator it1 = e.elementIterator(); it1.hasNext(); hm1.put(e1.getName(), e1.getText()))
							e1 = (Element)it1.next();

						hm.put(e.getName(), hm1);
					} else
					{
						hm.put(e.getName(), e.getText());
					}
				}

			}
			doc.clearContent();
			in.close();
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return hm;
	}
}
