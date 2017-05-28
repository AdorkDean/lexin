<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/adminsqwe/css/admin.css">
<script language="javascript" type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="/js/local.js"></script>
</head>
<body>
	<table class="mtb" cellspacing="0" style="width:95%;margin:auto"> 
		<caption>彩种销售状态</caption>
		<tr>
			<td>
				<table width="100%">
					<thead>
					<tr class="tit">
						<td>编号</td>
						<td>彩种</td>
						<td>状态</td>
						<td>操作</td>
					<thead>
					<tbody>
<c:forEach var="d" items="${find}"><tr>
<td>${d.lotsale_id}</td>
<td><script>document.write(Show.LotName('${d.lotsale_name}'))</script></td>
<td><c:choose>
<c:when test="${d.lotsale_status==0}"><font color="green">销售中</font></c:when>
<c:when test="${d.lotsale_status==1}"><font color="red">停售中</font></c:when>
<c:otherwise>--</c:otherwise>
</c:choose></td>
<td><c:choose>
<c:when test="${d.lotsale_status==0}"><a href="/admin/AdminNews!LotStatus.jzh?status=1&lot=${d.lotsale_name}">停售</a></c:when>
<c:when test="${d.lotsale_status==1}"><a href="/admin/AdminNews!LotStatus.jzh?status=0&lot=${d.lotsale_name}">开售</a></c:when>
<c:otherwise>--</c:otherwise>
</c:choose></td>
</tr></c:forEach></tbody>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>