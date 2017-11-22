<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>乐欣彩票帮助中心</title>
    <link rel="stylesheet" href="/css/base.css"/>
    <link rel="stylesheet" href="/css/core.css"/>
    <link rel="stylesheet" href="/css/help/help.css"/>
    <script src="/js/jquery-1.7.2.min.js"></script>
    <script src="/js/local.js"></script>
</head>
<body>
<%@ include file="/top.jsp" %>
<div class="main gray_l_border">
    <div class="index_help_con">
        <%@ include file="/help/menu.jsp" %>
        <div class="index_help_right ">
            <div class="gray_border">
                <div class="clum_title b_b_CBCBCB">购买流程</div>
                <div class="step">
                    <ul>
                        <li>
                            <span class="text">1. 选号</span>
                            <span class="img_1"></span>
                            <span class="img_next"></span>
                        </li>
                        <li>
                            <span class="text">2. 填写领奖人信息</span>
                            <span class="img_2"></span>
                            <span class="img_next"></span>
                        </li>
                        <li>
                            <span class="text">3. 支付</span>
                            <span class="img_3"></span>
                            <span class="img_next"></span>
                        </li>
                        <li>
                            <span class="text">4. 兑奖</span>
                            <span class="img_4"></span>
                        </li>
                    </ul>
                </div>

            </div>

            <div class="gray_border  mt10 clear">
                <div class="clum_title b_b_CBCBCB">彩种介绍</div>
                <div class="cz_list">
                    <ul style="height:auto">
                        <li>
                            <span class="cz_img cz_img_1"></span>
                            <span class="cz_text">
					<a href="/help/play/play_ssq.jsp"><b>双色球</b><br>彩民的最爱<br> 每期销量过亿</a>
                </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_2"></span>
                            <span class="cz_text">
					<a href="/help/play/play_dlt.jsp"><b>大乐透</b><br>奖金最丰厚<br>3元赢1600万</a>
                </span>
                        </li>
                        <!-- <li>
                            <span class="cz_img cz_img_3"></span>
                            <span class="cz_text">
                                <a href=""><b>七星彩</b><br> 二/五/日开奖<br>2元赢500万</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_4"></span>
                            <span class="cz_text">
                                <a href=""><b>七乐彩</b><br>一/三/五开奖<br>2元赢500万</a>
                            </span>
                        </li> -->
                        <li>
                            <span class="cz_img cz_img_5"></span>
                            <span class="cz_text">
					<a href="/help/play/play_fc3d.jsp"><b>福彩3D</b><br>简单三位数<br>轻松赢千元</a>
                </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_6"></span>
                            <span class="cz_text">
					<a href="/help/play/play_pl3.jsp"><b>排列三</b><br> 天天开奖<br>轻松赢千元</a>
                </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_7"></span>
                            <span class="cz_text">
					<a href="/help/play/play_pl5.jsp"><b>排列五</b><br>天天开奖<br>2元赢10万</a>
				</span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_8"></span>
                            <span class="cz_text">
					<a href="/help/play/play_sd11x5.jsp"><b>十一运夺金</b><br>每天78期<br>期期好运气</a> 
                </span>
                        </li>
                        <li>
                            <span class="czLogo icon_jxd11"></span>
                            <span class="cz_text">
					<a href="/help/play/play_jx11x5.jsp"><b>江西11选5</b><br/>每天78期<br/>单注可中1170元</a> 
                </span>
                        </li>
                        <li>
                            <span class="czLogo icon_gdd11"></span>
                            <span class="cz_text">
					<a href="/help/play/play_gd11x5.jsp"><b>广东11选5</b><br/>每天84期<br/>猜对1个号就中奖</a> 
                </span>
                        </li>
                        <li>
                            <span class="czLogo icon_jxd11"></span>
                            <span class="cz_text">
					<a href="/help/play/play_cq11x5.jsp"><b>重庆11选5</b><br/>每天85期<br/>单注可中1170元</a> 
                </span>
                        </li>
                        <!-- <li>
                            <span class="czLogo icon_hljd11"></span>
                             <span class="cz_text">
                                <a href=""><b>好运11选5</b><br/>10分钟一期<br/>猜对1个号就中奖</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_k3"></span>
                             <span class="cz_text"><a href="">
                            <b>新快3</b><br/>
                           10分钟一期<br/>
                           最易中的高频彩</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_k3"></span>
                            <span class="cz_text">
                                <a href=""><b>快3</b><br>10分钟一期<br>最易中的高频彩</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_k3"></span>
                            <span class="cz_text">
                                <a href=""><b>老快3</b><br>10分钟一期<br>最易中的高频彩</a>
                            </span>
                        </li>
                        <li>
                            <span class="czLogo icon_kuai2"></span>
                             <span class="cz_text">
                                <a href=""><b>快2</b><br/>5分钟一期<br/>猜对1个号就中奖</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_13"></span>
                            <span class="cz_text">
                                <a href=""><b>快乐8</b><br>每天179期<br>最高奖500万</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_14"></span>
                            <span class="cz_text">
                                <a href=""><b>竞彩足球</b><br/>玩法多、返奖高<br>赢取100万大奖</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_15"></span>
                            <span class="cz_text">
                                <a href=""><b>竞彩篮球</b><br/> 玩法多、返奖高<br>赢取100万大奖</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_9"></span>
                            <span class="cz_text">
                                <a href=""><b>胜负十四</b><br> 足彩最火玩法<br> 轻松赢500万</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_10"></span>
                            <span class="cz_text">
                                <a href=""><b>任选九场</b><br>猜中胜负即中奖<br>大奖500万</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_11"></span>
                            <span class="cz_text">
                                <a href=""><b>单场胜平负</b><br> 快乐单场<br> 玩法多样</a>
                            </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_klpk"></span>
                            <span class="cz_text">
                                <a href=""><b>快乐扑克</b><br>3张扑克牌<br>简单中大奖</a>
                            </span>
                        </li>  -->
                        <li>
                            <span class="cz_img cz_img_12"></span>
                            <span class="cz_text">
					<a href="/help/play/play_jxssc.jsp"><b>江西时时彩</b><br>每天84期<br> 最高奖11.6万</a> 
                </span>
                        </li>
                        <li>
                            <span class="cz_img cz_img_12"></span>
                            <span class="cz_text">
					<a href="/help/play/play_cqssc.jsp"><b>老时时彩</b><br>每天120期<br> 最高赢10万</a> 
                </span>
                        </li>
                        <li class="no_cz">
                            <span class="cz_img cz_img_none"></span>
                            <span class="cz_text">
                	<b>精彩新彩种</b><br> 即将上线<br>敬请期待
                </span>
                        </li>
                        <div class="clear"></div>
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<%@ include file="/foot.jsp" %>
</html>