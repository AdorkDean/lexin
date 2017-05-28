
//    Bc_autoIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_auto;
import java.util.List;
import java.util.Map;

public interface Bc_autoIntface
{

	public abstract boolean add(Bc_auto bc_auto);

	public abstract boolean delete(int i);

	public abstract boolean update(int i, Map<String, Object> map);

	public abstract Bc_auto find(int i);

	public abstract List finds(String s, String s1, int i, String s2, String s3, int j, int k);

	public abstract int findsCount(String s, String s1, int i, String s2, String s3);
}
