package com.caipiao.intface;

import com.caipiao.entity.out.Achievement;

import java.util.List;

/**
 * Created by liujie on 2017/8/21.
 */
public interface BcCaiwuIntface {
    List<String> findsAllAgent();

    List<Achievement> findsAchievementByPage(String var1, String var2, String var3, String var4, int var5, int var6);

    int findsAchievementByPageCount(String var1, String var2, String var3, String var4);

    Achievement findsAchievementByTotal(String var1, String var2, String var3, String var4);
}
