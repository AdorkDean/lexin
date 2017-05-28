
//    Bc_msgIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_msg;
import java.util.List;
import java.util.Map;

public interface Bc_msgIntface
{

	public abstract boolean add(Bc_msg bc_msg);

	public abstract boolean delete(int i);

	public abstract boolean delete(int i, int j);

	public abstract boolean update(int i, Map map);

	public abstract boolean update(int i, int j);

	public abstract Bc_msg find(int i);

	public abstract List finds(int i, String s, String s1, int j, int k, int l, int i1);

	public abstract int findscount(int i, String s, String s1, int j, int k);
}
