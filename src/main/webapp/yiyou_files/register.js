$(function(){
	$('#regEmail input').bind("focus",function(){
		$(this).parent().parent().attr('class','u-ipt u-ipt-foc');
		$(this).parent().parent().parent().find('p').attr('class','u-tips u-tips-warn').find('span').html(Reg.ShowWarn(this.name));
	}).bind("blur",function(){
		$(this).parent().parent().attr('class','u-ipt');
		$(this).parent().parent().parent().find('p').attr('class','u-tips').find('span').html('');
		var b = this.value;
		var n = this.name;
		if(b.length>0){
			if ('username_r'==n){
				Reg.CheckName(b);
			}else if('password'==n){
				Reg.CheckPass(b);
			}else if('cpassword'==n){
				Reg.CheckPass2(b);
			}else if('radomPass'==n){
				Reg.CheckYZM(b);
			}else if('user_name'==n){
				Reg.CheckUser(b);
			}else if('user_qq'==n){
				Reg.CheckQQ(b);
			}
		}
	});
	$('#regEmail button').click(function(){
		if(!$(this).hasClass("u-btn2-dis")&&Reg.Email&&Reg.Pass&&Reg.Pass2&&Reg.Yzm){
			if(Reg.QQ){
				$('#regemail').submit();
			}else{
				alert('请填写QQ号码');
			}
		}
	});
});
Reg={
	Email:false,Pass:false,Pass2:false,Yzm:false,_IsLogin:false,QQ:false,
	CheckQQ:function(o){//验证QQ
		var n = $('#user_qq').parent().parent();
		var patrn = /^[1-9]\d{4,9}$/ ;
		if (!patrn.exec(o)) {
			n.addClass('u-ipt-err');Reg.QQ=false;
			n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('QQ号码错误。');
		}else{
			Reg.QQ=true;
			n.parent().find('p').attr('class','u-tips u-tips-ok').find('span').html('');
		}
	},
	ShowWarn:function(o){//显示提示信息
		var r='输入邮箱来完成注册，一个邮箱只能注册一个帐号。';
		if('password'==o){
			r='5到15个字符，区分大小写。';
		}else if('cpassword'==o){
			r='再次输入您的密码。';
		}else if('radomPass'==o){
			r='输入五位数字验证码。';
		}else if('mobile'==o){
			r='输入正确的手机号码。';
		}else if('user_name'==o){
			r='输入您的用户昵称。';
		}else if('user_qq'==o){
			r='输入您的用户QQ。';
		}
		return r;
	},
	CheckUser:function(o){//验证用户名注册
		var n = $('#user_name').parent().parent();
		var len = local.GetLenth(o);
		if(len<5||len>18){
			n.addClass('u-ipt-err');Reg.Email=false;
			n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('用户名的长度为5-18个字符（不能包含特殊字符,一个汉字2个字符）');
		}else{
			$.post('/RegAction!CheckName.jzh',{name:o,t:local.TimeLong()},function(data){
				if(data=='0'){
					Reg.Email=true;
					n.parent().find('p').attr('class','u-tips u-tips-ok').find('span').html('');
				}else if(data=='1'){
					Reg.Email=false;
					n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('该帐号已存在，请直接 <a href="/login.html">登录</a>');
				}else{
					Reg.Email=false;
					n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('账户错误或网络连接延时，请重新尝试。');
				}
			});
		}
	},
	CheckName:function(o){//验证注册邮箱
		var n = $('#email_name_r').parent().parent();
		if(local.CheckEmail(o)){
			n.addClass('u-ipt-err');Reg.Email=false;
			n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('请输入正确的邮件地址。');
		}else if(o.length>30){
			n.addClass('u-ipt-err');Reg.Email=false;
			n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('邮件地址长度不能大于30个字符。');
		}else{
			$.post('/RegAction!CheckEmail.jzh',{email:o,t:local.TimeLong()},function(data){
				if(data=='0'){
					Reg.Email=true;
					n.parent().find('p').attr('class','u-tips u-tips-ok').find('span').html('');
				}else if(data=='1'){
					Reg.Email=false;
					n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('该通行证帐号已存在，请直接 <a href="/login.html">登录</a>');
				}else{
					Reg.Email=false;
					n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('邮箱地址错误！请输入正确邮箱地址。');
				}
			});
		}
	},
	CheckPass:function(o){//验证注册密码
		var n = $('#password').parent().parent();
		if(o.length<5||o.length>15){
			n.addClass('u-ipt-err');Reg.Pass=false;
			n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('请输入5-15个字符，区分大小写。');
		}else{
			Reg.Pass=true;
			if(/[a-zA-Z]+/.test(o) && /[0-9]+/.test(o) && /\W+\D+/.test(o)){
				n.parent().find('p').attr('class','u-tips u-tips-ok').find('span').html('强：请牢记您的密码<br><strong class="u-pwdStrength u-pwdStrength-high"></strong>');
			}else if(/[a-zA-Z]+/.test(o) && /[0-9]+/.test(o)){
				n.parent().find('p').attr('class','u-tips u-tips-ok').find('span').html('中：试试字母、数字、符号混搭<br><strong class="u-pwdStrength u-pwdStrength-mid"></strong>');
			}else{
				n.parent().find('p').attr('class','u-tips u-tips-ok').find('span').html('弱：试试字母、数字、符号混搭<br><strong class="u-pwdStrength u-pwdStrength-low"></strong>');
			}
		}
	},
	CheckPass2:function(o){//验证密码是否一致
		var n = $('#re_password').parent().parent();
		var p = $('#password').val();
		if (p!=o){
			n.addClass('u-ipt-err');Reg.Pass2=false;
			n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('两次输入的密码不一致。');
		}else{
			Reg.Pass2=true;
			n.parent().find('p').attr('class','u-tips u-tips-ok').find('span').html('');
		}
	},
	CheckYZM:function(o){//验证码是否正确
		var n = $('#usercheckcode').parent().parent();
		if (o.length!=5){
			n.addClass('u-ipt-err');Reg.Yzm=false;
			n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('验证码错误。');
		}else{
			$.post('/RegAction!CheckYZM.jzh',{yzm:o,t:local.TimeLong()},function(data){
				if(data=='0'){
					Reg.Yzm=true;
					n.parent().find('p').attr('class','u-tips u-tips-ok').find('span').html('');
				}else{
					Reg.Yzm=false;
					n.parent().find('p').attr('class','u-tips u-tips-err').find('span').html('验证码错误。');
				}
			});
		}
	},
	AgreeChange:function(){//用户是否同意协议
		if ($('#agree').is(":checked")){
			$('#regEmail button').removeClass('u-btn2-dis');
		}else{
			$('#regEmail button').addClass('u-btn2-dis');
		}
	},
	ReSendEmail:function(u){//邮箱验证码重新发送
		$.get('/RegAction!ReSend.jzh',{upid:u,t:local.TimeLong()},function(data){
			if(data=='0'){
				alert('邮箱验证码重新发送成功！');
			}else{
				alert('邮箱验证码重新发送失败！请重新注册。');
				location.href="/regemail.html";
			}
		});
	},
	Login:function(){//用户登录验证
		if(Reg._IsLogin){
			alert('登录中...');return;
		}
		Reg._IsLogin=true;$('submitBtn').html('登录中...请稍后');
		var nm = $('#loginname').val();
		var ps = $('#loginpass').val();
		var ym = $('#loginyzm').val();
		if(local.GetLenth(nm)<1||local.GetLenth(nm)>32){
			alert('用户名不正确！');return;
		}
		if(ps.length==0){
			alert('密码不能为空！');return;
		}
		if(ym.length!=5){
			alert('验证码长度不正确！');return;
		}
		$.post('/RegAction!Login.jzh',{nm:nm,ps:ps,ym:ym,t:local.TimeLong()},function(data){
			$('submitBtn').html('立即登录');
			Reg._IsLogin=false;
			if(data=='0'){
				location.href="/index.html";
			}else if(data=='-2'){
				alert('验证码错误！');$('#yzm').click();
			}else if(data=='-1'){
				alert('用户名不存在！');
			}else if(data=='1'){
				alert('该用户已停用，请联系客服！');
			}else{
				alert(data);
			}
		});
	}
}