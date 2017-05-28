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
		<caption>用户列表管理</caption>
		<tr>
			<td><form action="/admin/AdminUser.jzh" method="get">条件查询：用户名：<input style="width:100px" name="u" type="text" value="${user}"/> | 余额大于：<input style="width:80px" name="m" type="text" value="${m}"/> | 状态：<select name="s"><option value="-1">全部</option><option value="0">正常</option><option value="1">停用</option></select> | 
			类型：<select name="t"><option value="-1">全部</option><option value="0">普通</option><option value="2">测试</option><option value="1">代理</option><option value="7">客服</option><option value="8">财务</option><option value="9">超级</option></select> | 时间：<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="bt" value="${bt}"/> - <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="et" value="${et}"/> <input type="submit" value="搜索"/></form></td>
		</tr>
		<tr><td>便捷筛选：<a href="/admin/AdminUser.jzh">全部</a> | <a href="/admin/AdminUser.jzh?t=0">普通</a> | <a href="/admin/AdminUser.jzh?t=2">测试</a> | <a href="/admin/AdminUser.jzh?t=1">代理</a> | <a href="/admin/AdminUser.jzh?t=7">客服</a> | <a href="/admin/AdminUser.jzh?t=8">财务</a> | <a href="/admin/AdminUser.jzh?t=9">超级</a></td></tr>
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
						<td>定制编号</td>
						<td>用户名</td>
						<td>定制时间</td>
						<td>彩种</td>
						<td>定制金额</td>
						<td>定制次数</td>
						<td>对象用户</td>
						<td>状态</td>
						<td>操作</td>
					<thead>
					<tbody>
<c:forEach var="d" items="${find}">
<tr>
<td>${d.auto_item}</td>
<td>${d.user_name}</td>
<td>${d.auto_time}</td>
<td>${d.auto_lot}</td>
<td>${d.auto_money}</td>
<td>${d.auto_count}</td>
<td>${d.auto_name}</td>
<td>${d.auto_status}</td>
<td><a href="">查看</a></td>
</tr>
</c:forEach></tbody>
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
	$('select[name=t]').val(${t});
	$('select[name=s]').val(${s});
});
</script>
</html>