//    Bc_lotteryIntface.java

package com.caipiao.intface;

import com.caipiao.entity.Bc_lottery;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Bc_lotteryIntface {

    public abstract boolean add(Bc_lottery bc_lottery);

    public abstract boolean delete(int i);

    public abstract boolean update(String s, String s1, Map map);

    public abstract boolean update(int i, Map map);

    public abstract Bc_lottery find(String s, String s1);

    public abstract Bc_lottery find(int i);

    public abstract Bc_lottery findByNowTime(String s, String s1);

    public abstract Bc_lottery findByEtime(String s, String s1);

    public abstract List findAllOpen();

    public abstract List findNowAfter(String s, String s1, int i, int j);

    public abstract int findNowAfterCount(String s, String s1);

    public abstract List findDay(String s, String s1);

    public abstract List findNewOpen(String s, int i);

    /**
     * 获取已开奖数量
     *
     * @param lot
     * @return
     */
    public int findOpenCount(String lot,Date day);

    public abstract List findNotOpenByTime(String s, String s1);

    public abstract List findNowAgo(String s, String s1, int i, int j);

    public abstract List findHaveWithOpen(int i);

    public abstract List finds(String s, String s1, int i, int j, String s2, String s3, int k,
                               int l);

    public abstract int findsCount(String s, String s1, int i, int j, String s2, String s3);
}
