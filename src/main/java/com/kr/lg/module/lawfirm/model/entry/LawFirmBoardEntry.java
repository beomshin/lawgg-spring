package com.kr.lg.module.lawfirm.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.common.utils.CommonUtils;
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

    private long id;
    private Long lawFirmId;
    private Long userId; // 유저 식별자
    private int type;
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
}
