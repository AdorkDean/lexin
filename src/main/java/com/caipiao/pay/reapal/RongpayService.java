
//    RongpayService.java

package com.caipiao.pay.reapal;

import java.util.*;

// Referenced classes of package com.caipiao.pay.reapal:
//			RongpayFunction, RongpayConfig

public class RongpayService
{

	public RongpayService()
	{
	}

	public static String BuildForm(String service, String payment_type, String merchant_ID, String seller_email, String return_url, String notify_url, String order_no, String title, 
			String body, String total_fee, String buyer_email, String charset, String paymethod, String defaultbank, String key, 
			String sign_type)
	{
		Map sPara = new HashMap();
		sPara.put("service", service);
		sPara.put("payment_type", payment_type);
		sPara.put("merchant_ID", merchant_ID);
		sPara.put("seller_email", seller_email);
		sPara.put("return_url", return_url);
		sPara.put("notify_url", notify_url);
		sPara.put("charset", charset);
		sPara.put("order_no", order_no);
		sPara.put("title", title);
		sPara.put("body", body);
		sPara.put("total_fee", total_fee);
		sPara.put("buyer_email", buyer_email);
		sPara.put("paymethod", paymethod);
		sPara.put("defaultbank", defaultbank);
		String mysign = RongpayFunction.BuildMysign(sPara, key);
		StringBuffer sbHtml = new StringBuffer();
		List keys = new ArrayList(sPara.keySet());
		String gateway = RongpayConfig.rongpay_url;
		sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" action=\"").append(gateway).append("\" method=\"get\">");
		String name = "";
		String value = "";
		for (int i = 0; i < keys.size(); i++)
		{
			name = (String)keys.get(i);
			value = (String)sPara.get(name);
			if (value != null && !"".equals(value))
				sbHtml.append("<input type=\"hidden\" name=\"").append(name).append((new StringBuilder("\" value=\"")).append(value).append("\"/>").toString());
		}

		sbHtml.append("<input type=\"hidden\" name=\"sign\" value=\"").append(mysign).append("\"/>");
		sbHtml.append("<input type=\"hidden\" name=\"sign_type\" value=\"").append(sign_type).append("\"/>");
		sbHtml.append("</form>");
		return sbHtml.toString();
	}
}
