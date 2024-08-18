package com.kr.lg.module.user.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.common.utils.DateUtils;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class UserAlertEntry {

    private long alertId; // 유저 알림 아이디
    private long trialId; // 트라이얼 아이디
    private long boardId; // 게시판 아이디
    private String title; // 제목
    private String content; // 내용
    private Integer type; // 타입
    private Integer readFlag; // 읽음여부
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일
    private boolean isWithinLastHour; // 한시간 이내

    public void additionalContent() {
        this.title = CommonUtils.subString(this.title, 30); // 30자 처리
        this.content = CommonUtils.subString(this.content, 30); // 30자 처리
        this.isWithinLastHour = DateUtils.isWithinLastHour(this.regDt); // 등록 1시간 이내 플래그
    }
}
