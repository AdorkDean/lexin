package com.caipiao.data.open.crawler;

import com.caipiao.utils.LotEmun;
import com.caipiao.utils.OmmitUtil;
import com.caipiao.utils.TimeUtil;

import java.util.HashMap;

// Referenced classes of package com.caipiao.data.open.crawler:
//			OpenCrawler, GetOpenNumber

public class CrawlerAhk3 extends OpenCrawler {

    public CrawlerAhk3() {
        super.lot = LotEmun.Ahk3.name;
    }

    public HashMap getHaoma(int i) {
        return GetOpenNumber.GetAhk3(i);
    }

    public String getOldQihao(String qihao) {
        String result = "";
        String day = qihao.substring(0, 8);
        String nums = qihao.substring(8);
        int num = 1;
        try {
            num = Integer.valueOf(nums).intValue();
        } catch (Exception exception) {
        }
        if (--num >= 10 && num < 100)
            result = (new StringBuilder(String.valueOf(day))).append("0").append(num).toString();
        else if (num > 0 && num < 10) {
            result = (new StringBuilder(String.valueOf(day))).append("00").append(num).toString();
        } else {
            long stringToLong = TimeUtil.StringToLong(day, "yyyyMMdd") - 0x5265c00L;
            String oldday = TimeUtil.LongToString(stringToLong, "yyyyMMdd");
            result = (new StringBuilder(String.valueOf(oldday))).append("080").toString();
        }
        return result;
    }

    public String getOmmit(String oldommit, String haoma) {
        return OmmitUtil.K3(oldommit, haoma);
    }
}
