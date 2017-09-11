package com.caipiao.data.open;

import com.caipiao.utils.*;
import com.sysbcjzh.utils.StringUtils;

import java.util.HashMap;
import java.util.Properties;

public class MethodOpenCode {

    static final String money = "money";
    static final String point = "point";
    static final Properties moneyset;

    public MethodOpenCode() {
    }

    private static double getTryMoney(String lot, String type) {
        return TryStatic.StrToDouble(moneyset.getProperty((new StringBuilder(String.valueOf(lot))).append("_").append(type).append("_").append("money").toString()));
    }

    private static double getTryPoint(String lot, String type) {
        return TryStatic.StrToDouble(moneyset.getProperty((new StringBuilder(String.valueOf(lot))).append("_").append(type).append("_").append("point").toString()));
    }

    public static HashMap GetWinMoney(String lot, String codes, int bs, String haoma) {
        HashMap temp = new HashMap();
        double moneytemp = 0.0D;
        double pointtemp = 0.0D;
        int type = LotEmun.valueOf(lot).type;
        String split[] = codes.split("#");//
        String as[];
        int j = (as = split).length;
        for (int i = 0; i < j; i++) {
            String s = as[i];
            if (1 == type)
                temp = OpenOneSsc(s, lot, haoma);
            else if (type == 0)
                temp = OpenOneSsq(s, lot, haoma);
            moneytemp += ((Double) temp.get("money")).doubleValue();
            pointtemp += ((Double) temp.get("point")).doubleValue();
        }

        temp.put("money", Double.valueOf(moneytemp * (double) bs));
        temp.put("point", Double.valueOf(pointtemp * (double) bs));
        return temp;
    }

    /**
     *
     * @param thecode  购买号码的类型形式
     * @param lot  彩种类型
     * @param haoma 开奖号码
     * @return
     */
    private static HashMap OpenOneSsc(String thecode, String lot, String haoma) {
        HashMap result = new HashMap();
        double win = 0.0D;
        double winPoint = 0.0D;
        String split[] = thecode.split(":");
        String type = split[0]; //玩法
        String code = split[1]; //购买的号码
        double tryMoney = getTryMoney(lot, type);//获取某彩种某玩法的规定底层奖金
        String hms[] = haoma.split(",");
        if (LotEmun.Cqssc.name.equals(lot) || LotEmun.Jxssc.name.equals(lot) || LotEmun.Hnssc.name.equals(lot)
                || LotEmun.Ynssc.name.equals(lot)) {

            win = processSscWinMoney(lot, win, split, type, code, tryMoney, hms);
        } else if (LotEmun.Sd11x5.name.equals(lot) || LotEmun.Jx11x5.name.equals(lot) || LotEmun.Gd11x5.name.equals(lot)
                || LotEmun.Cq11x5.name.equals(lot) || LotEmun.Sh11x5.name.equals(lot)) {

            win = process11x5WinMoney(lot, haoma, win, type, code, tryMoney, hms);
        } else if (LotEmun.Jsk3.name.equals(lot) || LotEmun.Gxk3.name.equals(lot) || LotEmun.Ahk3.name.equals(lot)) {

            win = processK3WinMoney(lot, win, type, code, tryMoney, hms);
        }
        if (win > 0.0D)
            winPoint = win * getTryPoint(lot, type);

        result.put("money", Double.valueOf(win));
        result.put("point", Double.valueOf(winPoint));
        return result;
    }

    private static double processK3WinMoney(String lot, double win, String type, String code, double tryMoney, String[] hms) {
        boolean sth = false;
        boolean eth = false;
        boolean sbt = false;
        boolean slh = false;
        Integer hz1 = Integer.valueOf(TryStatic.StrToInt(hms[0]));
        Integer hz2 = Integer.valueOf(TryStatic.StrToInt(hms[1]));
        Integer hz3 = Integer.valueOf(TryStatic.StrToInt(hms[2]));
        Integer HZ = Integer.valueOf(hz1.intValue() + hz2.intValue() + hz3.intValue());
        if (hms[0].equals(hms[1]) && hms[1].equals(hms[2]))
            sth = true;
        else if (hms[0].equals(hms[1]) || hms[1].equals(hms[2])) {
            eth = true;
        } else {
            sbt = true;
            int int0 = TryStatic.StrToInt(hms[0]);
            int int1 = TryStatic.StrToInt(hms[1]);
            int int2 = TryStatic.StrToInt(hms[2]);
            if (int0 + 1 == int1 && int1 + 1 == int2)
                slh = true;
        }
        String buyhm[] = code.split(",");
        if (PlayType.T400.equals(type)) {
            String r = String.valueOf(HZ);
            String as6[];
            int l3 = (as6 = buyhm).length;
            for (int k3 = 0; k3 < l3; k3++) {
                String str = as6[k3];
                boolean equals = r.equals(str);
                if (!equals)
                    continue;
                if ("3".equals(r) || "18".equals(r))
                    tryMoney = getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T400))).append("1").toString());
                else if ("4".equals(r) || "17".equals(r))
                    tryMoney = getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T400))).append("2").toString());
                else if ("5".equals(r) || "16".equals(r))
                    tryMoney = getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T400))).append("3").toString());
                else if ("6".equals(r) || "15".equals(r))
                    tryMoney = getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T400))).append("4").toString());
                else if ("7".equals(r) || "14".equals(r))
                    tryMoney = getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T400))).append("5").toString());
                else if ("8".equals(r) || "13".equals(r))
                    tryMoney = getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T400))).append("6").toString());
                else if ("9".equals(r) || "12".equals(r))
                    tryMoney = getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T400))).append("7").toString());
                else if ("10".equals(r) || "11".equals(r))
                    tryMoney = getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T400))).append("8").toString());
                win = tryMoney;
                break;
            }

        } else if (PlayType.T401.equals(type) || PlayType.T402.equals(type)) {
            if (sth)
                if (PlayType.T401.equals(type) && code.equals("三同号通选"))
                    win = tryMoney;
                else if (PlayType.T402.equals(type) && code.contains((new StringBuilder(String.valueOf(hms[0]))).append(hms[1]).append(hms[2]).toString()))
                    win = tryMoney;
        } else if (PlayType.T403.equals(type) || PlayType.T404.equals(type)) {
            if (sbt)
                if (PlayType.T403.equals(type)) {
                    boolean con1 = code.contains(hms[0]);
                    boolean con2 = code.contains(hms[1]);
                    boolean con3 = code.contains(hms[2]);
                    if (con1 && con2 && con3)
                        win = tryMoney;
                } else {
                    PlayType.T404.equals(type);
                }
        } else if (PlayType.T405.equals(type)) {
            if (slh && code.equals("三连号通选"))
                win = tryMoney;
        } else if (PlayType.T406.equals(type) || PlayType.T407.equals(type)) {
            if (eth) {
                String t = hms[0];
                String bt = hms[2];
                if (hms[1].equals(hms[2])) {
                    t = hms[1];
                    bt = hms[0];
                } else if (hms[0].equals(hms[2]))
                    bt = hms[1];
                if (PlayType.T406.equals(type) && code.contains((new StringBuilder(String.valueOf(t))).append(t).toString()))
                    win = tryMoney;
                else if (PlayType.T407.equals(type)) {
                    String hao[] = code.split("\\$");
                    if (hao[0].contains((new StringBuilder(String.valueOf(t))).append(t).toString()) && hao[1].contains(bt))
                        win = tryMoney;
                }
            }
        } else if (PlayType.T408.equals(type)) {
            if (!sth) {
                int zhong = 0;
                if (code.contains(hms[0]))
                    zhong++;
                if (code.contains(hms[1]))
                    zhong++;
                if (code.contains(hms[2]))
                    zhong++;
                if (sbt) {
                    if (2 == zhong)
                        win = tryMoney;
                    else if (3 == zhong)
                        win = 3D * tryMoney;
                } else if (3 == zhong)
                    win = tryMoney;
            }
        } else if (PlayType.T409.equals(type) || PlayType.T410.equals(type)) {
            String haos = (new StringBuilder(String.valueOf(hms[0]))).append(hms[1]).append(hms[2]).toString();
            String hao[] = code.split("\\$");
            String dms[] = hao[0].split(",");
            String tms[] = hao[1].split(",");
            if (PlayType.T409.equals(type)) {
                if (!sth && haos.contains(hao[0])) {
                    haos = haos.replaceAll(hao[0], "");
                    int tzhong = 0;
                    String as7[];
                    int k4 = (as7 = tms).length;
                    for (int i4 = 0; i4 < k4; i4++) {
                        String str = as7[i4];
                        if (haos.contains(str)) {
                            tzhong++;
                            haos = haos.replaceAll(str, "");
                        }
                    }

                    win = getTryMoney(lot, PlayType.T408) * (double) tzhong;
                }
            } else if (PlayType.T410.equals(type) && sbt) {
                boolean zhong = true;
                String as8[];
                int l4 = (as8 = dms).length;
                for (int j4 = 0; j4 < l4; j4++) {
                    String str = as8[j4];
                    if (!haos.contains(str)) {
                        zhong = false;
                        break;
                    }
                    haos = haos.replaceAll(str, "");
                }

                if (zhong) {
                    String tm[] = haos.split("");
                    String as9[];
                    int j5 = (as9 = tm).length;
                    for (int i5 = 0; i5 < j5; i5++) {
                        String str = as9[i5];
                        if (StringUtils.isBlank(str) || hao[1].contains(str))
                            continue;
                        zhong = false;
                        break;
                    }

                }
                if (zhong)
                    win = getTryMoney(lot, PlayType.T403);
            }
        }
        return win;
    }

    private static double process11x5WinMoney(String lot, String haoma, double win, String type, String code, double tryMoney, String[] hms) {
        if (PlayType.T350.equals(type) || PlayType.T352.equals(type) || PlayType.T354.equals(type)) {
            boolean h0 = code.contains(hms[0]);
            boolean h1 = code.contains(hms[1]);
            boolean h2 = code.contains(hms[2]);
            if (PlayType.T350.equals(type) && h0)
                win = tryMoney;
            else if (PlayType.T352.equals(type) && h0 && h1)
                win = tryMoney;
            else if (PlayType.T354.equals(type) && h0 && h1 && h2)
                win = tryMoney;
        } else if (PlayType.T351.equals(type) || PlayType.T353.equals(type)) {
            String hao[] = code.split(",");
            String hao0 = hao[0];
            String hao1 = hao[1];
            String hao2 = "";
            try {
                hao2 = hao[2];
            } catch (Exception exception) {
            }
            boolean h0 = hao0.contains(hms[0]);
            boolean h1 = hao1.contains(hms[1]);
            boolean h2 = hao2.contains(hms[2]);
            if (PlayType.T351.equals(type) && h0 && h1)
                win = tryMoney;
            else if (PlayType.T353.equals(type) && h0 && h1 && h2)
                win = tryMoney;
        } else if (PlayType.T355.equals(type) || PlayType.T356.equals(type) || PlayType.T357.equals(type) || PlayType.T358.equals(type) || PlayType.T359.equals(type) || PlayType.T360.equals(type) || PlayType.T361.equals(type)) {
            boolean h0 = code.contains(hms[0]);
            boolean h1 = code.contains(hms[1]);
            boolean h2 = code.contains(hms[2]);
            boolean h3 = code.contains(hms[3]);
            boolean h4 = code.contains(hms[4]);
            int l = code.split(",").length;
            if (l >= 11)
                l = 11;
            int i = 0;
            int ddbase = 0;
            if (h0)
                i++;
            if (h1)
                i++;
            if (h2)
                i++;
            if (h3)
                i++;
            if (h4)
                i++;
            if (PlayType.T355.equals(type))
                ddbase = 2;
            else if (PlayType.T356.equals(type))
                ddbase = 3;
            else if (PlayType.T357.equals(type))
                ddbase = 4;
            else if (PlayType.T358.equals(type))
                ddbase = 5;
            else if (PlayType.T359.equals(type) && i == 5) {
                ddbase = 1;
                i = l - 5;
            } else if (PlayType.T360.equals(type) && i == 5) {
                ddbase = 2;
                i = l - 5;
            } else if (PlayType.T361.equals(type) && i == 5) {
                ddbase = 3;
                i = l - 5;
            } else {
                ddbase = 5;
            }
            win = tryMoney * (double) Comb(i, ddbase);
        } else if (PlayType.T362.equals(type) || PlayType.T363.equals(type) || PlayType.T364.equals(type) || PlayType.T365.equals(type) || PlayType.T366.equals(type) || PlayType.T367.equals(type) || PlayType.T368.equals(type) || PlayType.T369.equals(type) || PlayType.T370.equals(type)) {
            String hao[] = code.split("\\$");
            String dm[] = hao[0].split(",");
            String tm[] = hao[1].split(",");
            int max = 0;
            if (PlayType.T362.equals(type)) {
                max = 2;
                tryMoney = getTryMoney(lot, PlayType.T352);
            } else if (PlayType.T363.equals(type)) {
                max = 3;
                tryMoney = getTryMoney(lot, PlayType.T354);
            } else if (PlayType.T364.equals(type)) {
                max = 2;
                tryMoney = getTryMoney(lot, PlayType.T355);
            } else if (PlayType.T365.equals(type)) {
                max = 3;
                tryMoney = getTryMoney(lot, PlayType.T356);
            } else if (PlayType.T366.equals(type)) {
                max = 4;
                tryMoney = getTryMoney(lot, PlayType.T357);
            } else if (PlayType.T367.equals(type)) {
                max = 5;
                tryMoney = getTryMoney(lot, PlayType.T358);
            } else if (PlayType.T368.equals(type)) {
                max = 6;
                tryMoney = getTryMoney(lot, PlayType.T359);
            } else if (PlayType.T369.equals(type)) {
                max = 7;
                tryMoney = getTryMoney(lot, PlayType.T360);
            } else if (PlayType.T370.equals(type)) {
                max = 8;
                tryMoney = getTryMoney(lot, PlayType.T361);
            }
            if (dm.length < max && dm.length + tm.length >= max) {
                int dzhong = 0;
                String pan = haoma;
                String phms[] = hms;
                if (PlayType.T362.equals(type)) {
                    pan = haoma.substring(0, 5);
                    phms = pan.split(",");
                } else if (PlayType.T363.equals(type)) {
                    pan = haoma.substring(0, 8);
                    phms = pan.split(",");
                }
                String as2[];
                int j2 = (as2 = dm).length;
                for (int j1 = 0; j1 < j2; j1++) {
                    String str = as2[j1];
                    if (pan.contains(str))
                        dzhong++;
                }

                if (dm.length == dzhong || dm.length - dzhong <= max - 5) {
                    int tzhong = 0;
                    String as5[];
                    int j3 = (as5 = phms).length;
                    for (int k2 = 0; k2 < j3; k2++) {
                        String str = as5[k2];
                        if (hao[1].contains(str))
                            tzhong++;
                    }

                    if (max > 5) {
                        if (tzhong + dzhong == 5)
                            win = tryMoney * (double) Comb(tm.length - tzhong, max - dm.length - tzhong);
                    } else {
                        win = tryMoney * (double) Comb(tzhong, max - dm.length);
                    }
                }
            }
        }
        return win;
    }

    private static double processSscWinMoney(String lot, double win, String[] split, String type, String code, double tryMoney, String[] hms) {
        String buyhm[] = code.split(",");
        boolean bz = hms[2].equals(hms[3]) && hms[2].equals(hms[4]);//豹子
        boolean zl = !hms[2].equals(hms[3]) && !hms[2].equals(hms[4]) && !hms[4].equals(hms[3]);
        boolean dz = hms[3].equals(hms[4]);
        if (PlayType.T300.equals(type)) {//买单双猜大小
            Integer hz3 = Integer.valueOf(hms[3]);
            Integer hz4 = Integer.valueOf(hms[4]);
            String dx3 = hz3.intValue() <= 4 ? "小" : "大";
            String sd3 = hz3.intValue() % 2 != 0 ? "单" : "双";
            String dx4 = hz4.intValue() <= 4 ? "小" : "大";
            String sd4 = hz4.intValue() % 2 != 0 ? "单" : "双";
            boolean bd3 = buyhm[0].contains(dx3);
            boolean bs3 = buyhm[0].contains(sd3);
            boolean bd4 = buyhm[1].contains(dx4);
            boolean bs4 = buyhm[1].contains(sd4);
            int tbei1 = 0;
            int tbei2 = 0;
            if (bd3)
                tbei1++;
            if (bs3)
                tbei1++;
            if (bd4)
                tbei2++;
            if (bs4)
                tbei2++;
            win = (double) (tbei1 * tbei2) * tryMoney;
        } else if (PlayType.T303.equals(type) || PlayType.T306.equals(type) || PlayType.T309.equals(type) || PlayType.T312.equals(type)) {
            Integer hz2 = Integer.valueOf(hms[2]);
            Integer hz3 = Integer.valueOf(hms[3]);
            Integer hz4 = Integer.valueOf(hms[4]);
            String r = "";
            if (PlayType.T303.equals(type) || PlayType.T306.equals(type))
                r = String.valueOf(hz3.intValue() + hz4.intValue());
            else if (PlayType.T309.equals(type) || PlayType.T312.equals(type))
                r = String.valueOf(hz2.intValue() + hz3.intValue() + hz4.intValue());
            String as3[];
            int l2 = (as3 = buyhm).length;
            for (int k1 = 0; k1 < l2; k1++) {
                String str = as3[k1];
                boolean equals = r.equals(str);
                if (!equals)
                    continue;
                if (PlayType.T303.equals(type))
                    tryMoney = getTryMoney(lot, PlayType.T302);
                else if (PlayType.T306.equals(type)) {
                    if (dz)
                        tryMoney = getTryMoney(lot, PlayType.T302);
                    else
                        tryMoney = getTryMoney(lot, PlayType.T304);
                } else if (PlayType.T309.equals(type))
                    tryMoney = getTryMoney(lot, PlayType.T308);
                else if (PlayType.T312.equals(type))
                    if (bz)
                        tryMoney = getTryMoney(lot, PlayType.T308);
                    else if (zl)
                        tryMoney = getTryMoney(lot, PlayType.T311);
                    else
                        tryMoney = getTryMoney(lot, PlayType.T310);
                win = tryMoney;
                break;
            }

        } else if (PlayType.T304.equals(type) || PlayType.T305.equals(type) || PlayType.T314.equals(type) || PlayType.T310.equals(type) || PlayType.T311.equals(type) || PlayType.T322.equals(type)) {
            boolean h2 = code.contains(hms[2]);
            boolean h3 = code.contains(hms[3]);
            boolean h4 = code.contains(hms[4]);
            if (PlayType.T304.equals(type)) {
                if (!dz && h3 && h4)
                    win = tryMoney;
            } else if (PlayType.T305.equals(type)) {
                h3 = buyhm[3].contains(hms[3]);
                h4 = buyhm[4].contains(hms[4]);
                boolean h3T = buyhm[3].contains(hms[4]);
                boolean h4T = buyhm[4].contains(hms[3]);
                if (h3 && h4 || h3T && h4T)
                    if (dz)
                        win = getTryMoney(lot, PlayType.T302);
                    else
                        win = getTryMoney(lot, PlayType.T304);
            } else if (PlayType.T310.equals(type)) {
                if (!zl && !bz && h2 && h3 && h4)
                    win = tryMoney;
            } else if (PlayType.T322.equals(type)) {
                if (!bz && !zl) {
                    String split2[] = split[1].split(",");
                    String temp = split2[0];
                    String temp2 = split2[1];
                    String htmep = hms[2];
                    String htmep2 = hms[3];
                    if (split2[1].equals(split2[2])) {
                        temp = split2[1];
                        temp2 = split2[0];
                    } else if (split2[0].equals(split2[1]))
                        temp2 = split2[2];
                    if (hms[3].equals(hms[4])) {
                        htmep = hms[3];
                        htmep2 = hms[2];
                    } else if (hms[2].equals(hms[3]))
                        htmep2 = hms[4];
                    if (temp.equals(htmep) && temp2.equals(htmep2))
                        win = getTryMoney(lot, PlayType.T310);
                }
            } else if ((PlayType.T314.equals(type) || PlayType.T311.equals(type)) && zl && h2 && h3 && h4) {
                if (PlayType.T314.equals(type))
                    tryMoney = getTryMoney(lot, PlayType.T308);
                win = tryMoney;
            }
        } else if (PlayType.T307.equals(type) || PlayType.T313.equals(type)) {
            String as[];
            int k = (as = buyhm).length;
            for (int j = 0; j < k; j++) {
                String bh = as[j];
                if (PlayType.T307.equals(type)) {
                    if (bh.equals(hms[3]) || bh.equals(hms[4]))
                        if (dz)
                            win += getTryMoney(lot, PlayType.T302);
                        else
                            win += getTryMoney(lot, PlayType.T304);
                } else if (PlayType.T313.equals(type) && (bh.equals(hms[2]) || bh.equals(hms[3]) || bh.equals(hms[4])))
                    if (bz)
                        win += getTryMoney(lot, PlayType.T308);
                    else if (zl)
                        win += getTryMoney(lot, PlayType.T311);
                    else
                        win += getTryMoney(lot, PlayType.T310);
            }

        } else if (PlayType.T320.equals(type) || PlayType.T321.equals(type)) {
            buyhm = code.split("\\$");
            if (PlayType.T320.equals(type) && !zl && !bz) {
                String h01 = hms[2];
                String h02 = hms[3];
                if (hms[2].equals(hms[3]))
                    h02 = hms[4];
                boolean h0 = buyhm[0].equals(h01);
                boolean h1 = buyhm[0].equals(h02);
                boolean ot0 = buyhm[1].contains(h01);
                boolean ot1 = buyhm[1].contains(h02);
                if (h0 && ot1 || h1 && ot0)
                    win = getTryMoney(lot, PlayType.T310);
            } else if (PlayType.T321.equals(type) && zl) {
                String splitd[] = buyhm[0].split(",");
                String haos = (new StringBuilder(String.valueOf(hms[2]))).append(hms[3]).append(hms[4]).toString();
                boolean zhong = true;
                String as1[];
                int l1 = (as1 = splitd).length;
                for (int i1 = 0; i1 < l1; i1++) {
                    String str = as1[i1];
                    if (!haos.contains(str)) {
                        zhong = false;
                        break;
                    }
                    haos = haos.replaceAll(str, "");
                }

                if (zhong) {
                    String tm[] = haos.split("");
                    String as4[];
                    int i3 = (as4 = tm).length;
                    for (int i2 = 0; i2 < i3; i2++) {
                        String str = as4[i2];
                        if (buyhm[1].contains(str))
                            continue;
                        zhong = false;
                        break;
                    }

                }
                if (zhong)
                    win = getTryMoney(lot, PlayType.T311);
            }
        } else {
            boolean h0 = buyhm[0].contains(hms[0]);
            boolean h1 = buyhm[1].contains(hms[1]);
            boolean h2 = buyhm[2].contains(hms[2]);
            boolean h3 = buyhm[3].contains(hms[3]);
            boolean h4 = buyhm[4].contains(hms[4]);
            if (PlayType.T308.equals(type)) {
                if (h2 && h3 && h4)
                    win = tryMoney;
            } else if (PlayType.T315.equals(type)) {
                if (h1 && h2 && h3 && h4)
                    win = tryMoney;
                if (h1 && h2 && h3 || h2 && h3 && h4)
                    win += getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T315))).append("1").toString());
            } else if (PlayType.T316.equals(type) || PlayType.T317.equals(type)) {
                if (h0 && h1 && h2 && h3 && h4)
                    win = tryMoney;
                if (PlayType.T317.equals(type)) {
                    if (h0 && h1 && h2)
                        win += getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T317))).append("1").toString());
                    if (h4 && h3 && h2)
                        win += getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T317))).append("1").toString());
                    if (h0 && h1)
                        win += getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T317))).append("2").toString());
                    if (h3 && h4)
                        win += getTryMoney(lot, (new StringBuilder(String.valueOf(PlayType.T317))).append("2").toString());
                }
            } else if (PlayType.T318.equals(type)) {
                if (h0)
                    win += tryMoney;
                if (h1)
                    win += tryMoney;
                if (h2)
                    win += tryMoney;
                if (h3)
                    win += tryMoney;
                if (h4)
                    win += tryMoney;
            } else if (PlayType.T319.equals(type)) {
                int i = 0;
                if (h0)
                    i++;
                if (h1)
                    i++;
                if (h2)
                    i++;
                if (h3)
                    i++;
                if (h4)
                    i++;
                win = tryMoney * (double) Comb(i, 2);
            } else if (PlayType.T301.equals(type)) {
                if (h0)
                    win += tryMoney;
                if (h1)
                    win += tryMoney;
                if (h2)
                    win += tryMoney;
                if (h3)
                    win += tryMoney;
                if (h4)
                    win += tryMoney;
            } else if (PlayType.T302.equals(type) && h3 && h4)
                win = tryMoney;
        }
        return win;
    }

    public static HashMap OpenOneSsq(String thecode, String lot, String haoma) {
        HashMap result = new HashMap();
        double win = 0.0D;
        double winPoint = 0.0D;
        String split[] = thecode.split(":");
        String type = split[0];
        String code = split[1];
        double tryMoney = getTryMoney(lot, type);
        if (LotEmun.Fc3d.name.equals(lot) || LotEmun.Pl3.name.equals(lot) || LotEmun.Pl5.name.equals(lot)) {
            String hms[] = haoma.split(",");
            String buyhm[] = code.split(",");
            boolean bz = hms[0].equals(hms[1]) && hms[0].equals(hms[2]);
            boolean zl = !hms[0].equals(hms[1]) && !hms[0].equals(hms[2]) && !hms[2].equals(hms[1]);
            if (PlayType.T114.equals(type) || PlayType.T117.equals(type) || PlayType.T120.equals(type)) {
                boolean h0 = code.contains(hms[0]);
                boolean h1 = code.contains(hms[1]);
                boolean h2 = code.contains(hms[2]);
                if (PlayType.T114.equals(type)) {
                    if (!zl && !bz && h0 && h1 && h2)
                        win = tryMoney;
                } else if (PlayType.T120.equals(type)) {
                    if (!bz && !zl) {
                        String split2[] = split[1].split(",");
                        String temp = split2[0];
                        String temp2 = split2[1];
                        String htmep = hms[0];
                        String htmep2 = hms[1];
                        if (split2[1].equals(split2[2])) {
                            temp = split2[1];
                            temp2 = split2[0];
                        } else if (split2[0].equals(split2[1]))
                            temp2 = split2[2];
                        if (hms[1].equals(hms[2])) {
                            htmep = hms[1];
                            htmep2 = hms[0];
                        } else if (hms[0].equals(hms[1]))
                            htmep2 = hms[2];
                        if (temp.equals(htmep) && temp2.equals(htmep2))
                            win = getTryMoney(lot, PlayType.T114);
                    }
                } else if (PlayType.T117.equals(type) && zl && h0 && h1 && h2)
                    win = tryMoney;
            } else if (PlayType.T116.equals(type) || PlayType.T119.equals(type)) {
                buyhm = code.split("\\$");
                if (PlayType.T116.equals(type) && !zl && !bz) {
                    String h01 = hms[0];
                    String h02 = hms[1];
                    if (hms[0].equals(hms[1]))
                        h02 = hms[2];
                    boolean h0 = buyhm[0].equals(h01);
                    boolean h1 = buyhm[0].equals(h02);
                    boolean ot0 = buyhm[1].contains(h01);
                    boolean ot1 = buyhm[1].contains(h02);
                    if (h0 && ot1 || h1 && ot0)
                        win = getTryMoney(lot, PlayType.T114);
                } else if (PlayType.T119.equals(type) && zl) {
                    String splitd[] = buyhm[0].split(",");
                    String haos = (new StringBuilder(String.valueOf(hms[0]))).append(hms[1]).append(hms[2]).toString();
                    boolean zhong = true;
                    String as[];
                    int j = (as = splitd).length;
                    for (int i = 0; i < j; i++) {
                        String str = as[i];
                        if (!haos.contains(str)) {
                            zhong = false;
                            break;
                        }
                        haos = haos.replaceAll(str, "");
                    }

                    if (zhong) {
                        String tm[] = haos.split("");
                        String as1[];
                        int i1 = (as1 = tm).length;
                        for (int k = 0; k < i1; k++) {
                            String str = as1[k];
                            if (buyhm[1].contains(str))
                                continue;
                            zhong = false;
                            break;
                        }

                    }
                    if (zhong)
                        win = getTryMoney(lot, PlayType.T117);
                }
            } else if (PlayType.T113.equals(type) || PlayType.T115.equals(type) || PlayType.T118.equals(type)) {
                Integer hz0 = Integer.valueOf(hms[0]);
                Integer hz1 = Integer.valueOf(hms[1]);
                Integer hz2 = Integer.valueOf(hms[2]);
                String r = String.valueOf(hz0.intValue() + hz1.intValue() + hz2.intValue());
                String as2[];
                int j1 = (as2 = buyhm).length;
                for (int l = 0; l < j1; l++) {
                    String str = as2[l];
                    boolean equals = r.equals(str);
                    if (!equals)
                        continue;
                    if (PlayType.T113.equals(type))
                        win = getTryMoney(lot, PlayType.T112);
                    else if (PlayType.T115.equals(type)) {
                        if (!zl && !bz)
                            win = getTryMoney(lot, PlayType.T114);
                    } else if (PlayType.T118.equals(type) && zl)
                        win = getTryMoney(lot, PlayType.T117);
                    break;
                }

            } else {
                boolean h0 = buyhm[0].contains(hms[0]);
                boolean h1 = buyhm[1].contains(hms[1]);
                boolean h2 = buyhm[2].contains(hms[2]);
                if (PlayType.T110.equals(type)) {
                    boolean h3 = buyhm[3].contains(hms[3]);
                    boolean h4 = buyhm[4].contains(hms[4]);
                    if (h0 && h1 && h2 && h3 && h4)
                        win = tryMoney;
                } else if (PlayType.T112.equals(type) && h0 && h1 && h2)
                    win = tryMoney;
            }
        } else {
            "".equals(type);
        }
        result.put("money", Double.valueOf(win));
        result.put("point", Double.valueOf(winPoint));
        return result;
    }

    private static int Comb(int n, int m) {
        int n1 = 1;
        int n2 = 1;
        int i = n;
        for (int j = 1; j <= m; ) {
            n1 *= i--;
            n2 *= j++;
        }

        return n1 / n2;
    }

    static {
        moneyset = SystemSet.money;
    }

    public static void main(String[] args) {
        MethodOpenCode open = new MethodOpenCode();
        HashMap hnssc = open.OpenOneSsc("301:5,5,7,2,6:5", "Hnssc", "5,5,7,2,6");
        System.out.println(hnssc.get("money"));
    }
}
