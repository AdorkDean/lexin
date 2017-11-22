<%@ page language="java" pageEncoding="utf-8" %>
<%@page import="com.caipiao.activity.SignEntity" %>
<%@page import="com.caipiao.activity.SignEntityService" %>
<%@page import="com.caipiao.utils.TimeUtil" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>欢乐签到活动-遨游彩票网</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <link rel="stylesheet" type="text/css" href="/activity/css/sigin.css"/>
    <link rel="stylesheet" type="text/css" href="/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="/css/core.css"/>
    <script src="/js/jquery-1.7.2.min.js"></script>
    <script src="/js/local.js"></script>
</head>
<%
    int userid = -1;
    int day = 0;
    Object objs = session.getAttribute(UserSession.bcjzhuser);
    if (null != objs) {
        Bc_user find = UserStatic.find(objs.toString());
        if (null != find) {
            userid = find.getUser_id();
        }
    }
    if (-1 != userid) {
        SignEntityService dao = new SignEntityService();
        SignEntity sign = dao.find(userid, TimeUtil.getToday(TimeUtil.S_Y));
        if (null == sign) {
            sign = dao.find(userid, TimeUtil.getYesterday(TimeUtil.S_Y));
        }
        if (null != sign) {
            day = sign.getSignAll();
        }
    } else {
        out.print("<script>parent.location.href='/login.html';</script>");
    }
%>
<body style="background-color: #ffdc7e;">
<%@ include file="/top.jsp" %>
<div class="jdd-hd-top">
    <div class="jdd-hd-cd-a">
        <ul>
            <input id="btnHB20" type="button" value="今日签到" class="jdd-hd-cd_lib"/>
        </ul>
    </div>
    <div class="jdd-hd-cd-b">
        <ul>
            <input id="btnHB60" type="button" value="领取大礼包" class="jdd-hd-cd_lib"/>
        </ul>
    </div>
    <!-- <div style="margin:60px 0 0 300px;float: left;width: 80%"><font size="150px;">即将上线！！！！</div> -->
    <div style="margin:60px 0 0 300px;float: left;width: 80%"><font size="150px;">您已连续签到<font color="red"><%=day%>
    </font>天</font></div>
</div>

<div class="jdd-hd-min">
    <div class="jdd-hd-min_tit">
        活动规则
    </div>
    <p class="jdd-hd-min-texta">
        1、活动时间：2014-10-26 到 2014-11-26 。
        <br/>
        2、每天投注满<font color="red">999</font>元即可完成每日签到任务，抽取签到好礼有<font color="red">3</font>元、<font
            color="red">5</font>元、<font color="red">10</font>元、<font color="red">15</font>元、<font color="red">38</font>元、<font
            color="red">118</font>元不等。玩家随机抽取。连续签到7天还可额外获取<font color="red">38</font>元活跃礼包，签到满15天可获取至尊礼包<font
            color="red">58</font>元。
        <br/>
        3、相同ip用户签到不可重复参与。
        <br/>
        4. 获得活跃礼包和至尊礼包的当天不领取，次日无法领取，视为作废。
        <br/>
        5. 任何对冲刷两行为不计入有效投注，【遨游】保留取消该账户赠送礼金的权利。
        <br/>
        6. 【遨游】保留此次活动作出更改、终止权利，并享有最终解释权。
    </p>
</div>
<div class="clear"></div>
<div id="divNG">
</div>
</body>
<%@ include file="/foot.jsp" %>
<script>
    $(function () {
        $('#btnHB20').click(function () {
            location.href = '/activity/SignEntityServlet.jzh';
        });
        $('#btnHB60').click(function () {
            location.href = '/activity/SignEntityServlet!Big.jzh';
        });
    });
</script>
</html>
