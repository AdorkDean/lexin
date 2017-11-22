package com.caipiao.data.open;

import com.caipiao.data.open.crawler.*;
import com.caipiao.utils.LockList;
import com.caipiao.utils.LotEmun;

/**
 * 爬虫线程
 */
public class NumberThread extends Thread {
    private String Lot_name;

    public NumberThread(String name) {
        this.Lot_name = name;
    }

    public void run() {
        if (!LockList.numberlock.contains(this.Lot_name)) {
            LockList.numberlock.add(this.Lot_name);

            label624:
            try {
                if ("Cqssc".equals(this.Lot_name)) {
                    new CrawlerCqssc().Instance();
                } else if ("Jxssc".equals(this.Lot_name)) {
                    new CrawlerJxssc().Instance();
                } else if ("Sd11x5".equals(this.Lot_name)) {
                    new CrawlerSd11x5().Instance();
                } else if ("Jx11x5".equals(this.Lot_name)) {
                    new CrawlerJx11x5().Instance();
                } else if ("Gd11x5".equals(this.Lot_name)) {
                    new CrawlerGd11x5().Instance();
                } else if ("Cq11x5".equals(this.Lot_name)) {
                    new CrawlerCq11x5().Instance();
                } else if ("Sh11x5".equals(this.Lot_name)) {
                    new CrawlerSh11x5().Instance();
                } else if ("Jsk3".equals(this.Lot_name)) {
                    new CrawlerJsk3().Instance();
                } else if (LotEmun.Bjpk10.name.equals(this.Lot_name)) {
                    new CrawlerBjpk10().Instance();
                } else if (!"Jlk3".equals(this.Lot_name)) {
                    if ("Gxk3".equals(this.Lot_name)) {
                        new CrawlerGxk3().Instance();
                        break label624;
                    }
                    if ("Ahk3".equals(this.Lot_name)) {
                        new CrawlerAhk3().Instance();
                        break label624;
                    }
                    if ((!"Kl8".equals(this.Lot_name)) && (!"Dwzdy".equals(this.Lot_name)) && (!"Xync".equals(this.Lot_name)) &&
                            (!"Gdklsf".equals(this.Lot_name)) && (!"Xysc".equals(this.Lot_name)) && (!"Qyh".equals(this.Lot_name))) {
                        if ("Ssq".equals(this.Lot_name)) {
                            new CrawlerSsq().Instance();
                            break label624;
                        }
                        if ("Dlt".equals(this.Lot_name)) {
                            new CrawlerDlt().Instance();
                            break label624;
                        }
                        if ("Fc3d".equals(this.Lot_name)) {
                            new CrawlerFc3d().Instance();
                            break label624;
                        }
                        if ("Pl5".equals(this.Lot_name)) {
                            new CrawlerPl5().Instance();
                            break label624;
                        }
                        if ("Pl3".equals(this.Lot_name)) {
                            new CrawlerPl3().Instance();
                            break label624;
                        }
                        if ("Ynssc".equals(this.Lot_name)) {
                            new CrawlerYnssc().Instance();
                            break label624;//新加的印尼时时彩
                        }
                        if ((!"Qxc".equals(this.Lot_name)) && (!"Qlc".equals(this.Lot_name)) && (!"Hd15x5".equals(this.Lot_name)) &&
                                (!"Hcy".equals(this.Lot_name)) && (!"Hdljy".equals(this.Lot_name)) && ("Hnssc".equals(this.Lot_name)))
                            new CrawlerHnssc().Instance();
                    }
                }

            } finally {
                LockList.numberlock.remove(this.Lot_name);
                System.out.println("爬虫任务锁：" + this.Lot_name + "<--" + LockList.numberlock);
            }
        }
    }
}