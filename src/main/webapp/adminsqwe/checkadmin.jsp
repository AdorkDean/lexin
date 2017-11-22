<%@ page language="java" import="com.caipiao.utils.UserSession" pageEncoding="utf-8" %>
<%
    Object obj = session.getAttribute(UserSession.adminuser);
    if (obj == null) {
        out.print("<script>parent.location.href='/adminsqwe/login.jsp';</script>");
    }
%>