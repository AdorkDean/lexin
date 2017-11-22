<%@page import="com.caipiao.entity.Bc_rech" %>
<%@page import="com.caipiao.entity.Bc_user" %>
<%@page import="com.caipiao.pay.alipay.AlipayNotify" %>
<%@page import="com.caipiao.service.my.MyRechangeService" %>
<%@page import="com.caipiao.service.systeminit.UserStatic" %>
<%@page import="com.caipiao.utils.TryStatic" %>
<%
    /* *
     功能：支付宝服务器异步通知页面
     版本：3.3
     日期：2012-08-17
     说明：
     以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
     该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

     //***********页面功能说明***********
     创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
     该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
     该页面调试工具请使用写文本函数logResult，该函数在com.alipay.util文件夹的AlipayNotify.java类文件中
     如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
     //********************************
     * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%
    String partner = "2088711663427403";//pid
    String key = "s1athsp0ka2uuavkshpq07f58z301wyu";//key
    //获取支付宝POST过来反馈信息
    Map<String, String> params = new HashMap<String, String>();
    Map requestParams = request.getParameterMap();
    double rechmoney = 0;
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
        String name = (String) iter.next();
        String[] values = (String[]) requestParams.get(name);
        String valueStr = "";
        for (int i = 0; i < values.length; i++) {
            valueStr = (i == values.length - 1) ? valueStr + values[i]
                    : valueStr + values[i] + ",";
        }
        //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
        //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
        if (name.equals("notify_id")) {
            valueStr = URLEncoder.encode(valueStr);
        } else {
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
        }
        params.put(name, valueStr);
        if ("price".equals(name)) {
            rechmoney = TryStatic.StrToDouble(valueStr);
        }
    }

    //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    //商户订单号

    String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

    //支付宝交易号

    String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

    //交易状态
    String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

    //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
    System.out.println("收到支付宝一步通知 订单号" + out_trade_no + " 状态：" + AlipayNotify.verify(params));
    if (AlipayNotify.verify(params)) {//验证成功
        //////////////////////////////////////////////////////////////////////////////////////////
        //请在这里加上商户的业务逻辑程序代码
        MyRechangeService dao = new MyRechangeService();
        Bc_rech en = dao.find(out_trade_no);
        if (null != en) {
            int sta = en.getRech_status();
            if (0 == sta) {
                double money = en.getRech_money();
                if (rechmoney == money) {
                    double newgive = 0;
                    int tk = 0;
                    try {
                        tk = dao.findUserDrawCount(en.getUser_id());
                    } finally {
                    }
                    //+++++++++++++++++++++++++++++++注释原有的充值规则
                    //if(tk==0){
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
                        UserStatic.AddMoney(uen, money + newgive, 0, out_trade_no, 2, "支付宝充值", (money + newgive) * 1);
                    }
                }
            }
        }
        out.println("success");    //请不要修改或删除

        //////////////////////////////////////////////////////////////////////////////////////////
    } else {//验证失败
        out.println("fail");
    }
%>
