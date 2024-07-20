package com.kr.lg.model.querydsl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.enums.*;
import com.kr.lg.exception.LgException;
import com.kr.lg.web.dto.global.GlobalCode;
import com.querydsl.core.annotations.QueryProjection;
import com.vdurmont.emoji.EmojiParser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardQ {

    private Long boardId;
    private String postName;
    private String icon;
    private String title;
    private String writer;
    private String tierName;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt;
    private Long view;
    private Long recommendCount;
    private String content;
    private Long commentCount;

    private List<BoardQ> files;
    private Long boardCommentId;
    private Long parentId;
    private Integer order;
    private Integer depth;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt;
    private String password;
    private Long userId;
    private Integer status;
    private Integer writerType;

    private Long boardAttachId;
    private String path;
    private String oriName;
    private String newName;
    private Long size;
    private Long rootId;
    private Long created;
    private Long recommend;
    private Long anonymous;
    private Integer lineType;
    private String profile;
    private Integer postType;
    private String ip;

    @QueryProjection
    public BoardQ(
            Long boardId,
            PostEnum postType,
            String title,
            String writer,
            Timestamp writeDt,
            Long view,
            Long recommendCount,
            Long commentCount,
            WriterEnum writerType,
            LineEnum lineType,
            String profile
    ) {
        this.boardId = boardId;
        this.postType = postType.getCode();
        this.title = title;
        this.writer = writer;
        this.writeDt = writeDt;
        this.view = view;
        this.recommendCount = recommendCount;
        this.commentCount = commentCount;
        this.writerType = writerType.getCode();
        this.lineType = lineType.getCode();
        this.profile = profile;
    }

    @QueryProjection
    public BoardQ(
            Long boardId
            , String profile
            , PostEnum postType
            , String title
            , String content
            , String writer
            , WriterEnum writerType
            , Timestamp writeDt
            , Long view
            , StatusEnum status
            , Long recommendCount
            , Long commentCount
            , Long boardCommentId
            , String ip
    ) {
        this.boardId = boardId;
        this.profile = profile;
        this.postType = postType.getCode();
        this.title = title;
        this.content = EmojiParser.parseToUnicode(content);
        this.writer = writer;
        this.writeDt = writeDt;
        this.writerType = writerType.getCode();
        this.view = view;
        this.status = status.getCode();
        this.recommendCount = recommendCount;
        this.commentCount = commentCount;
        this.boardCommentId = boardCommentId;
        this.ip = ip;
    }

    @QueryProjection
    public BoardQ(
          Long boardAttachId
        , String path
        , String oriName
        , String newName
        , Long size
    ) {
        this.boardAttachId = boardAttachId;
        this.path = path;
        this.oriName = oriName;
        this.newName = newName;
        this.size = size;
    }

    @QueryProjection
    public BoardQ(
            Long boardCommentId,
            Long parentId,
            Integer order,
            DepthEnum depth,
            String writer,
            String content,
            Timestamp regDt
    ) {
        this.boardCommentId = boardCommentId;
        this.parentId = parentId;
        this.order = order;
        this.depth = depth.getCode();
        this.writer = writer;
        this.content = EmojiParser.parseToUnicode(content);
        this.regDt = regDt;
    }

    @QueryProjection
    public BoardQ(
            Long boardCommentId,
            Long parentId,
            Integer order,
            DepthEnum depth,
            String writer,
            String content,
            StatusEnum status,
            Timestamp regDt,
            Integer created,
            Integer anonymous
    ) {
        this.boardCommentId = boardCommentId;
        this.parentId = parentId;
        this.order = order;
        this.depth = depth.getCode();
        this.writer = writer;
        this.content = EmojiParser.parseToUnicode(content);
        this.status = status.getCode();
        this.regDt = regDt;
        this.created = Long.valueOf(created);
        this.anonymous = Long.valueOf(anonymous);
    }

    @QueryProjection
    public BoardQ(
            Long boardCommentId,
            Long parentId,
            Integer order,
            DepthEnum depth,
            String writer,
            String content,
            StatusEnum status,
            Timestamp regDt,
            Integer anonymous
    ) {
        this.boardCommentId = boardCommentId;
        this.parentId = parentId;
        this.order = order;
        this.depth = depth.getCode();
        this.writer = writer;
        this.content = EmojiParser.parseToUnicode(content);
        this.status = status.getCode();
        this.regDt = regDt;
        this.anonymous = Long.valueOf(anonymous);
    }

    public void neUse() throws LgException {
        if (this.status == StatusEnum.NORMAL_STATUS.getCode()) return;
        else if (this.status == StatusEnum.DELETE_STATUS.getCode()) throw new LgException(GlobalCode.DELETE_BOARD); // 삭제 게시판
        else if (this.status == StatusEnum.REPORT_STATUS.getCode()) throw new LgException(GlobalCode.REPORT_BOARD); // 정지 게시판
    }
}
