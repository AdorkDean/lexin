//    User.java

package com.caipiao.servlet.app;

import com.caipiao.entity.Bc_banks;
import com.caipiao.entity.Bc_detail;
import com.caipiao.entity.Bc_phb;
import com.caipiao.entity.Bc_user;
import com.caipiao.service.my.MyBuyService;
import com.caipiao.service.my.MyDetailService;
import com.caipiao.service.my.MyRechangeService;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.AppUtils;
import com.caipiao.utils.TimeUtil;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.StringUtils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class User extends IndexAction {

    private static final long serialVersionUID = 0x8b88a2574dbcbe21L;
    MyBuyService service;
    MyDetailService detailservic;
    MyRechangeService bankservice;

    public User() {
        service = new MyBuyService();
        detailservic = new MyDetailService();
        bankservice = new MyRechangeService();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }

    public void LoginGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginname = request.getParameter("username");
        String loginpass = request.getParameter("userpass");
        String loginyzm = request.getParameter("yzm");
        PrintWriter out = response.getWriter();
        HashMap result = new HashMap();
        boolean cheackCodeIsRight = UserSession.CheackCodeIsRight(loginyzm, request);
        boolean dresult = false;
        String dmsg = "验证码错误";
        if (cheackCodeIsRight && StringUtils.length(loginname) < 20 && StringUtils.length(loginpass) < 20) {
            Bc_user find = UserStatic.find(loginname);
            if (find != null) {
                String userLogin = UserSession.userLogin(find, loginpass, request);
                if ("0".equals(userLogin)) {
                    dresult = true;
                    dmsg = "登录成功";
                } else if ("1".equals(userLogin))
                    dmsg = "用户已停用";
                else
                    dmsg = userLogin;
            } else {
                dmsg = "用户不存在";
            }
        }
        result.put("result", Boolean.valueOf(dresult));
        result.put("msg", dmsg);
        out.print(JSONObject.fromObject(result));
        out.flush();
        out.close();
    }

    public void InfoGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        HashMap result = new HashMap();
        if (user != null) {
            Bc_user find = UserStatic.find(user);
            Bc_phb findphb = service.findphb(find.getUser_id());
            result.put("name", find.getUser_name());
            result.put("money", Double.valueOf(find.getUser_money()));
            result.put("xiaofei", Double.valueOf(findphb.getPhb_day_c() + findphb.getPhb_hmday_c()));
            result.put("win", Double.valueOf(findphb.getPhb_day() + findphb.getPhb_hmday()));
            result.put("url", "/default.png");
            result.put("realname", find.getUser_realname());
            result.put("zip", find.getUser_zip());
            result.put("qq", find.getUser_qq());
        } else {
            result.put("nologin", "nologin");
        }
        out.print(JSONObject.fromObject(result));
        out.flush();
        out.close();
    }

    public void DetailGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        HashMap result = new HashMap();
        if (user != null) {
            Bc_user find = UserStatic.find(user);
            String p = request.getParameter("p");
            int userid = find.getUser_id();
            int date = 0;
            String btime = TryStatic.getAgo(date);
            String etime = TimeUtil.getToday("yyyy-MM-dd");
            int page = TryStatic.StrToInt(p, 1);
            int t = -1;
            int limit = 12;
            int statu = -1;
            int count = detailservic.findcount(userid, btime, etime, t, statu);
            if (count > 0) {
                List finds = detailservic.finds(userid, btime, etime, t, statu, (page - 1) * limit, limit);
                List demap = new ArrayList();
                HashMap temp;
                for (Iterator iterator = finds.iterator(); iterator.hasNext(); demap.add(temp)) {
                    Bc_detail d = (Bc_detail) iterator.next();
                    temp = new HashMap();
                    temp.put("time", d.getDetail_time());
                    temp.put("money", Double.valueOf(d.getDetail_addsub()));
                    temp.put("allmoney", Double.valueOf(d.getDetail_balance()));
                    temp.put("type", AppUtils.DetailType(Integer.valueOf(d.getDetail_type())));
                    temp.put("item", d.getDetail_item());
                    temp.put("msg", d.getDetail_desc());
                }

                int allpage = count / limit;
                if (count % limit > 0)
                    allpage++;
                result.put("allpage", Integer.valueOf(allpage));
                result.put("page", Integer.valueOf(page));
                result.put("data", demap);
            } else {
                result.put("data", "no");
            }
        } else {
            result.put("nologin", "nologin");
        }
        out.print(JSONObject.fromObject(result));
        out.flush();
        out.close();
    }

    public void BuyInfoGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        JSONObject json = new JSONObject();
        if (user != null) {
            Bc_user find = UserStatic.find(user);
            String p = request.getParameter("p");
            int userid = find.getUser_id();
            int date = 1;
            String btime = TryStatic.getAgo(date);
            String etime = TimeUtil.getToday("yyyy-MM-dd");
            int page = TryStatic.StrToInt(p, 1);
            int limit = 12;
            int statu = -2;
            int hm = -1;
            int count = service.findBuyCount(userid, btime, etime, null, statu, hm);
            List findBuy = service.findBuy(userid, btime, etime, null, statu, hm, (page - 1) * limit, limit);
            int allpage = count / limit;
            if (count % limit > 0)
                allpage++;
            if (findBuy != null) {
                json.put("allpage", Integer.valueOf(allpage));
                json.put("page", Integer.valueOf(page));
                json.put("data", findBuy);
            } else {
                json.put("data", "no");
            }
        } else {
            json.put("nologin", "nologin");
        }
        out.print(json.toString());
        out.flush();
        out.close();
    }

    public void BankGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = UserSession.getUser(request);
        HashMap result = new HashMap();
        if (user != null) {
            Bc_user find = UserStatic.find(user);
            String user_realname = find.getUser_realname();
            if (StringUtils.isNotBlank(user_realname)) {
                int user_id = find.getUser_id();
                List findBanks = bankservice.findBanks(user_id);
                if (findBanks != null) {
                    List demap = new ArrayList();
                    HashMap temp;
                    for (Iterator iterator = findBanks.iterator(); iterator.hasNext(); demap.add(temp)) {
                        Bc_banks d = (Bc_banks) iterator.next();
                        temp = new HashMap();
                        temp.put("bank", d.getBanks_bank());
                        temp.put("bankadd", d.getBanks_add());
                        temp.put("bankname", d.getBanks_name());
                        temp.put("card", d.getBanks_card());
                    }

                    result.put("realname", find.getUser_realname());
                    result.put("name", find.getUser_name());
                    result.put("data", demap);
                } else {
                    result.put("nobank", "nobank");
                }
            } else {
                result.put("norealname", "norealname");
            }
        } else {
            result.put("nologin", "nologin");
        }
        out.print(JSONObject.fromObject(result));
        out.flush();
        out.close();
    }
}
