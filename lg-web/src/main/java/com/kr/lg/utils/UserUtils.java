package com.kr.lg.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserUtils {

    public static boolean isAbove20(String dateOfBirth) {
        try {
            // 주어진 yyyyMMdd 형식의 문자열을 Date 객체로 파싱
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date birthDate = sdf.parse(dateOfBirth);

            // 현재 날짜 가져오기
            Calendar now = Calendar.getInstance();
            Date currentDate = now.getTime();

            // 나이 계산
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);
            int age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

            // 만약 생일이 아직 오지 않았다면 한 살을 뺌
            if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }

            // 나이가 20세 이상인지 확인
            return age >= 20;
        } catch (ParseException e) {
            // 날짜 파싱 에러 처리
            log.error("{}", e.getMessage());
            return false;
        }
    }
}
