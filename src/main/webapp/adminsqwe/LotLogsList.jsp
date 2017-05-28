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
	<table class="mtb" cellspacing="0" style="width:95%;margin:auto"> 
		<caption>用户订单列表</caption>
		<tr>
			<td><form action="/admin/AdminUser!Logs.jzh" method="get">条件查询：用户名：<input style="width:100px" name="user" type="text" value="${user}"/> | 类型：<select name="type"><option value="-1">全部</option><option value="0">登录</option><option value="1">注册</option><option value="2">密码错误</option></select> | 
			分组：<select name="level"><option value="-1">全部</option><option value="0">前端</option><option value="1">后台</option></select> | 时间：<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="btime" value="${btime}"/> - <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="etime" value="${etime}"/> <input type="submit" value="搜索"/></form></td>
		</tr>
		<tr><td>便捷筛选：<a href="/admin/AdminUser!Logs.jzh?level=0">前端</a> | <a href="/admin/AdminUser!Logs.jzh?level=1">后台</a></td></tr>
		<tr>
			<td align="center"><div class="my_page" style="height:40px;">
                <div id="page_wrapper" class="page">${page}</div>
            </div></td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<thead>
					<tr class="tit">
						<td>用户名</td>
						<td>ip地址</td>
						<td>发生时间</td>
						<td>日志类型</td>
						<td>日志内容</td>
					<thead>
					<tbody>
<c:forEach var="d" items="${find}"><tr>
<td><a href="/admin/AdminUser!Logs.jzh?user=${d.user_name}">${d.user_name}</a></td>
<td>${d.logs_ip}</td>
<td>${d.logs_time}</td>
<td><c:choose>
<c:when test="${d.logs_type==0}">用户登陆</c:when>
<c:when test="${d.logs_type==1}">用户注册</c:when>
<c:when test="${d.logs_type==2}">密码错误</c:when>
<c:otherwise>--</c:otherwise>
</c:choose></td>
<td>${d.logs_desc}</td>
</tr></c:forEach></tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center"><div class="my_page" style="height:40px">
                <div id="page_wrapper" class="page">${page}</div>
            </div></td>
		</tr>
	</table>
</body>
<script type="text/javascript">
$(function(){
	$('select[name=type]').val(${type});
	$('select[name=level]').val(${level});
});
</script>
</html>