
//    Bc_logsIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_logs;
import java.util.List;

public interface Bc_logsIntface
{

	public abstract boolean add(Bc_logs bc_logs);

	public abstract List finds(int i, String s, String s1, int j, int k, int l, int i1);

	public abstract int findscount(int i, String s, String s1, int j, int k);

	public abstract int findError(int i, int j);
}
