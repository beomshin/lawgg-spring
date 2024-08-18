package com.kr.lg.module.lawfirm.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.common.utils.DateUtils;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class LawFirmBoardEntry {

    private long trialId;
    private Long lawFirmId;
    private Long userId; // 유저 식별자
    private int postType;
    private String title;
    private String writer; // 작성자
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt; // 작성일
    private int view; // 조회수
    private int recommendCount; // 추천수
    private int commentCount; // 댓글수
    private Integer lineType; // 라인타입
    private int status; // 게시판 상태
    private String profile; // 프로필 url
    private String formattedDate; // 포맷 데이트
    private boolean isWithinLastHour; // 한시간 이내

    public void additionalContent() {
        this.formattedDate = DateUtils.formatDateTime(this.writeDt); // 오늘이면 시간, 이외 날짜
        this.title = CommonUtils.subString(this.title, 30); // 30자 처리
        this.writer = CommonUtils.subString(this.writer, 6); // 6자 처리
        this.isWithinLastHour = DateUtils.isWithinLastHour(this.writeDt); // 등록 1시간 이내 플래그
    }
}
