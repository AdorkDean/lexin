<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/adminsqwe/css/admin.css">
<script language="javascript" type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<table class="mtb" cellspacing="0" style="width:95%;margin:auto"> 
		<caption>用户列表管理</caption>
		<tr>
			<td><form action="/admin/AdminUser!Comm.jzh" method="get">条件查询：用户名：<input style="width:100px" name="user" type="text" value="${user}"/> | <input type="submit" value="搜索"/></form></td>
		</tr>
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
						<td>双色球</td>
						<td>大乐透</td>
						<td>排列5</td>
						<td>福彩3D</td>
						<td>排列3</td>
						<td>重庆时时彩</td>
						<td>江西时时彩</td>
						<td>山东11选5</td>
						<td>江西11选5</td>
						<td>广东11选5</td>
						<td>重庆11选5</td>
						<td>操作</td>
					<thead>
					<tbody>
<c:forEach var="d" items="${find}">
<tr>
<td>${d.user_name}</td>
<td><c:choose><c:when test="${d.ssq>0}"><font color="red">${d.ssq}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.dlt>0}"><font color="red">${d.dlt}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.pl5>0}"><font color="red">${d.pl5}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.fc3d>0}"><font color="red">${d.fc3d}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.pl3>0}"><font color="red">${d.pl3}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.cqssc>0}"><font color="red">${d.cqssc}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.jxssc>0}"><font color="red">${d.jxssc}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.sd11x5>0}"><font color="red">${d.sd11x5}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.jx11x5>0}"><font color="red">${d.jx11x5}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.gd11x5>0}"><font color="red">${d.gd11x5}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.cq11x5>0}"><font color="red">${d.cq11x5}</font></c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><a href="/admin/AdminUser!UserEdit.jzh?uid=${d.user_id}">设置</a></td>
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
</html>