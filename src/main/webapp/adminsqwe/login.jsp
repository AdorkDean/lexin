<%@ page language="java" import="java.util.*,com.caipiao.utils.UserSession" pageEncoding="utf-8"%>
<%Object obj = session.getAttribute(UserSession.adminuser);
if(null!=obj){
 out.print("<script>location.href='/adminsqwe/index.jsp';</script>");
} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>后台管理系统</title>
 <style>
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre, 
form,fieldset,input,textarea,p,blockquote,th,td { 
     padding: 0; 
     margin: 0; 
	 list-style:none;
     } 
body{
	 background:#22438f;
	 font-size:12px;
	font-family:Verdana, Geneva, sans-serif;
	 }
.login{
	width:548px;
	height:438px;
	margin:-220px 0 0 -20%;
	position:absolute;
	top:50%;
	left:50%;
	overflow:hidden;
	}
.login_table{
	width:548px;
	height:238px;
	border-radius:8px;
	background:#e9edf4;
 	border:1px solid #072956;
	box-shadow:-1px -1px 0px 0px rgba(0,0,0,0.1);
	position:relative;
	}
.login_table:after{
	width: 534px;
	height: 234px;
	content: "";
	display: block;
	border-radius: 330px/290px;
	position: absolute;
	z-index: -1;
	left: 0;
	bottom: 0px;
	box-shadow: 5px 5px 20px 0px rgba(0,0,0,0.5);
	}
.login_table ul{
	width:239px;
	height:198px;
	float:left;
	padding:20px 50px;
 	border-right:1px solid #69799d;
 	}
.login_table li{
	width:238px;
	height:28px;
	margin:15px 0;
 	}
.inputwrap{
	width:238px;
	height:28px;
 	border:1px solid #6c768e;
	border-radius:30px;
	overflow:hidden;
	display:inline-block;
	box-shadow:-1px -1px 0px 0px rgba(0,0,0,0.1);
	float:left;
	}
.login_table li div.inputwrong{
	border-color:#ff6c6c
	}
.tableico{
	width:35px;
	height:28px;
	display:block;
	background:-webkit-linear-gradient(top,#a3aec8,#8794b1);
	background:-moz-linear-gradient(top,#a3aec8,#8794b1);
	box-shadow:0 1px 0px 0px rgba(255,255,255,0.3) inset;
	border-right:1px solid #768198;
	border-radius:15px 0px 0px 0px;
	float:left;
	}
.tableico span{
	width:18px;
	height:18px;
	display:block;
	margin:5px auto;
	background-image:url(images/ele.png)
	}
.tableico .person{
	background-position:-23px -249px;
	}
.tableico .key{
	background-position:-43px -249px;
	}
.tableico .card{
	background-position:-65px -249px;
	}
.textinput{
	height:28px;
	border:0 none;
	width:182px;
	color:#333333;
	padding:0 10px;
	line-height:28px;
	outline:none;
	}
.w101{width:81px;}
.btblue{
	width:118px;
	height:33px;
	display:block;
	background:-webkit-linear-gradient(top,#4f6fb7,#22438f);
	background:-moz-linear-gradient(top,#4f6fb7,#22438f);
	border-radius:30px;
	line-height:33px;
	text-align:center;
	color:#fff;
	margin:10px auto 0;
	text-decoration:none;
	font-size:12px;
	font-weight:bold;
	border:1px solid #072956;
	box-shadow:-1px -1px 0px 0px rgba(0,0,0,0.1),0 1px 0px 0px rgba(255,255,255,0.3) inset
	}
.btblue:hover{
	opacity:0.9
	}
.red1{color:#d01212;font-size:12px}
.login_des{
	width:207px;
	height:238px;
	float:left;
	overflow:hidden;
	border-radius:0px 5px 5px 0px;
	}
.login_name{
	width:119px;
	overflow:hidden;
	height:35px;
	background:#6477a3;
	border-bottom:1px solid #516185;
	padding:18px 44px;
	color:#fff;
	font-size:12px;
	line-height:18px
	}
.login_warning{
	width:148px;
	height:54px;
	padding:56px 15px 56px 45px;
	background:url(images/ele.png) -304px -328px;
	font-size:12px;
	color:#fff;
	line-height:18px;
	}
.tx_c{
	text-align:center;
	}
.fwhite{
	color:#fff;
	}
.mt80{
	margin-top:80px;
	}
</style>
</head>

<body>
<div class="login">
<div class="login_table">
<form action="/admin/AdminUser!Login.jzh" method="post">
<ul>
<li><div class="inputwrap"><span class="tableico"><span class="person"></span></span><input class="textinput" name="username" type="text" placeholder="用户名" maxlength="20"/></div></li>
<li><div class="inputwrap"><span class="tableico"><span class="key"></span></span><input class="textinput" name="password" type="password" placeholder="密码"  maxlength="20"/></div></li>
<li><div class="inputwrap" style="width:137px"><span class="tableico"><span class="card"></span></span><input name="yzm" class="textinput w101" placeholder="验证码" type="text" maxlength="5"/></div><div><img alt="点击刷新" src="/images/image.jsp" width="60" onclick="this.src='/images/image.jsp?'+Math.random();" height="30" style="margin-left:15px;cursor:pointer;"/></div></li>
<li><input class="btblue" type="submit" value="登 录"/><!--<a href="##" class="btblue">登 录</a>--></li>
<li><!-- <p class="red1">密码错误</p> --></li>
</ul>
</form>
<div class="login_des">
<div class="login_name">
<p>Sanmu.com</p>
<p>系统管理入口</p>
</div>
<div class="login_warning">
为保证最佳浏览效果，请
选择火狐浏览器进行浏览，
谢谢理解。
</div>

</div>
</div>

<p class="tx_c fwhite mt80">Sanmu.com ©2013</p>
</div>
</body>
</html>
