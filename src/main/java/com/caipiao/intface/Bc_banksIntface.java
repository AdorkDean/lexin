//    Bc_banksIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_banks;

import java.util.List;
import java.util.Map;

public interface Bc_banksIntface {

    public abstract boolean add(Bc_banks bc_banks);

    public abstract boolean delete(int i);

    public abstract boolean update(int i, Map<String, Object> map);

    public abstract Bc_banks find(int i);

    public abstract List finds(int i);

    public abstract int findCountByUser(int i);
}
