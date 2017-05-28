<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/adminsqwe/css/admin.css">
<script language="javascript" type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<table class="mtb" cellspacing="0" style="width:90%;margin:auto"> 
		<caption>充值查询</caption> 
		<tr><td><form action="/admin/AdminCaiwu.jzh" method="get">用户名：<input name="user" id="user" style="width:100px" value="${user}" type="text"/> 
		| 开始时间： <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="btime" id="btime" value="${btime}" style="width:100px"/>-结束时间： <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="etime" id="etime" value="${etime}" style="width:100px"/>
		 | 充值方式：<select name="type" id="type"><option value="-1">全部</option><option value="0">手工充值</option><option value="1">网银在线</option><option value="2">易宝支付</option><option value="3">支付宝</option><option value="5">汇潮</option></select>
		 | 充值状态：<select name="status" id="status"><option value="-1">全部</option><option value="0">等待支付</option><option value="1">已支付</option></select>
		 | 用户类型：<select name="ut" id="ut"><option value="-1">全部</option><option value="0">普通</option><option value="1">代理</option><option value="2">测试</option><option value="7">客服</option><option value="8">财务</option><option value="9">超级</option></select>
		 | <input type="submit" value="查询"/></form></td></tr>
		<tr>
			<td>便捷筛选：<a href="/admin/AdminCaiwu.jzh?ut=-1">全部</a> | <a href="/admin/AdminCaiwu.jzh?ut=0">普通</a><div style="float:right;margin-right:10px;"><a href="/admin/AdminCaiwu!RechDown.jzh?user=${user}&btime=${btime}&etime=${etime}&type=${type}&status=${status}&ut=${ut}">下载查询结果</a></div></td>
		</tr>
		<c:if test="${null!=page}">
		<tr>
			<td align="center"><div class="my_page">
                <div id="page_wrapper" class="page">${page}</div>
            </div></td>
		</tr>
		</c:if>
		<tr>
			<td>
		<table width="100%">
			<thead><tr class="tit">
				<td>充值编号</td>
				<td>充值时间</td>
				<td>充值用户</td>
				<td>充值金额</td>
				<td>赠送金额</td>
				<td>充值方式</td>
				<td>状态</td>
				<td>处理时间</td>
				<td>操作员</td>
				<td>描述</td>
				<td>操作</td>
			</tr><thead>
			<tbody>
<c:choose>
<c:when test="${null!=list}">
<c:forEach var="c" items="${list}">
	<tr><td>${c.rech_item}</td><td>${c.rech_time}</td>
	<td><a href="/admin/AdminCaiwu.jzh?user=${c.user_name}">${c.user_name}</a>
	<c:choose>
		<c:when test="${c.user_type == 0}">（普）</c:when>
		<c:when test="${c.user_type == 1}">（<font color="red">代</font>）</c:when>
		<c:when test="${c.user_type == 2}">（<font color="red">测</font>）</c:when>
		<c:when test="${c.user_type == 7}">（<font color="red">客</font>）</c:when>
		<c:when test="${c.user_type == 8}">（<font color="red">财</font>）</c:when>
		<c:when test="${c.user_type == 9}">（<font color="red">超</font>）</c:when>
		<c:otherwise>--</c:otherwise>
	</c:choose><c:if test="${null!=c.user_upname}"><br><font color="red">上级：${c.user_upname}</font></c:if></td>
	<td>${c.rech_money}</td><td>${c.rech_give}</td>
	<td><c:choose>
		<c:when test="${c.rech_type == 0}">手工充值</c:when>
		<c:when test="${c.rech_type == 1}">网银在线</c:when>
		<c:when test="${c.rech_type == 2}">易宝支付</c:when>
		<c:when test="${c.rech_type == 3}">支付宝</c:when>
		<c:when test="${c.rech_type == 5}">汇潮支付</c:when>
		<c:otherwise>--</c:otherwise>
	</c:choose></td>
	<td><c:choose>
		<c:when test="${c.rech_status == 0}">等待支付</c:when>
		<c:when test="${c.rech_status == 1}"><font color="red">已支付</font></c:when>
		<c:otherwise>--</c:otherwise>
	</c:choose></td>
	<td>${c.rech_dotime}</td>
	<td>${c.rech_douser}</td>
	<td>${c.rech_desc}</td><td><a>详情</a></td></tr>
</c:forEach>
</c:when>
<c:otherwise>
	<tr><td colspan="10">没有您查询的充值记录</td></tr>
</c:otherwise>
</c:choose>
</tbody>
				</table></td>
		</tr>
		<c:if test="${null!=page}">
		<tr>
			<td align="center"><div class="my_page">
                <div id="page_wrapper" class="page">${page}</div>
            </div></td>
		</tr>
		</c:if>
	</table> 
</body>
<script type="text/javascript">
$(function(){
	$("#type").val(${type});
	$("#status").val(${status});
	$("#ut").val(${ut});
});
</script>
</html>