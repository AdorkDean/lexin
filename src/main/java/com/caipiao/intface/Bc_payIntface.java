//    Bc_payIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_pay;

import java.util.List;
import java.util.Map;

/**
 * 支付接口
 */
public interface Bc_payIntface {

    public abstract boolean add(Bc_pay bc_pay);

    public abstract boolean delete(int i);

    public abstract boolean update(int i, Map map);

    public abstract Bc_pay find(int i);

    public abstract List finds(int i);

    public abstract int findCountByUser(int i);
}
