package com.kr.lg.module.user.sort;

import org.springframework.data.domain.Sort;

public class UserSort {

    public static Sort writeDesc() { // 식별자 내림차순
        return Sort.by("A.writeDt").descending();
    }

    public static Sort alertIdDesc() { // 식별자 내림차순
        return Sort.by("at2.alertId").descending();
    }

    public static Sort regDtDesc() { // 식별자 내림차순
        return Sort.by("at2.regDt").descending();
    }

}
