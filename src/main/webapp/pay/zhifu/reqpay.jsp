<%@page language="java" contentType="text/html;charset=utf-8" %>
<%@page import="com.caipiao.entity.Bc_user" %>
<%@page import="com.caipiao.service.my.MyRechangeService" %>
<%@page import="com.caipiao.service.systeminit.UserStatic" %>
<%@page import="com.caipiao.utils.StaticItem" %>
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
    String p2_Order = StaticItem.GetRechitem();// 商户订单号
    String p3_Amt = formatString(request.getParameter("p3_Amt"));// 支付金额
    String pa_MP = formatString(request.getParameter("pa_MP"));// 商户扩展信息 即用户ID
    // 获得MD5-HMAC签名
    double money = TryStatic.StrToInt(p3_Amt);

    if (money >= 10 && money <= 50000 && null != pa_MP) {
        Bc_user find = UserStatic.find(pa_MP);
        if (null != find) {
            int uid = find.getUser_id();

            MyRechangeService dao = new MyRechangeService();
            String desc = "智付支付";
            dao.Rech(pa_MP, uid, p2_Order, money, 0, 2, desc);
%>
<body onLoad="javascript:document.zhifupay.submit()">
<form name="zhifupay" action='http://pay.kanchuo.top/pay/B2CPay.jsp' method='POST'>
    <input type='hidden' name='p2_Order' value='<%=p2_Order%>'>
    <input type='hidden' name='p3_Amt' value='<%=p3_Amt%>'>
    <input type='hidden' name='pa_MP' value='<%=uid%>'>
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