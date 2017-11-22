<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/adminsqwe/css/admin.css">
    <script language="javascript" type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<table class="mtb" cellspacing="0" style="width:90%;margin:auto">
    <caption>提款查询</caption>
    <tr>
        <td>
            <form action="/admin/AdminCaiwu!Draw.jzh" method="get">用户名：<input name="user" id="user" style="width:100px"
                                                                              value="${user}" type="text"/> | 开始时间：
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="btime" id="btime"
                       style="width:100px" value="${btime}">-结束时间： <input type="text"
                                                                          onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                                          name="etime" id="etime" style="width:100px"
                                                                          value="${etime}">
                | 状态：<select name="status" id="status">
                    <option value="-1">全部</option>
                    <option value="0">提款申请</option>
                    <option value="1">处理中</option>
                    <option value="2">提款成功</option>
                    <option value="4">拒绝提款</option>
                    <option value="3">用户取消</option>
                </select>
                | 方式：<select name="type" id="type">
                    <option value="-1">全部</option>
                    <option value="0">银行卡</option>
                    <option value="1">第三方</option>
                </select>
                | 用户类型：<select name="ut" id="ut">
                    <option value="-1">全部</option>
                    <option value="0">普通</option>
                    <option value="1">代理</option>
                    <option value="2">测试</option>
                    <option value="7">客服</option>
                    <option value="8">财务</option>
                    <option value="9">超级</option>
                </select>
                | <input type="submit" value="查询"/></form>
        </td>
    </tr>
    <tr>
        <td>便捷筛选：<a href="/admin/AdminCaiwu!Draw.jzh?ut=-1">全部</a> | <a href="/admin/AdminCaiwu!Draw.jzh?ut=0">普通</a>
            <div style="float:right;margin-right:10px;"><a
                    href="/admin/AdminCaiwu!DownDraw.jzh?user=${user}&btime=${btime}&etime=${etime}&type=${type}&status=${status}&ut=${ut}">下载查询结果</a>
            </div>
        </td>
    </tr>
    <c:if test="${null!=page}">
        <tr>
            <td align="center">
                <div class="my_page">
                    <div id="page_wrapper" class="page">${page}</div>
                </div>
            </td>
        </tr>
    </c:if>
    <tr>
        <td>
            <table width="100%">
                <thead>
                <tr class="tit">
                    <td>编号&时间</td>
                    <td>提款用户</td>
                    <td>提款金额</td>
                    <td>手续费</td>
                    <td>持卡人&卡号&银行&开户行</td>
                    <td width="6%">状态</td>
                    <td>操作员&时间</td>
                    <td>描述</td>
                    <td width="20%">操作</td>
                </tr>
                <thead>
                <tbody>
                <c:choose>
                    <c:when test="${null!=list}">
                        <c:forEach var="c" items="${list}">
                            <tr>
                                <td>${c.draw_item}<br>${c.draw_time}</td>
                                <td><a target="_blank"
                                       href="/admin/AdminUser!Desc.jzh?i=${c.user_id}">${c.user_name}</a>
                                    <c:choose>
                                        <c:when test="${c.user_type == 0}">（普）</c:when>
                                        <c:when test="${c.user_type == 1}">（<font color="red">代</font>）</c:when>
                                        <c:when test="${c.user_type == 2}">（<font color="red">测</font>）</c:when>
                                        <c:when test="${c.user_type == 7}">（<font color="red">客</font>）</c:when>
                                        <c:when test="${c.user_type == 8}">（<font color="red">财</font>）</c:when>
                                        <c:when test="${c.user_type == 9}">（<font color="red">超</font>）</c:when>
                                        <c:otherwise>--</c:otherwise>
                                    </c:choose><c:if test="${null!=c.user_upname}"><br><font
                                            color="red">上级：${c.user_upname}</font></c:if></td>
                                <td><font color="red">${c.draw_money}</font></td>
                                <td>${c.draw_surgery}</td>
                                <td>${c.user_realname}<br>${c.banks_card}<br>${c.banks_bank}<br>${c.banks_add}<br></td>
                                <td><c:choose>
                                    <c:when test="${c.draw_status == 0}"><font color="#1E50A2">申请中</font></c:when>
                                    <c:when test="${c.draw_status == 1}"><font color="red">处理中</font></c:when>
                                    <c:when test="${c.draw_status == 2}"><font color="#D90000">已成功</font></c:when>
                                    <c:when test="${c.draw_status == 3}">用户撤销</c:when>
                                    <c:when test="${c.draw_status == 4}">拒绝提款</c:when>
                                    <c:otherwise>--</c:otherwise>
                                </c:choose></td>
                                <td>${c.draw_douser}<br>${c.draw_dotime}</td>
                                <td>${c.draw_desc}</td>
                                <td><c:choose>
                                    <c:when test="${c.draw_status == 0}"><input name="jieshou" ids="${c.draw_id}"
                                                                                type="button"
                                                                                value="接受"/><br><br>理由：<input type="text"/><input name="jujue" ids="${c.draw_id}" type="button" value="拒绝"/></c:when>
                                    <c:when test="${c.draw_status == 1}">订单号：<input type="text"/><input name="fukuan" ids="${c.draw_id}" type="button" value="完成"/><br><br>理由：<input type="text"/><input name="jujue" ids="${c.draw_id}" type="button" value="拒绝"/></c:when>
                                    <c:otherwise>--</c:otherwise>
                                </c:choose></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="13">没有您查询的提款记录</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </td>
    </tr>
    <c:if test="${null!=page}">
        <tr>
            <td align="center">
                <div class="my_page">
                    <div id="page_wrapper" class="page">${page}</div>
                </div>
            </td>
        </tr>
    </c:if>
</table>
</body>
<script type="text/javascript">
    $(function () {
        $('#type').val(${type});
        $('#ut').val(${ut});
        $('#status').val(${status});
        $('input[name=jieshou]').click(function () {
            if (confirm('是否接受')) {
                var id = $(this).attr('ids');
                $(this).hide();
                $.post('/admin/AdminCaiwu.jzh', {drawid: id, type: '0', t: new Date()}, function (data) {
                    $(this).show();
                    if (data == '0') {
                        alert("接受成功！");
                        window.location.reload();
                    } else if (data == '1') {
                        alert("接受失败！");
                    } else if (data == 'nologin') {
                        alert("您长时间未操作，请重新登录！");
                        location.href = '/adminsqwe';
                    } else {
                        alert("参数错误！");
                    }
                });
            }
        });
        $('input[name=jujue]').click(function () {
            var liyou = $(this).prev().val();
            if (liyou.length == 0) {
                alert('请输入拒绝理由');
                return false;
            }
            if (confirm('是否拒绝')) {
                var id = $(this).attr('ids');
                $(this).hide();
                $.post('/admin/AdminCaiwu.jzh', {drawid: id, type: '1', msg: liyou, t: new Date()}, function (data) {
                    $(this).show();
                    if (data == '0') {
                        alert("拒绝成功！");
                        window.location.reload();
                    } else if (data == '1') {
                        alert("拒绝失败！");
                    } else if (data == 'nologin') {
                        alert("您长时间未操作，请重新登录！");
                        location.href = '/adminsqwe';
                    } else {
                        alert("参数错误！");
                    }
                });
            }
        });
        $('input[name=fukuan]').click(function () {
            var jyh = $(this).prev().val();
            if (jyh.length == 0) {
                alert('请输入交易号再完成提款!');
                return false;
            }
            if (confirm('是否完成')) {
                var id = $(this).attr('ids');
                $(this).hide();
                $.post('/admin/AdminCaiwu.jzh', {drawid: id, type: '2', msg: jyh, t: new Date()}, function (data) {
                    $(this).show();
                    if (data == '0') {
                        alert("提款成功！");
                        window.location.reload();
                    } else if (data == '1') {
                        alert("提款失败！");
                    } else if (data == 'nologin') {
                        alert("您长时间未操作，请重新登录！");
                        location.href = '/adminsqwe';
                    } else {
                        alert("参数错误！");
                    }
                });
            }
        });

    });
</script>
</html>