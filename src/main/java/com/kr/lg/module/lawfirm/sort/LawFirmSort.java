package com.kr.lg.module.lawfirm.sort;

import org.springframework.data.domain.Sort;

public class LawFirmSort {

    private static final String SORT_HOT_POST_TYPE = "CASE WHEN postType = 3 THEN 2 WHEN postType = 2 THEN 1 ELSE 0 END";

    public static Sort idDesc() { // 식별자 내림차순
        return Sort.by("lft.lawFirmId").descending();
    }

    public static Sort writeDtDesc() { // 식별자 내림차순
        return Sort.by("writeDt").descending();
    }

    public static Sort hotDesc() { // 식별자 내림차순
        return Sort.by(SORT_HOT_POST_TYPE).descending();
    }

    public static Sort viewDesc() { // 식별자 내림차순
        return Sort.by("tt.view").descending();
    }

    public static Sort recommendDesc() {
        return Sort.by("recommendCount").descending();
    }
}
