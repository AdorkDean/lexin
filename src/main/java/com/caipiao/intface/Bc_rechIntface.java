
//    Bc_rechIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_rech;
import java.util.List;
import java.util.Map;

public interface Bc_rechIntface
{

	public abstract boolean add(Bc_rech bc_rech);

	public abstract boolean delete(int i);

	public abstract boolean update(int i, Map<String, Object> map);

	public abstract Bc_rech find(int i);

	public abstract Bc_rech find(String s, String s1);

	public abstract Bc_rech find(String s);

	public abstract List finds(int i, String s, String s1, int j, int k, int l, int i1,
							   int j1);

	public abstract int findscount(int i, String s, String s1, int j, int k, int l);

	public abstract List finds(int i, String s, String s1, int j, int k, int l, int i1);

	public abstract int findscount(int i, String s, String s1, int j, int k);

	public abstract int findsDayRechCount(int i);

	public abstract List findCpsRech(int i, String s, String s1, String s2, int j, int k, int l);

	public abstract int findCpsRechcount(int i, String s, String s1, String s2, int j);
}
