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
		<caption>订单列表</caption>
		<tr>
			<td><form action="/admin/AdminLot!BuyLot.jzh" method="get">订单编号：<input style="width:100px" name="item" type="text" value="${item}"/> | 期号：<input style="width:80px" name="qihao" type="text" value="${qihao}"/> | 彩种：<select name="lot"><option value="">全部</option><option value="Ssq">双色球</option><option value="Dlt">大乐透</option><option value="Pl5">排列5</option><option value="Fc3d">福彩3D</option><option value="Pl3">排列3</option>
			<option value="Cqssc">重庆时时彩</option><option value="Jxssc">江西时时彩</option><option value="Hnssc">河内五分彩</option><option value="Ynssc">印尼五分彩</option><option value="Sd11x5">山东11选5</option><option value="Jx11x5">江西11选5</option><option value="Gd11x5">广东11选5</option><option value="Cq11x5">重庆11选5</option></select> | 
			期号状态：<select name="status"><option value="-2">全部</option><option value="-1">等待满员</option><option value="0">等待出票</option><option value="1">等待开奖</option><option value="2">已中奖</option><option value="3">未中奖</option><option value="4">已撤单</option></select> | <input type="submit" value="搜索"/></form></td>
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
						<td>订单编号</td>
						<td>彩种+期号</td>
						<td>出票时间</td>
						<td>开奖时间</td>
						<td>开奖号码</td>
						<td>倍数</td>
						<td>金额</td>
						<td>中奖金额</td>
						<td>状态</td>
						<td>操作</td></tr>
					<thead>
					<tbody>
<c:forEach var="d" items="${find}"><tr>
<td><a href="/admin/AdminLot!Item.jzh?t=${d.buy_item}" target="_blank">${d.buy_item}</a></td>
<td>${d.buylot_lot}-${d.buylot_qihao}</td>
<td>${d.buylot_outtime}</td>
<td>${d.buylot_opentime}</td>
<td>${d.buylot_haoma}</td>
<td>${d.buylot_multiple}</td>
<td>${d.buylot_money}</td>
<td>${d.buylot_winmon}</td>
<td><script>document.write(Show.LotStatus('${d.buylot_status}'))</script></td>
<td><c:choose><c:when test="${d.buylot_status==-1||d.buylot_status==0||d.buylot_status==1}"><a href="/admin/AdminLot!Item.jzh?t=${d.buy_item}" target="_blank">编辑</a></c:when><c:otherwise>--</c:otherwise></c:choose></td>
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
	$('select[name=status]').val(${status});
	$('select[name=lot]').val('${lot}');
});
</script>
</html>