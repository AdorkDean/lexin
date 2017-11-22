//    LotSystem.java

package com.caipiao.servlet;

import com.caipiao.entity.Bc_lottery;
import com.caipiao.utils.NowQihao;
import com.sysbcjzh.utils.IndexAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class LotSystem extends IndexAction {

    private static final long serialVersionUID = 1L;

    public LotSystem() {
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
    }

    public void TempOpenGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lot = request.getParameter("lot");
        List findOpenByLot = NowQihao.findOpenByLot(lot);
        PrintWriter out = response.getWriter();
        String html = "<?xml version=\"1.0\" encoding=\"utf-8\"?><xml>";
        for (Iterator iterator = findOpenByLot.iterator(); iterator.hasNext(); ) {
            Bc_lottery newOpen = (Bc_lottery) iterator.next();
            String substring = newOpen.getLot_qihao().substring(2);
            if ("Gd11x5".equals(lot) || "Jxssc".equals(lot))
                substring = newOpen.getLot_qihao();
            html = (new StringBuilder(String.valueOf(html))).append("<row expect=\"").append(substring).append("\" opencode=\"").append(newOpen.getLot_haoma()).append("\" endtime=\"").append(newOpen.getLot_etime()).append("\"></row>").toString();
        }

        html = (new StringBuilder(String.valueOf(html))).append("</xml>").toString();
        out.write(html);
        out.flush();
        out.close();
    }
}
