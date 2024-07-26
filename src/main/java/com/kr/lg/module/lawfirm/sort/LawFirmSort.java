package com.kr.lg.module.lawfirm.sort;

import org.springframework.data.domain.Sort;

public class LawFirmSort {

    public static Sort idDesc() { // 식별자 내림차순
        return Sort.by("lft.lawFirmId").descending();
    }

}
