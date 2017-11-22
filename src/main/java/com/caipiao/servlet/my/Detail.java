//    Detail.java

package com.caipiao.servlet.my;

import com.caipiao.entity.Bc_user;
import com.caipiao.service.my.MyDetailService;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.TimeUtil;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.PageUtils;
import com.sysbcjzh.utils.VelocityHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Detail extends IndexAction {

    private static final long serialVersionUID = 0xe284095c4516c72bL;
    MyDetailService serivce;

    public Detail() {
        serivce = new MyDetailService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        if (user != null) {
            Bc_user find = UserStatic.find(user);
            String datestr = request.getParameter("date");
            String type = request.getParameter("type");
            String status = request.getParameter("status");
            String p = request.getParameter("p");
            int userid = find.getUser_id();
            int date = TryStatic.StrToInt(datestr, 0);
            String btime = TryStatic.getAgo(date);
            String etime = TimeUtil.getToday("yyyy-MM-dd");
            int page = TryStatic.StrToInt(p, 1);
            int t = TryStatic.StrToInt(type, -1);
            int limit = 12;
            int statu = TryStatic.StrToInt(status, -1);
            int count = serivce.findcount(userid, btime, etime, t, statu);
            String ajaxPage = PageUtils.Page163(count, page, limit, (new StringBuilder("/my/Detail.jzh?status=")).append(statu).append("&date=").append(date).append("&type=").append(t).append("&").toString());
            java.util.List finds = serivce.finds(userid, btime, etime, t, statu, (page - 1) * limit, limit);
            VelocityHelper velo = new VelocityHelper();
            velo.Put("user", find);
            velo.Put("find", finds);
            velo.Put("page", ajaxPage);
            velo.Put("date", Integer.valueOf(date));
            velo.Put("type", Integer.valueOf(t));
            velo.Put("status", Integer.valueOf(statu));
            velo.init("my/descoupon.vm", out);
        } else {
            out.print(UserSession.loginstr);
        }
        out.flush();
        out.close();
    }

    public void LogsGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        if (user != null) {
            Bc_user find = UserStatic.find(user);
            String status = request.getParameter("status");
            String p = request.getParameter("p");
            String datestr = request.getParameter("date");
            int date = TryStatic.StrToInt(datestr, 0);
            String btime = TryStatic.getAgo(date);
            String etime = TimeUtil.getToday("yyyy-MM-dd");
            int userid = find.getUser_id();
            int page = TryStatic.StrToInt(p, 1);
            int limit = 12;
            int statu = TryStatic.StrToInt(status, -1);
            int count = serivce.findLogscount(userid, btime, etime, statu);
            String ajaxPage = PageUtils.Page163(count, page, limit, (new StringBuilder("/my/Detail!Logs.jzh?status=")).append(statu).append("&date=").append(date).append("&").toString());
            java.util.List finds = serivce.findLogs(userid, btime, etime, statu, (page - 1) * limit, limit);
            VelocityHelper velo = new VelocityHelper();
            velo.Put("user", find);
            velo.Put("find", finds);
            velo.Put("page", ajaxPage);
            velo.Put("date", Integer.valueOf(date));
            velo.Put("status", Integer.valueOf(statu));
            velo.init("my/deslogs.vm", out);
        } else {
            out.print(UserSession.loginstr);
        }
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }
}
