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
	<table class="mtb" cellspacing="0" style="width:90%;margin:auto"> 
		<caption>用户明细</caption> 
		<tr>
			<td><form action="/admin/AdminUser!Desc.jzh" method="get" onsubmit="return DesCheck()">用户名称：<input id="user" name="u" type="text" value="${name}" style="width:100px"/> 交易类型
                <select name="t">
                	<option value="-1" selected="">全部</option>
                	<option value="0">购彩派奖</option>
                	<option value="1">购买彩票</option>
                	<option value="2">用户充值</option>
                	<option value="3">用户提款</option>
                	<option value="4">取消提款</option>
                	<option value="5">积分兑换</option>
                	<option value="7">方案保底</option>
                    <option value="6">保底返还</option>
                	<option value="8">方案撤单</option>
                	<option value="9">用户返利</option>
                </select><br><br>起始日期 <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="b" value="${bt}"> 终止日期 <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="e" value="${et}">
<br><br><input type="submit" value="查询"/></form></td>
		</tr>
		<c:if test="${null!=page}">
		<tr>
			<td align="center"><div class="my_page">
                <div id="page_wrapper" class="page">${page}</div>
            </div></td>
		</tr>
		</c:if>
		<c:if test="${null!=find}">
		<tr>
			<td><table width="100%">
					<thead>
					<tr class="tit"><td width="18%">交易时间</td><td width="10%">交易类型</td><td width="18%">订单号</td><td width="10%">用户余额</td><td width="10%">收支金额</td><td width="24%">描述</td></tr>
					<thead>
					<tbody>
<c:forEach var="d" items="${find}">
	<tr><td>${d.detail_time}</td><script>document.write(Show.MyDetType('${d.detail_type}','${d.detail_item}'))</script><td style="color:#CA6100">￥${d.detail_balance}</td><td style="color:red">￥${d.detail_addsub}</td><td>${d.detail_desc}</td>
</tr>
</c:forEach>
</tbody>
				</table></td>
		</tr>
		</c:if>
		<c:if test="${empty not page}">
		<tr>
			<td align="center"><div class="my_page">
                <div id="page_wrapper" class="page">${page}</div>
            </div></td>
		</tr>
		</c:if>
	</table> 
</body>
<script type="text/javascript">
$(function(){
	$('select[name=t]').val(${t});
});
	function DesCheck(){
		var user=$('#user').val();
		if (user.length==0) {
			parent.alert('用户名不能为空');return false;
		}
		return true;
	}
</script>
</html>