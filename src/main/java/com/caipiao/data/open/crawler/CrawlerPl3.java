package com.caipiao.data.open.crawler;

import com.caipiao.utils.LotEmun;
import com.caipiao.utils.OmmitUtil;
import com.caipiao.utils.TryStatic;

import java.util.HashMap;

// Referenced classes of package com.caipiao.data.open.crawler:
//			OpenCrawler, GetOpenNumber

public class CrawlerPl3 extends OpenCrawler {

    public CrawlerPl3() {
        super.lot = LotEmun.Pl3.name;
    }

    public HashMap getHaoma(int i) {
        return GetOpenNumber.GetPl3(i);
    }

    public String getOldQihao(String qihao) {
        String result = "";
        String year = qihao.substring(0, 4);
        String nums = qihao.substring(4);
        int num = TryStatic.StrToInt(nums, 1);
        if (--num >= 100)
            result = (new StringBuilder(String.valueOf(year))).append(num).toString();
        else if (num >= 10 && num < 100)
            result = (new StringBuilder(String.valueOf(year))).append("0").append(num).toString();
        else if (num > 0 && num < 10)
            result = (new StringBuilder(String.valueOf(year))).append("00").append(num).toString();
        return result;
    }

    public String getOmmit(String oldommit, String haoma) {
        return OmmitUtil.Fc3d_Pl3(oldommit, haoma);
    }
}
