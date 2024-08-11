package com.kr.lg.common.utils;

public class CommonUtils {

    public static String subString(String s, int size) {
        if (s.length() < size) return s;
        return s.substring(0, size) + "...";
    }
}
