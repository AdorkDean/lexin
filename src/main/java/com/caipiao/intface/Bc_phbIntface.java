//    Bc_phbIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_phb;

import java.util.List;
import java.util.Map;

public interface Bc_phbIntface {

    public abstract boolean add(Bc_phb bc_phb);

    public abstract boolean delete(int i);

    public abstract boolean update(int i, Map<String, Object> map);

    public abstract Bc_phb find(int i);

    public abstract Bc_phb findByUser(int i);

    public abstract List finds(int i, String s, String s1, int j, int k);

    public abstract boolean update(int i, String s, double d, double d1, int j);

    public abstract List findsPHB(int i, String s, int j, int k);

    public abstract int findsPHBCount(int i, String s);

    public abstract boolean updateinit(int i);
}
