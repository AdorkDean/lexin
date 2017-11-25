package com.caipiao.data.open.crawler;

import com.caipiao.entity.Bc_lottery;
import com.caipiao.utils.LotEmun;

import java.util.HashMap;
import java.util.List;

/**
 * @Author yintiefu
 * @Date 17/11/2
 */
public class CrawlerBjpk10 extends OpenCrawler {

    public CrawlerBjpk10() {
        super.lot = LotEmun.Bjpk10.name;
    }

    @Override
    public HashMap getHaoma(int i) {
        List noAgoTen = findNoAgoTen();
        if (noAgoTen.size() > 0) {
            return GetOpenNumber.GetBjpk10(i, ((Bc_lottery) noAgoTen.get(0)).getLot_qihao());
        } else {
            return new HashMap();
        }
    }

    @Override
    public String getOmmit(String s, String s1) {
        return "";
    }

    @Override
    public String getOldQihao(String s) {
        return String.valueOf(Integer.parseInt(s) - 1);
    }
}
