
//    Bc_newsIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_news;
import java.util.List;
import java.util.Map;

public interface Bc_newsIntface
{

	public abstract boolean add(Bc_news bc_news);

	public abstract boolean delete(int i);

	public abstract boolean update(int i, Map<String, Object> map);

	public abstract Bc_news find(int i);

	public abstract List findByType(String s, String s1, String s2, String s3, String s4, int i, int j,
									int k, int l, int i1);

	public abstract int findByTypeCount(String s, String s1, String s2, String s3, String s4, int i, int j,
										int k);
}
