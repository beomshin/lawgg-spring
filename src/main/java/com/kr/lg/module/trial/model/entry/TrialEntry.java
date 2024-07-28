package com.kr.lg.module.trial.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.module.comment.model.entry.TrialCommentEntry;
import lombok.*;

import java.sql.Timestamp;
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
    private Long postType; // 게시글 타입
    private Long liveType; // 라이브 타입
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
    private String replay;
    private Integer plaintiffCount;
    private Integer defendantCount;
    private Integer created;
    private Integer isRecommend;
    private Integer isVote;
    private List<TrialAttachEntry> files;
    private List<TrialCommentEntry> comments; // 댓글 리스트

}
