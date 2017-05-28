<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.caipiao.utils.LotEmun"%>
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
	<table class="mtb" cellspacing="0" style="width:80%;margin:auto"> 
		<caption>彩种期号管理</caption>
		<tr>
			<td>选择彩种：<a href="/admin/AdminLot!Qihao.jzh?l=Cqssc">重庆时时彩</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Jxssc">江西时时彩</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Hnssc">河内五分彩</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Ynssc">印尼五分彩</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Sd11x5">山东11选5</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Gd11x5">广东11选5</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Jx11x5">江西11选5</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Cq11x5">重庆11选5</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Sh11x5">上海11选5</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Jsk3">江苏快三</a><br>
	<a href="/admin/AdminLot!Qihao.jzh?l=Fc3d">福彩3D</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Pl3">排列3</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Pl5">排列5</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Ssq">双色球</a> | <a href="/admin/AdminLot!Qihao.jzh?l=Dlt">大乐透</a>
</td>
		</tr>
		<tr>
			<td class="alt">当前彩种：<span style="color: red"><%=LotEmun.valueOf(request.getAttribute("lot").toString()).namestr%></span><input id="lot" type="hidden" value="${lot}"/>
	<div>添加期号：从 <input id="Riqi" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/> 开始 | 连续 <input type="text" style="width:30px;" value="3" id="days"/>天 /期(高频是天，数字是期)| 期号：<input type="text" id="qihao"/>（高频不用填） | <input onclick="AddQihao()" type="button" value="添加"/><br>（2013-01-01表示2013年1月1日,期号系统自动添加，一般不用手动添加）</div>
</td>
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
					<tr><th>期号</th><th>开始时间</th><th>结束时间</th><th>操作</th></tr>
					</thead>
					<tbody>
<c:forEach var="d" items="${find}">
	<tr><td>${d.lot_qihao}</td><td>${d.lot_btime}</td><td>${d.lot_etime}</td><td><a href="/admin/AdminLot!UpQihao.jzh?lot=${d.lot_name}&id=${d.lot_id}">修改</a></td></tr>
</c:forEach>
                    </tbody>
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
	function AddQihao(){
		var reg=/^(\d{4})\-(\d{2})\-(\d{2})$/;
		var lot=$('#lot').val();
		var riqi=$('#Riqi').val();
		var num=Number($('#days').val());
		var qihao=$('#qihao').val();
		if (!reg.test(riqi)) {
			alert('日期的格式不对！');return;
		}else if(!(num>0)){
			alert('添加的天数错误！');return;
		}
		$.post('/admin/AdminLot!Qihao.jzh',{lot:lot,num:num,riqi:riqi,qihao:qihao,t:new Date()},function(data){
			if(data=='0'){
				alert('添加成功');location.reload();
			}else{
				alert('操作有误');
			}
		});
	}
</script>
</html>