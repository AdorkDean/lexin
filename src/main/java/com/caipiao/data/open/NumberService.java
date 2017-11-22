package com.caipiao.data.open;

import com.caipiao.entity.Bc_lottery;
import com.caipiao.intface.Bc_lotteryIntface;
import com.caipiao.intfaceImpl.LotteryIntfaceImpl;
import com.caipiao.utils.Log;
import com.caipiao.utils.SystemSet;
import com.caipiao.utils.TimeUtil;
import com.caipiao.utils.TryStatic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.caipiao.data.open:
//			NumberThread

/**
 * 控制 开奖号码的业务逻辑 note by nicholas
 */
public class NumberService {

    long yctime;
    static Bc_lotteryIntface dao = new LotteryIntfaceImpl();
    Set set;

    public NumberService() {
        yctime = 40L;
        set = new HashSet();
        long time = System.currentTimeMillis();
        String btime = TimeUtil.LongToString(time - 3600000l, "yyyy-MM-dd HH:mm:ss");//一个小时内尚未开奖的彩票 note by nicholas
        String ntime = TimeUtil.LongToString(time, "yyyy-MM-dd HH:mm:ss");
        List findNotOpenByTime = dao.findNotOpenByTime(btime, ntime);
        if (findNotOpenByTime != null) {
            long nowtime = System.currentTimeMillis();
            for (Iterator iterator = findNotOpenByTime.iterator(); iterator.hasNext(); ) {
                Bc_lottery b = (Bc_lottery) iterator.next();
                String lot_name = b.getLot_name();
                yctime = TryStatic.StrToInt(SystemSet.crawler.getProperty(lot_name));
                long etime = TimeUtil.StringToLong(b.getLot_etime(), "yyyy-MM-dd HH:mm:ss");
                if (nowtime >= etime + yctime * 1000L) {
                    Log.ShowInfo((new StringBuilder(String.valueOf(ntime))).append(":发现").append(lot_name).append("有开奖号码需要抓取！").toString());
                    set.add(lot_name);
                }
            }
        }
    }

    public void Instance() {
        if (set.size() >= 2) {
            NumberThread getOpenThread;
            for (Iterator iterator = set.iterator(); iterator.hasNext();) {
                String s = (String) iterator.next();
                getOpenThread = new NumberThread(s);
                getOpenThread.start();
            }

        }
    }

    public static void main(String[] args) {
        //note by nicholas
        long time = System.currentTimeMillis();
        String btime = TimeUtil.LongToString(time - 0x36ee80L, "yyyy-MM-dd HH:mm:ss");
        String ntime = TimeUtil.LongToString(time, "yyyy-MM-dd HH:mm:ss");
        System.out.println("hhhhhhhhhhhh:" + btime);
        System.out.println("nnnnnnnnnnnn:" + ntime);

    }

}