//    MyRedsService.java

package com.caipiao.service.my;

import com.caipiao.intface.Bc_redsIntface;
import com.caipiao.intfaceImpl.RedsIntfaceImpl;

import java.util.List;

public class MyRedsService {

    Bc_redsIntface dao;

    public MyRedsService() {
        dao = new RedsIntfaceImpl();
    }

    public List findRed(int userid, String btime, String etime, int type, int subadd, int start, int limit) {
        return dao.finds(userid, btime, etime, type, subadd, start, limit);
    }

    public int findRedcount(int userid, String btime, String etime, int type, int subadd) {
        return dao.findscount(userid, btime, etime, type, subadd);
    }
}
