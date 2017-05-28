
//    Bc_userIntface.java

package com.caipiao.intface;

import com.caipiao.entity.*;
import java.util.List;
import java.util.Map;

public interface Bc_userIntface
{

	public abstract boolean updatetimeip(int i, String s, String s1);

	public abstract boolean updateUserPass(int i, String s);

	public abstract boolean add(Bc_user bc_user);

	public abstract boolean add(Bc_detail bc_detail);

	public abstract boolean add(Bc_point bc_point);

	public abstract boolean add(Bc_comm bc_comm);

	public abstract boolean add(Bc_phb bc_phb);

	public abstract boolean delete(int i);

	public abstract boolean delete(String s);

	public abstract boolean update(int i, Map map);

	public abstract boolean update(String s, Map map);

	public abstract Bc_user find(String s);

	public abstract Bc_user find(int i);

	public abstract boolean EmailIsExist(String s);

	public abstract boolean NameIsExist(String s);

	public abstract boolean addMoney(int i, double d, int j, int k, double d1);

	public abstract boolean addMoney(int i, double d, int j);

	public abstract boolean MoneyToDongjie(int i, double d);

	public abstract boolean DongToMoney(int i, double d);

	public abstract boolean DongSub(int i, double d);

	public abstract List findlist(String s, String s1, double d, int i, int j, int k,
								  String s2, String s3, int l, int i1);

	public abstract int findlistCount(String s, String s1, double d, int i, int j, int k,
									  String s2, String s3);

	public abstract List findInlist(int i, int j, String s, String s1, int k, int l);

	public abstract int findInlistCount(int i, int j, String s, String s1);
}
