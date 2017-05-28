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
		<caption>添加文章</caption> 
		<tr>
			<td>文章类型：<select id="n_type"><option value="0">公告</option><option value="1">新闻</option><option value="2">攻略</option>
			<option value="20">重庆时时彩（高频）</option><option value="21">江西时时彩（高频）</option><option value="26">河内五分彩（高频）</option><option value="22">山东11选5（高频）</option><option value="23">江西11选5（高频）</option><option value="24">广东11选5（高频）</option>
			<option value="40">双色球（福彩）</option><option value="41">福彩3D（福彩）</option>
			<option value="60">大乐透（体彩）</option><option value="61">排列三（体彩）</option></select></td>
		</tr>
		<tr>
			<td>文章标题：<input id="n_title" style="width:300px" type="text"/></td>
		</tr>
		<tr>
			<td>文章来源：<input id="n_souce" type="text"/></td>
		</tr>
		<tr>
			<td>文章排序：<input id="n_sort" value="0" type="text"/></td>
		</tr>
		<tr>
			<td>内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：
			<script charset="utf-8" src="/js/edit/kindeditor-min.js"></script>
			<script charset="utf-8" src="/js/edit/lang/zh_CN.js"></script>
<script>
        KindEditor.ready(function(K) {
                window.editor = K.create('#n_text',{width:'700px'});
        });
</script>
			<textarea style="height:350px" id="n_text"></textarea></td>
		</tr>
		<tr>
			<td align="center"><input type="button" onclick="addnews()" value="添加"/></td>
		</tr>
	</table> 
</body>
<script type="text/javascript">
	function addnews(){
		var type=$('#n_type').val();
		var title=$('#n_title').val();
		var souce=$('#n_souce').val();
		var sort=$('#n_sort').val();
		var text=editor.html();
		if (title.length==0) {
			parent.alert('标题不能为空');return;
		}
		if (text.length==0) {
			parent.alert('内容不能为空');return;
		}
		$.post('/AdminNews.jzh',{title:title,source:souce,type:type,text:text,t:new Date()},function(data){
			if(data=='0'){
				alert('添加成功！');location.href='/AdminNews.jzh?statu=0';
			}else if(data=='1'){
				alert('添加失败！');
			}else if(data=='nologin'){
				parent.location.href='/admins/login.jsp';
			}else{
				alert('请填写完整再添加！');
			}
		});
	}
</script>
</html>
56 1 56--160
56 1 112--160
112 2 224--320
168 3 392--480
280 5 672--900
448 8 1120--1280
672 12 1772--1920
1008 18 2780--2880
1568 28 4348--4480