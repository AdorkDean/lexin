<%@page language="java" contentType="text/html;charset=utf-8" %>
<%@page import="com.caipiao.entity.Bc_rech" %>
<%@page import="com.caipiao.entity.Bc_user" %>
<%@page import="com.caipiao.pay.yeepay.PaymentForOnlineService" %>
<%@page import="com.caipiao.service.my.MyRechangeService" %>
<%@page import="com.caipiao.service.systeminit.UserStatic" %>
<%@page import="com.caipiao.utils.TryStatic" %>
<%! String formatString(String text) {
    if (text == null) {
        return "";
    }
    return text;
}
%>
<%
    out.println("支付金额" + request.getParameter("r3_Amt"));
    String keyValue = formatString("33B4DB3244BB5C2875D98CB1B79B68DA");   // 商家密钥
    String r0_Cmd = formatString(request.getParameter("r0_Cmd")); // 业务类型
    String p1_MerId = formatString("10000");   // 商户编号
    String r1_Code = formatString(request.getParameter("r1_Code"));// 支付结果
    String r2_TrxId = formatString(request.getParameter("r2_TrxId"));// 易宝支付交易流水号
    String r3_Amt = formatString(request.getParameter("r3_Amt"));// 支付金额
    String r4_Cur = formatString(request.getParameter("r4_Cur"));// 交易币种
    String r5_Pid = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "utf-8");// 商品名称
    String r6_Order = formatString(request.getParameter("r6_Order"));// 商户订单号
    String r7_Uid = formatString(request.getParameter("r7_Uid"));// 易宝支付会员ID
    String r8_MP = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"), "utf-8");// 商户扩展信息
    String r9_BType = formatString(request.getParameter("r9_BType"));// 交易结果返回类型
    String hmac = formatString(request.getParameter("hmac"));// 签名数据
    boolean isOK = false;
    // 校验返回数据包
    isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code,
            r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
    out.println(isOK);
    if (isOK) {
        MyRechangeService dao = new MyRechangeService();
        Bc_rech en = dao.find(r6_Order);
        int uid = TryStatic.StrToInt(r8_MP);
        double rechmoney = TryStatic.StrToDouble(r3_Amt);
        if (null != en && en.getUser_id() == uid) {
            int sta = en.getRech_status();
            if (0 == sta) {
                double money = en.getRech_money();
                if (rechmoney == money) {
                    double newgive = 0;
                    //int rechcount = dao.findUserRechCount(en.getUser_id());//首次充值
                    int rechcount = dao.findUserDrawCount(en.getUser_id());//今日提款记录
                    //+++++++++++++++++++++++++++++++注释原有的充值规则
                    //if(rechcount==0){//首次充值赠送
                    //if(money>=5000){
                    //	newgive = money*0.05;
                    //}else if(money>=100){
                    //	newgive = money*0.03;
                    //}
                    //newgive = newgive>=5000?5000:newgive;//赠送金额最高5000
                    //}
                    //+++++++++++++++++++++++++++++++注释原有的充值规则

                    //+++++++++++++++++++++++++++++++添加新的充值规则
                    if (money >= 100) {
                        newgive = money * 0.1;
                    }
                    newgive = newgive >= 6000 ? 6000 : newgive;//赠送金额最高6000
                    //+++++++++++++++++++++++++++++++添加新的充值规则

                    boolean up = dao.updateRech(en.getRech_id(), 1, newgive);
                    if (up) {
                        Bc_user uen = UserStatic.find(en.getUser_id());
                        UserStatic.AddMoney(uen, money + newgive, 0, r6_Order, 2, "支付宝充值", (money + newgive) * 1);
                    }
                }
            }
        }
        //在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
        if (r1_Code.equals("1")) {
            // 产品通用接口支付成功返回-浏览器重定向
            if (r9_BType.equals("1")) {
                out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
                // 产品通用接口支付成功返回-服务器点对点通讯
            } else if (r9_BType.equals("2")) {
                // 如果在发起交易请求时	设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
                out.println("SUCCESS");
                // 产品通用接口支付成功返回-电话支付返回
            }
            // 下面页面输出是测试时观察结果使用
            out.println("<br>交易成功!<br>商家订单号:" + r6_Order + "<br>支付金额:" + r3_Amt + "<br>交易流水号:" + r2_TrxId);
        }
    } else {
        out.println("交易签名被篡改!");
    }
%>