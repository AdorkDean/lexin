//    Bc_pointIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_point;

import java.util.List;
import java.util.Map;

public interface Bc_pointIntface {

    public abstract boolean delete(int i);

    public abstract boolean update(int i, Map<String, Object> map);

    public abstract Bc_point find(int i);

    public abstract List finds(int i, String s, String s1, int j, int k, int l, int i1);

    public abstract int findscount(int i, String s, String s1, int j, int k);
}
