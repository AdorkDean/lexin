<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<caption>期号（录入号码&出票&撤单&开奖）</caption>
		<tr>
			<td><form action="/admin/AdminLot!LotList.jzh" method="get">彩种:<select name="lot"><option value="">全部</option><option value="Ssq">双色球</option><option value="Dlt">大乐透</option><option value="Pl5">排列5</option><option value="Fc3d">福彩3D</option><option value="Pl3">排列3</option><option value="Cqssc">重庆时时彩</option><option value="Jxssc">江西时时彩</option><option value="Hnssc">河内五分彩</option><option value="Ynssc">印尼五分彩</option>
			<option value="Sd11x5">山东11选5</option><option value="Jx11x5">江西11选5</option><option value="Gd11x5">广东11选5</option><option value="Cq11x5">重庆11选5</option></select> | 期号：<input style="width:100px" name="qihao" type="text" value="${qihao}"/> 
			| 是否存在开奖号码：<select name="havehm"><option value="-1">全部</option><option value="0">无号码</option><option value="1">有号码</option></select>
			| 是否已开奖：<select name="isopen"><option value="-1">全部</option><option value="0">未开奖</option><option value="1">已开奖</option></select> | <input type="submit" value="搜索"/></form></td>
		</tr>
		<tr><td>便捷筛选：<a href="/admin/AdminLot!LotList.jzh?havehm=0">录入号码</a> | <a href="/admin/AdminLot!LotList.jzh?isopen=0&havehm=1">开奖派奖</a></td></tr>
		<tr><td>注意开奖号码格式：一般格式是用 , 来分开，如果有蓝球（例如双色球）蓝球和红球之间用 $ 隔开。<font color="green">一般录入号码之后，系统会自动派奖，无需手动派奖。</font></td></tr>
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
						<td>彩种</td>
						<td>期号</td>
						<td>开始时间</td>
						<td>结束时间</td>
						<td>状态</td>
						<td>开奖号码</td>
						<td>操作</td></tr>
					<thead>
					<tbody>
<c:forEach var="d" items="${find}"><tr>
<td>${d.lot_name}</td>
<td>${d.lot_qihao}</td>
<td>${d.lot_btime}</td>
<td>${d.lot_etime}</td>
<td><c:choose><c:when test="${d.lot_isopen==0}"><font color="red">未开奖</font></c:when><c:when test="${d.lot_isopen==1}">已开奖</c:when><c:otherwise>--</c:otherwise></c:choose></td>
<td><c:choose><c:when test="${fn:length(d.lot_haoma)>0}">${d.lot_haoma}</c:when><c:otherwise><input style="width:100px" type="text"/></c:otherwise></c:choose></td>
<td><c:choose> 
<c:when test="${fn:length(d.lot_haoma)<=0}"><input name="baocun" ids="${d.lot_id}" type="button" value="保存"/></c:when>
<c:when test="${d.lot_isopen==0}"><input name="kaijiang" ids="${d.lot_id}" type="button" value="开奖"/></c:when>
<c:otherwise>--</c:otherwise>
</c:choose></td>
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
	$('select[name=isopen]').val(${isopen});
	$('select[name=havehm]').val(${havehm});
	$('select[name=lot]').val('${lot}');
	$('input[name=baocun]').click(function(){
		var hm = $(this).parent().prev().find('input').val();
		var ids = $(this).attr('ids');
		$(this).hide();
		$.post('/admin/AdminLot!Lot.jzh',{i:ids,h:hm,t:new Date()},function(data){
			$(this).show();
			if (data=='0'){
				alert("录入号码成功！");window.location.reload();
			}else if(data=='1'){
				alert("录入号码失败！");
			}else if(data=='nologin'){
				alert("您长时间未操作，请重新登录！");location.href='/adminsqwe';
			}else{
				alert("参数错误！");
			}
		});
	});
	$('input[name=kaijiang]').click(function(){
		var ids = $(this).attr('ids');
		$(this).hide();
		$.post('/admin/AdminLot.jzh',{i:ids,t:new Date()},function(data){
			$(this).show();
			if (data=='0'){
				alert("开奖成功！");window.location.reload();
			}else if(data=='1'){
				alert("开奖失败，系统已开奖！");
			}else if(data=='nologin'){
				alert("您长时间未操作，请重新登录！");location.href='/adminsqwe';
			}else{
				alert("参数错误！");
			}
		});
	});
});
</script>
</html>