package com.caipiao.data.service;

import com.caipiao.entity.Bc_lottery;
import com.caipiao.intface.Bc_lotteryIntface;
import com.caipiao.intfaceImpl.LotteryIntfaceImpl;
import com.caipiao.utils.*;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddCaiqiService {

    Bc_lotteryIntface dao;

    public AddCaiqiService() {
        dao = new LotteryIntfaceImpl();
    }

    /**
     * 添加期号
     *
     * @param lot
     * @param days
     * @param riqi
     * @param qihao
     */
    public void AddQiHao(String lot, int days, String riqi, String qihao) {
        String today = riqi;
        int type = LotEmun.valueOf(lot).type;
        if (1 == type) {
            String old = TimeUtil.LongToString(TimeUtil.StringToLong((new StringBuilder(String.valueOf(riqi))).append(" 11:11:00").toString(), "yyyy-MM-dd HH:mm:ss") - 0x5265c00L, "yyyy-MM-dd HH:mm:ss").substring(0, 10);
            for (int i = 1; i < days; i++) {
                if (lot.equals(LotEmun.Cqssc.name))
                    addCqssc(today);
                else if (lot.equals(LotEmun.Jxssc.name))
                    addJxssc(old, today);
                else if (lot.equals(LotEmun.Hnssc.name))
                    addHnssc(old, today);
                else if (lot.equals(LotEmun.Ynssc.name))
                    addYnssc(old, today);
                else if (lot.equals(LotEmun.Sd11x5.name))
                    addSd11x5(old, today);
                else if (lot.equals(LotEmun.Jx11x5.name))
                    addJx11x5(old, today);
                else if (lot.equals(LotEmun.Gd11x5.name))
                    addGd11x5(old, today);
                else if (lot.equals(LotEmun.Cq11x5.name))
                    addCq11x5(old, today);
                else if (lot.equals(LotEmun.Sh11x5.name))
                    addSh11x5(old, today);
                else if (lot.equals(LotEmun.Jsk3.name))
                    addJsk3(old, today);
                else if (lot.equals(LotEmun.Gxk3.name))
                    addGxk3(old, today);
                else if (lot.equals(LotEmun.Ahk3.name))
                    addAhk3(old, today);
                else if (lot.equals(LotEmun.Bjpk10.name)) {
                    addBjpk10(today);
                }
                old = today;
                today = TimeUtil.LongToString(TimeUtil.StringToLong(today, "yyyy-MM-dd") + 0x5265c00L, "yyyy-MM-dd");
            }

        } else if (type == 0)
            if (lot.equals(LotEmun.Pl3.name))
                addPl3ForNum(days, qihao, riqi);
            else if (lot.equals(LotEmun.Pl5.name))
                addPl5ForNum(days, qihao, riqi);
            else if (lot.equals(LotEmun.Fc3d.name))
                addFc3dForNum(days, qihao, riqi);
            else if (lot.equals(LotEmun.Ssq.name))
                addSsqForNum(days, qihao, riqi);
            else if (lot.equals(LotEmun.Dlt.name))
                addDltForNum(days, qihao, riqi);
    }

    private boolean addJsk3(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Jsk3.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("082").toString());
        if (find == null) {
            for (int i = 1; i < 83; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("00").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 22:10:00").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Jsk3[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Jsk3[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Jsk3[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addGxk3(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Gxk3.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("078").toString());
        if (find == null) {
            for (int i = 1; i < 79; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("00").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 22:29:00").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Gxk3[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Gxk3[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Gxk3[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addAhk3(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Ahk3.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("080").toString());
        if (find == null) {
            for (int i = 1; i < 81; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("00").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 22:00:00").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Ahk3[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Ahk3[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Ahk3[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addCqssc(String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Cqssc.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("120").toString());
        if (find == null) {
            for (int i = 1; i < 121; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("00").append(i).toString();
                else if (i >= 10 && i < 100)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" 00:00:00").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Cqssc[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Cqssc[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Cqssc[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addJxssc(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Jxssc.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("084").toString());
        if (find == null) {
            for (int i = 1; i < 85; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("00").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 23:12:02").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Jxssc[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Jxssc[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Jxssc[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addHnssc(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Hnssc.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("288").toString());
        if (find == null) {
            for (int i = 1; i < 289; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("00").append(i).toString();
                else if (i < 100)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 23:59:50").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Hnssc[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Hnssc[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Hnssc[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addYnssc(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Ynssc.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("288").toString());
        if (find == null) {
            for (int i = 1; i < 289; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("00").append(i).toString();
                else if (i < 100)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 23:59:50").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Ynssc[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Ynssc[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Ynssc[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addSd11x5(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Sd11x5.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("78").toString());
        if (find == null) {
            for (int i = 1; i < 79; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 21:55:00").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Sd11x5[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Sd11x5[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Sd11x5[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addJx11x5(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Jx11x5.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("78").toString());
        if (find == null) {
            for (int i = 1; i < 79; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 22:00:00").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Jx11x5[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Jx11x5[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Jx11x5[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addGd11x5(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Gd11x5.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("84").toString());
        if (find == null) {
            for (int i = 1; i < 85; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 23:00:00").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Gd11x5[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Gd11x5[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Gd11x5[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addCq11x5(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Cq11x5.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("85").toString());
        if (find == null) {
            for (int i = 1; i < 86; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 23:00:00").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Cq11x5[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Cq11x5[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Cq11x5[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private boolean addSh11x5(String old, String day) {
        String replace = day.replace("-", "");
        String lot = LotEmun.Sh11x5.name;
        boolean flag = false;
        Bc_lottery find = dao.find(lot, (new StringBuilder(String.valueOf(replace))).append("90").toString());
        if (find == null) {
            for (int i = 1; i < 91; i++) {
                String qihao;
                if (i < 10)
                    qihao = (new StringBuilder(String.valueOf(replace))).append("0").append(i).toString();
                else
                    qihao = (new StringBuilder(String.valueOf(replace))).append(i).toString();
                if (i == 1)
                    add(lot, qihao, (new StringBuilder(String.valueOf(old))).append(" 23:50:00").toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Sh11x5[i - 1]).toString());
                else
                    add(lot, qihao, (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Sh11x5[i - 2]).toString(), (new StringBuilder(String.valueOf(day))).append(" ").append(CaiqiTime.Sh11x5[i - 1]).toString());
            }

            flag = true;
        }
        return flag;
    }

    private void addPl3ForNum(int num, String qihao, String begin) {
        String lot = LotEmun.Pl3.name;
        String beday = begin;
        int qihaos = TryStatic.StrToInt(qihao, 0);
        for (int i = 0; i < num; i++) {
            String enday = TimeUtil.LongToString(TimeUtil.StringToLong(beday, "yyyy-MM-dd") + 0x5265c00L, "yyyy-MM-dd");
            add(lot, (new StringBuilder(String.valueOf(qihaos))).toString(), (new StringBuilder(String.valueOf(beday))).append(" 20:00:00").toString(), (new StringBuilder(String.valueOf(enday))).append(" 20:00:00").toString());
            qihaos++;
            beday = enday;
        }

    }

    private void addPl5ForNum(int num, String qihao, String begin) {
        String lot = LotEmun.Pl5.name;
        String beday = begin;
        int qihaos = TryStatic.StrToInt(qihao, 0);
        for (int i = 0; i < num; i++) {
            String enday = TimeUtil.LongToString(TimeUtil.StringToLong(beday, "yyyy-MM-dd") + 0x5265c00L, "yyyy-MM-dd");
            add(lot, (new StringBuilder(String.valueOf(qihaos))).toString(), (new StringBuilder(String.valueOf(beday))).append(" 20:00:00").toString(), (new StringBuilder(String.valueOf(enday))).append(" 20:00:00").toString());
            qihaos++;
            beday = enday;
        }

    }

    private void addFc3dForNum(int num, String qihao, String begin) {
        String lot = LotEmun.Fc3d.name;
        String beday = begin;
        int qihaos = TryStatic.StrToInt(qihao, 0);
        for (int i = 0; i < num; i++) {
            String enday = TimeUtil.LongToString(TimeUtil.StringToLong(beday, "yyyy-MM-dd") + 0x5265c00L, "yyyy-MM-dd");
            add(lot, (new StringBuilder(String.valueOf(qihaos))).toString(), (new StringBuilder(String.valueOf(beday))).append(" 21:00:00").toString(), (new StringBuilder(String.valueOf(enday))).append(" 21:00:00").toString());
            qihaos++;
            beday = enday;
        }

    }

    private boolean addSsqForNum(int num, String qihao, String bday) {
        boolean result = false;
        String lot = LotEmun.Ssq.name;
        String bd = bday;
        String ed = "";
        int qihaos = TryStatic.StrToInt(qihao, 0);
        long time = TimeUtil.StringToLong(bd, "yyyy-MM-dd");
        int weekOfDate = getWeekOfDate(time);
        if (2 == weekOfDate || 4 == weekOfDate || weekOfDate == 0) {
            result = true;
            for (int i = 0; i < num; i++) {
                time = TimeUtil.StringToLong(bd, "yyyy-MM-dd");
                weekOfDate = getWeekOfDate(time);
                if (2 == weekOfDate || weekOfDate == 0)
                    ed = TimeUtil.LongToString(time + 0xa4cb800L, "yyyy-MM-dd");
                else if (4 == weekOfDate)
                    ed = TimeUtil.LongToString(time + 0xf731400L, "yyyy-MM-dd");
                add(lot, (new StringBuilder(String.valueOf(qihaos))).toString(), (new StringBuilder(String.valueOf(bd))).append(" 20:00:00").toString(), (new StringBuilder(String.valueOf(ed))).append(" 20:00:00").toString());
                bd = ed;
                qihaos++;
            }

        }
        return result;
    }

    private boolean addDltForNum(int num, String qihao, String bday) {
        boolean result = false;
        String lot = LotEmun.Dlt.name;
        String bd = bday;
        String ed = "";
        int qihaos = TryStatic.StrToInt(qihao, 0);
        long time = TimeUtil.StringToLong(bd, "yyyy-MM-dd");
        int weekOfDate = getWeekOfDate(time);
        if (1 == weekOfDate || 3 == weekOfDate || 6 == weekOfDate) {
            result = true;
            for (int i = 0; i < num; i++) {
                time = TimeUtil.StringToLong(bd, "yyyy-MM-dd");
                weekOfDate = getWeekOfDate(time);
                if (1 == weekOfDate || 6 == weekOfDate)
                    ed = TimeUtil.LongToString(time + 0xa4cb800L, "yyyy-MM-dd");
                else if (3 == weekOfDate)
                    ed = TimeUtil.LongToString(time + 0xf731400L, "yyyy-MM-dd");
                add(lot, (new StringBuilder(String.valueOf(qihaos))).toString(), (new StringBuilder(String.valueOf(bd))).append(" 20:00:00").toString(), (new StringBuilder(String.valueOf(ed))).append(" 20:00:00").toString());
                bd = ed;
                qihaos++;
            }

        }
        return result;
    }

    private boolean addBjpk10(String day) {
        day = day + " 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int init_qihao = 647410;
        //每天179期
        int total = 179;
        //开彩频率 5min
        int freq = 5;
        String lot = LotEmun.Bjpk10.name;
        String init_date = "2017-10-27 09:07:00";
        try {
            int diff = getDiffDays(init_date, day);
            //计算初始期号
            int start_qihao = init_qihao + total * diff;
            String start_date = day.substring(0, 10) + " " + "09:07:00";
            Date start = sdf.parse(start_date);
            for (int i = 0; i < total; i++) {
                int qihao = start_qihao + i;
                Date stime = DateUtils.addMinutes(start, i * freq);
                Date etime = DateUtils.addMinutes(start, (i + 1) * freq);
                add(lot, String.valueOf(qihao), sdf.format(stime), sdf.format(etime));
                System.out.println(lot + " " + qihao + " " + sdf.format(stime) + " " + sdf.format(etime));
            }
            return true;
        } catch (ParseException e) {
            Log.ShowErr("Bjpk10 抓取错误");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 计算日期的时间间隔
     *
     * @param s1
     * @param s2
     * @return
     * @throws ParseException
     */
    private static int getDiffDays(String s1, String s2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse(s1);
        Date d2 = sdf.parse(s2);

        Calendar calst = Calendar.getInstance();
        ;
        Calendar caled = Calendar.getInstance();

        calst.setTime(d1);
        caled.setTime(d2);

        //设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    private static int getWeekOfDate(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        int w = cal.get(7) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    private void add(String lot, String qihao, String btime, String etime) {
        Bc_lottery en = new Bc_lottery();
        en.setLot_btime(btime);
        en.setLot_etime(etime);
        en.setLot_haoma("");
        en.setLot_isopen(0);
        en.setLot_name(lot);
        en.setLot_ommit("");
        en.setLot_qihao(qihao);
        dao.add(en);
    }

}
