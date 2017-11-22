package com.caipiao.admin;

import com.caipiao.admin.service.AdminNewsService;
import com.caipiao.entity.Bc_news;
import com.caipiao.utils.LotSale;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.PageUtils;
import com.sysbcjzh.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 新闻
 */
public class AdminNews extends IndexAction {

    private static final long serialVersionUID = 1L;
    AdminNewsService service;

    public AdminNews() {
        service = new AdminNewsService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        if (admin != null) {
            String auth = request.getParameter("auth");
            String title = request.getParameter("title");
            String btime = request.getParameter("btime");
            String etime = request.getParameter("etime");
            String soures = request.getParameter("soures");
            String typestr = request.getParameter("type");
            String statusstr = request.getParameter("status");
            int status = TryStatic.StrToInt(statusstr, -1);
            int type = TryStatic.StrToInt(typestr, -1);
            String p = request.getParameter("p");
            int page = TryStatic.StrToInt(p, 1);
            int limit = 30;
            String url = (new StringBuilder("/admin/AdminNews.jzh?type=")).append(type).append("&status=").append(status).append("&").toString();
            request.setAttribute("status", Integer.valueOf(status));
            request.setAttribute("type", Integer.valueOf(type));
            if (StringUtils.isNotEmptyAll(new String[]{
                    btime, etime
            })) {
                url = (new StringBuilder(String.valueOf(url))).append("btime=").append(btime).append("&etime=").append(etime).append("&").toString();
                request.setAttribute("btime", btime);
                request.setAttribute("etime", etime);
            }
            if (StringUtils.isNotBlank(auth)) {
                request.setAttribute("auth", auth);
                url = (new StringBuilder(String.valueOf(url))).append("auth=").append(auth).append("&").toString();
            }
            if (StringUtils.isNotBlank(title)) {
                request.setAttribute("title", title);
                url = (new StringBuilder(String.valueOf(url))).append("title=").append(title).append("&").toString();
            }
            if (StringUtils.isNotBlank(soures)) {
                request.setAttribute("soures", soures);
                url = (new StringBuilder(String.valueOf(url))).append("soures=").append(soures).append("&").toString();
            }
            int count = service.findsNewsCount(auth, title, btime, etime, soures, type, type, status);
            String pagehtml = PageUtils.Page(count, page, limit, url);
            List finds = service.findsNews(auth, title, btime, etime, soures, type, type, status, (page - 1) * limit, limit);
            if (finds != null) {
                request.setAttribute("list", finds);
                request.setAttribute("page", pagehtml);
            }
            request.getRequestDispatcher("/adminsqwe/NewsList.jsp").forward(request, response);
        } else {
            out.print(UserSession.loginadminstr);
        }
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String soures = request.getParameter("source");
        String title = request.getParameter("title");
        String types = request.getParameter("type");
        String text = request.getParameter("text");
        String image = request.getParameter("image");
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        if (admin != null) {
            if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(text)) {
                int type = TryStatic.StrToInt(types, 0);
                boolean addNews = service.addNews(admin, title, soures, image, text, type);
                if (addNews)
                    out.print("0");
                else
                    out.print("1");
            } else {
                out.print("err");
            }
        } else {
            out.print(UserSession.loginadminstr);
        }
        out.flush();
        out.close();
    }

    public void EditGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        if (admin != null) {
            if (StringUtils.isNotBlank(id)) {
                int ids = TryStatic.StrToInt(id, 0);
                Bc_news find = service.find(ids);
                if (find != null) {
                    request.setAttribute("find", find);
                    request.getRequestDispatcher("/adminsqwe/NewsUpdate.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/error404.html");
                }
            } else {
                response.sendRedirect("/News.jzh");
            }
        } else {
            out.print(UserSession.loginadminstr);
        }
        out.flush();
        out.close();
    }

    public void EditPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String statustr = request.getParameter("status");
        String soures = request.getParameter("source");
        String title = request.getParameter("title");
        String types = request.getParameter("type");
        String text = request.getParameter("text");
        String image = request.getParameter("image");
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        if (admin != null) {
            if (StringUtils.isNotEmptyAll(new String[]{
                    ids, title, text
            })) {
                int id = TryStatic.StrToInt(ids, 0);
                int status = TryStatic.StrToInt(statustr, 0);
                int type = TryStatic.StrToInt(types, 0);
                if (id != 0) {
                    boolean addNews = service.UpNews(id, status, admin, title, soures, image, text, type);
                    if (addNews)
                        out.print("0");
                    else
                        out.print("1");
                } else {
                    out.print("err");
                }
            } else {
                out.print("err");
            }
        } else {
            out.print(UserSession.loginadminstr);
        }
        out.flush();
        out.close();
    }

    public void MsgGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }

    public void LotStatusGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String admin = UserSession.getAdmin(request);
        if (admin != null) {
            String lot = request.getParameter("lot");
            String status = request.getParameter("status");
            if (StringUtils.isNotBlank(lot)) {
                int s = TryStatic.StrToInt(status, 1);
                LotSale.UpdateSale(lot, s);
            }
            List finds = LotSale.finds();
            request.setAttribute("find", finds);
            request.getRequestDispatcher("/adminsqwe/LotStatus.jsp").forward(request, response);
        } else {
            out.print(UserSession.loginadminstr);
        }
        out.flush();
        out.close();
    }
}
