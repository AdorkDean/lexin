package com.caipiao.admin;

import com.caipiao.admin.service.AdminCaiwuService;
import com.caipiao.admin.service.AdminUserService;
import com.caipiao.entity.Bc_user;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.caipiao.utils.XlsOutUtil;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.PageUtils;
import com.sysbcjzh.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;

public class AdminCaiwu extends IndexAction {

    private static final long serialVersionUID = 0x841a711c0d7b9db4L;
    AdminCaiwuService caiwu;
    AdminUserService service;

    public AdminCaiwu() {
        caiwu = new AdminCaiwuService();
        service = new AdminUserService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        if (admin != null) {
            String user = request.getParameter("user");
            String btime = request.getParameter("btime");
            String etime = request.getParameter("etime");
            String typestr = request.getParameter("type");
            String utstr = request.getParameter("ut");
            String statusstr = request.getParameter("status");
            String p = request.getParameter("p");
            int page = TryStatic.StrToInt(p, 1);
            int limit = 30;
            int type = TryStatic.StrToInt(typestr, -1);
            int ut = TryStatic.StrToInt(utstr, -1);
            int status = TryStatic.StrToInt(statusstr, -1);
            int user_id = -1;
            String url = (new StringBuilder("/admin/AdminCaiwu.jzh?type=")).append(type).append("&ut=").append(ut).append("&status=").append(status).append("&").toString();
            request.setAttribute("status", Integer.valueOf(status));
            request.setAttribute("type", Integer.valueOf(type));
            request.setAttribute("ut", Integer.valueOf(ut));
            request.setAttribute("p", p);
            if (StringUtils.isNotBlank(user)) {
                Bc_user find = UserStatic.find(user);
                if (find != null) {
                    user_id = find.getUser_id();
                    url = (new StringBuilder(String.valueOf(url))).append("user=").append(find.getUser_name()).append("&").toString();
                    request.setAttribute("user", user);
                }
            }
            if (StringUtils.isNotEmptyAll(new String[]{
                    btime, etime
            })) {
                url = (new StringBuilder(String.valueOf(url))).append("btime=").append(btime).append("&etime=").append(etime).append("&").toString();
                request.setAttribute("btime", btime);
                request.setAttribute("etime", etime);
            }
            int findscount = caiwu.findsRechcount(user_id, btime, etime, type, ut, status);
            String pagehtml = PageUtils.Page(findscount, page, limit, url);
            List finds = caiwu.findsRech(user_id, btime, etime, type, ut, status, (page - 1) * limit, limit);
            if (finds != null) {
                request.setAttribute("list", finds);
                request.setAttribute("page", pagehtml);
            }
            request.getRequestDispatcher("/adminsqwe/CaiwuRechList.jsp").forward(request, response);
        } else {
            out.write(UserSession.loginadminstr);
        }
        out.flush();
        out.close();
    }

    public void RechDownGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        int adminLevel = UserSession.getAdminLevel(request);
        if (admin != null && adminLevel != 7) {
            String user = request.getParameter("user");
            String btime = request.getParameter("btime");
            String etime = request.getParameter("etime");
            String typestr = request.getParameter("type");
            String utstr = request.getParameter("ut");
            String statusstr = request.getParameter("status");
            int type = TryStatic.StrToInt(typestr, -1);
            int ut = TryStatic.StrToInt(utstr, -1);
            int status = TryStatic.StrToInt(statusstr, -1);
            int user_id = -1;
            if (StringUtils.isNotBlank(user)) {
                Bc_user find = UserStatic.find(user);
                if (find != null)
                    user_id = find.getUser_id();
            }
            int findscount = caiwu.findsRechcount(user_id, btime, etime, type, ut, status);
            List finds = caiwu.findsRech(user_id, btime, etime, type, ut, status, 0, findscount + 1);
            String filePath = XlsOutUtil.SaveRechXLS(finds);
            File f = new File(filePath);
            if (!f.exists()) {
                response.sendError(404, "File not found!");
                return;
            }
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
            byte buf[] = new byte[1024];
            int len = 0;
            response.reset();
            URL u = new URL((new StringBuilder("file:///")).append(filePath).toString());
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", (new StringBuilder("inline; filename=")).append(f.getName()).toString());
            OutputStream outf = response.getOutputStream();
            while ((len = br.read(buf)) > 0)
                outf.write(buf, 0, len);
            br.close();
            outf.close();
        } else {
            out.write(UserSession.loginadminstr);
        }
        out.flush();
        out.close();
    }

    public void DrawGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        if (admin != null) {
            String user = request.getParameter("user");
            String btime = request.getParameter("btime");
            String etime = request.getParameter("etime");
            String typestr = request.getParameter("type");
            String utstr = request.getParameter("ut");
            String statusstr = request.getParameter("status");
            String p = request.getParameter("p");
            int page = TryStatic.StrToInt(p, 1);
            int limit = 30;
            int type = TryStatic.StrToInt(typestr, -1);
            int ut = TryStatic.StrToInt(utstr, -1);
            int status = TryStatic.StrToInt(statusstr, -1);
            int user_id = -1;
            String url = (new StringBuilder("/admin/AdminCaiwu!Draw.jzh?type=")).append(type).append("&ut=").append(ut).append("&status=").append(status).append("&").toString();
            request.setAttribute("status", Integer.valueOf(status));
            request.setAttribute("ut", Integer.valueOf(ut));
            request.setAttribute("type", Integer.valueOf(type));
            if (StringUtils.isNotBlank(user)) {
                Bc_user find = UserStatic.find(user);
                if (find != null) {
                    user_id = find.getUser_id();
                    url = (new StringBuilder(String.valueOf(url))).append("user=").append(find.getUser_name()).append("&").toString();
                    request.setAttribute("user", user);
                }
            }
            if (StringUtils.isNotEmptyAll(new String[]{
                    btime, etime
            })) {
                url = (new StringBuilder(String.valueOf(url))).append("btime=").append(btime).append("&etime=").append(etime).append("&").toString();
                request.setAttribute("btime", btime);
                request.setAttribute("etime", etime);
            }
            int findscount = caiwu.findsDrawcount(user_id, btime, etime, type, ut, status);
            String pagehtml = PageUtils.Page(findscount, page, limit, url);
            List finds = caiwu.findsDraw(user_id, btime, etime, type, ut, status, (page - 1) * limit, limit);
            if (finds != null) {
                request.setAttribute("list", finds);
                request.setAttribute("page", pagehtml);
            }
            request.getRequestDispatcher("/adminsqwe/CaiwuDrawList.jsp").forward(request, response);
        } else {
            out.print(UserSession.loginadminstr);
        }
        out.flush();
        out.close();
    }

    public void DownDrawGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        int adminLevel = UserSession.getAdminLevel(request);
        if (admin != null && adminLevel != 7) {
            String user = request.getParameter("user");
            String btime = request.getParameter("btime");
            String etime = request.getParameter("etime");
            String typestr = request.getParameter("type");
            String utstr = request.getParameter("ut");
            String statusstr = request.getParameter("status");
            int type = TryStatic.StrToInt(typestr, -1);
            int ut = TryStatic.StrToInt(utstr, -1);
            int status = TryStatic.StrToInt(statusstr, -1);
            int user_id = -1;
            if (StringUtils.isNotBlank(user)) {
                Bc_user find = UserStatic.find(user);
                if (find != null)
                    user_id = find.getUser_id();
            }
            int findscount = caiwu.findsDrawcount(user_id, btime, etime, type, ut, status);
            List finds = caiwu.findsDraw(user_id, btime, etime, type, ut, status, 0, findscount + 1);
            String filePath = XlsOutUtil.SaveDrawXLS(finds);
            File f = new File(filePath);
            if (!f.exists()) {
                response.sendError(404, "File not found!");
                return;
            }
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
            byte buf[] = new byte[1024];
            int len = 0;
            response.reset();
            URL u = new URL((new StringBuilder("file:///")).append(filePath).toString());
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", (new StringBuilder("inline; filename=")).append(f.getName()).toString());
            OutputStream outf = response.getOutputStream();
            while ((len = br.read(buf)) > 0)
                outf.write(buf, 0, len);
            br.close();
            outf.close();
        } else {
            out.print(UserSession.loginadminstr);
        }
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        int adminLevel = UserSession.getAdminLevel(request);
        if (admin != null && adminLevel != 7) {
            String drawidstr = request.getParameter("drawid");
            String typestr = request.getParameter("type");
            String msgstr = request.getParameter("msg");
            int drawid = TryStatic.StrToInt(drawidstr, 0);
            int type = TryStatic.StrToInt(typestr, -1);
            if (drawid != 0 && -1 != type) {
                boolean drawUpdate = caiwu.DrawUpdate(drawid, admin, type, msgstr);
                if (drawUpdate)
                    out.write("0");
                else
                    out.write("1");
            } else {
                out.write("err");
            }
        } else {
            out.write("nologin");
        }
        out.flush();
        out.close();
    }

    public void DrawCountPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        if (admin != null) {
            int findNowDraw = caiwu.findNowDraw();
            out.write((new StringBuilder(String.valueOf(findNowDraw))).toString());
        } else {
            out.write("nologin");
        }
        out.flush();
        out.close();
    }

    public void FindYeJiPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        if (admin != null) {
            String name = request.getParameter("name");
            StringUtils.isNotBlank(name);
            int findNowDraw = caiwu.findNowDraw();
            out.write((new StringBuilder(String.valueOf(findNowDraw))).toString());
        } else {
            out.write("nologin");
        }
        out.flush();
        out.close();
    }
}
