package com.kr.lg.module.message.sort;

import org.springframework.data.domain.Sort;

public class MessageSort {

    public static Sort idDesc() { // 식별자 내림차순
        return Sort.by("A.messageId").descending();
    }

}
