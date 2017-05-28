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
		<caption>文章编辑</caption>
		<tr>
			<td>文章类型：<select id="n_type"><option value="0">公告</option><option value="1">新闻</option><option value="2">攻略</option>
			<option value="20">重庆时时彩（高频）</option><option value="21">江西时时彩（高频）</option><option value="22">山东11选5（高频）</option><option value="23">江西11选5（高频）</option><option value="24">广东11选5（高频）</option><option value="25">重庆11选5（高频）</option><option value="26">河内五分彩（高频）</option>
			<option value="50">双色球（数字）</option><option value="51">福彩3D（数字）</option><option value="52">大乐透（数字）</option><option value="53">排列三（数字）</option><option value="54">排列五（数字）</option></select></td>
		</tr>
		<tr>
			<td>文章标题：<input id="n_id" value="${find.news_id}" type="hidden"/><input id="n_title" style="width:300px" value="${find.news_title}" type="text"/></td>
		</tr>
		<tr>
			<td>文章来源：<input id="n_souce" value="${find.news_soures}" type="text"/></td>
		</tr>
		<tr>
			<td>文章排序：<input id="n_sort" value="${find.news_sort}" type="text"/></td>
		</tr>
		<tr>
			<td>文章状态：<select id="n_status"><option value="0">正常</option><option value="1">关闭</option></select></td>
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
			<textarea style="height:350px" id="n_text">${find.news_text}</textarea></td>
		</tr>
		<tr>
			<td align="center"><input type="button" onclick="addnews()" value="添加"/></td>
		</tr>
	</table> 
</body>
<script type="text/javascript">
$(function(){
	$('#n_type').val(${find.news_type});
	$('#n_status').val(${find.news_status});
});
	function addnews(){
		var id=$('#n_id').val();
		var status=$('#n_status').val();
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
		$.post('/admin/AdminNews!Edit.jzh',{ids:id,status:status,title:title,source:souce,type:type,text:text,t:new Date()},function(data){
			if(data=='0'){
				alert('修改成功！');location.href='/admin/AdminNews.jzh';
			}else if(data=='1'){
				alert('修改失败！');
			}else if(data=='nologin'){
				parent.location.href='/adminsqwe/login.jsp';
			}else{
				alert('请填写完整再修改！');
			}
		});
	}
</script>
</html>