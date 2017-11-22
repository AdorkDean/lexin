//    Bc_lotsaleIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_lotsale;

import java.util.List;

public interface Bc_lotsaleIntface {

    public abstract Bc_lotsale find(String s);

    public abstract boolean update(String s, int i);

    public abstract List finds();
}
