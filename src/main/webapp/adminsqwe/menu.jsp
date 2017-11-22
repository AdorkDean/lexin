<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
    <link href="css/admin.css" rel="stylesheet" type="text/css"/>
    <SCRIPT language=javascript>
        function expand(el) {
            childObj = document.getElementById("child" + el);
            if (childObj.style.display == 'none') {
                childObj.style.display = 'block';
            } else {
                childObj.style.display = 'none';
            }
            return;
        }
    </SCRIPT>
</HEAD>
<BODY>
<TABLE height="100%" cellSpacing="0" cellPadding="0" width="170" background="images/menu_bg.jpg" border="0">
    <TR>
        <TD vAlign=top align=middle>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TR>
                    <TD height=10></TD>
                </TR>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=22>
                    <TD style="PADDING-LEFT: 30px" background=images/menu_bt.jpg>
                        <A class=menuParent onclick=expand(0) href="javascript:void(0);">便捷通道</A></TD>
                </TR>
                <TR height=4>
                    <TD></TD>
                </TR>
            </TABLE>
            <TABLE id=child0 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/adminsqwe/UserAddMoney.jsp" target=main>手动充值</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminCaiwu.jzh" target=main>充值明细</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminCaiwu!Draw.jzh" target=main>提款明细</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminUser.jzh" target=main>用户列表</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminLot!Qihao.jzh" target=main>期号管理</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminLot.jzh" target=main>订单列表</A></TD>
                </TR>
                <TR height=4>
                    <TD colSpan=2></TD>
                </TR>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=22>
                    <TD style="PADDING-LEFT: 30px" background=images/menu_bt.jpg>
                        <A class=menuParent onclick=expand(1) href="javascript:void(0);">彩票管理</A></TD>
                </TR>
                <TR height=4>
                    <TD></TD>
                </TR>
            </TABLE>
            <TABLE id=child1 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminLot!Qihao.jzh" target=main>期号管理</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminLot.jzh" target=main>订单列表</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminLot!BuyLot.jzh" target=main>订单期号（出票&撤单&开奖）</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminLot!LotList.jzh?havehm=0" target=main>期号（录入开奖号码）</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminLot!LotList.jzh" target=main>期号（出票&撤单&开奖）*总</A></TD>
                </TR>
                <TR height=4>
                    <TD colSpan=2></TD>
                </TR>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=22>
                    <TD style="PADDING-LEFT: 30px" background=images/menu_bt.jpg>
                        <A class=menuParent onclick=expand(2) href="javascript:void(0);">用户管理</A></TD>
                </TR>
                <TR height=4>
                    <TD></TD>
                </TR>
            </TABLE>
            <TABLE id=child2 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminUser.jzh" target=main>用户列表</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/adminsqwe/UserAddMoney.jsp" target=main>手动充值</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/adminsqwe/UserDesc.jsp" target=main>用户金额明细</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminUser!Logs.jzh" target=main>用户账户日志</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminUser!Comm.jzh" target=main>返点列表</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminLot!UserBuy.jzh" target=main>用户方案查看</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminUser!Auto.jzh" target=main>用户定制跟单</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminUser!Point.jzh" target=main>用户积分明细</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminUser!Reds.jzh" target=main>用户红包明细</A></TD>
                </TR>
                <TR height=4>
                    <TD colSpan=2></TD>
                </TR>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=22>
                    <TD style="PADDING-LEFT: 30px" background=images/menu_bt.jpg>
                        <A class=menuParent onclick=expand(3) href="javascript:void(0);">财务中心</A></TD>
                </TR>
                <TR height=4>
                    <TD></TD>
                </TR>
            </TABLE>
            <TABLE id=child3 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminCaiwu.jzh" target=main>充值明细</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminCaiwu!Draw.jzh" target=main>提款明细</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminLot.jzh?status=1" target=main>中奖订单查询</A></TD>
                </TR>
                <TR height=4>
                    <TD colSpan=2></TD>
                </TR>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=22>
                    <TD style="PADDING-LEFT: 30px" background=images/menu_bt.jpg>
                        <A class=menuParent onclick=expand(4) href="javascript:void(0);">内容管理</A></TD>
                </TR>
                <TR height=4>
                    <TD></TD>
                </TR>
            </TABLE>
            <TABLE id=child4 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminNews.jzh" target=main>内容列表</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/adminsqwe/NewsAdd.jsp" target=main>添加文章</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminNews!Msg.jzh" target=main>系统消息列表</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/adminsqwe/NewsMsgAdd.jsp" target=main>发送系统消息</A></TD>
                </TR>
                <TR height=4>
                    <TD colSpan=2></TD>
                </TR>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=22>
                    <TD style="PADDING-LEFT: 30px" background=images/menu_bt.jpg>
                        <A class=menuParent onclick=expand(5) href="javascript:void(0);">系统设置</A></TD>
                </TR>
                <TR height=4>
                    <TD></TD>
                </TR>
            </TABLE>
            <TABLE id=child5 style="DISPLAY: none" cellSpacing=0 cellPadding=0 width=150 border=0>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="/admin/AdminNews!LotStatus.jzh" target=main>停售开售</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="" target=main>内容操作说明</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="" target=main>彩票操作说明</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="" target=main>支付操作说明</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild href="" target=main>修改口令</A></TD>
                </TR>
                <TR height=20>
                    <TD align=middle width=30><IMG height=9 src="images/menu_icon.gif" width=9></TD>
                    <TD><A class=menuChild onclick="if (confirm('确定要退出吗？')) return true; else return false;"
                           href="/admin/AdminUser!Out.jzh" target=_top>退出系统</A></TD>
                </TR>
            </TABLE>
        </TD>
        <TD width=1 bgColor=#d1e6f7></TD>
    </TR>
</TABLE>
</BODY>
</HTML>