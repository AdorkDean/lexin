//    LotsaleIntfaceImpl.java

package com.caipiao.intfaceImpl;

import com.caipiao.entity.Bc_lotsale;
import com.caipiao.intface.Bc_lotsaleIntface;
import com.sysbcjzh.mysql.Mysql;

import java.util.List;

public class LotsaleIntfaceImpl
        implements Bc_lotsaleIntface {

    Mysql dao;

    public LotsaleIntfaceImpl() {
        dao = Mysql.getInstance();
    }

    public Bc_lotsale find(String name) {
        return (Bc_lotsale) dao.find("select * from Bc_lotsale where Lotsale_name=?", Bc_lotsale.class, new Object[]{
                name
        });
    }

    public boolean update(String name, int status) {
        return dao.update("update Bc_lotsale set Lotsale_status=? where Lotsale_name=?", new Object[]{
                Integer.valueOf(status), name
        });
    }

    public List finds() {
        return dao.finds("select * from Bc_lotsale order by Lotsale_name asc", Bc_lotsale.class, new Object[0]);
    }
}
