//    Bc_buyuserIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_buyuser;

import java.util.List;

public interface Bc_buyuserIntface {

    public abstract boolean add(Bc_buyuser bc_buyuser);

    public abstract List findBuy(int i, String s, String s1, String s2, int j, int k, int l,
                                 int i1);

    public abstract int findBuyCount(int i, String s, String s1, String s2, int j, int k);

    public abstract List findNewWin(String s, int i);

    public abstract List findNewWin(int i);

    public abstract List findByItem(String s);

    public abstract List findByItem(String s, int i, int j);

    public abstract int findByItemCount(String s);

    public abstract boolean addWin(int i, double d);
}
