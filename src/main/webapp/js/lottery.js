$(function(){
	Base.Begin();
});
var Lot={
	Namestr:'',//彩种名字
	Name:'',//彩种编号
	Pay:'',//玩法
	Tcode:'',//临时选购内容
	Code:'',//投注的内容
	Zhushu:0,//投注的注数
	IsOpen:1,//是否销售 默认停售
	Qihao:'-',//当前期号
	Qihaos:'',//认购的期号列表
	Beishu:'',//认购的倍数列表
	AMoney:0,//总金额
	HMyMon:0,//我认购金额
	HMyBao:0,//保底金额
	SelT:0,//0 本期 1常规 2倍投
	BuyT:0,//0自购 1合买
	Btpay:'',//倍投玩法
	IsBuy:false,//是否在提交数据
}
var Info=[];
Info[319]='从个、十、百、千、万位任意两位中各选1个或多个号，选号与奖号同位号码相同，即中奖<font class="c_ba2636">'+Mon[319]+'</font>元。';
Info[318]='从个、十、百、千、万位任意一位中选1个或多个号码，选号与奖号同位号码相同，即中奖<font class="c_ba2636">'+Mon[318]+'</font>元。';
Info[315]='从个、十、百、千位各选1个或多个号码，所选号码与开奖号码后四位一一对应，即中奖<font class="c_ba2636">'+Mon[315]+'</font>元；所选号码与开奖号码的后四位中的连续前三位或后三位按位相符，即中<font class="c_ba2636">'+Mon[3150]+'</font>元。';
Info[316]='从万、千、百、十、个位各选1个或多个号码，所选号码与开奖号码一 一对应，即中奖<font class="c_ba2636">'+Mon[316]+'</font>元。';
Info[317]='从个、十、百、千、万位各选1个或多个号码，所选号码与开奖号码一一对应，兼中兼得，即中奖<font class="c_ba2636">'+(Mon[317]+Mon[3170]+Mon[3171])+'</font>元；如所选号码和开奖号码，前三位或后三位一一对应，即中奖<font class="c_ba2636">'+(Mon[3170]+Mon[3171])+'</font>元；如前二位或者后二位一一对应，即中奖<font class="c_ba2636">'+Mon[3171]+'</font>元。';
Info[313]='竞猜开奖号码后三位的胆码，所有胆码开出即中奖，后三位开奖号码为豹子形态奖金<font class="c_ba2636">'+Mon[308]+'</font>元；组三形态奖金<font class="c_ba2636">'+Mon[310]+'</font>元；组六形态奖金<font class="c_ba2636">'+Mon[313]+'</font>元。';
Info[312]='至少选择一个和值，竞猜开奖号码后三位数字之和，后三位开奖号码为豹子形态奖金<font class="c_ba2636">'+Mon[308]+'</font>元；组三形态奖金<font class="c_ba2636">'+Mon[310]+'</font>元；组六形态奖金<font class="c_ba2636">'+Mon[312]+'</font>元。';
Info[321]='选1～2个胆码、1～9个拖码，胆码加拖码不少于3个，单注奖金<font class="c_ba2636">'+Mon[321]+'</font>元。';
Info[311]='至少选择三个号码投注，竞猜开奖号码后三位，开奖号码为，且号码都选中即中奖，奖金<font class="c_ba2636">'+Mon[311]+'</font>元。';
Info[320]='选1个胆码、1～9个拖码，胆码加拖码不少于2个，单注奖金<font class="c_ba2636">'+Mon[320]+'</font>元。';
Info[310]=Info[322]='至少选择两个号码投注，竞猜开奖号码后三位，开奖号码为组三形态，且号码都选中即中奖，奖金<font class="c_ba2636">'+Mon[310]+'</font>元。';
Info[314]='至少选择三个号码投注，竞猜开奖号码后三位，且开奖号码为组六形态即中奖，奖金<font class="c_ba2636">'+Mon[314]+'</font>元。';
Info[309]='至少选择1个和值投注，所选和值与开奖号码后三位和值一致即中奖<font class="c_ba2636">'+Mon[309]+'</font>元。';
Info[308]='从百、十、个位各选1个或多个号码，所选号码与开奖号码后三位一 一对应，即中奖<font class="c_ba2636">'+Mon[308]+'</font>元。';
Info[307]='竞猜开奖号码后两位的胆码，任意一位开出即中奖，非对子奖金<font class="c_ba2636">'+Mon[307]+'</font>元，对子奖金<font class="c_ba2636">'+Mon[302]+'</font>元。';
Info[305]='每位至少选择一个号码，竞猜开奖号码的最后两位，非对子奖金<font class="c_ba2636">'+Mon[305]+'</font>元，对子奖金<font class="c_ba2636">'+Mon[302]+'</font>元。';
Info[306]='至少选择一个和值，竞猜开奖号码后两位数字之和，非对子奖金<font class="c_ba2636">'+Mon[306]+'</font>元，对子奖金<font class="c_ba2636">'+Mon[302]+'</font>元。';
Info[304]='至少选择两个号码，竞猜开奖号码的最后两位，奖金<font class="c_ba2636">'+Mon[304]+'</font>元（开出对子不算中奖）。';
Info[303]='至少选择一个和值，竞猜开奖号码后两位数字之和，奖金<font class="c_ba2636">'+Mon[303]+'</font>元。';
Info[302]='从十、个位各选1个或多个号码，所选号码与开奖号码后两位一 一对应，即中奖<font class="c_ba2636">'+Mon[302]+'</font>元。';
Info[301]='从0~9中任选1个或多个号码投注，投注号码与当期开奖号码的最后一位号码相同，即中奖<font class="c_ba2636">'+Mon[301]+'</font>元。';
Info[300]='从十、个位中的大小单双4种属性中各选1种属性，所选属性与开奖号码的属性相同，即中奖<font class="c_ba2636">'+Mon[300]+'</font>元，注：号码5～9为大，0～4为小；13579为单，02468为双；举例：开奖号码9,5,8,0,8；十位:小双 ，个位:大双 ，用户选：小大、双双、小双、双大，都算中奖。';
/** 排列五 排3，3d*/
Info[110]='万位至个位，每位至少选择一个号码，选号与奖号相同（顺序一致），即中<font class="c_ba2636">'+Mon[110]+'</font>元。';
Info[112]='所选号码与开奖号码相同（且顺序一致）即中<font class="c_ba2636">'+Mon[112]+'</font>元 中奖示例：选号168，奖号168。';
Info[113]='选号和奖号的三个数相加之和相同，即中<font class="c_ba2636">'+Mon[112]+'</font>元 中奖示例：选号168 和值15。';
Info[114]='选号与奖号一致（但顺序不限），且有任意两位相同即中 <font class="c_ba2636">'+Mon[114]+'</font>元 中奖示例：选号：188 818 881 奖号：188。';
Info[115]='选号和奖号三个数相加之和相同，且开奖号有两个号码相同时，即中 <font class="c_ba2636">'+Mon[114]+'</font>元。';
Info[116]='选1个胆码，选1～9个拖码，胆码加拖码不少于2个；选号与奖号相同（顺序不限），即中奖 <font class="c_ba2636">'+Mon[114]+'</font>元';
Info[117]='选号与奖号相同（顺序不限）即中<font class="c_ba2636">'+Mon[117]+'</font>元 中奖示例：选号168 186 618 681 861 816 奖号168';
Info[118]='所选号码和开奖号码之和相同，且开奖号各不相同时，即中<font class="c_ba2636">'+Mon[117]+'</font>元';
Info[119]='选1～2个胆码、1～9个拖码，胆码加拖码不少于3个；选号与奖号相同（顺序不限），即中奖<font class="c_ba2636">'+Mon[117]+'</font>元';
/** 11选5*/
Info[350]='从01～11中任选1个或多个号码，所选号码与开奖号码第一位号码相同，即中奖<font class="c_ba2636">'+Mon[350]+'</font>元';
Info[351]='从每位各选1个或多个号码，所选号码与开奖号码前两位号码相同（且顺序一致），即中奖<font class="c_ba2636">'+Mon[351]+'</font>元';
Info[352]='从01～11中任选2个或多个号码，所选号码与开奖号码前两位号码相同（顺序不限），即中奖<font class="c_ba2636">'+Mon[352]+'</font>元';
Info[353]='从每位各选1个或多个号码，所选号码与开奖号码前三位号码相同（且顺序一致），即中奖<font class="c_ba2636">'+Mon[353]+'</font>元';
Info[354]='从01～11中任选3个或多个号码，所选号码与开奖号码前三位号码相同（顺序不限），即中奖<font class="c_ba2636">'+Mon[354]+'</font>元';
Info[355]='从01～11中任选2个或多个号码，所选号码与开奖号码任意两个号码相同，即中奖<font class="c_ba2636">'+Mon[355]+'</font>元';
Info[356]='从01～11中任选3个或多个号码，所选号码与开奖号码任意三个号码相同，即中奖<font class="c_ba2636">'+Mon[356]+'</font>元';
Info[357]='从01～11中任选4个或多个号码，所选号码与开奖号码任意四个号码相同，即中奖<font class="c_ba2636">'+Mon[357]+'</font>元';
Info[358]='从01～11中任选5个或多个号码，所选号码与开奖号码相同，即中奖<font class="c_ba2636">'+Mon[358]+'</font>元';
Info[359]='从01～11中任选6个或多个号码，所选号码与开奖号码五个号码相同，即中奖<font class="c_ba2636">'+Mon[359]+'</font>元';
Info[360]='从01～11中任选7个或多个号码，所选号码与开奖号码五个号码相同，即中奖<font class="c_ba2636">'+Mon[360]+'</font>元';
Info[361]='从01～11中任选8个或多个号码，所选号码与开奖号码五个号码相同，即中奖<font class="c_ba2636">'+Mon[361]+'</font>元';
Info[362]='选1个胆码、1～10个拖码，胆码加拖码不少于2个，单注奖金<font class="c_ba2636">'+Mon[352]+'</font>元';
Info[363]='选1~2个胆码、2～10个拖码，胆码加拖码不少于3个，单注奖金<font class="c_ba2636">'+Mon[354]+'</font>元';
Info[364]='选1个胆码、1～10个拖码，胆码加拖码不少于2个，单注奖金<font class="c_ba2636">'+Mon[355]+'</font>元';
Info[365]='选1~2个胆码、1～10个拖码，胆码加拖码不少于3个，单注奖金<font class="c_ba2636">'+Mon[356]+'</font>元';
Info[366]='选1~3个胆码、1～10个拖码，胆码加拖码不少于4个，单注奖金<font class="c_ba2636">'+Mon[357]+'</font>元';
Info[367]='选1~4个胆码、1～10个拖码，胆码加拖码不少于5个，单注奖金<font class="c_ba2636">'+Mon[358]+'</font>元';
Info[368]='选1~5个胆码、1～10个拖码，胆码加拖码不少于6个，单注奖金<font class="c_ba2636">'+Mon[359]+'</font>元';
Info[369]='选1~6个胆码、1～10个拖码，胆码加拖码不少于7个，单注奖金<font class="c_ba2636">'+Mon[360]+'</font>元';
Info[370]='选1~7个胆码、1～10个拖码，胆码加拖码不少于8个，单注奖金<font class="c_ba2636">'+Mon[361]+'</font>元';

Info[400]='至少选择1个和值（3个号码之和）进行投注，所选和值与开奖的3个号码的和值相同即中奖，最高可中<font class="c_ba2636">'+Mon[400]+'</font>元。';
Info[401]='对所有相同的三个号码（111、222、333、444、555、666）进行投注，任意号码开出即中奖，单注奖金<font class="c_ba2636">'+Mon[401]+'</font>元。';
Info[402]='对相同的三个号码（111、222、333、444、555、666）中的任意一个进行投注，所选号码开出即中奖，单注奖金<font class="c_ba2636">'+Mon[402]+'</font>元。';
Info[403]='从1～6中任选3个或多个号码，所选号码与开奖号码的3个号码相同即中奖，单注奖金<font class="c_ba2636">'+Mon[403]+'</font>元。';
Info[410]='选1～2个胆码，选1～5个拖码，胆码加拖码不少于3个；选号与奖号相同即中奖，单注奖金<font class="c_ba2636">'+Mon[403]+'</font>元。';
Info[405]='对所有3个相连的号码（123、234、345、456）进行投注，任意号码开出即中奖，单注奖金<font class="c_ba2636">'+Mon[405]+'</font>元。';
Info[406]='从11～66中任选1个或多个号码，选号与奖号（包含11～66，不限顺序）相同，即中奖<font class="c_ba2636">'+Mon[406]+'</font>元。';
Info[407]='选择1对相同号码和1个不同号码投注，选号与奖号相同（顺序不限），即中奖<font class="c_ba2636">'+Mon[407]+'</font>元。';
Info[408]='从1～6中任选2个或多个号码，所选号码与开奖号码任意2个号码相同，即中奖<font class="c_ba2636">'+Mon[408]+'</font>元。';
Info[409]='选1个胆码，选1～5个拖码，胆码加拖码不少于2个；选号与奖号任意2号相同即中奖，单注奖金<font class="c_ba2636">'+Mon[408]+'</font>元。';
//三星直选和值
var SXHZ = new Array(1,3,6,10,15,21,28,36,45,55,63,69,73,75,75,73,69,63,55,45,36,28,21,15,10,6,3,1);
//二星直选和值
var EXHZ = new Array(1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1);
//组三包点
var SZBD = new Array(1,1,2,3,4,5,7,8,10,12,13,14,15,15,15,15,14,13,12,10,8,7,5,4,3,2,1,1);
//二星包点
var EXBD = new Array(1,1,2,2,3,3,4,4,5,5,5,4,4,3,3,2,2,1,1);
//三星组三和值
var SXZSHZ =new Array(0,1,2,1,3,3,3,4,5,4,5,5,4,5,5,4,5,5,4,5,4,3,3,3,1,2,1,0);
//三星组六和值
var SXZLHZ =new Array(0,0,0,1,1,2,3,4,5,7,8,9,10,10,10,10,9,8,7,5,4,3,2,1,1,0,0,0);
var Base={
	Ball:'active_red',
	Begin:function(){/**基本操作js*/
		$('ul.betway_tabs li').click(function(){//选几星
			$('ul.betway_tabs li').removeClass('active');
			$(this).addClass('active');var a_index=$(this).index();
			$('#palyer_con>.select_num>.way_tags div').hide();
			$('#palyer_con>.select_num>.way_tags div').eq(a_index).show();
			$('#palyer_con>.select_num>.way_tags div').eq(a_index).find("input").eq(0).attr("checked",true).click();
		});
		$('.way_tags input').click(function(){//选玩法
			Lot.Pay=$(this).val();
			Base.ClearBall();
			Base.ShowPay(Lot.Pay);
			
		});
		$('ul.bcballbox>li>span,ul.bcballbox>li>em').click(function(){//选球事件
			var p=$(this).parent();
			if (!p.hasClass(Base.Ball)){
				var n =Number($(this).parent().parent().attr('val'));
				if(!isNaN(n)&&n!=0){
					var num = p.parent().find('li.'+Base.Ball).length;
					if (num>=n){
						alert('最多只能选择'+n+'个号码。');Base.BallInfo();return;
					}
				}
				p.addClass(Base.Ball);
			}else{
				p.removeClass(Base.Ball);
			}
			Base.BallInfo();
		});
		$('ul.bcballboxs>li>span').click(function(){//选球事件(取消其他)
			var p=$(this).parent();
			var n =Number($(this).parent().parent().attr('val'));
			if(!isNaN(n)&&n!=0){
				if (!p.hasClass(Base.Ball)){
					var num = p.parent().find('li.'+Base.Ball).length;
					if (num>=n){
						alert('最多只能选择'+n+'个号码。');
					}else{
						var bn = p.index();
						$('ul.bcballboxs').each(function(){
							$(this).find('li:eq('+bn+')').removeClass(Base.Ball);
						});
						p.addClass(Base.Ball);
					}
				}else{
					p.removeClass(Base.Ball);
				}
			}else{
				if (!p.hasClass(Base.Ball)){
					var bn = p.index();
					$('ul.bcballboxs').each(function(){
						$(this).find('li:eq('+bn+')').removeClass(Base.Ball);
					});
					p.addClass(Base.Ball);
				}else{
					p.removeClass(Base.Ball);
				}
			}
			Base.BallInfo();
		});
		$('ul.fs_zx_btns>li').click(function(){//大小奇偶事件
			var odd='odd';
			var even='even';
			var num = Number($(this).parent().attr('val'));
			if (1==num){
				odd='even';even='odd'
			}
			var txt = $.trim($(this).text());
			var u = $(this).parent().prev();
			switch (txt){
				case "全":u.find('li').removeClass(Base.Ball).find('span').click();break;
				case "大":u.find('li').removeClass(Base.Ball).each(function(index){if(index>4){$(this).find('span').click();}});break;
				case "小":u.find('li').removeClass(Base.Ball).each(function(index){if(index<5){$(this).find('span').click();}});break;
				case "奇":u.find('li').removeClass(Base.Ball);u.find('li:'+odd).find('span').click();break;
				case "偶":u.find('li').removeClass(Base.Ball);u.find('li:'+even).find('span').click();break;
				case "清除":u.find('li').removeClass(Base.Ball);break;
				default:break;
			}
			Base.BallInfo();
		});
		$('#beitou').keyup(function(){//自购倍数
			$(this).val($(this).val().replace(/[^\d]/g,''));
			var num = Number($(this).val()==0?1:$(this).val());
			$(this).val(num);
			Base.ShowMoney();
		});
		$('#clearall').click(function(){//清空选号事件
			Base.ClearBall();
		});
		$('#confirm_select').click(function(){//选号添加到投注栏
			if($(this).hasClass('addbtn')){
				if (!$("#box_ds").is(":hidden")){//单式
					var pastecontent = $("#box_ds .zhantie_textarea").val();
					if(pastecontent != ""){
						pastecontent = pastecontent.replace(/\r\n/g,"$");
						pastecontent = pastecontent.replace(/\n/g,"$");
						pastecontent = pastecontent.replace(/\s/g,"");
						pastecontent = pastecontent.replace(/(\$)\1+/g, '$1');
						if(pastecontent.substr(pastecontent.length-1,1)=="$"){
							pastecontent = pastecontent.substr(0,pastecontent.length-1);
						}
						var num=Number(pastecontent.split("$").length);
						Base.AddDSLine(pastecontent);
					}else{
						layer.alert("你输入的投注内容不够一注！");
					}
				}else{
					var nm=PName[Lot.Pay];var cd=Lot.Tcode;var zs=Base.GetZhuShu(cd);
					var h='<dd style="background-color: transparent;"><span title="'+nm+cd+'['+zs+'注 '+zs*2+'元]" data="'+Lot.Pay+':'+cd+':'+zs+'" class="f_left"><b>'+nm+'&nbsp;&nbsp;<span class="c_ba2636">'+cd+'</span></b>&nbsp;['+zs+'注 '+zs*2+'元]</span><span class="f_right"><a href="javascript:;" title="删除" class="delete">删除</a></span></dd>';
					$('#select_list').append(h);
				}
				Base.ListDel();
				Base.ClearBall();
				Base.ShowMoney();
			}else{
				alert('至少选择一注号码进行投注！');
			}
		});
		$('#del_ball_all').click(function(){/**清空认购栏*/
			$('#select_list').empty();Base.ShowMoney();
		});
		$('#touzhutype input').click(function(){/**投注方式*/
			$('#touzhutype li').removeClass('active');
			var ids = $(this).attr('id');
			if(ids=='mcut'){
				Lot.SelT=1;
				$('#diyBox').hide();
				$(this).parent().parent().addClass('active');
				$('#additional_con').addClass('zhChanggui').removeClass('zhGaoji').show();
				if($('dl.zhuihaoBox_con').length<=0){
					Base.GetCutData();
				}
			}else if(ids=='hcut'){
				Lot.SelT=2;
				$('#diyBox').hide();
				$(this).parent().parent().addClass('active');
				$('#additional_con').addClass('zhGaoji').removeClass('zhChanggui').show();
				if($('dl.zhuihaoBox_con').length<=0){
					Base.GetCutData();
				}
				$('dl.zhuihaoBox_con dd').removeClass('display2').removeClass('active');
			}else{
				Lot.SelT=0;
				$('#diyBox').show();
				$('#additional_con').hide();
			}
			Base.ShowMoney();
		});
		$('#daigoutype input').click(function(){/**购买方式*/
			$('#daigoutype li').removeClass('active');
			var ids = $(this).attr('id');
			if(ids=='hemai'){
				$(this).parent().parent().addClass('active');
				Lot.BuyT=1;
				$('#group_con').show();
			}else{
				$('#group_con').hide();
				Lot.BuyT=0;
			}
			Base.ShowMoney2();
		});
		$('div.tips_open,div.tips_close').click(function(){//展开收起
			var txt = $.trim($(this).text());
			var b = $(this).parent().next();
			if(txt=='收起'){
				b.hide();
				$(this).removeClass('tips_close').addClass('tips_open').html('展开<em></em>');
			}else{
				b.show();
				$(this).removeClass('tips_open').addClass('tips_close').html('收起<em></em>');
			}
		});
		$('#random1').click(function(){
			Base.GetJiXuan(Lot.Pay);
		});
		$('#random5').click(function(){
			for ( var i = 0; i < 5; i++) {
				Base.GetJiXuan(Lot.Pay);
			}
		});
		$('#randomXX').click(function(){
			var num = Number($('#randomNN').val());
			num = num>99?99:num;
			for ( var i = 0; i < num; i++) {
				Base.GetJiXuan(Lot.Pay);
			}
		});
		$('#secretBtnBox>button').click(function(){
			$('#secretBtnBox>button').attr('class','btn_gray');
			$(this).attr('class','btn_org');
		});
		$('#commissionBtnBox>li').click(function(){
			$('#commissionBtnBox>li').removeClass('active');
			$(this).addClass('active');
		});
		$('#reserve_share').keyup(function(){
			Base.ShowMoney2();
		});
		$('#share_cnt').change(function(){
			Base.ShowMoney2();
		});
		$('#qbao').click(function(){
			if($(this).is(":checked")){
				var all = Number($('#tzallmon').text());
				if(all>0){
					var buy = Number($('#share_cnt').val());
					$('#reserve_share').val(all-buy);
				}
			}else{
				$('#reserve_share').val(0);
			}
			Base.ShowMoney2();
		});
		$('#get_data').click(function(){
			if(Lot.IsOpen!=0){
				alert("您好，已暂停销售！");return;
			}else if(Lot.IsBuy){
				alert("数据正在提交中，请稍后。");return;
			}else if(Lot.AMoney==0||Lot.Code.length==0||Lot.Zhushu==0){
				alert('至少选择一注号码才能投注。');return;
			}else if(Lot.Code.length>55000){
				alert('您投注的内容过多，请删减');return;
			}else if(!$('#agree_rule').attr('checked')){
				alert('同意《委托投注规则》才能投注');return;
			}else if(Lot.Qihaos==''||Lot.Beishu==''){
				alert('请选择您要投注的期号！');return;
			}else if(Lot.BuyT==1){//合买
				if(Lot.HMyMon<Math.ceil(Lot.AMoney*0.05)){
					alert('合买发起人至少认购5%，'+Math.ceil(Lot.AMoney*0.05)+'元。');return;
				}else if(Lot.HMyMon>Lot.AMoney){
					alert('合买认购金额不能大于总金额。');return;
				}else if(Lot.HMyBao>0){//如果有保底
					if(Lot.HMyBao>Lot.AMoney){
						alert('保底金额不能大于总金额。');return;
					}else if((Lot.HMyMon+Lot.HMyBao)>Lot.AMoney){
						alert('认购金额+保底金额不能超过总额。');return;
					}
				}
			}
			var alq = Lot.Qihaos.split(',').length;
			var cont='订单信息：'+Lot.Namestr+' 第'+Lot.Qihao+'期<br>应付金额：'+Lot.AMoney+'元';
			if(Lot.BuyT==1){/** 合买 */
				var cont='订单信息：'+Lot.Namestr+' 第'+Lot.Qihao+'期发起合买<br>应付金额：'+(Lot.HMyMon+Lot.HMyBao)+'元';
				if(alq>1){
					cont='订单信息：'+Lot.Namestr+' 连续发起'+alq+'期合买<br>应付金额：'+(Lot.HMyMon+Lot.HMyBao)+'元';
				}
			}else{/** 自购 */
				if(alq>1){
					cont='订单信息：'+Lot.Namestr+' 追号'+alq+'期<br>应付金额：'+Lot.AMoney+'元';
				}
			}
			new $.bcbox({
				title:'支付提示',
				type:'confirm',
				content:cont,
				onClose:function(){if(this.getValue()){Base.Buy()}}
			}).show();
		});
		$('#box_ds .zhantie_textarea').blur(function(){//单式事件
			Base.FormatDS();
			var pastecontent = $("#box_ds .zhantie_textarea").val();
			var num=0;
			if(pastecontent != ""){
				pastecontent = pastecontent.replace(/\r\n/g,"$");
				pastecontent = pastecontent.replace(/\n/g,"$");
				pastecontent = pastecontent.replace(/\s/g,"");
				pastecontent = pastecontent.replace(/(\$)\1+/g, '$1');
				if(pastecontent.substr(pastecontent.length-1,1)=="$"){
					pastecontent = pastecontent.substr(0,pastecontent.length-1);
				}
				num=Number(pastecontent.split("$").length);
			}
			$("#select_info strong").text(num);
			$("#select_info b").text(" "+(num*2)+" ");
			if(num>0){
				$('#confirm_select').addClass('addbtn').removeClass('addbtn_disabled');
			}else{
				$('#confirm_select').addClass('addbtn_disabled').removeClass('addbtn');
			}
		});
		Base.CutStatic();//追号的相关事件
	},
	FormatDS:function(){//单式格式化
		var pasecode = $("#box_ds .zhantie_textarea").val();
		pasecode=pasecode.replace(/[^0-9]/g,'');
		if(pasecode==""){return;}
		len= pasecode.length;
		var lt=1;
		if (Number(Lot.Pay)>=350&&Number(Lot.Pay)<=370){
			len= pasecode.length/2;
			lt=2;
		}
		var num="";
		var numtxt="";
		var n=0;
		var maxnum=3;
		if (['361'].indexOf(Lot.Pay)>=0){maxnum=8;
		}else if(['360'].indexOf(Lot.Pay)>=0){maxnum=7;
		}else if(['359'].indexOf(Lot.Pay)>=0){maxnum=6;
		}else if(['316','358'].indexOf(Lot.Pay)>=0){maxnum=5;
		}else if(['315','357'].indexOf(Lot.Pay)>=0){maxnum=4;
		}else if(['302','355','351','351'].indexOf(Lot.Pay)>=0){maxnum=2;
		}else if(['350'].indexOf(Lot.Pay)>=0){maxnum=1;}
		for(var i=0; i<len; i++){
			if(i%maxnum==0){
				n=1;
			}else{
				n = n + 1;
			}
			if(n<maxnum){
				num = num + pasecode.substr(i*lt,lt)+",";
			}else {
				num = num + pasecode.substr(i*lt,lt);
				numtxt = numtxt + num + "\n";
				num = "";
			}
		}
		$("#box_ds .zhantie_textarea").val(numtxt);
	},
	AddDSLine:function(dscode){//单式添加
		var codes = dscode.split('$');
		var ptt=Lot.Pay;
		var qz='';
		if (['315'].indexOf(ptt)>=0){
			qz='-,';
		}else if(['308'].indexOf(ptt)>=0){
			qz='-,-,';
		}else if(['302'].indexOf(ptt)>=0){
			qz='-,-,-,';
		}
		var reg;
		if(['350'].indexOf(ptt)>=0){
			reg = /^(?:(0[1-9]|1[01]))$/;
		}else if(['355','351','352'].indexOf(ptt)>=0){
			reg = /^(?:(0[1-9]|1[01])[,\|]){1}(?:0[1-9]|1[01])$/;
		}else if(['356','353','354'].indexOf(ptt)>=0){
			reg = /^(?:(0[1-9]|1[01])[,\|]){2}(?:0[1-9]|1[01])$/;
		}else if(['357'].indexOf(ptt)>=0){
			reg = /^(?:(0[1-9]|1[01])[,\|]){3}(?:0[1-9]|1[01])$/;
		}else if(['358'].indexOf(ptt)>=0){
			reg = /^(?:(0[1-9]|1[01])[,\|]){4}(?:0[1-9]|1[01])$/;
		}else if(['359'].indexOf(ptt)>=0){
			reg = /^(?:(0[1-9]|1[01])[,\|]){5}(?:0[1-9]|1[01])$/;
		}else if(['360'].indexOf(ptt)>=0){
			reg = /^(?:(0[1-9]|1[01])[,\|]){6}(?:0[1-9]|1[01])$/;
		}else if(['361'].indexOf(ptt)>=0){
			reg = /^(?:(0[1-9]|1[01])[,\|]){7}(?:0[1-9]|1[01])$/;
		}
		for(var i=0;i<codes.length;i++){
			var codet=codes[i];
			if (['322','120'].indexOf(ptt)>=0){//组三单式
				var eq=0;
				var codesp = codet.split(',');
				if(codesp[0]==codesp[1]){eq+=1;}
				if(codesp[0]==codesp[2]){eq+=1;}
				if(codesp[2]==codesp[1]){eq+=1;}
				if (eq!=1){continue;}
			}else if(['311','117'].indexOf(ptt)>=0){//组六单式
				var eq=0;
				var codesp = codet.split(',');
				if(codesp[0]==codesp[1]){eq+=1;}
				if(codesp[0]==codesp[2]){eq+=1;}
				if(codesp[2]==codesp[1]){eq+=1;}
				if(eq>0){continue;}
			}
			if(Number(ptt)>=350&&Number(ptt)<=370){
				if(!reg.test(codet)||distinct(codet)){continue;}
			}
			var nm=PName[ptt];var cd=qz+codet;var zs=Base.GetZhuShu(cd);
			var h='<dd style="background-color: transparent;"><span title="'+nm+cd+'['+zs+'注 '+zs*2+'元]" data="'+ptt+':'+cd+':'+zs+'" class="f_left"><b>'+nm+'&nbsp;&nbsp;<span class="c_ba2636">'+cd+'</span></b>&nbsp;['+zs+'注 '+zs*2+'元]</span><span class="f_right"><a href="javascript:;" title="删除" class="delete">删除</a></span></dd>';
			$('#select_list').append(h);
		}
	},
	Buy:function(){//自购
		Lot.IsBuy=true;
		var stopWhenFeed = $('input[name=stopWhenFeed]').is(":checked");
		var iscont = stopWhenFeed?0:1;// 0中奖不追 1中奖继续
		var take = $('#commissionBtnBox>li.active').attr('v');
		var isopen = $('#secretBtnBox>button.btn_org').attr('v');
		$.post('/lottery/BuyLot.jzh',{lot:Lot.Name,money:Lot.AMoney,code:Lot.Code,ishm:Lot.BuyT,qihao:Lot.Qihaos,beishu:Lot.Beishu,iscont:iscont,isopen:isopen,buymon:Lot.HMyMon,bao:Lot.HMyBao,take:take,t:local.TimeLong()},function(data){
			switch (data){//-1 期号过期0成功 1金额错误 2余额不足
				case 'no':alert('您还未登录');break;
				case '-1':alert('期号已过期。');break;
				case '0':
					alert('购买成功。');
					$('#select_list').empty();Base.ShowMoney();
					break;
				case '1':alert('金额不正确。');break;
				case '2':alert('您余额不足。');break;
				default:alert('购买失败！');break;
			}
			Lot.IsBuy=false;
		});
	},
	ShowMoney:function(){/**显示投注总金额*/
		Lot.Btpay='';
		Lot.Code='';
		Lot.Zhushu=0;
		$("#select_list dd").each(function(){
			var code =$(this).find('span.f_left').attr('data');
			var codesz=code.split(':');
			if(Lot.Btpay==''){
				Lot.Btpay=codesz[0];
			}else if(Lot.Btpay!=codesz[0]&&Lot.Btpay!='-'){
				Lot.Btpay='-';
			}
			Lot.Code+=code+'#';
			Lot.Zhushu+=Number(codesz[2]);
		});
		Lot.Code=Lot.Code.substring(0,Lot.Code.length-1);
		if(Lot.SelT==2||Lot.SelT==1){//追号
			Lot.Qihaos='';
			Lot.Beishu='';
			Lot.AMoney=0;
			if (Lot.SelT==1){//常规追号
				var jiang=0;
				if (Lot.Btpay!='-'){
					jiang=Mon[Lot.Btpay];
				}
				$('dl.zhuihaoBox_con dd.display1').each(function(){
					var ischeck = $(this).find('input[name=period]').is(":checked");
					if(ischeck){
						$(this).addClass('active');
						Lot.Qihaos += $(this).attr('pid')+',';
						var mul = Number($(this).find('.multiple input.zhuihaoInput').val());
						Lot.Beishu += mul+',';
						var mon = Lot.Zhushu*2*mul;
						Lot.AMoney+=mon;
						$(this).find('.amount em').html(mon);
						$(this).find('.sumAmount').html('<em>'+Lot.AMoney+'</em>元');
						if (jiang>0){
							var tmon = jiang*mul-Lot.AMoney;
							$(this).find('.profit').html(tmon);
							$(this).find('.profitability').html(parseInt((tmon*100)/Lot.AMoney)+'%');
						}else{
							$(this).find('.profit').html('--');
							$(this).find('.profitability').html('--');
						}
					}else{
						$(this).find('.amount em').html(0);
						$(this).find('.sumAmount').html('--');
						$(this).find('.profit').html('--');
						$(this).find('.profitability').html('--');
					}
				});
			}else{
				$('#dbjj').val(Mon[Lot.Btpay]);
				$('dl.zhuihaoBox_con dd.display2').each(function(){
					var ischeck = $(this).find('input[name=period]').is(":checked");
					if($(this).hasClass('active')){
						Lot.Qihaos += $(this).attr('pid')+',';
						var mul = Number($(this).find('.multiple input.zhuihaoInput').val());
						Lot.Beishu += mul+',';
						Lot.AMoney+=Lot.Zhushu*2*mul;
					}
				});
			}
			Lot.Qihaos=Lot.Qihaos.substring(0,Lot.Qihaos.length-1);
			Lot.Beishu=Lot.Beishu.substring(0,Lot.Beishu.length-1);
			var alq = Lot.Qihaos.split(',').length;
			$('#aqi').html(alq);
			$('#amon').html(Lot.AMoney);
		}else{
			Lot.Qihaos=Lot.Qihao;
			Lot.Beishu=$('#beitou').val();
			Lot.AMoney=Number(Lot.Zhushu*Lot.Beishu*2);
		}
		Base.ShowMoney2();
	},
	ShowMoney2:function(){//计算需要支付多少钱
		var alq = Lot.Qihaos.split(',').length;
		if(Lot.BuyT==1){//合买
			$('#tzallmon').html(Lot.AMoney);
			var MinM=Math.ceil(Lot.AMoney*0.05);
			$('#total_fen').html(MinM);
			var Buym = Number($('#share_cnt').val());
			if (Buym<MinM){
				Lot.HMyMon=MinM;
				$('#share_cnt').val(MinM);
			}else if((Lot.HMyBao+Buym)>Lot.AMoney){
				Lot.HMyMon=Lot.AMoney-Lot.HMyBao;
				$('#share_cnt').val(Lot.HMyMon);
			}else{
				Lot.HMyMon=Buym;
			}
			var Buyb = Number($('#reserve_share').val());
			if(Buyb<0){
				Lot.HMyBao=0;$('#reserve_share').val(0);
			}else if((Buyb+Lot.HMyMon)>Lot.AMoney){
				Lot.HMyBao=Lot.AMoney-Lot.HMyMon;
				$('#reserve_share').val(Lot.HMyBao);
			}else{
				Lot.HMyBao=Buyb;
			}
			$('#touzhuInfo').html('方案金额<strong>'+Lot.AMoney+'</strong>元 共'+alq+'期，需支付<b class="c_ba2636">'+(Lot.HMyMon+Lot.HMyBao)+'</b>元(认购<font color="#EF4F00">'+Lot.HMyMon+'</font>元+保底<font color="#EF4F00">'+Lot.HMyBao+'</font>元)');
		}else{//自购
			if(alq>1){
				$('#touzhuInfo').html('本次投注追号共'+alq+'期 需支付<span class="c_ba2534">'+Lot.AMoney+'</span>元');
			}else{
				$('#touzhuInfo').html('您选择了 '+Lot.Zhushu+' 注× '+Lot.Beishu+' 倍=<span class="c_ba2534">'+Lot.AMoney+'</span>元');
			}
		}
	},
	JHBT:function(){/**计划背投*/
		$('dl.zhuihaoBox_con dd').removeClass('display2').removeClass('active');
		var bonus = Number($('#dbjj').val());//单倍奖金
		if(Lot.Btpay=='-'){
			alert('计划倍投不支持混投！');return;
		}else if(Lot.Btpay==''){
			alert('请先选择投注号码！');return;
		}else if(bonus==0){
			alert('请设置单倍奖金！');return;
		}
		var tzqs = Number($('input[name=zhPeriods]').val());
		if(tzqs<1){
			alert("您好，最少投注一期！");return;
		}
		var qsqs = $('select[name=startPeriod] > option:selected').index(); //起始期数索引
		var zqs=$('select[name=startPeriod]>option').length-qsqs; //可投注总期数
		if (tzqs>zqs){//是否超过可投注其数
			alert("您好，您投注的期数超过可投注期数，现在最大可投注期数为"+zqs+"期！");return;
		}
		var qsbs = Number($('input[name=startBeiNum]').val()); //起始倍数
		if (qsbs<1){
			alert("您好，起始倍数最少为1！");return;
		}
		var maxbs = 999; //最大倍数
		if(Lot.Zhushu*2 > bonus){//验证注数是否大于奖金
			alert("按照当前设置，投注金额"+(Lot.Zhushu*2)+"元，超过收益" + bonus + "元，请重新设置！");return;
		}
		var rdoWinCondition = $('input[name=profitMode]:checked').val();
		var syl = Number($('input[name=profitPercent]').val());//最低收益率
		var qjs = 0 //定制起始几期
		var syje =0 //最低收益金额
		if (rdoWinCondition==2){
			qjs = Number($('input[name=threshold]').val());
			if (qjs<1){
				alert("您好，前几期最少为1！");return;
			}
			syl = Number($('input[name=profitPercent1]').val());
		}else if(rdoWinCondition==3){
			syje = Number($('input[name=profitMoney]').val());
		}
		var pretr = 0;//截至上期总投注
		var vArr = new Array(7);
		for(var i = 1;i <= tzqs;i++){
			if(rdoWinCondition==2 && i==(qjs+1)){
				syl = Number($('input[name=profitPercent2]').val());
			}
			if(rdoWinCondition==3){
				while (((bonus * qsbs) - (pretr + (Lot.Zhushu * qsbs * 2)))<syje){
					if(qsbs > maxbs){
						var cqqs= $('select[name=startPeriod] > option').eq(qsqs+i-1).val();
						var msg = "<p style='text-align:left;'>按照当前设置 : 从第<font color='red' style='font-weight:bold'>"+cqqs+"</font>期开始，投注倍数将超过"+maxbs+"倍，建议从以下方面调整方案.</p>";
						msg = msg + "<p style='text-align:left;text-indent:2em;'>1.减少方案期数(推荐)</p>";
						msg = msg + "<p style='text-align:left;text-indent:2em;'>2.降低预期收益率(推荐)</p>";
						msg = msg + "<p style='text-align:left;text-indent:2em;'>3.减少投入注数</p>";
						msg = msg + "<p style='text-align:left;text-indent:2em;'>4.提高最大倍数设置</p>"
						new $.bcbox({
							title:'提示',
							type:'alert',
							content:msg,
						}).show();Base.ShowMoney();return;
					}
					qsbs++;
				}
			}else{
				while(((((bonus * qsbs) - (pretr + (Lot.Zhushu * qsbs * 2))) * 100) / (pretr + (Lot.Zhushu * qsbs*2))) < syl) {
					if(qsbs > maxbs){
						var cqqs= $('select[name=startPeriod] > option').eq(qsqs+i-1).val();
						var msg = "<p style='text-align:left;'>按照当前设置 : 从第<font color='red' style='font-weight:bold'>"+cqqs+"</font>期开始，投注倍数将超过"+maxbs+"倍，建议从以下方面调整方案.</p>";
						msg = msg + "<p style='text-align:left;text-indent:2em;'>1.减少方案期数(推荐)</p>";
						msg = msg + "<p style='text-align:left;text-indent:2em;'>2.降低预期收益率(推荐)</p>";
						msg = msg + "<p style='text-align:left;text-indent:2em;'>3.减少投入注数</p>";
						msg = msg + "<p style='text-align:left;text-indent:2em;'>4.提高最大倍数设置</p>"
						new $.bcbox({
							title:'提示',
							type:'alert',
							content:msg,
						}).show();Base.ShowMoney();return;
					}
					qsbs++;
				}
			}
			pretr += (Lot.Zhushu * qsbs * 2); //累计投入
			tempSy = ((bonus * qsbs) - pretr) * 100 / pretr; //收益率
			vArr[0] =$('select[name=startPeriod] > option').eq(qsqs+i-1).val();
			vArr[1] = qsbs.toString();
			vArr[2] = (Lot.Zhushu * qsbs * 2).toString();
			vArr[3] = pretr.toString();
			vArr[4] = ((bonus * qsbs) - pretr).toString();
			vArr[5] = tempSy.toFixed(2) + "%";
			var thiss = $('dl.zhuihaoBox_con>dd[pid='+vArr[0]+']');
			thiss.addClass('display2').addClass('active');
			thiss.find('.zhuihaoInput').val(vArr[1]);
			thiss.find('.amount>em').html(vArr[2]);
			thiss.find('.sumAmount').html('<em>'+vArr[3]+'</em>元');
			thiss.find('.profit').html(vArr[4]);
			thiss.find('.profitability').html(vArr[5]);
		}
		Base.ShowMoney();
	},
	ListDel:function(){/**删除投注*/
		$('#select_list .delete').click(function(){
			$(this).parent().parent().remove();
			Base.ShowMoney();
		});
	},
	ShowPay:function(p){/**显示玩法选求*/
		$('p.gameIntro span').html(Info[p]);
		$('#pay_box>div').hide();
		if ($('input[name=star_radio]:checked').hasClass('ds')) {
			var ms = $('input[name=star_radio]:checked').attr('ms');
			$('#dsmsg').html(ms);
			$('#box_ds').show();
		}else if('316'==p||'317'==p||'318'==p||'319'==p||'110'==p){
			$('#box_wan,#box_qian,#box_bai,#box_shi,#box_ge').show();
		}else if('308'==p||'112'==p){
			$('#box_bai,#box_shi,#box_ge').show();
		}else if('315'==p){
			$('#box_qian,#box_bai,#box_shi,#box_ge').show();
		}else if('302'==p||'305'==p){
			$('#box_shi,#box_ge').show();
		}else if('301'==p){
            $('#box_wan,#box_qian,#box_bai,#box_shi,#box_ge').show();
		}else if('304'==p||'307'==p||'310'==p||'311'==p||'313'==p||'314'==p||'114'==p||'117'==p){
			if('304'==p){
				$('#box_zuxuan ul:eq(0)').attr('val',7);
			}else{
				$('#box_zuxuan ul:eq(0)').removeAttr('val');
			}
			$('#box_zuxuan').show();
		}else if('309'==p||'312'==p||'113'==p){
			$('#box_s3hz').show();
		}else if('303'==p||'306'==p){
			$('#box_s2hz').show();
		}else if('300'==p){
			$('#box_dxsd').show();
		}else if('115'==p||'118'==p){
			if ('115'==p) {
				$('#box_zshz').show();
			}else if('118'==p){$('#box_zlhz').show();}
		}else if('320'==p||'321'==p||'116'==p||'119'==p){
			var n = 0;
			if('320'==p||'116'==p){n=1;}else if('321'==p||'119'==p){n=2;}
			$('#box_dt ul:eq(0)').attr('val',n);
			$('#box_dt').show();
		//11选5
		}else if('350'==p){
			$('#box_wan').show();
		}else if('351'==p){
			$('#box_wan,#box_qian').show();
		}else if('353'==p){
			$('#box_wan,#box_qian,#box_bai').show();
		}else if('355'==p||'352'==p||'354'==p||'356'==p||'357'==p||'358'==p||'359'==p||'360'==p||'361'==p){
			$('#box_zuxuan').show();
		}else if('362'==p||'363'==p||'364'==p||'365'==p||'366'==p||'367'==p||'368'==p||'369'==p||'370'==p){
			var n = 0;
			if('364'==p||'362'==p){n=1;}
			else if('365'==p||'363'==p){n=2;}
			else if('366'==p){n=3;}
			else if('367'==p){n=4;}
			else if('368'==p){n=5;}
			else if('369'==p){n=6;}
			else if('370'==p){n=7;}
			$('#box_dt ul:eq(0)').attr('val',n);
			$('#box_dt').show();
		/**快三*/
		}else if('400'==p||'401'==p||'402'==p||'403'==p||'405'==p||'406'==p||'407'==p||'408'==p||'409'==p||'410'==p){
			if('400'==p){$('#box_hz').show();}
			else if('401'==p){$('#box_sthtx').show();}
			else if('402'==p){$('#box_sthdx').show();}
			else if('405'==p){$('#box_slhtx').show();}
			else if('406'==p){$('#box_ethfx').show();}
			else if('407'==p){$('#box_ethdx').show();}
			else if('403'==p||'408'==p){$('#box_def').show();}
			else if('410'==p||'409'==p){$('#box_dt').show();}
		}
	},
	ClearBall:function(){//清除选球
		$('ul.bcballbox li,ul.bcballboxs li').removeClass('active_red');
		$("#box_ds .zhantie_textarea").val('');
		Base.BallInfo();
	},
	BallInfo:function(){//计算选球信息
		Lot.Tcode = Base.GetCode();
		var zs = Base.GetZhuShu(Lot.Tcode);
		$('#select_info>strong').html(zs);
		$('#select_info>b').html(zs*2);
		if(zs>0){
			$('#confirm_select').addClass('addbtn').removeClass('addbtn_disabled');
		}else{
			$('#confirm_select').addClass('addbtn_disabled').removeClass('addbtn');
		}
	},
	GetCode:function(){//获取选球
		var l = Lot.Pay;
		var temp='';
		var re='';
		if('316'==l||'317'==l||'318'==l||'319'==l||'110'==l){//五星直选，通选，任选一，任选二,排列五
			$("#box_wan,#box_qian,#box_bai,#box_shi,#box_ge").find("ul.fs_red_area").each(function(index){
				temp =$(this).find('li.'+Base.Ball+' span').text();
				if('318'==l||'319'==l){//任选
					if(temp.length<=0){
						temp='-';
					}
				}
				re+=temp;
				re+=",";
			});
		}else if('315'==l){//四星直选
			re+='-,';
			$("#box_qian,#box_bai,#box_shi,#box_ge").find("ul.fs_red_area").each(function(index){
				temp =$(this).find('li.'+Base.Ball+' span').text();
				re+=temp;
				re+=",";
			});
		}else if('308'==l||'112'==l){//三星直选
			if ('308'==l){
				re+='-,-,';
			}
			$("#box_bai,#box_shi,#box_ge").find("ul.fs_red_area").each(function(index){
				temp =$(this).find('li.'+Base.Ball+' span').text();
				re+=temp;
				re+=",";
			});
		}else if('302'==l||'305'==l){//二星直选，二组分位
			re+='-,-,-,';
			$("#box_shi,#box_ge").find("ul.fs_red_area").each(function(index){
				temp =$(this).find('li.'+Base.Ball+' span').text();
				re+=temp;
				re+=",";
			});
		}else if('301'==l){//一星直选
			// re+='-,-,-,-,';
			// re+=$("#box_ge").find('ul.fs_red_area li.'+Base.Ball+' span').text()+',';
            $("#box_wan,#box_qian,#box_bai,#box_shi,#box_ge").find("ul.fs_red_area").each(function(index){
                temp =$(this).find('li.'+Base.Ball+' span').text();
                if('318'==l||'319'==l){//任选
                    if(temp.length<=0){
                        temp='-';
                    }
                }
                re+=temp;
                re+=",";
            });
		}else if('314'==l||'310'==l||'311'==l||'313'==l||'307'==l||'304'==l||'114'==l||'117'==l){//直选组合，组三，组六，包胆,组选
			$("#box_zuxuan").find('ul.fs_red_area li.'+Base.Ball+' span').each(function(index){
				temp =$(this).text();
				re += temp;
				re += ",";
			});
		}else if('303'==l||'306'==l){//二星和值,二组包点
			$("#box_s2hz").find('ul.fs_red_area li.'+Base.Ball+' span').each(function(index){
				temp =$(this).text();
				re += temp;
				re += ",";
			});
		}else if('309'==l||'312'==l||'113'==l){//三星和值,三组包点
			$("#box_s3hz").find('ul.fs_red_area li.'+Base.Ball+' span').each(function(index){
				temp =$(this).text();
				re += temp;
				re += ",";
			});
		}else if('115'==l||'118'==l){//组三，组六和值
			if ('115'==l) {
				$("#box_zshz").find('ul.fs_red_area li.'+Base.Ball+' span').each(function(index){
					temp =$(this).text();
					re += temp;
					re += ",";
				});
			}else if('118'==l){
				$("#box_zlhz").find('ul.fs_red_area li.'+Base.Ball+' span').each(function(index){
					temp =$(this).text();
					re += temp;
					re += ",";
				});
			}
		}else if('320'==l||'321'==l||'116'==l||'119'==l){//胆拖玩法
			$("#box_dt>ul.fs_red_area:eq(0)").find('li.'+Base.Ball+' span').each(function(index){
				temp =$(this).text();
				re += temp;
				re += ",";
			});
			re = re.substr(0,re.length-1)+'$';
			$("#box_dt>ul.fs_red_area:eq(1)").find('li.'+Base.Ball+' span').each(function(index){
				temp =$(this).text();
				re += temp;
				re += ",";
			});
		}else if('300'==l){//大小单双
			$("#box_dxsd div ul.dxds_list").each(function(index){
				temp =$(this).find('li.'+Base.Ball+' span').text();
				re+=temp;
				re+=",";
			});
		//11选5
		}else if('350'==l){
			$("#box_wan").find('ul.fs_red_area li.'+Base.Ball+' span').each(function(index){
				temp =$(this).text();
				re += temp;
				re += " ";
			});
		}else if('351'==l){
			$("#box_wan,#box_qian").find("ul.fs_red_area").each(function(index){
				$(this).find('li.'+Base.Ball+' span').each(function(index){
					temp =$(this).text();
					re += temp;
					re += " ";
				});
				re = re.substr(0,re.length-1);
				re+=",";
			});
		}else if('353'==l){
			$("#box_wan,#box_qian,#box_bai").find("ul.fs_red_area").each(function(index){
				$(this).find('li.'+Base.Ball+' span').each(function(index){
					temp =$(this).text();
					re += temp;
					re += " ";
				});
				re = re.substr(0,re.length-1);
				re+=",";
			});
		}else if('352'==l||'354'==l||'355'==l||'356'==l||'357'==l||'358'==l||'359'==l||'360'==l||'361'==l){
			$("#box_zuxuan").find('ul.fs_red_area li.'+Base.Ball+' span').each(function(index){
				temp =$(this).text();
				re += temp;
				re += ",";
			});
		}else if('362'==l||'363'==l||'364'==l||'365'==l||'366'==l||'367'==l||'368'==l||'369'==l||'370'==l){
			$("#box_dt>ul.fs_red_area:eq(0)").find('li.'+Base.Ball+' span').each(function(index){
				temp =$(this).text();
				re += temp;
				re += ",";
			});
			re = re.substr(0,re.length-1)+'$';
			$("#box_dt>ul.fs_red_area:eq(1)").find('li.'+Base.Ball+' span').each(function(index){
				temp =$(this).text();
				re += temp;
				re += ",";
			});
		}
		if(re.length>0){re = re.substr(0,re.length-1);}
		return re;
	},
	GetZhuShu:function(c){//根据选求内容计算注数
		if(c.length==0){return 0;}
		var zs=0;
		var arr=c.split(',');
		var t=Lot.Pay;
		if('320'==t||'116'==t){//组三胆拖
			c=c.replaceAll(',','');
			var cod=c.split('\$');
			var cnun = cod[0].length;//胆码长度
			if (cnun==1) {
				var cnum=0;
				try {
					cnum = cod[1].length;//拖码长得
				} catch (e) {}
				zs=Base.Comb(cnum,2-cnun)*2;
			}else if(cnun>1){
				alert('胆码长度只能为1');
			}
			return zs;
		}else if('321'==t||'119'==t){//组六胆拖
			c=c.replaceAll(',','');
			var cod = c.split("\$");
			var cnun = cod[0].length;
			if (cnun==1||cnun==2){
				var cnum=0;
				try {
					cnum = cod[1].length;//拖码长得
				} catch (e) {}
				zs=Base.Comb(cnum,3-cnun)*1;
			}else if(cnun>2){
				alert('胆码长度1-2个');
			}
			return zs;
		}else if('309'==t||'312'==t||'303'==t||'306'==t||'113'==t||'115'==t||'118'==t){
			for (var i=0; i<arr.length; i++){
				if('309'==t||'113'==t){//三星和值
					zs += SXHZ[arr[i]];
				}else if('312'==t){//三组包点
					zs += SZBD[arr[i]];
				}else if('303'==t){//二星和值
					zs += EXHZ[arr[i]];
				}else if('306'==t){//二组包点
					zs += EXBD[arr[i]];
				}else if('115'==t){//组三和值
					zs += SXZSHZ[arr[i]];
				}else if('118'==t){//组六和值
					zs += SXZLHZ[arr[i]];
				}
			}
			return zs;
		}else if('304'==t||'310'==t||'311'==t||'314'==t||'114'==t||'117'==t){
			var l = arr.length;
			var base=2;
			var ddbb=1;
			if('304'==t){//二星组选
				if(l<2||l>7){
					return 0;
				}
			}else if('310'==t||'114'==t){//组三
				ddbb=2;
			}else if('311'==t||'117'==t){//组六
				base=3;
			}else if('314'==t){//直选组合
				base=3;ddbb=6
				if(l<3){
					return 0;
				}
			}
			zs=Base.Comb(l,base)*ddbb;
			return zs;
		}else if('313'==t){//三组包胆
			zs = arr.length*55;
			return zs;
		}else if('307'==t){//二组包胆
			zs = arr.length*10;
			return zs;
		}else if('318'==t || '301'==t){//任选一
			for (var i=0; i<arr.length; i++){
				zs += arr[i].replace('-','').length;
			}
			return zs;
		}else if('319'==t){//任选二
			var l0 = arr[0].replace("-", "").length;
			var l1 = arr[1].replace("-", "").length;
			var l2 = arr[2].replace("-", "").length;
			var l3 = arr[3].replace("-", "").length;
			var l4 = arr[4].replace("-", "").length;
			zs=l0*(l1+l2+l3+l4)+l1*(l2+l3+l4)+l2*(l3+l4)+l3*l4;
			return zs;
		/**11选5开始*/
		}else if('355'==t||'356'==t||'357'==t||'358'==t||'359'==t||'360'==t||'361'==t||'352'==t||'354'==t){//任选二-八，前二，三组选
			var l = arr.length;
			var dbase=0;
			if ('355'==t||'352'==t){dbase=2;}
			else if('356'==t||'354'==t){dbase=3;}
			else if('357'==t){dbase=4;}
			else if('358'==t){dbase=5;}
			else if('359'==t){dbase=6;}
			else if('360'==t){dbase=7;}
			else if('361'==t){dbase=8;}
			zs=Base.Comb(l,dbase);
			return zs;
		}else if('364'==t||'365'==t||'366'==t||'367'==t||'368'==t||'369'==t||'370'==t||'362'==t||'363'==t){//任选二-八，前二，三组选胆拖
			zs=0;
			var max=0;
			c=c.replaceAll(',','');
			var arrcode = c.split("\$");
			if('364'==t||'362'==t){max=2;}
			else if('365'==t||'363'==t){max=3;}
			else if('366'==t){max=4;}
			else if('367'==t){max=5;}
			else if('368'==t){max=6;}
			else if('369'==t){max=7;}
			else if('370'==t){max=8;}
			var cnun = arrcode[0].length/2;
			if (cnun<max&&cnun>0){
				var cnum = arrcode[1].length/2;
				zs=Base.Comb(cnum,max-cnun);
			}else if(cnun>=max){
				alert('胆码长度少于'+max+'个');
			}
			return zs;
		}else if('350'==t){//前一
			var l = arr[0].split(' ');
			zs = 1*l.length;
			return zs;
		}else if('351'==t){//前二
			zs = 0;
			if(arr.length==2){
				var tt1 = arr[0].split(' ');
				var tt2 = arr[1].split(' ');
				for (var i=0; i<tt1.length; i++){
					if(tt1[i] == '')continue;  
					for (var k=0;k<tt2.length;k++){
						if(tt2[k] == '')continue;  
						if(tt1[i] != tt2[k]){
							zs++;
						}
					}
				}
			}
			return zs;
		}else if('353'==t){//前三
			zs = 0;
			if(arr.length==3){
				var tt1=arr[0].split(' ');
				var tt2=arr[1].split(' ');
				var tt3=arr[2].split(' ');
				for (var i=0; i<tt1.length; i++){
					if(tt1[i] == '')continue;  
					for (var k=0;k<tt2.length;k++){
					   if(tt2[k] == '')continue;  	
					   for (var n=0; n<tt3.length;n++){
						  if(tt3[n] == '')continue;  
						  if(tt1[i] != tt3[n] && tt2[k] != tt3[n] && tt1[i] != tt2[k]){
							zs++;
						  }
					   }
					}
				}
			}
			return zs;
		}else{
			zs=1;
			for (var i=0; i<arr.length; i++){
				zs *= arr[i].length;
			}
			return zs;
		}
	},
	Comb:function(n,m){//组合个数计算
		var n1=1, n2=1;
		for (var i=n,j=1; j<=m; n1*=i--,n2*=j++);
		return n1/n2;
	},
	CutStatic2:function(){//追号相关事件2
		$('input[name=period]').change(function(){
			var check = $(this).is(":checked");
			if (check){
				$(this).parent().parent().parent().addClass('active');
			}else{
				$(this).parent().parent().parent().removeClass('active');
			}
			Base.ShowMoney();
		});
		$('input[name=zhuihaoNum]').change(function(){
			$(this).val($(this).val().replace(/[^\d]/g,''));
			var num = Number($(this).val()==0?1:$(this).val());
			$(this).val(num);
			Base.ShowMoney();
		});
	},
	CutStatic:function(){//追号相关的事件
		$('input[name=periodLeader]').change(function(){
			var check = $(this).is(":checked");
			var ac='active';
			var tclass=Lot.SelT==1?'display1':'display2';
			if(check){
				var tdom = $('dl.zhuihaoBox_con').find('dd.'+tclass+':not(.disabled)');
				var mul = $('input[name=zhuihaoNumLeader]').val();
				tdom.addClass(ac).find('input[name=period]').attr("checked",true);
				tdom.find('input[name=zhuihaoNum]').val(mul);
			}else{
				$('dl.zhuihaoBox_con').find('dd').removeClass(ac);
				$('input[name=period]').removeAttr("checked");
				$('input[name=zhuihaoNum]').val('');
			}
			Base.ShowMoney();
		});
		$('input[name=periodNumLeader]').change(function(){
			$(this).val($(this).val().replace(/[^\d]/g,''));
			var num = Number($(this).val()==0?1:$(this).val());
			$(this).val(num);
			if(num>=1){
				var ac='active';
				var tclass=Lot.SelT==1?'display1':'display2';
				var mul = $('input[name=zhuihaoNumLeader]').val();
				$('dl.zhuihaoBox_con').find('dd').removeClass(ac)
				$('input[name=period]').removeAttr("checked");
				$('input[name=zhuihaoNum]').val('');
				var tdom = $('dl.zhuihaoBox_con').find('dd:not(.disabled)');
				tdom.removeClass(tclass);
				tdom.each(function(index){
					if(index<num){
						$(this).addClass(tclass).addClass(ac).find('input[name=period]').attr("checked",true);
						$(this).find('input[name=zhuihaoNum]').val(mul);
					}else{
						return false;
					}
				});
			}
			Base.ShowMoney();
		});
		$('input[name=zhuihaoNumLeader]').keyup(function(){
			$(this).val($(this).val().replace(/[^\d]/g,''));
			var num = Number($(this).val()==0?1:$(this).val());
			$(this).val(num);
			if(num>=1){
				var tclass=Lot.SelT==1?'display1':'display2';
				$('dl.zhuihaoBox_con').find('dd.'+tclass+':not(.disabled)').find('input[name=zhuihaoNum]').val(num);
			}
			Base.ShowMoney();
		});
		$('input[name=profitMode]').click(function(){
			$('span.setProfit_com span').addClass('grayRow');
			$(this).parent().parent().find('input[type=text]').attr('disabled','');
			$(this).parent().removeClass('grayRow');
			$(this).parent().find('input[type=text]').removeAttr('disabled');
		});
		$('#creatBtjhBtn').click(function(){
			Base.JHBT();
		});
	},
	GetCutData:function(){//获取追号数据
		$('#chckHolder').show();
		$.post('/lottery/Mode!CutList.jzh',{lot:Lot.Name,t:local.TimeLong()},function(data){
			var selecthtml='';
			var datahtml='<dl class="zhuihaoBox_con clearfix">';
			if(data.msg!='err'&&data.msg!='no'){
				for ( var i = 0; i < data.msg.length; i++){
					var qhl = data.msg[i].lot_qihao;
					var qhs = qhl.substr(2,qhl.length);
					selecthtml+='<option value="'+qhl+'">'+qhs;
					if (i%2==0) {
						datahtml+='<dd class="';
					}else{
						datahtml+='<dd class="zhbg_f7f7f7 sumzhbg_f7f7f7';
					}
					if(i<10){
						datahtml+=' display1';
					}
					datahtml+='" pid="'+qhl+'"><span class="sumSerialNum">'+Number(i+1)+'</span><span class="serialNum">1</span><span class="period"><label><input type="checkbox" checked="checked" name="period" value="'+qhl+'">'+qhs+'</label>';
					if(i==0){
						datahtml+='<em>(当前期)</em>';
						selecthtml+='[当前期]';
					}
					selecthtml+='</option>';
					datahtml+='</span><span class="multiple"><input type="text" style="ime-mode:disabled;" class="zhuihaoInput" name="zhuihaoNum" autocomplete="off" pid="'+qhl+'" value="1"> 倍</span>';
					datahtml+='<span class="amount"><em>0</em>元</span><span class="sumAmount">--</span>';
					datahtml+='<span class="profit">--</span><span class="profitability">--</span></dd>';
				}
			}else{
				datahtml+='暂无追号';
			}
			datahtml+='</dl>';
			$('#chckHolder').hide();
			$('#additional_con .gpZhBox_com').html(datahtml);
			$('select[name=startPeriod]').html(selecthtml);
			Base.CutStatic2();
			Base.ShowMoney();
		},'json');
	},
	GetJiXuan:function(p){//机选
		var codeArr=new Array();
		var code;
		var min;
		var max;
		if(['300'].indexOf(p)>=0){
			min=0;max=3;
		}else if(['113','309','312'].indexOf(p)>=0){
			min=0;max=27;
		}else if(['115'].indexOf(p)>=0){
			min=1;max=26;
		}else if(['118'].indexOf(p)>=0){
			min=3;max=24;
		}else if(['303','306'].indexOf(p)>=0){
			min=0;max=18;
		}else if(Number(p)>=350&&Number(p)<=399){//11选5
			min=1;max=11;
		}else{
			min=0;max=9;
		}
		var num=0;
		if (['110','316','317'].indexOf(p)>=0){
			num=5;
		}else if(['315'].indexOf(p)>=0){
			num=4;
		}else if(['112','308'].indexOf(p)>=0){
			num=3;
		}else if(['302','305'].indexOf(p)>=0){
			num=2;
		}else if(['301','307','313'].indexOf(p)>=0){
			num=1;
		}
		if (false){
		}else if(['113','115','118','303','306','309','312'].indexOf(p)>=0){//和值
			code=local.getRandom(min,max)+'';
		}else if(['114','116','304','310','320'].indexOf(p)>=0){//组三,组三胆拖
			codeArr[0]=local.getRandom(min,max);
			codeArr[1]=local.getRandom(min,max);
			while(codeArr[1]==codeArr[0]){
				codeArr[1] = local.getRandom(min,max);
			}
			if (['116','320'].indexOf(p)>=0) {
				code=codeArr.join("$");
			}else{
				code=codeArr.join(",");
			}
		}else if(['117','119','311','314','321'].indexOf(p)>=0){//组六,组六胆拖
			var dm = local.getRandom(min,max);
			codeArr[0] = local.getRandom(min,max);
			codeArr[1] = local.getRandom(min,max);
			while((dm==codeArr[0])||(dm==codeArr[1])||(codeArr[0]==codeArr[1])){
				codeArr[0] = local.getRandom(min,max);
				codeArr[1] = local.getRandom(min,max);
			}
			if (['119','321'].indexOf(p)>=0) {
				code=dm+'$'+codeArr.join(",");
			}else{
				code=dm+','+codeArr.join(",");
			}
		}else if(['300'].indexOf(p)>=0){//大小单双
			codeArr[0] = local.getRandom(min,max);
			codeArr[1] = local.getRandom(min,max);
			code=codeArr.join(",");
			code=code.replace(/0/g,"大").replace(/1/g,"小").replace(/2/g,"单").replace(/3/g,"双");
		}else if(['318'].indexOf(p)>=0){//任选一
			codeArr=['-','-','-','-','-'];
			codeArr[local.getRandom(0,4)]=local.getRandom(min,max);
			code=codeArr.join(",");
		}else if(['319'].indexOf(p)>=0){//任选二
			codeArr=['-','-','-','-','-'];
			var ind = local.getRandom(0,4);
			var ind2 = local.getRandom(0,4);
			while(ind2==ind){
				ind2 = local.getRandom(0,4);
			}
			codeArr[ind]=local.getRandom(min,max);
			codeArr[ind2]=local.getRandom(min,max);
			code=codeArr.join(",");
		/**11选5部分*/
		}else if(Number(p)>=350&&Number(p)<=399){//11选5
			var _11x5=['01','02','03','04','05','06','07','08','09','10','11'];
			var nums=0;
			if (['350'].indexOf(p)>=0) {
				nums=1;
			}else if(['351','352','355','362','364'].indexOf(p)>=0){
				nums=2;
			}else if(['353','354','356','363','365'].indexOf(p)>=0){
				nums=3;
			}else if(['357','366'].indexOf(p)>=0){
				nums=4;
			}else if(['358','367'].indexOf(p)>=0){
				nums=5;
			}else if(['359','368'].indexOf(p)>=0){
				nums=6;
			}else if(['360','369'].indexOf(p)>=0){
				nums=7;
			}else if(['361','370'].indexOf(p)>=0){
				nums=8;
			}
			for(var n=0;n<nums;n++){
				index = local.getRandom(min,max);
				codeArr[n] = _11x5[index];
				while(typeof(_11x5[index])=="undefined"){
					index = local.getRandom(min,max);
					codeArr[n] =_11x5[index];
				}
				_11x5.remove(index);
			}
			code=codeArr.join(",");
			if (['362','363','364','365','366','367','368','369','370'].indexOf(p)>=0){
				code=code.replace(',','$');
			}
		}else{
			for(var n=0;n<num;n++){
				codeArr[n]= local.getRandom(min,max);
			}
			code=codeArr.join(",");
			if (['301'].indexOf(p)>=0){
				code='-,-,-,-,'+code;
			}else if(['305','302'].indexOf(p)>=0){
				code='-,-,-,'+code;
			}else if(['308'].indexOf(p)>=0){
				code='-,-,'+code;
			}else if(['315'].indexOf(p)>=0){
				code='-,'+code;
			}
		}
		if (code!=''){
			var nm=PName[p];var cd=code;var zs=Base.GetZhuShu(cd);
			var h='<dd style="background-color: transparent;"><span title="'+nm+cd+'['+zs+'注 '+zs*2+'元]" data="'+Lot.Pay+':'+cd+':'+zs+'" class="f_left"><b>'+nm+'&nbsp;&nbsp;<span class="c_ba2636">'+cd+'</span></b>&nbsp;['+zs+'注 '+zs*2+'元]</span><span class="f_right"><a href="javascript:;" title="删除" class="delete">删除</a></span></dd>';
			$('#select_list').append(h);
			Base.ListDel();
			Base.ShowMoney();
		}
	}
}