package com.kr.lg.module.board.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.common.enums.entity.status.BoardStatus;
import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.common.utils.DateUtils;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.comment.model.entry.BoardCommentEntry;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class BoardEntry {

    private long boardId; // board 식별자
    private long boardCommentId; // root comment 식별자
    private int postType; // 게시글 타입
    private String title; // 제목
    private String writer; // 작성자
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt; // 작성일
    private String formattedDate; // 포맷 데이트
    private boolean isWithinLastHour; // 한시간 이내
    private int view; // 조회수
    private int recommendCount; // 추천수
    private int commentCount; // 댓글수
    private int writerType; // 작성자 타입
    private int lineType; // 라인타입
    private String profile; // 프로필 url
    private int status; // 게시판 상태
    private String content; // 게시판 내용
    private Integer created; // 작성자 플래그 ( 0: 미작성자, 1:작성자)
    private List<BoardAttachEntry> files; // 게시판 파일 리스트
    private List<BoardCommentEntry> comments; // 댓글 리스트

    /**
     * 게시판 정상 게시 상태 확인 메소드
     * @return
     * @throws BoardException
     */
    public boolean isNormalStatus() throws BoardException {
        if (this.status == BoardStatus.NORMAL_STATUS.getCode()) return true;
        else if (this.status == BoardStatus.DELETE_STATUS.getCode()) throw new BoardException(BoardResultCode.DELETE_BOARD); // 삭제 게시판
        else if (this.status == BoardStatus.REPORT_STATUS.getCode()) throw new BoardException(BoardResultCode.REPORT_BOARD); // 정지 게시판
        else {
            return false; // 미존재 상태 처리
        }
    }

    public void additionalContent() {
        this.formattedDate = DateUtils.formatDateTime(this.writeDt); // 오늘이면 시간, 이외 날짜
        this.title = CommonUtils.subString(this.title, 30); // 30자 처리
        this.writer = CommonUtils.subString(this.writer, 6); // 6자 처리
        this.isWithinLastHour = DateUtils.isWithinLastHour(this.writeDt); // 등록 1시간 이내 플래그
    }

}
