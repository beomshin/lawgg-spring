package com.kr.lg.common.utils;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final long ONE_HOUR_IN_MILLIS = 60 * 60 * 1000;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String formatDateTime(Timestamp timestamp) {
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        if (dateTime.toLocalDate().isEqual(now.toLocalDate())) {
            return dateTime.format(TIME_FORMATTER);
        } else {
            return dateTime.format(DATE_FORMATTER);
        }
    }

    public static boolean isWithinLastHour(Timestamp timestamp) {
        LocalDateTime dateTime = timestamp.toLocalDateTime(); // Timestamp를 LocalDateTime으로 변환
        LocalDateTime now = LocalDateTime.now(); // 현재 시간
        Duration duration = Duration.between(dateTime, now); // 주어진 시간과 현재 시간 사이의 차이 계산
        return duration.toMillis() <= ONE_HOUR_IN_MILLIS; // 차이가 1시간 이하인지 확인
    }
}
