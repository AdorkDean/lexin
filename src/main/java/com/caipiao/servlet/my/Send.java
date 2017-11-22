//    Send.java

package com.caipiao.servlet.my;

import com.caipiao.entity.Bc_user;
import com.caipiao.pay.chinabank.BankName;
import com.caipiao.pay.chinabank.MD5;
import com.caipiao.service.my.MyRechangeService;
import com.caipiao.service.systeminit.UserStatic;
import com.caipiao.utils.StaticItem;
import com.caipiao.utils.SystemSet;
import com.caipiao.utils.TryStatic;
import com.caipiao.utils.UserSession;
import com.sysbcjzh.utils.IndexAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Send extends IndexAction {

    private static final long serialVersionUID = 1L;
    MyRechangeService dao;
    MD5 MD5;

    public Send() {
        dao = new MyRechangeService();
        MD5 = new MD5();
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }

    public void ChinaBankPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String v_mid = SystemSet.paytype.getProperty("chinabank_pid");
        String key = SystemSet.paytype.getProperty("chinabank_key");
        String v_url = SystemSet.paytype.getProperty("chinabank_url");
        String v_moneytype = "CNY";
        String v_md5info = "";
        double v_amount = TryStatic.StrToDouble(request.getParameter("v_amount"), 0.0D);
        String v_pmode = request.getParameter("v_pmode");
        String username = UserSession.getUser(request);
        if (v_amount > 0.0D && username != null) {
            Bc_user find = UserStatic.find(username);
            if (find != null) {
                String v_oid = StaticItem.GetRechitem();
                dao.Rech(username, find.getUser_id(), v_oid, v_amount, 0.0D, 1, BankName.GetName(v_pmode));
                String text = (new StringBuilder(String.valueOf(v_amount))).append(v_moneytype).append(v_oid).append(v_mid).append(v_url).append(key).toString();
                v_md5info = MD5.getMD5ofStr(text);
                request.setAttribute("v_md5info", v_md5info);
                request.setAttribute("v_mid", v_mid);
                request.setAttribute("v_oid", v_oid);
                request.setAttribute("v_amount", Double.valueOf(v_amount));
                request.setAttribute("v_moneytype", v_moneytype);
                request.setAttribute("v_url", v_url);
                request.setAttribute("v_pmode", v_pmode);
                request.setAttribute("remark1", username);
                request.getRequestDispatcher("/pay/chinabank/Send.jsp").forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.print("<script>alert('用户不存在');</script>");
                out.flush();
                out.close();
            }
        } else {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('金额或用户不能为空!');</script>");
            out.flush();
            out.close();
        }
    }
}
