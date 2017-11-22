<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/adminsqwe/css/admin.css">
    <script language="javascript" type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script language="javascript" type="text/javascript" src="/js/local.js"></script>
    <script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<table class="mtb" cellspacing="0" style="width:80%;margin:auto">
    <caption>编辑用户</caption>
    <tr>
        <td>&nbsp;&nbsp;用&nbsp;户&nbsp;名：<span id="u_user" ids="${find.user_id}">${find.user_name}</span></td>
    <tr>
    <tr>
        <td>&nbsp;&nbsp;用户余额：<span style="color:#8B4500">￥${find.user_money} </span> | 冻结金额：<span style="color:#CD1076"> ￥${find.user_dong} </span>
            | 提款消费金额：<span style="color:#CD1076"> ￥${find.user_show} </span></td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;红 包：<span style="color:#8B4500">￥${find.user_red}</span> | 积分：<span
                style="color:#CD1076">${find.user_point}</span> | 等级积分：<span
                style="color:#EE0000;">${find.user_level} </span></td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;座右铭：${find.user_word} | 住址：${find.user_liveadd} | 生日：${find.user_birth}</td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;注册时间：${find.user_regtime} | 本次登录时间：${find.user_lgtime} | 本次登录ip：${find.user_lgip}</td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;手机<c:if test="${find.user_phonecheck==0}">(未验证)</c:if><c:if
                test="${find.user_phonecheck==1}">(<font color="green">已验证</font>)</c:if>：${find.user_phone} | 身份证<c:if
                test="${find.user_zipcheck==0}">(未验证)</c:if><c:if
                test="${find.user_zipcheck==1}">(<font color="green">已验证</font>)</c:if>：${find.user_realname}（${find.user_zip}）
        </td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;密保问题：${find.user_aqasking} 答案：${find.user_aqanswer}| 邮箱<c:if
                test="${find.user_emailcheck==0}">(未验证)</c:if><c:if
                test="${find.user_emailcheck==1}">(<font color="green">已验证</font>)</c:if>：${find.user_email} |
            QQ：${find.user_qq}</td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;用户类型：<select id="u_type">
            <option value="0" <c:if test="${find.user_type == 0 }"> selected="selected" </c:if> >普通</option>
            <option value="1" <c:if test="${find.user_type == 1 }"> selected="selected" </c:if>>代理</option>
            <option value="2" <c:if test="${find.user_type == 2 }"> selected="selected" </c:if>>测试</option>
            <option value="3" <c:if test="${find.user_type == 3 }"> selected="selected" </c:if>>机器人</option>
            <option value="7" <c:if test="${find.user_type == 7 }"> selected="selected" </c:if>>客服</option>
            <option value="8" <c:if test="${find.user_type == 8 }"> selected="selected" </c:if>>财务</option>
            <option value="9" <c:if test="${find.user_type == 9 }"> selected="selected" </c:if>>超级</option>
        </select>
            | 用户状态：<select id="u_sta">
                <option value="0" <c:if test="${find.user_status == 0 }"> selected="selected" </c:if>>正常</option>
                <option value="1" <c:if test="${find.user_status == 1 }"> selected="selected" </c:if>>停用</option>
            </select></td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;上级：${find.user_upid} | 上次登录时间：${find.user_lgtimeold}</td>
    </tr>
    <tr>
        <td><input type="button" onclick="editInfo()" value="修改"/> | <a href="javascript:history.go(-1);">返回</a></td>
    </tr>
    <tr>
        <td><input type="button" onclick="RePass()" value="重置密码"/></td>
    </tr>
</table>
<table class="mtb" cellspacing="0" style="width:80%;margin:auto">
    <caption>返点设置</caption>
    <tr>
        <td colspan="3">&nbsp;&nbsp;用&nbsp;户&nbsp;名：<span id="c_user">${comm.user_name}</span></td>
    <tr>
    <tr>
        <td>&nbsp;&nbsp;双色球：<input type="text" id="c_ssq" value="${comm.ssq}" style="width:30px;"/> %</td>
        <td>&nbsp;&nbsp;大乐透：<input type="text" id="c_dlt" value="${comm.dlt}" style="width:30px;"/> %</td>
        <td>&nbsp;&nbsp;排列5：<input type="text" id="c_pl5" value="${comm.pl5}" style="width:30px;"/> %</td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;排列三：<input type="text" id="c_pl3" value="${comm.pl3}" style="width:30px;"/> %</td>
        <td>&nbsp;&nbsp;福彩3D：<input type="text" id="c_fc3d" value="${comm.fc3d}" style="width:30px;"/> %</td>
        <td>&nbsp;&nbsp;重庆时时彩：<input type="text" id="c_cqssc" value="${comm.cqssc}" style="width:30px;"/> %</td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;江西时时彩：<input type="text" id="c_jxssc" value="${comm.jxssc}" style="width:30px;"/> %</td>
        <td>&nbsp;&nbsp;山东11选5：<input type="text" id="c_sd11x5" value="${comm.sd11x5}" style="width:30px;"/> %</td>
        <td>&nbsp;&nbsp;江西11选5：<input type="text" id="c_jx11x5" value="${comm.jx11x5}" style="width:30px;"/> %</td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;广东11选5：<input type="text" id="c_gd11x5" value="${comm.gd11x5}" style="width:30px;"/> %</td>
        <td>&nbsp;&nbsp;重庆11选5：<input type="text" id="c_cq11x5" value="${comm.cq11x5}" style="width:30px;"/> %</td>
        <td>&nbsp;&nbsp;河内五分彩：<input type="text" id="c_hnssc" value="${comm.hnssc}" style="width:30px;"/> %</td>
    </tr>
    <%--
    需修改 没有印尼5分彩
    <tr>
        <td>&nbsp;&nbsp;印尼五分彩：<input type="text" id="c_ynssc" value="${comm.ynssc}" style="width:30px;"/> %</td>
    </tr>
    --%>
    <tr>
        <td><input type="button" onclick="editComm()" value="修改"/> | <a href="javascript:history.go(-1);">返回</a></td>
    </tr>
</table>
</body>
<script type="text/javascript">
    $(function () {
        $('#u_type').val(${find.user_type});
        $('#u_sta').val(${find.user_status});
    });
    function editComm() {
        var ids = $('#u_user').attr('ids');
        var ssq = $('#c_ssq').val();
        var dlt = $('#c_dlt').val();
        var pl5 = $('#c_pl5').val();
        var fc3d = $('#c_fc3d').val();
        var pl3 = $('#c_pl3').val();
        var cqssc = $('#c_cqssc').val();
        var jxssc = $('#c_jxssc').val();
        var jx11x5 = $('#c_jx11x5').val();
        var sd11x5 = $('#c_sd11x5').val();
        var gd11x5 = $('#c_gd11x5').val();
        var cq11x5 = $('#c_cq11x5').val();
        var hnssc = $('#c_hnssc').val();
        var ynssc = $('#c_ynssc').val();
        $.post('/admin/AdminUser!Comm.jzh', {
            ids: ids,
            cqssc: cqssc,
            jxssc: jxssc,
            jx11x5: jx11x5,
            sd11x5: sd11x5,
            cq11x5: cq11x5,
            gd11x5: gd11x5,
            fc3d: fc3d,
            dlt: dlt,
            ssq: ssq,
            pl3: pl3,
            pl5: pl5,
            hnssc: hnssc,
            ynssc: ynssc,
            t: new Date()
        }, function (data) {
            if (data == '0') {
                alert('修改成功');
                location.reload();
            } else if (data == '1') {
                alert('修改失败');
            } else if (data = 'err') {
                alert('操作有误');
            } else if (data = 'userNull') {
                alert('用户不存在');
            } else if (data = 'nologin') {
                parent.location.href = '/admins/login.jsp';
            }
        });
    }
    function editInfo() {
        var ids = $('#u_user').attr('ids');
        var type = $('#u_type').val();
        var sta = $('#u_sta').val();
        $.post('/admin/AdminUser!User.jzh', {ids: ids, type: type, status: sta, t: new Date()}, function (data) {
            if (data == '0') {
                alert('修改成功');
                location.reload();
            } else if (data == '1') {
                alert('修改失败');
            } else if (data = 'err') {
                alert('操作有误');
            } else if (data = 'nologin') {
                parent.location.href = '/admins/login.jsp';
            }
        });
    }
    function RePass() {
        if (confirm("是否重置密码?")) {
            var ids = $('#u_user').attr('ids');
            var pass = getRandom(6);
            $.post('/admin/AdminUser!SetPass.jzh', {ids: ids, pass: pass, t: new Date()}, function (data) {
                if (data == '0') {
                    alert('密码重置成功，新密码是：' + pass);
                } else if (data == '1') {
                    alert('修改失败');
                } else if (data == 'userNull') {
                    alert('用户不存在');
                } else if (data = 'err') {
                    alert('操作有误');
                } else if (data = 'nologin') {
                    parent.location.href = '/admins/login.jsp';
                }
            });
        }
    }
    function getRandom(n) {
        var s = '';
        var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'j', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
        for (var i = 0; i < n; i++) {
            var id = Math.ceil(Math.random() * 35);
            s += chars[id];
        }
        return s;
    }
</script>
</html>