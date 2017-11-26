//    Mode.java

package com.caipiao.servlet.lottery;

import com.caipiao.service.lottery.ModeService;
import com.caipiao.utils.NowQihao;
import com.caipiao.utils.TryStatic;
import com.sysbcjzh.utils.IndexAction;
import com.sysbcjzh.utils.StringUtils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Mode extends IndexAction {

    private ModeService service;
    private static final long serialVersionUID = 0x632f01c3e4d41e8eL;

    public Mode() {
        service = new ModeService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List findOpen = NowQihao.findOpen();
        JSONObject json = new JSONObject();
        if (findOpen != null)
            json.put("msg", findOpen);
        else
            json.put("msg", "no");
        out.print(json.toString());
        out.flush();
        out.close();
    }

    public void DayOpenGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String lot = request.getParameter("lot");
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(lot)) {
            List findOpenByDay = NowQihao.findOpenByDay(lot);
            if (findOpenByDay != null)
                json.put("msg", findOpenByDay);
            else
                json.put("msg", "no");
        } else {
            json.put("msg", "err");
        }
        out.print(json.toString());
        out.flush();
        out.close();
    }

    public void LotOpenGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String lot = request.getParameter("lot");
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(lot)) {
            List findOpenByLot = NowQihao.findOpenByLot(lot);
            int count = NowQihao.findOpenCount(lot);
            if (findOpenByLot != null) {
                json.put("msg", findOpenByLot);
                json.put("openCount", count);
            } else {
                json.put("msg", "no");
            }
        } else {
            json.put("msg", "err");
        }
        out.print(json.toString());
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String lot = request.getParameter("lot");
        String ids = request.getParameter("id");
        String types = request.getParameter("type");
        int id = TryStatic.StrToInt(ids, -1);
        int type = TryStatic.StrToInt(types, -1);
        List findphb = service.findphb(id, type, lot);
        JSONObject json = new JSONObject();
        if (findphb != null)
            json.put("msg", findphb);
        else
            json.put("msg", "no");
        out.print(json.toString());
        out.flush();
        out.close();
    }

    public void TimeGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String lot = request.getParameter("lot");
        String res = "err";
        if (StringUtils.isNotBlank(lot)) {
            String result = NowQihao.getNowTime(lot);
            if (StringUtils.isNotBlank(result))
                res = result;
        }
        out.print(res);
        out.flush();
        out.close();
    }

    public void NewWinGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String lot = request.getParameter("lot");
        JSONObject json = new JSONObject();
        List findNewWin = null;
        if (StringUtils.isNotBlank(lot))
            findNewWin = service.findNewWin(lot, 30);
        else
            findNewWin = service.findNewWin(30);
        if (findNewWin != null)
            json.put("msg", findNewWin);
        else
            json.put("msg", "no");
        out.print(json.toString());
        out.flush();
        out.close();
    }

    public void CutListPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String lot = request.getParameter("lot");
        JSONObject json = new JSONObject();
        if (StringUtils.isNotBlank(lot)) {
            List cutList = NowQihao.getCutList(lot);
            if (cutList != null)
                json.put("msg", cutList);
            else
                json.put("msg", "no");
        } else {
            json.put("msg", "err");
        }
        out.print(json.toString());
        out.flush();
        out.close();
    }
}
