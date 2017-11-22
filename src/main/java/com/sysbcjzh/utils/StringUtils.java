package com.sysbcjzh.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils {
    public static final String md5String(String data) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Failed to load the MD5 MessageDigest.");
        }
        return encodeHex(md5.digest());
    }

    public static String encodeHex(byte[] bytes) {
        if ((bytes == null) || (bytes.length == 0)) return null;
        StringBuilder buf = new StringBuilder(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xFF) < 16) {
                buf.append("0");
            }
            buf.append(Long.toString(bytes[i] & 0xFF, 16));
        }
        return buf.toString();
    }

    public static boolean isNotEmptyAll(String[] strings) {
        String[] arrayOfString = strings;
        int j = strings.length;
        for (int i = 0; i < j; i++) {
            String string = arrayOfString[i];
            if (isBlank(string)) {
                return false;
            }
        }
        return true;
    }

    public static boolean CheckName(String name) {
        String regex = "^[a-zA-Z0-9一-龥]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(name);
        return match.matches();
    }
}