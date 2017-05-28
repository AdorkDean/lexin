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
		<caption>用户订单管理</caption>
		<tr>
			<td><form action="/admin/AdminLot!UserBuy.jzh" method="get">条件查询：用户名：<input style="width:100px" name="user" type="text" value="${user}"/> | 彩种：<select name="lot"><option value="">全部</option><option value="Ssq">双色球</option><option value="Dlt">大乐透</option><option value="Pl5">排列5</option><option value="Fc3d">福彩3D</option><option value="Pl3">排列3</option><option value="Cqssc">重庆时时彩</option><option value="Jxssc">江西时时彩</option><option value="Hnssc">河内五分彩</option><option value="Ynssc">印尼五分彩</option>
			<option value="Sd11x5">山东11选5</option><option value="Jx11x5">江西11选5</option><option value="Gd11x5">广东11选5</option><option value="Cq11x5">重庆11选5</option></select> 
			| 状态：<select name="status"><option value="-2">全部</option><option value="-1">未满员</option><option value="0">进行中</option><option value="1">已中奖</option><option value="2">未中奖</option><option value="3">已撤单</option></select> | 是否合买：<select name="ishm"><option value="-1">全部</option><option value="0">自购</option><option value="1">合买</option></select> 
			| 时间：<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="btime" value="${btime}"/> - <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="etime" value="${etime}"/> <input type="submit" value="搜索"/></form></td>
		</tr>
		<tr><td>便捷筛选：<a href="/admin/AdminLot!UserBuy.jzh">全部</a> | <a href="/admin/AdminLot!UserBuy.jzh?ishm=0">自购</a> | <a href="/admin/AdminLot!UserBuy.jzh?ishm=1">合买</a></td></tr>
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
						<td>订单号</td>
						<td>彩种</td>
						<td>首期期号</td>
						<td>发起人</td>
						<td>方案金额</td>
						<td>认购时间</td>
						<td>用户认购</td>
						<td>用户奖金</td>
						<td>状态</td>
						<td>操作</td></tr>
					<thead>
					<tbody>
<c:forEach var="d" items="${find}">
<tr>
<td><a target="_blank" href="/admin/AdminLot!Item.jzh?t=${d.buy_item}">${d.buy_item}</a></td>
<td>${d.buy_lot}</td>
<td>${d.buy_fqihao}</td>
<td><a href="/admin/AdminLot!UserBuy.jzh?user=${d.user_name}">${d.user_name}</a></td>
<td>${d.buy_money}</td>
<td>${d.buyuser_time}</td>
<td>${d.buyuser_money}</td>
<td>${d.buyuser_win}</td>
<td>${d.buy_status}</td>
<td><a target="_blank" href="/admin/AdminLot!Item.jzh?t=${d.buy_item}">查看</a></td>
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
	$('select[name=ishm]').val(${ishm});
	$('select[name=status]').val(${status});
	$('select[name=lot]').val('${lot}');
});
</script>
</html>