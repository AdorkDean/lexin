
//    Bc_drawIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_draw;
import java.util.List;
import java.util.Map;

public interface Bc_drawIntface
{

	public abstract boolean add(Bc_draw bc_draw);

	public abstract boolean delete(int i);

	public abstract boolean update(int i, Map map);

	public abstract Bc_draw find(int i);

	public abstract List finds(int i, String s, String s1, int j, int k, int l, int i1,
							   int j1);

	public abstract int findscount(int i, String s, String s1, int j, int k, int l);

	public abstract List finds(int i, String s, String s1, int j, int k, int l, int i1);

	public abstract int findscount(int i, String s, String s1, int j, int k);

	public abstract int findUserDrawCount(int i);

	public abstract int findNowDraw();
}
