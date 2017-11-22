//    AutoBuy.java

package com.caipiao.servlet;

import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.VelocityHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AutoBuy extends IndexAction {

    private static final long serialVersionUID = 1L;

    public AutoBuy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.init("autobuy.vm", out);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }
}
