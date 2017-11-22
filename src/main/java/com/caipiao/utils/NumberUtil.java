//    NumberUtil.java

package com.caipiao.utils;

import java.math.BigDecimal;
import java.util.Random;

public class NumberUtil {

    private static final int DEFAULT_SCALE = 4;

    public NumberUtil() {
    }

    public static double getRandom(double range, int format) {
        Random random = new Random();
        double result = 0.0D;
        if (random.nextBoolean())
            result = random.nextDouble() * range;
        else
            result = -random.nextDouble() * range;
        return formatDouble(Double.valueOf(Math.abs(result)), format).doubleValue();
    }

    public static double getRandom(double range) {
        return getRandom(range, 4);
    }

    public static Double formatDouble(Double num, int format) {
        if (num != null)
            return Double.valueOf((new BigDecimal(String.format((new StringBuilder("%.")).append(format + 1).append("f").toString(), new Object[]{
                    num
            }))).setScale(format, 1).doubleValue());
        else
            return null;
    }

    public static double add(double v1, double v2) {
        return add(v1, v2, 4);
    }

    public static double add(double v1, double v2, int format) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return formatDouble(Double.valueOf(b1.add(b2).doubleValue()), format).doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return formatDouble(Double.valueOf(b1.subtract(b2).doubleValue()), 4).doubleValue();
    }

    public static double mul(double v1, double v2, int format) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return formatDouble(Double.valueOf(b1.multiply(b2).doubleValue()), format).doubleValue();
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return formatDouble(Double.valueOf(b1.multiply(b2).doubleValue()), 2).doubleValue();
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, 4);
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            System.err.println("除法精度必须大于0!");
            return 0.0D;
        } else {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            double doubleValue = b1.divide(b2, 7, scale).doubleValue();
            return formatDouble(Double.valueOf(doubleValue), scale).doubleValue();
        }
    }

    public static void main(String args[]) {
        System.out.println(formatDouble(Double.valueOf(0.81930000000000003D), 4));
    }
}
