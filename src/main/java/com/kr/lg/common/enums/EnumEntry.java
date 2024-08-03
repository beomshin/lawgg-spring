package com.kr.lg.common.enums;

public interface EnumEntry {

    int getCode(); // 코드 getter

    static <E extends Enum<E> & EnumEntry> E of(E[] values, int code) { // value 조회
        for (E e : values) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

}
