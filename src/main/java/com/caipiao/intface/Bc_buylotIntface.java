//    Bc_buylotIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_buylot;
import com.caipiao.entity.out.OpenEntity;
import com.caipiao.entity.out.OutEntity;

import java.util.List;
import java.util.Map;

public interface Bc_buylotIntface {

    public abstract boolean add(Bc_buylot bc_buylot);

    public abstract boolean delete(int i);

    public abstract boolean update(int i, Map<String, Object> map);

    public abstract boolean update(String s, Map<String, Object> map);

    public abstract Bc_buylot findByItemqh(String s, String s1);

    public abstract List findByItem(String s);

    public abstract List findEntityList(String s, String s1, int i);

    public abstract OpenEntity findEntityOne(int i);

    public abstract List findOutList(int i, String s);

    public abstract List findOutList(String s, int i);

    public abstract List findOutList(int i, int j);

    public abstract OutEntity findEntityOne(String s, String s1);

    public abstract OutEntity findOutEntityOne(int i);

    public abstract int findItemNotOpen(String s);

    public abstract int findItemOpenNum(String s);

    public abstract List finds(String s, String s1, String s2, int i, int j, int k);

    public abstract int findsCount(String s, String s1, String s2, int i);
}
