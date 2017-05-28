<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="com.caipiao.pay.yeepay.*"%>
<%@page import="com.caipiao.utils.SystemSet"%>
<%@page import="com.caipiao.utils.StaticItem"%>
<%@page import="com.caipiao.utils.TryStatic"%>
<%@page import="com.caipiao.entity.Bc_user"%>
<%@page import="com.caipiao.service.systeminit.UserStatic"%>
<%@page import="com.caipiao.service.my.MyRechangeService"%>
<%@page import="com.caipiao.pay.chinabank.BankName"%>
<%!	String formatString(String text){ 
			if(text == null) {
				return ""; 
			}
			return text;
		}
%>
<html>
	<head><title>To YeePay Page</title></head>
<%
	request.setCharacterEncoding("UTF-8");
	String keyValue	= formatString("33B4DB3244BB5C2875D98CB1B79B68DA");	// 鍟嗗瀵嗛挜
	String nodeAuthorizationURL = formatString("http://www.mgpay.cn/GateWay/Bank.aspx");// 浜ゆ槗璇锋眰鍦板潃
	// 鍟嗗璁剧疆鐢ㄦ埛璐拱鍟嗗搧鐨勬敮浠樹俊鎭?
	String p0_Cmd = formatString("Buy");// 鍦ㄧ嚎鏀粯璇锋眰锛屽浐瀹氬€?鈥滲uy鈥?
	String p1_MerId = formatString("10000");// 鍟嗘埛缂栧彿
	String p2_Order = StaticItem.GetRechitem();// 鍟嗘埛璁㈠崟鍙?
	String p3_Amt = formatString(request.getParameter("p3_Amt"));// 鏀粯閲戦
	String p4_Cur = formatString("CNY");// 浜ゆ槗甯佺
	String p5_Pid = formatString(request.getParameter("p5_Pid"));// 鍟嗗搧鍚嶇О
	String p6_Pcat = formatString(request.getParameter("p6_Pcat"));// 鍟嗗搧绉嶇被
	String p7_Pdesc = formatString(request.getParameter("p7_Pdesc"));// 鍟嗗搧鎻忚堪
	String p8_Url = formatString("http://210.209.117.249:8089/pay/alipay/callback.jsp");// 鍟嗘埛鎺ユ敹鏀粯鎴愬姛鏁版嵁鐨勫湴鍧€
	String p9_SAF = formatString(request.getParameter("p9_SAF"));// 闇€瑕佸～鍐欓€佽揣淇℃伅 0锛氫笉闇€瑕? 1:闇€瑕?
	String pa_MP = formatString(request.getParameter("pa_MP"));// 鍟嗘埛鎵╁睍淇℃伅
	String pd_FrpId = formatString(request.getParameter("pd_FrpId"));// 鏀粯閫氶亾缂栫爜
	// 閾惰缂栧彿蹇呴』澶у啓
	pd_FrpId = pd_FrpId.toUpperCase();
	String pr_NeedResponse = formatString("1");    // 榛樿涓?1"锛岄渶瑕佸簲绛旀満鍒?
  	String hmac = formatString("");// 浜ゆ槗绛惧悕涓?
    // 鑾峰緱MD5-HMAC绛惧悕
	double money = TryStatic.StrToInt(p3_Amt);
	if (money>=0&&money<=50000&&null!=pa_MP){
	    Bc_user find = UserStatic.find(pa_MP);
	    if (null!=find){
	    	int uid = find.getUser_id();
	    	  hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(p0_Cmd,
			p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,
			p8_Url,p9_SAF,String.valueOf(uid),pd_FrpId,pr_NeedResponse,keyValue);
	    	MyRechangeService dao = new MyRechangeService();
	    	//dao.Rech(pa_MP, uid, p2_Order, money, 0, 2, BankName.GetNameY(pd_FrpId));
			dao.Rech(pa_MP, find.getUser_id(), p2_Order, money, 0, 3, BankName.GetNameY(pd_FrpId));
	 		%>
			<body onLoad="javascript:document.yeepay.submit()">
				<form name="yeepay" action='<%=nodeAuthorizationURL%>' method='POST'>
					<input type='hidden' name='p0_Cmd'   value='<%=p0_Cmd%>'>
					<input type='hidden' name='p1_MerId' value='<%=p1_MerId%>'>
					<input type='hidden' name='p2_Order' value='<%=p2_Order%>'>
					<input type='hidden' name='p3_Amt'   value='<%=p3_Amt%>'>
					<input type='hidden' name='p4_Cur'   value='<%=p4_Cur%>'>
					<input type='hidden' name='p5_Pid'   value='<%=p5_Pid%>'>
					<input type='hidden' name='p6_Pcat'  value='<%=p6_Pcat%>'>
					<input type='hidden' name='p7_Pdesc' value='<%=p7_Pdesc%>'>
					<input type='hidden' name='p8_Url'   value='<%=p8_Url%>'>
					<input type='hidden' name='p9_SAF'   value='<%=p9_SAF%>'>
					<input type='hidden' name='pa_MP'    value='<%=uid%>'>
					<input type='hidden' name='pd_FrpId' value='<%=pd_FrpId%>'>
					<input type="hidden" name="pr_NeedResponse"  value="<%=pr_NeedResponse%>">
					<input type='hidden' name='hmac'     value='<%=hmac%>'>
				</form>
			</body>
			<%
	    }else{
	    	%><body>鐢ㄦ埛鍚嶄笉瀛樺湪</body><%
	    }
	}else{
		%><body>鐢ㄦ埛鍚嶆垨鑰呴噾棰濓紙鏈€灏?0鍏冿紝鏈€澶?涓囷級閿欒</body><%
	}
%>
</html>