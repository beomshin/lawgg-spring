package com.kr.lg.common.utils;

import com.kr.lg.common.enums.LegacyEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumSet;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumValueConvertUtils {
  public static <T extends Enum<T> & LegacyEnum> T ofCode(Class<T> enumClass, final int code) {
    return EnumSet.allOf(enumClass).stream()
        .filter(it -> it.getCode() == code)
        .findFirst()
        .orElse(null);
  }

  public static <T extends Enum<T> & LegacyEnum> int toCode(T enumValue) {
    return Optional.of(enumValue).map(it -> it.getCode()).orElse(null);
  }
}
