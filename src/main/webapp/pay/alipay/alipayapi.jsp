
<%@page import="com.sysbcjzh.utils.IPUtils"%>
<%@page import="com.caipiao.utils.StaticItem"%>
<%@page import="com.caipiao.utils.SystemSet"%>
<%@page import="com.caipiao.utils.TryStatic"%>
<%@page import="com.caipiao.entity.Bc_user"%>
<%@page import="com.caipiao.service.systeminit.UserStatic"%>
<%@page import="com.caipiao.service.my.MyRechangeService"%><%
/* *
 *功能：即时到账交易接口接入页 
 *版本：3.3
 *日期：2012-08-14  
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、商户服务中心（https://b.alipay.com/support/helperApply.htm?action=consultationApply），提交申请集成协助，我们会有专业的技术工程师主动联系您协助解决
 *2、商户帮助中心（http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&info_type=9）
 *3、支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.caipiao.pay.alipay.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝即时到账交易接口</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////

		//支付类型
		String payment_type = "1";
		//必填，不能修改
		
		//服务器异步通知页面路径
		//String notify_url = SystemSet.paytype.getProperty("alipay_ref");
		String notify_url = "";
		//需http://格式的完整路径，不能加?id=123这类自定义参数		
		
		//页面跳转同步通知页面路径
		//String return_url = SystemSet.paytype.getProperty("alipay_url");
		String return_url = "";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/		
		
		//卖家支付宝帐户
		String seller_email = new String("shenzhendingxia8@163.com1");
		//必填		
		
		//商户订单号
		String out_trade_no = new String(StaticItem.GetRechitem());
		//商户网站订单系统中唯一订单号，必填		
		
		//订单名称
		String subject = new String(out_trade_no);
		//必填		
		
		//付款金额
		String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
		//必填		
		
		//订单描述
		String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		
		//商品展示地址
		//String show_url = new String(request.getParameter("WIDshow_url").getBytes("ISO-8859-1"),"UTF-8");
		String show_url = "";
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html		
		
		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数		
		
		//客户端的IP地址
		String exter_invoke_ip = IPUtils.GetIP(request);
		//非局域网的外网IP地址，如：221.0.0.1
		
		double money = TryStatic.StrToInt(total_fee);
		if (money>=0&&null!=body){
			Bc_user find = UserStatic.find(body);
			if (null!=find){
				body = find.getUser_name();
				MyRechangeService dao = new MyRechangeService();
				dao.Rech(body, find.getUser_id(), out_trade_no, money, 0, 3, body);
				%>
			<body onLoad="javascript:document.yeepay.submit()">
				<form name="yeepay" action="http://210.209.117.249:8089/ytyeepay/sendPay_02.jsp" method="POST">
					<input size="70" type="text" name="p0_Cmd" value="Buy" readonly="readonly" />
					<input size="70" type="text" name="p2_Order" value="<%=out_trade_no%>"  readonly="readonly" />
					<input size="70" type="text" name="p3_Amt" value="<%=money%>" />
					<input size="70" type="text" name="p4_Cur" value="CNY" readonly="readonly" />
					<input size="70" type="text" name="p5_Pid" value="productname" />
					<input size="70" type="text" name="p6_Pcat" value="productcat" />
					<input size="70" type="text" name="p7_Pdesc" value="productdesc" />
					<input size="70" type="text" name="p8_Url" value="http://210.209.117.249:8089/pay/alipay/callback.jsp" />
				<select name="p9_SAF">
							<option value="0">不需要</option>
							<option value="1">需要</option>
						</select>
						<input type="submit" value="提交订单" />
				 
				</form>
			</body>
			<%
				//把请求参数打包成数组
				//Map<String, String> sParaTemp = new HashMap<String, String>();
				//sParaTemp.put("service", "create_direct_pay_by_user");
		        //sParaTemp.put("partner", AlipayConfig.partner);
		        //sParaTemp.put("_input_charset", AlipayConfig.input_charset);
				//sParaTemp.put("payment_type", payment_type);
				//sParaTemp.put("notify_url", notify_url);
				//sParaTemp.put("return_url", return_url);
				//sParaTemp.put("seller_email", seller_email);
				//sParaTemp.put("out_trade_no", out_trade_no);
				//sParaTemp.put("subject", subject);
				//sParaTemp.put("total_fee", total_fee);
				//sParaTemp.put("body", body);
				//sParaTemp.put("show_url", show_url);
				//sParaTemp.put("anti_phishing_key", anti_phishing_key);
				//sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
				
				//建立请求
				//String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
				//out.println(sHtmlText);
			}else{
				%><body>用户名不存在</body><%
			}
		}else{
			%><body>用户名或者金额（最小50）错误</body><%
		}
		//////////////////////////////////////////////////////////////////////////////////
	%>
</html>
