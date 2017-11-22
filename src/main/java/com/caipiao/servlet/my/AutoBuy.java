//    AutoBuy.java

package com.caipiao.servlet.my;

import com.caipiao.service.my.MyAutoBuyService;
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

    private static final long serialVersionUID = 0x7908d1a061311658L;
    MyAutoBuyService service;

    public AutoBuy() {
        service = new MyAutoBuyService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            VelocityHelper velo = new VelocityHelper();
            velo.Put("user", find);
            velo.init("my/autobuy.vm", out);
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
