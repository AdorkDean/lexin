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
	<table class="mtb" cellspacing="0" style="width:80%;margin:auto"> 
		<caption>订单管理--编号：${buy.buy_item} | <a target="_blank" href="/lottery/BuyLot.jzh?spm=${buy.buy_item}">前台查看</a></caption>
		<tr>
			<td colspan="2">
				<table width="100%">
					<thead><tr><th>彩种</th><th>发起人</th><th>发起时间</th><th>方案金额</th><th>中奖金额</th><th>注数</th><c:if test="${buy.buy_ishm==1}"><th>佣金</th><th>剩余/保底</th></c:if><th>追号设置</th><th>是否合买</th><th>状态</th><th>操作</th></tr></thead>
					<tbody><tr>
<td><script>document.write(Show.LotName('${buy.buy_lot}'))</script></td>
<td>${buy.user_name}</td><td>${buy.buy_time}</td><td>￥${buy.buy_money}</td><td style="color:red">￥${buy.buy_winmoney}</td><td>${buy.buy_zhushu}</td><c:if test="${buy.buy_ishm==1}"><td style="color:#2C99FF">${buy.buy_take}%</td><td><font color="red">${buy.buy_have}</font>/<font color="#9F5CE1">${buy.buy_baodi}</font></td></c:if>
<td><c:choose><c:when test="${buy.buy_iscont==0}">中奖不追</c:when><c:when test="${buy.buy_iscont==1}">中奖继续</c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${buy.buy_ishm==0}">自购</c:when><c:when test="${buy.buy_ishm==1}">合买</c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><script>document.write(Show.BuyStatus('${buy.buy_status}'))</script></td>
<td><c:choose> 
<c:when test="${buy.buy_status==-1}"><a href="javascript:" name="isOk">验证满员（慎用）</a></c:when>
<c:when test="${buy.buy_status==0}"><a href="javascript:" name="allche">全部撤单</a></c:when>
<c:otherwise>--</c:otherwise>
</c:choose></td>
</tr>
</tbody>
				</table>
			</td>
		</tr>
		<tr><td style="width: 160px">认购内容：（<c:choose> <c:when test="${buy.buy_isopen==1}">参与可见</c:when><c:when test="${buy.buy_isopen==2}">截止可见</c:when><c:when test="${buy.buy_isopen==3}">永久保密</c:when><c:otherwise>公开</c:otherwise></c:choose>）</td>
			<td style="height:100px;overflow:auto"><script>document.write(Show.LotCode2('${buy.buy_code}'))</script></td></tr>
		<tr>
			<td colspan="2">
				<table width="100%">
					<caption>期号列表</caption>
					<thead><tr><th>期号</th><th>倍数</th><th>出票时间</th><th>金额</th><th>奖金</th><th>状态</th><th>开奖时间</th><th>开奖号码</th><th>操作</th></tr></thead>
					<tbody>
<c:forEach var="l" items="${blot}"><tr>
<td>${l.buylot_qihao}</td><td>${l.buylot_multiple} 倍</td><td>${l.buylot_outtime}</td><td>￥${l.buylot_money}</td><td style="color:red">￥${l.buylot_winmon}</td><td><script>document.write(Show.LotStatus('${l.buylot_status}'))</script></td><td>${l.buylot_opentime}</td><td style="color:red;">${l.buylot_haoma}</td>
<td tid="${l.buylot_id}"><c:choose> 
<c:when test="${l.buylot_status==0}"><a href="javascript:" name="oneche">撤单</a> | <a href="javascript:" name="chup">出票</a></c:when>
<c:when test="${l.buylot_status==1}"><a href="javascript:" name="oneche">撤单</a> | <a href="javascript:" name="kaij">开奖</a></c:when>
<c:otherwise>--</c:otherwise>
</c:choose></td></tr>
</c:forEach>
                    </tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table width="80%">
					<caption>认购列表</caption>
					<thead><tr><th>用户名</th><th>认购金额</th><th>购买时间</th><th>奖金</th></tr></thead>
					<tbody>
<c:forEach var="f" items="${buser}">
                        <tr>
                        	<td>${f.user_name}</td>
                            <td>￥${f.buyuser_money}</td>
                            <td>${f.buyuser_time}</td>
                            <td>￥<span class="tor">${f.buyuser_win}</span></td>
                        </tr>
</c:forEach>
                    </tbody>
				</table>
			</td>
		</tr>
	</table> 
</body>
<script type="text/javascript">
$(function(){
	var ids ='${buy.buy_id}';
	$('a[name=allche]').click(function(){
		if (confirm("是否整个认购撤单?")) {
			$.post('/admin/AdminLot!PeopleDo.jzh',{ids:ids,mt:'allche',t:new Date()},function(data){
				if (data=='0'){
					alert('撤单成功！');location.reload();
				}else if(data=='err'){
					alert('参数错误！请检查或提交给管理员。');
				}else if(data=='nologin'){
					alert("您长时间未操作，请重新登录！");location.href='/adminsqwe';
				}else{
					alert('撤单失败！');
				}
			});
		}
	});
	$('a[name=oneche]').click(function(){
		var id = $(this).parent().attr('tid');
		if (confirm("是否撤单本期?")) {
			$.post('/admin/AdminLot!PeopleDo.jzh',{ids:id,mt:'oneche',t:new Date()},function(data){
				if (data=='0'){
					alert('撤单成功！');location.reload();
				}else if(data=='1'){
					alert('撤单失败！');
				}else if(data=='err'){
					alert('参数错误！请检查或提交给管理员。');
				}else if(data=='nologin'){
					alert("您长时间未操作，请重新登录！");location.href='/adminsqwe';
				}else{
					alert('撤单失败！');
				}
			});
		}
	});
	$('a[name=kaij]').click(function(){
		var id = $(this).parent().attr('tid');
		if (confirm("是否单期开奖?")) {
			$.post('/admin/AdminLot!PeopleDo.jzh',{ids:id,mt:'kaij',t:new Date()},function(data){
				if (data=='0'){
					alert('开奖成功！');location.reload();
				}else if(data=='1'){
					alert('开奖失败，订单不是等待开奖状态，或期号无开奖号码。');
				}else if(data=='err'){
					alert('参数错误！请检查或提交给管理员。');
				}else if(data=='nologin'){
					alert("您长时间未操作，请重新登录！");location.href='/adminsqwe';
				}else{
					alert('开奖失败！');
				}
			});
		}
	});
	$('a[name=chup]').click(function(){
		var id = $(this).parent().attr('tid');
		if (confirm("是否单期出票?")) {
			$.post('/admin/AdminLot!PeopleDo.jzh',{ids:id,mt:'chup',t:new Date()},function(data){
				if (data=='0'){
					alert('出票成功！');location.reload();
				}else if(data=='1'){
					alert('出票失败，订单可能不是等待出票状态。');
				}else if(data=='err'){
					alert('参数错误！请检查或提交给管理员。');
				}else if(data=='nologin'){
					alert("您长时间未操作，请重新登录！");location.href='/adminsqwe';
				}else{
					alert('出票失败！');
				}
			});
		}
	});
	$('a[name=isOk]').click(function(){
		if (confirm("是否验证合买是否完成?")) {
			$.post('/admin/AdminLot!PeopleDo.jzh',{ids:ids,mt:'isOk',t:new Date()},function(data){
				if (data=='0'){
					alert('验证成功！');location.reload();
				}else if(data=='1'){
					alert('验证失败！');
				}else if(data=='err'){
					alert('参数错误！请检查或提交给管理员。');
				}else if(data=='nologin'){
					alert("您长时间未操作，请重新登录！");location.href='/adminsqwe';
				}else{
					alert('验证失败！');
				}
			});
		}
	});
});
</script>
</html>