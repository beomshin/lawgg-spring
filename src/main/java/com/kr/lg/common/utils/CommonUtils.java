package com.kr.lg.common.utils;

public class CommonUtils {

    public static String subString(String s, int size) {
        if (s.length() < size) return s;
        return s.substring(0, size) + "...";
    }

    public static String getLineTag(int type) {
        switch (type) {
            case 0: return "탑";
            case 1: return "정글";
            case 2: return "미드";
            case 3: return "원딜";
            case 4: return "서폿";
        }
        return "기타";
    }

}
