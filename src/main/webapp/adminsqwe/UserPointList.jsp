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
<table class="mtb" cellspacing="0" style="width:95%;margin:auto">
    <caption>用户列表管理</caption>
    <tr>
        <td>
            <form action="/admin/AdminUser!Point.jzh" method="get">条件查询：用户名：<input style="width:100px" name="user"
                                                                                   type="text" value="${user}"/> |
                类型：<select name="type">
                    <option value="-1">全部</option>
                    <option value="0">中奖积分</option>
                    <option value="1">积分兑换</option>
                </select> |
                收入支出：<select name="subadd">
                    <option value="-1">全部</option>
                    <option value="0">收入积分</option>
                    <option value="1">支出积分</option>
                </select> | 时间：<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" name="btime"
                                      value="${btime}"/> - <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                                  style="width:100px" name="etime" value="${etime}"/>
                <input type="submit" value="搜索"/></form>
        </td>
    </tr>
    <tr>
        <td>便捷筛选：<a href="/admin/AdminUser!Point.jzh">全部</a> | <a href="/admin/AdminUser!Point.jzh?subadd=0">收入</a> | <a
                href="/admin/AdminUser!Point.jzh?subadd=1">支出</a></td>
    </tr>
    <tr>
        <td align="center">
            <div class="my_page" style="height:40px;">
                <div id="page_wrapper" class="page">${page}</div>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <table width="100%">
                <thead>
                <tr class="tit">
                    <td>用户名</td>
                    <td>订单号</td>
                    <td>时间</td>
                    <td>类型</td>
                    <td>剩余积分</td>
                    <td>收支积分</td>
                    <td>备注</td>
                <thead>
                <tbody>
                <c:forEach var="d" items="${find}">
                    <tr>
                        <td><a href="/admin/AdminUser!Point.jzh?user=${d.user_name}">${d.user_name}</a></td>
                        <td>${d.point_item}</td>
                        <td>${d.point_time}</td>
                        <td>${d.point_type}</td>
                        <td>${d.point_have}</td>
                        <td>${d.point_addsub}</td>
                        <td>${d.point_desc}</td>
                    </tr>
                </c:forEach></tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td align="center">
            <div class="my_page" style="height:40px">
                <div id="page_wrapper" class="page">${page}</div>
            </div>
        </td>
    </tr>
</table>
</body>
<script type="text/javascript">
    $(function () {
        $('select[name=type]').val(${type});
        $('select[name=subadd]').val(${subadd});
    });
</script>
</html>