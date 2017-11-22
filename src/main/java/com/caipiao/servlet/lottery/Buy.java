//    Buy.java

package com.caipiao.servlet.lottery;

import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.VelocityHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Buy extends IndexAction {

    private static final long serialVersionUID = 1L;

    public Buy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/Index!BuyHome.jzh");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void Gxk3Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/gxk3.vm", out);
        out.flush();
        out.close();
    }

    public void Ahk3Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/ahk3.vm", out);
        out.flush();
        out.close();
    }

    public void CqsscGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/cqssc.vm", out);
        out.flush();
        out.close();
    }

    public void JxsscGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/jxssc.vm", out);
        out.flush();
        out.close();
    }

    public void HnsscGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/hnssc.vm", out);
        out.flush();
        out.close();
    }

    public void YnsscGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/ynssc.vm", out);
        out.flush();
        out.close();
    }

    public void Sd11x5Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/sd11x5.vm", out);
        out.flush();
        out.close();
    }

    public void Jx11x5Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/jx11x5.vm", out);
        out.flush();
        out.close();
    }

    public void Gd11x5Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/gd11x5.vm", out);
        out.flush();
        out.close();
    }

    public void Cq11x5Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/cq11x5.vm", out);
        out.flush();
        out.close();
    }

    public void Sh11x5Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/sh11x5.vm", out);
        out.flush();
        out.close();
    }

    public void SsqGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/ssq.vm", out);
        out.flush();
        out.close();
    }

    public void DltGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/dlt.vm", out);
        out.flush();
        out.close();
    }

    public void Fc3dGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/fc3d.vm", out);
        out.flush();
        out.close();
    }

    public void Pl3Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/pl3.vm", out);
        out.flush();
        out.close();
    }

    public void Pl5Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/pl5.vm", out);
        out.flush();
        out.close();
    }

    public void Pk10Get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        VelocityHelper velo = new VelocityHelper();
        if (user != null) {
            com.caipiao.entity.Bc_user find = UserStatic.find(user);
            velo.Put("user", find);
        }
        velo.Put("ind", Integer.valueOf(1));
        velo.init("lottery/pk10.vm", out);
        out.flush();
        out.close();
    }
}
