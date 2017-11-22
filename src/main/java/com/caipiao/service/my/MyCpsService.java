//    MyCpsService.java

package com.caipiao.service.my;

import com.caipiao.entity.Bc_comm;
import com.caipiao.intface.Bc_commIntface;
import com.caipiao.intface.Bc_rechIntface;
import com.caipiao.intface.Bc_userIntface;
import com.caipiao.intfaceImpl.CommIntfaceImpl;
import com.caipiao.intfaceImpl.RechIntfaceImpl;
import com.caipiao.intfaceImpl.UserIntfaceImpl;

import java.util.HashMap;
import java.util.List;

public class MyCpsService {

    static Bc_commIntface comm = new CommIntfaceImpl();
    static Bc_userIntface user = new UserIntfaceImpl();
    Bc_rechIntface rechdao;

    public MyCpsService() {
        rechdao = new RechIntfaceImpl();
    }

    public Bc_comm findcomm(int User_id) {
        return comm.findById(User_id);
    }

    public List findList(int User_id, int cus, String btime, String etime, int start, int limit) {
        return user.findInlist(User_id, cus, btime, etime, start, limit);
    }

    public int findListCount(int User_id, int cus, String btime, String etime) {
        return user.findInlistCount(User_id, cus, btime, etime);
    }

    public boolean upcomm(int userid, double ssq, double dlt, double pl5,
                          double fc3d, double pl3, double cqssc, double jxssc, double sd11x5, double jx11x5, double gd11x5,
                          double cq11x5) {
        HashMap map = new HashMap();
        map.put("Ssq", Double.valueOf(ssq));
        map.put("Dlt", Double.valueOf(dlt));
        map.put("Pl5", Double.valueOf(pl5));
        map.put("Fc3d", Double.valueOf(fc3d));
        map.put("Pl3", Double.valueOf(pl3));
        map.put("Cqssc", Double.valueOf(cqssc));
        map.put("Jxssc", Double.valueOf(jxssc));
        map.put("Sd11x5", Double.valueOf(sd11x5));
        map.put("Jx11x5", Double.valueOf(jx11x5));
        map.put("Gd11x5", Double.valueOf(gd11x5));
        map.put("Cq11x5", Double.valueOf(cq11x5));
        return comm.update(userid, map);
    }

    public List findsRech(int upid, String btime, String etime, String name, int status, int start, int limit) {
        return rechdao.findCpsRech(upid, btime, etime, name, status, start, limit);
    }

    public int findsRechCount(int upid, String btime, String etime, String name, int status) {
        return rechdao.findCpsRechcount(upid, btime, etime, name, status);
    }

}
