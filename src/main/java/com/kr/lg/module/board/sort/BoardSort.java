package com.kr.lg.module.board.sort;

import org.springframework.data.domain.Sort;

public class BoardSort {

    private static final String SORT_NOTIFICATION_POST_TYPE = "CASE WHEN postType = 99 THEN 2 WHEN postType = 98 THEN 1 ELSE 0 END";
    private static final String SORT_DATE_TIME = "DATE_FORMAT(bt.writeDt, '%Y-%m-%d %H:%i:%s')";
    private static final String SORT_DATE = "DATE_FORMAT(bt.writeDt, '%Y-%m-%d')";
    private static final String SORT_HOT_POST_TYPE = "CASE WHEN postType = 3 THEN 2 WHEN postType = 2 THEN 1 ELSE 0 END";

    public static Sort notificationSortWithDesc() { // 공지순 내림찬순
        return Sort.by(SORT_NOTIFICATION_POST_TYPE).descending();
    }

    public static Sort dateTimeWithDesc() { // 날짜&시간순 내림차순
        return Sort.by(SORT_DATE_TIME).descending();
    }

    public static Sort dateWithDesc() { // 날짜순 내림차순
        return Sort.by(SORT_DATE).descending();
    }

    public static Sort hotDesc() { // 인기순 내림차순
        return Sort.by(SORT_HOT_POST_TYPE).descending();
    }

}
