package com.kr.lg.common.enums;

public interface LegacyEnum {

    int getCode();

    static <E extends Enum<E> & LegacyEnum> E of(E[] values, int code) {
        for (E e : values) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
