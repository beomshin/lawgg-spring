package com.kr.lg.module.trial.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.common.utils.DateUtils;
import com.kr.lg.module.comment.model.entry.TrialCommentEntry;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class TrialEntry {

    private Long trialId; // 트라이얼 식별자
    private String title; // 트라이얼 제목
    private String content; // 트라이얼 내용
    private String writer; // 작성자
    private String thumbnail; // 썸네일 url
    private Long view; // 조회수
    private Long recommendCount; // 추천수
    private Integer commentCount; // 댓글 수
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt; // 작성일
    private Integer postType; // 게시글 타입
    private Integer liveType; // 라이브 타입
    private String profile; // 프로필
    private Long userId;
    private String plaintiff;
    private String defendant;
    private String judgeName;
    private String playVideo;
    private String plaintiffOpinion;
    private String defendantOpinion;
    private String url;
    private Integer precedent;
    private Integer endingType;
    private Integer status;
    private Long trialCommentId;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp  liveDt;
    private String formattedDate; // 포맷 데이트
    private boolean isWithinLastHour; // 한시간 이내
    private String replay;
    private int plaintiffCount;
    private int defendantCount;
    private Integer created;
    private Integer isRecommend;
    private Integer isVote;
    private boolean isCanDelete; // 삭제 가능 플래그 7일 지남여부
    private List<TrialAttachEntry> files;
    private List<TrialCommentEntry> comments; // 댓글 리스트

    public void additionalContent() {
        this.formattedDate = DateUtils.formatDateTime(this.writeDt); // 오늘이면 시간, 이외 날짜
        this.title = CommonUtils.subString(this.title, 30); // 30자 처리
        this.writer = CommonUtils.subString(this.writer, 6); // 6자 처리
        this.isWithinLastHour = DateUtils.isWithinLastHour(this.writeDt); // 등록 1시간 이내 플래그
    }

    public void additionalContent2() {
        this.plaintiff = CommonUtils.subString(this.plaintiff, 6); // 6자 처리
        this.defendant = CommonUtils.subString(this.defendant, 6); // 6자 처리
        this.judgeName = CommonUtils.subString(this.judgeName, 6); // 6자 처리
        this.isCanDelete = isSevenDaysPassed(this.writeDt);
    }

    public boolean isSevenDaysPassed(Timestamp timestamp) {
        Instant now = Instant.now();// 현재 시간
        Instant givenDate = timestamp.toInstant();// 입력된 타임스탬프를 Instant로 변환
        long daysBetween = ChronoUnit.DAYS.between(givenDate, now);// 입력된 날짜와 현재 날짜 사이의 차이를 일 단위로 계산
        return daysBetween >= 7; // 7일이 지났는지 확인
    }

}
