//    Bc_redsIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_reds;

import java.util.List;
import java.util.Map;

public interface Bc_redsIntface {

    public abstract boolean add(Bc_reds bc_reds);

    public abstract boolean delete(int i);

    public abstract boolean update(int i, Map map);

    public abstract Bc_reds find(int i);

    public abstract List finds(int i, String s, String s1, int j, int k, int l, int i1);

    public abstract int findscount(int i, String s, String s1, int j, int k);
}
