$(function(){
	$('span.mcSelectBox').mouseover(function(){
		$(this).find('.optionList').show();
	}).mouseout(function(){
		$(this).find('.optionList').hide();
	});
	setPwd();
	setPayPwd();
});
function CheckBindPay(){
	var payse=$('#payse').val();
	var pays=$('#pays').val();
	var pays2=$('#pays2').val();
	var paypass=$('#paypass').val();
	if(payse.length==0){
		alert('请选择支付账户类型！');return;
	}
	if(pays.length==0){
		alert('账号不能为空！');return;
	}
	if (pays!=pays2){
		alert('账户前后不一致！');return;
	}
	if (paypass.length==0){
		alert('支付密码不能为空！');return;
	}
	$('form#PayForm').submit();
}
/**
 * 用户绑定银行卡
 * */
function CheckBindCard(){
	var bankse=$('#bankse').val();
	var province=$('#province').val();
	var city=$('#city').val();
	var cardadd=$('#cardadd').val();
	var card=$('#card').val();
	var card2=$('#card2').val();
	var paypass=$('#paypass').val();
	if (bankse.length==0){
		alert('开户银行不能为空！');return;
	}
	if (province.length==0||city.length==0){
		alert('开户银行地址不能为空！');return;
	}
	if (cardadd.length==0){
		alert('开户支行不能为空！');return;
	}
	if (card.length==0){
		alert('银行账户不能为空！');return;
	}
	if (card2!=card){
		alert('银行账户前后不一致！');return;
	}
	if (paypass.length==0){
		alert('支付密码不能为空！');return;
	}
	$('form#userInfoForm').submit();
}
/**
 * 用户修改密码
 * @return
 */
function setPwd(){
	$('#usersetpwd a[ref=submit]').click(function(){
		var op=$('#oldpass').val();
		var np=$('#newpass').val();
		var np2=$('#newpass2').val();
		if (op.length==0){
			alert('原登录密码不能为空');return;
		}
		if (np.length==0){
			alert('新登录密码不能为空');return;
		}
		if (np!=np2){
			alert('两次输入密码不相同');return;
		}
		$.post('/my/BaseInfo!SetPwd.jzh',{op:op,np:np,t:local.TimeLong()},function(data){
			if(data=='0'){
				alert('登录密码修改成功');location.reload();
			}else if(data=='1'){
				alert('原登录密码错误');
			}else if(data=='no'){
				alert('您长时间未操作，请重新登录！');location.href='/my/login.html';
			}else{
				alert('登录密码修改失败');
			}
		});
	});
}
/**
 * 用户修改支付密码
 * @return
 */
function setPayPwd(){
	$('#userpaypwd a[ref=submit]').click(function(){
		var op=$('#oldpay').val();
		var np=$('#newpay').val();
		var np2=$('#newpay2').val();
		if (op.length==0){
			alert('原支付密码不能为空');return;
		}
		if (np.length==0){
			alert('新支付密码不能为空');return;
		}
		if (np!=np2){
			alert('两次输入密码不相同');return;
		}
		$.post('/my/BaseInfo!SetPay.jzh',{op:op,np:np,t:local.TimeLong()},function(data){
			if(data=='0'){
				alert('支付密码修改成功');location.reload();
			}else if(data=='1'){
				alert('原支付密码错误');
			}else if(data=='no'){
				alert('您长时间未操作，请重新登录！');location.href='/my/login.html';
			}else{
				alert('支付密码修改失败');
			}
		});
	});
}
/**
 * 修改个人信息
 * @return
 */
function InfoSub(){
	$('span.uu-tips').attr('class','uu-tips').find('span').html('');
	var realname = $('#realname').val();
	if(!InfoCheck_realname(realname)){
		$('#realnametip').addClass('err_tip').find('span').html('真实姓名错误');return;
	}
	var realzip = $('#realzip').val();
	if(realzip.length<1||!IdCardValidate(realzip)){
		$('#realziptip').addClass('err_tip').find('span').html('身份证号码错误');return;
	}
	var qq = $('#qq').val();
	if(qq.length>0&&!isQQ(qq)){
		$('#qqtip').addClass('err_tip').find('span').html('qq号码错误（可不填）');return;
	}
	var birth = $('#birth').val();
	var add = $('#address').val();
	var ask = $('#ask').val();
	var ans = $('#ans').val();
	var word = $('#word').val();
	$.post('/my/BaseInfo.jzh',{realname:realname,realzip:realzip,birth:birth,address:add,qq:qq,ask:ask,ans:ans,word:word,t:local.TimeLong()},function(data){
		if(data=='0'){
			alert('修改成功');location.reload();
		}else{
			alert('修改失败');
		}
	});
}
//QQ验证信息
function isQQ(s) {
	var patrn = /^[1-9]\d{4,9}$/ ;
	if (!patrn.exec(s)) {
		return false;
	}
	return true;
}
/**
 * 判断真实姓名
 * @param s
 * @return
 */
function InfoCheck_realname(s){
	if(s.length<=1){
		return false;
	}else{
		var reg = /^([\u4E00-\u9FA5])*$/;
		if (s.match(reg)){
			if(!check_surname(s)){
				return false;
			}
		}else{
			return false;
		}
	}
	return true;
}
/**
 * 判断姓名
 * @param str
 * @return
 */
function check_surname(str){
	//var str=str.substr(0,1); //截取用户提交的用户名的前两字节，也就是姓。
	//var surname="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤 滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵堪汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董粱杜阮蓝闵席季麻强贾路娄危江童颜郭 梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松 井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双 闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍郤璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东 殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊 澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱 晋楚闫法汝鄢涂钦段干百里东郭南门呼延妫海羊舌微生岳帅缑亢况後有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福肖智渠";
	//r = surname.search(str);// 查找字符串。
	//if(r==-1){
	//	return false
	//}else{
		return true
	//}
}
function BanEmail(){
	$('#banemailtip').attr('class','uu-tips').find('span').html('');
	var ban_email = $('#ban_email').val();
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(ban_email==''){
		$('#banemailtip').addClass('err_tip').find('span').html('请输入邮箱地址。');return;
	}else if(!reg.test(ban_email)){
		$('#banemailtip').addClass('err_tip').find('span').html('您输入的邮件地址不正确!');return;
	}
	$.get('/my/BaseInfo!BanEmail.jzh',{ban_email:ban_email,t:local.TimeLong()},function(data){
		if(data=='1'){
			$('#banemailtip').addClass('err_tip').find('span').html('该邮箱已经绑定过，请跟换邮箱或者登陆。');
		}else if(data=='0'){
			$('#infoBox>ul>li:eq(1)').html('<span>绑定邮箱验证发送成功，请到指定邮箱验证。</span>');
		}else if(data=='-1'){
			$('#banemailtip').addClass('err_tip').find('span').html('邮箱地址不能为空。');
		}
	});
}