<%@page language="java" contentType="text/html;charset=utf-8" %>
<%@page import="com.caipiao.entity.Bc_user" %>
<%@page import="com.caipiao.pay.chinabank.BankName" %>
<%@page import="com.caipiao.pay.yeepay.PaymentForOnlineService" %>
<%@page import="com.caipiao.service.my.MyRechangeService" %>
<%@page import="com.caipiao.service.systeminit.UserStatic" %>
<%@page import="com.caipiao.utils.StaticItem" %>
<%@page import="com.caipiao.utils.SystemSet" %>
<%@page import="com.caipiao.utils.TryStatic" %>
<%! String formatString(String text) {
    if (text == null) {
        return "";
    }
    return text;
}
%>
<html>
<head><title>To YeePay Page</title></head>
<%
    request.setCharacterEncoding("UTF-8");
    String keyValue = formatString(SystemSet.paytype.getProperty("yeepay_key"));    // 商家密钥
    String nodeAuthorizationURL = formatString(SystemSet.paytype.getProperty("yeepay_pay"));// 交易请求地址
    // 商家设置用户购买商品的支付信息
    String p0_Cmd = formatString("Buy");// 在线支付请求，固定值 ”Buy”
    String p1_MerId = formatString(SystemSet.paytype.getProperty("yeepay_pid"));// 商户编号
    String p2_Order = StaticItem.GetRechitem();// 商户订单号
    String p3_Amt = formatString(request.getParameter("p3_Amt"));// 支付金额
    String p4_Cur = formatString("CNY");// 交易币种
    String p5_Pid = formatString(request.getParameter("p5_Pid"));// 商品名称
    String p6_Pcat = formatString(request.getParameter("p6_Pcat"));// 商品种类
    String p7_Pdesc = formatString(request.getParameter("p7_Pdesc"));// 商品描述
    String p8_Url = formatString(SystemSet.paytype.getProperty("yeepay_url"));// 商户接收支付成功数据的地址
    String p9_SAF = formatString(request.getParameter("p9_SAF"));// 需要填写送货信息 0：不需要  1:需要
    String pa_MP = formatString(request.getParameter("pa_MP"));// 商户扩展信息
    String pd_FrpId = formatString(request.getParameter("pd_FrpId"));// 支付通道编码
    // 银行编号必须大写
    pd_FrpId = pd_FrpId.toUpperCase();
    String pr_NeedResponse = formatString("1");    // 默认为"1"，需要应答机制
    String hmac = formatString("");// 交易签名串
    // 获得MD5-HMAC签名
    double money = TryStatic.StrToInt(p3_Amt);
    if (money >= 10 && money <= 50000 && null != pa_MP) {
        Bc_user find = UserStatic.find(pa_MP);
        if (null != find) {
            int uid = find.getUser_id();
            hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(p0_Cmd,
                    p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
                    p8_Url, p9_SAF, String.valueOf(uid), pd_FrpId, pr_NeedResponse, keyValue);
            MyRechangeService dao = new MyRechangeService();
            dao.Rech(pa_MP, uid, p2_Order, money, 0, 2, BankName.GetNameY(pd_FrpId));
%>
<body onLoad="javascript:document.yeepay.submit()">
<form name="yeepay" action='<%=nodeAuthorizationURL%>' method='POST'>
    <input type='hidden' name='p0_Cmd' value='<%=p0_Cmd%>'>
    <input type='hidden' name='p1_MerId' value='<%=p1_MerId%>'>
    <input type='hidden' name='p2_Order' value='<%=p2_Order%>'>
    <input type='hidden' name='p3_Amt' value='<%=p3_Amt%>'>
    <input type='hidden' name='p4_Cur' value='<%=p4_Cur%>'>
    <input type='hidden' name='p5_Pid' value='<%=p5_Pid%>'>
    <input type='hidden' name='p6_Pcat' value='<%=p6_Pcat%>'>
    <input type='hidden' name='p7_Pdesc' value='<%=p7_Pdesc%>'>
    <input type='hidden' name='p8_Url' value='<%=p8_Url%>'>
    <input type='hidden' name='p9_SAF' value='<%=p9_SAF%>'>
    <input type='hidden' name='pa_MP' value='<%=uid%>'>
    <input type='hidden' name='pd_FrpId' value='<%=pd_FrpId%>'>
    <input type="hidden" name="pr_NeedResponse" value="<%=pr_NeedResponse%>">
    <input type='hidden' name='hmac' value='<%=hmac%>'>
</form>
</body>
<%
} else {
%>
<body>用户名不存在</body>
<%
    }
} else {
%>
<body>用户名或者金额（最小10元，最大5万）错误</body>
<%
    }
%>
</html>