
//    AlipaySubmit.java

package com.caipiao.pay.alipay;

import com.caipiao.pay.alipay.httpClient.HttpProtocolHandler;
import com.caipiao.pay.alipay.httpClient.HttpRequest;
import com.caipiao.pay.alipay.httpClient.HttpResponse;
import com.caipiao.pay.alipay.httpClient.HttpResultType;
import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.caipiao.pay.alipay:
//			AlipayCore, AlipayConfig, MD5

public class AlipaySubmit
{

	private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

	public AlipaySubmit()
	{
	}

	public static String buildRequestMysign(Map sPara, String key)
	{
		String prestr = AlipayCore.createLinkString(sPara);
		String mysign = "";
		if (AlipayConfig.sign_type.equals("MD5"))
			mysign = MD5.sign(prestr, key, AlipayConfig.input_charset);
		return mysign;
	}

	private static Map buildRequestPara(Map sParaTemp, String key)
	{
		Map sPara = AlipayCore.paraFilter(sParaTemp);
		String mysign = buildRequestMysign(sPara, key);
		sPara.put("sign", mysign);
		sPara.put("sign_type", AlipayConfig.sign_type);
		return sPara;
	}

	public static String buildRequest(Map sParaTemp, String strMethod, String strButtonName, String key)
	{
		Map sPara = buildRequestPara(sParaTemp, key);
		List keys = new ArrayList(sPara.keySet());
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append((new StringBuilder("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"https://mapi.alipay.com/gateway.do?_input_charset=")).append(AlipayConfig.input_charset).append("\" method=\"").append(strMethod).append("\">").toString());
		for (int i = 0; i < keys.size(); i++)
		{
			String name = (String)keys.get(i);
			String value = (String)sPara.get(name);
			sbHtml.append((new StringBuilder("<input type=\"hidden\" name=\"")).append(name).append("\" value=\"").append(value).append("\"/>").toString());
		}

		sbHtml.append((new StringBuilder("<input type=\"submit\" value=\"")).append(strButtonName).append("\" style=\"display:none;\"></form>").toString());
		sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
		return sbHtml.toString();
	}

	public static String buildRequest(Map sParaTemp, String strMethod, String strButtonName, String strParaFileName, String key)
	{
		Map sPara = buildRequestPara(sParaTemp, key);
		List keys = new ArrayList(sPara.keySet());
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append((new StringBuilder("<form id=\"alipaysubmit\" name=\"alipaysubmit\"  enctype=\"multipart/form-data\" action=\"https://mapi.alipay.com/gateway.do?_input_charset=")).append(AlipayConfig.input_charset).append("\" method=\"").append(strMethod).append("\">").toString());
		for (int i = 0; i < keys.size(); i++)
		{
			String name = (String)keys.get(i);
			String value = (String)sPara.get(name);
			sbHtml.append((new StringBuilder("<input type=\"hidden\" name=\"")).append(name).append("\" value=\"").append(value).append("\"/>").toString());
		}

		sbHtml.append((new StringBuilder("<input type=\"file\" name=\"")).append(strParaFileName).append("\" />").toString());
		sbHtml.append((new StringBuilder("<input type=\"submit\" value=\"")).append(strButtonName).append("\" style=\"display:none;\"></form>").toString());
		return sbHtml.toString();
	}

	public static String buildRequest(String strParaFileName, String strFilePath, Map sParaTemp, String key)
		throws Exception
	{
		Map sPara = buildRequestPara(sParaTemp, key);
		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
		HttpRequest request = new HttpRequest(HttpResultType.BYTES);
		request.setCharset(AlipayConfig.input_charset);
		request.setParameters(generatNameValuePair(sPara));
		request.setUrl((new StringBuilder("https://mapi.alipay.com/gateway.do?_input_charset=")).append(AlipayConfig.input_charset).toString());
		HttpResponse response = httpProtocolHandler.execute(request, strParaFileName, strFilePath);
		if (response == null)
		{
			return null;
		} else
		{
			String strResult = response.getStringResult();
			return strResult;
		}
	}

	private static NameValuePair[] generatNameValuePair(Map properties)
	{
		NameValuePair nameValuePair[] = new NameValuePair[properties.size()];
		int i = 0;
		for (Iterator iterator = properties.entrySet().iterator(); iterator.hasNext();)
		{
			Map.Entry entry = (Map.Entry)iterator.next();
			nameValuePair[i++] = new NameValuePair((String)entry.getKey(), (String)entry.getValue());
		}

		return nameValuePair;
	}

	public static String query_timestamp(String partner)
		throws MalformedURLException, DocumentException, IOException
	{
		String strUrl = (new StringBuilder("https://mapi.alipay.com/gateway.do?service=query_timestamp&partner=")).append(partner).append("&_input_charset").append(AlipayConfig.input_charset).toString();
		StringBuffer result = new StringBuffer();
		SAXReader reader = new SAXReader();
		Document doc = reader.read((new URL(strUrl)).openStream());
		List nodeList = doc.selectNodes("//alipay/*");
		for (Iterator iterator = nodeList.iterator(); iterator.hasNext();)
		{
			Node node = (Node)iterator.next();
			if (node.getName().equals("is_success") && node.getText().equals("T"))
			{
				List nodeList1 = doc.selectNodes("//response/timestamp/*");
				Node node1;
				for (Iterator iterator1 = nodeList1.iterator(); iterator1.hasNext(); result.append(node1.getText()))
					node1 = (Node)iterator1.next();

			}
		}

		return result.toString();
	}
}
