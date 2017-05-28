
//    LockList.java

package com.caipiao.utils;

import java.util.HashSet;
import java.util.Set;

public class LockList
{

	public static Set openlock = new HashSet();
	public static Set numberlock = new HashSet();
	public static Set itemlock = new HashSet();
	public static Set drawlock = new HashSet();
	public static int hemailock = 0;

	public LockList()
	{
	}

}
