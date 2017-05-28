<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.caipiao.utils.TimeUtil"%>
<%@page import="com.sysbcjzh.utils.IPUtils"%>
<%@ include file="checkadmin.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
</HEAD>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  <TR height=28><TD background=images/title_bg1.jpg>当前位置: </TD></TR>
  <TR><TD bgColor=#b1ceef height=1></TD></TR>
  <TR height=20><TD background=images/shadow_bg.jpg></TD></TR>
</TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=0>
  <TR height=100>
    <TD align=middle width=100><IMG height=100 src="images/admin_p.gif" width=90></TD>
    <TD width=60>&nbsp;</TD>
    <TD>
      <TABLE height=100 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TR><TD>当前时间：<%=TimeUtil.getToday(TimeUtil.L_Y)%></TD></TR>
        <TR><TD style="FONT-WEIGHT: bold; FONT-SIZE: 16px"><%=request.getSession().getAttribute(UserSession.adminuser)%></TD></TR>
        <TR><TD>欢迎进入后台管理中心！</TD></TR></TABLE></TD></TR>
  <TR><TD colSpan=3 height=10></TD></TR></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
  <TR height=20><TD></TD></TR>
  <TR height=22><TD style="PADDING-LEFT: 20px; FONT-WEIGHT: bold; COLOR: #ffffff" align=middle background=images/title_bg2.jpg>相关信息</TD></TR>
  <TR bgColor=#ecf4fc height=12><TD></TD></TR>
  <TR height=20><TD></TD></TR></TABLE>
<TABLE cellSpacing=0 cellPadding=2 width="95%" align=center border=0>
<TR>
    <TD align=right width=100>小赌：</TD>
    <TD style="COLOR: #880000">怡情</TD></TR>
<TR>
    <TD align=right>大赌：</TD>
    <TD style="COLOR: #880000">伤身</TD></TR>
<TR>
    <TD align=right>常赌：</TD>
    <TD style="COLOR: #880000">灰飞烟灭</TD></TR>
<TR>
    <TD align=right>身份过期：</TD>
    <TD style="COLOR: #880000">30 分钟</TD></TR>
<TR>
    <TD align=right>模板来源：</TD>
    <TD style="COLOR: #880000">网络</TD></TR>
  <TR>
    <TD align=right>Auther：</TD>
    <TD style="COLOR: #880000"><a href="">bcjzh</a></TD></TR>
  <TR>
    <TD align=right>开发者QQ：</TD>
    <TD style="COLOR: #880000">123456</TD></TR>
</TABLE></BODY></HTML>