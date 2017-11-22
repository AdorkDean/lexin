//    Bc_commIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_comm;

import java.util.List;
import java.util.Map;

public interface Bc_commIntface {

    public abstract boolean add(Bc_comm bc_comm);

    public abstract boolean delete(int i);

    public abstract boolean update(int i, Map<String, Object> map);

    public abstract Bc_comm find(int i);

    public abstract Bc_comm findById(int i);

    public abstract List finds(String s, int i, int j);

    public abstract int findsCount(String s);
}
