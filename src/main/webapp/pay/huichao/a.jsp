<%@page language="java" contentType="text/html;charset=utf-8" %>
<%@ page import="com.caipiao.entity.Bc_rech" %>
<%@page import="com.caipiao.entity.Bc_user" %>
<%@page import="com.caipiao.service.my.MyRechangeService" %>
<%@page import="com.caipiao.service.systeminit.UserStatic" %>
<%@page import="com.caipiao.utils.TryStatic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%! String formatString(String text) {
    if (text == null) {
        return "";
    }
    return text;
}
%>
<%
    //字符编码
    String CharacterEncoding = "UTF-8";
    request.setCharacterEncoding(CharacterEncoding);
    String key = formatString(request.getParameter("key"));
    String BillNo = formatString(request.getParameter("PayNO"));
    String Amount = formatString(request.getParameter("PayJe"));
    String PayMore = formatString(request.getParameter("PayMore"));

    Amount = Amount.replace(".00", "");
    int money = Integer.parseInt(Amount);

    Bc_user find = UserStatic.find(PayMore);
    int uid = find.getUser_id();
    MyRechangeService dao = new MyRechangeService();
    Bc_rech en = dao.find(BillNo);
    double rechmoney = TryStatic.StrToDouble(Amount);
    if (key.equals("99990000")) {

        double newgive = 0;
        //+++++++++++++++++++++++++++++++注释原有的充值规则
        //if(tk==0){
        //if(money>=5000){
        //newgive = money*0.05;
        //}else if(money>=100){
        //newgive = money*0.03;
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


        if (null != en) {
            out.print("false");
        } else {

            double xf = 1;
            int tk = 0;
            try {
                tk = dao.findUserDrawCount(uid);
            } finally {
            }


            boolean up = dao.Rech(find.getUser_name(), uid, BillNo, money, 0, 3, PayMore);
            Bc_rech enn = dao.find(BillNo);
            up = dao.updateRech(enn.getRech_id(), 1, newgive);
            if (up) {
                Bc_user uen = UserStatic.find(uid);
                double cm = money + newgive;
                UserStatic.AddMoney(uen, cm, 0, BillNo, 2, "支付宝", cm * xf);
                out.print("Success");
            }
        }
    }
%>
<!--
key=99990000&PayNO=20060830376049896860760055369769&PayJe=125.00&PayMore=admin
-->
