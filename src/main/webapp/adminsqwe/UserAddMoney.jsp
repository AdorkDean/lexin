<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/adminsqwe/css/admin.css">
<script language="javascript" type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<table class="mtb" cellspacing="0" style="width:90%;margin:auto"> 
		<caption>手工充值</caption> 
		<tr>
			<td>用户名称：<input id="u_name" type="text"/></td>
		</tr>
		<tr>
			<td>充值金额：￥<input id="u_money" style="width:80px" value="0" type="text"/>元 | 需消费多少可提款：￥<input id="u_show" style="width:80px" value="0" type="text"/>元</td>
		</tr>
		<tr>
			<td>赠送金额：￥<input id="u_song" style="width:80px" value="0" type="text"/>元</td>
		</tr>
		<tr>
			<td>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：<input id="des" type="text" style="width:350px"/></td>
		</tr>
		<tr>
			<td align="center" id="addmon"><input type="button" onclick="addMon()" value="确认充值"/></td>
		</tr>
	</table> 
</body>
<script type="text/javascript">
	function addMon(){
		$('#addmon').html('<img src="/img/loading.gif"/>');
		var user=$('#u_name').val();
		var money=Number($('#u_money').val());
		var song=Number($('#u_song').val());
		var des=$('#des').val();
		var show=$('#u_show').val();
		if (user.length==0) {
			parent.alert('用户名不能为空');return;
		}
		if (money==0) {
			parent.alert('充值金额不能为0');return;
		}
		$.post('/admin/AdminUser.jzh',{user:user,money:money,song:song,des:des,show:show,t:new Date()},function(data){
			$('#addmon').html('<input type="button" onclick="addMon()" value="确认充值"/>');
			if(data=='0'){
				parent.alert('充值成功');
				$('#u_name').val('');
				$('#u_money').val(0);
				$('#u_song').val(0);
			}else if(data=='1'){
				parent.alert('充值失败');
			}else if(data=='2'){
				parent.alert('该用户不存在');
			}else if(data=='err'){
				parent.alert('用户名或金额错误');
			}
		});
	}
</script>
</html>