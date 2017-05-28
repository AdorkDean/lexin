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
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<table class="mtb" cellspacing="0" style="width:95%;margin:auto"> 
		<caption>内容列表管理</caption>
		<tr>
			<td><form action="/admin/AdminNews.jzh" method="get">条件查询：作者：<input style="width:100px" name="auth" type="text" value="${auth}"/> | 标题：<input style="width:80px" name="title" type="text" value="${title}"/> | 来源：<input style="width:80px" name="soures" type="text" value="${soures}"/> | 状态：<select name="status"><option value="-1">全部</option><option value="0">正常</option><option value="1">关闭</option></select> | 
			类型：<select name="type"><option value="-1">全部</option><option value="0">公告</option><option value="1">新闻</option><option value="2">攻略</option>
			<option value="20">重庆时时彩（高频）</option><option value="21">江西时时彩（高频）</option><option value="22">山东11选5（高频）</option><option value="23">江西11选5（高频）</option><option value="24">广东11选5（高频）</option><option value="25">重庆11选5（高频）</option><option value="26">河内五分彩（高频）</option>
			<option value="50">双色球（数字）</option><option value="51">福彩3D（数字）</option><option value="52">大乐透（数字）</option><option value="53">排列三（数字）</option><option value="54">排列五（数字）</option></select> | 时间：<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="btime" value="${btime}"/> - <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="etime" value="${etime}"/> <input type="submit" value="搜索"/></form></td>
		</tr>
		<tr><td>便捷筛选：<a href="/admin/AdminNews.jzh">全部</a> | <a href="/admin/AdminNews.jzh?type=0">公告</a> | <a href="/admin/AdminNews.jzh?type=1">新闻</a> | <a href="/admin/AdminNews.jzh?type=2">攻略</a></td></tr>
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
						<td>作者</td>
						<td>标题</td>
						<td>发布时间</td>
						<td>修改时间</td>
						<td>来源</td>
						<td>状态</td>
						<td>点击率</td>
						<td>类型</td>
						<td>操作${find}</td>
					<thead>
					<tbody>
<c:forEach var="d" items="${list}">
<tr>
<td>${d.news_auther}</td>
<td>${d.news_title}</td>
<td>${d.news_time}</td>
<td>${d.news_etime}</td>
<td>${d.news_soures}</td>
<td><c:choose><c:when test="${d.news_status==1}"><span style="color:red">停用</span></c:when><c:otherwise>正常</c:otherwise></c:choose></td>
<td>${d.news_point}</td>
<td><script>document.write(Show.NewsType('${d.news_type}'))</script></td>
<td><a href="/admin/AdminNews!Edit.jzh?id=${d.news_id}">编辑</a></td>
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
	$('select[name=type]').val(${type});
	$('select[name=status]').val(${status});
});
</script>
</html>