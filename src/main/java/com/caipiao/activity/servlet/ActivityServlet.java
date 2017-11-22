package com.caipiao.activity.servlet;

import com.caipiao.activity.ActivityService;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.VelocityHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 活动servlet
 */
public class ActivityServlet extends IndexAction {

    private static final long serialVersionUID = 1L;
    ActivityService service;

    public ActivityServlet() {
        service = new ActivityService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int status = -1;
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("act", service.finds(status));
        velo.init("activity.vm", out);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }
}
