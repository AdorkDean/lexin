//    Bc_buyIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_buy;
import com.caipiao.entity.out.BuyOneOut;
import com.caipiao.entity.out.HemaiEntity;

import java.util.List;
import java.util.Map;

/**
 * 购买接口
 */
public interface Bc_buyIntface {

    public abstract boolean add(Bc_buy bc_buy);

    public abstract boolean delete(int i);

    public abstract boolean update(int i, Map<String, Object> map);

    public abstract boolean update(String s, Map<String, Object> map);

    public abstract boolean itemadd(String s, double d);

    public abstract boolean updatehave(String s, double d);

    public abstract Bc_buy find(int i);

    public abstract Bc_buy find(int i, int j);

    public abstract BuyOneOut find(String s);

    public abstract Bc_buy findBuyOne(String s);

    public abstract List findTheHemaiList(int i, String s);

    public abstract HemaiEntity findTheHemai(String s);

    public abstract HemaiEntity findTheHemai(int i);

    public abstract List findBuy(int i, String s, String s1, String s2, int j, int k, int l,
                                 int i1);

    public abstract int findBuyCount(int i, String s, String s1, String s2, int j, int k);

    public abstract List findsBuy(String s, String s1, String s2, int i, int j, String s3, String s4,
                                  String s5, int k, int l);

    public abstract int findsBuyCount(String s, String s1, String s2, int i, int j, String s3, String s4,
                                      String s5);

    public abstract List findsBuyForHM(String s, String s1, int i, int j, int k, int l, int i1);

    public abstract int findsBuyForHMCount(String s, String s1, int i, int j, int k);
}
