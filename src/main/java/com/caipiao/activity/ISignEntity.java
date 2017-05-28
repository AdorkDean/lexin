package com.caipiao.activity;

import java.util.Map;

/**
 * 是否为实体
 */
public interface ISignEntity 
{

	public abstract SignEntity find(int i, String s);

	public abstract int findsur(int i);

	public abstract boolean add(SignEntity signentity);

	public abstract boolean update(int i, Map map);
}
