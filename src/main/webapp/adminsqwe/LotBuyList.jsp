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
<script language="javascript" type="text/javascript" src="/js/local.js"></script>
</head>
<body>
	<table class="mtb" cellspacing="0" style="width:95%;margin:auto"> 
		<caption>用户订单列表</caption>
		<tr>
			<td><form action="/admin/AdminLot.jzh" method="get">发起人：<input style="width:100px" name="name" type="text" value="${name}"/> | 订单号：<input style="width:100px" name="item" type="text"/> | 彩种：<select name="lot"><option value="">所有</option><option value="Ssq">双色球</option><option value="Dlt">大乐透</option><option value="Pl5">排列5</option><option value="Fc3d">福彩3D</option><option value="Pl3">排列3</option><option value="Cqssc">重庆时时彩</option><option value="Jxssc">江西时时彩</option><option value="Hnssc">河内五分彩</option><option value="Ynssc">印尼五分彩</option>
			<option value="Sd11x5">山东11选5</option><option value="Jx11x5">江西11选5</option><option value="Gd11x5">广东11选5</option><option value="Cq11x5">重庆11选5</option></select> 
			| 首期期号：<input style="width:100px" name="fqh" type="text" value="${fqh}"/> 
			<br><br>时间：<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="btime" value="${btime}"/> - <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="etime" value="${etime}"/> 
			| 订单状态：<select name="status"><option value="-2">全部</option><option value="-1">未满员</option><option value="0">进行中</option><option value="1">已中奖</option><option value="2">未中奖</option><option value="3">已撤单</option></select> | 订单类型：<select name="ishm"><option value="-1">全部</option><option value="0">自购</option><option value="1">合买</option></select> | <input type="submit" value="搜索"/></form></td>
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
						<td>发起人</td>
						<td>发起时间</td>
						<td>订单编号</td>
						<td>彩种-首期期号</td>
						<td>总金额</td>
						<td>保底金额</td>
						<td>剩余金额</td>
						<td>中奖金额</td>
						<td>状态</td>
						<td>类型</td>
						<td>佣金</td>
						<td>操作</td></tr>
					<thead>
					<tbody>
<c:forEach var="d" items="${find}"><tr>
<td><a target="_blank" href="/admin/AdminUser!Desc.jzh?i=${d.user_id}">${d.user_name}</a></td>
<td>${d.buy_time}</td>
<td><a target="_blank" href="/lottery/BuyLot.jzh?spm=${d.buy_item}">${d.buy_item}</a></td>
<td><script>document.write(Show.LotName('${d.buy_lot}'))</script>-${d.buy_fqihao}</td>
<td>￥<font color="red">${d.buy_money}</font></td>
<td><c:choose><c:when test="${d.buy_baodi>0}">￥${d.buy_baodi}</c:when>
	<c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.buy_have>0}">￥${d.buy_have}</c:when>
	<c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${d.buy_winmoney>0}">￥<font color="red">${d.buy_winmoney}</font></c:when>
	<c:otherwise>--</c:otherwise></c:choose></td>
<td><script>document.write(Show.BuyStatus('${d.buy_status}'))</script></td>
<td><c:choose><c:when test="${d.buy_ishm==0}">自购</c:when><c:when test="${d.buy_ishm==1}">合买</c:when>
	<c:otherwise>--</c:otherwise></c:choose></td>
<td>${d.buy_take}%</td>
<td><a href="/lottery/BuyLot.jzh?spm=${d.buy_item}" target="_blank">前端查看</a> | <a href="/admin/AdminLot!Item.jzh?i=${d.buy_id}" target="_blank">详细</a></td>
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
	$('select[name=ishm]').val(${ishm});
	$('select[name=lot]').val('${lot}');
});
</script>
</html>