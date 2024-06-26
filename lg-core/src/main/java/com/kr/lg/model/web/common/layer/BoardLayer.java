package com.kr.lg.model.web.common.layer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.entities.LawFirmTb;
import com.kr.lg.entities.UserTb;
import com.kr.lg.enums.common.element.BoardTopicEnum;
import com.kr.lg.enums.common.element.BoardTypeEnum;
import com.kr.lg.enums.entity.element.DepthEnum;
import com.kr.lg.enums.entity.element.LineEnum;
import com.kr.lg.enums.entity.element.PostEnum;
import com.kr.lg.enums.entity.element.WriterEnum;
import com.kr.lg.model.web.common.global.GlobalFile;
import com.kr.lg.model.web.common.root.RootRequest;
import com.kr.lg.model.web.net.request.board.base.*;
import com.kr.lg.model.web.net.request.board.comment.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Slf4j
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardLayer {

    private Long id;
    private String ip;
    private String loginId;
    private String password;
    private String title;
    private String content;
    private String writer;
    private LineEnum lineType;
    private List<GlobalFile> files;
    private UserTb userTb;
    private LawFirmTb lawFirmTb;
    private Integer page;
    private LineEnum type;
    private Integer pageNum;
    private BoardTypeEnum subject;
    private BoardTopicEnum topic;
    private String keyword;
    private Long parentId;
    private DepthEnum depth;
    private WriterEnum writerType;
    private List<Long> deleteFiles;
    private Integer order;
    private String emoticon;
    private PostEnum postType;
    private Integer isLawFirm;
    private Long boardCommentId;

    public BoardLayer(RootRequest request) {
        if (request instanceof LoginABRequest) {
            this.id = ((LoginABRequest) request).getId();
            this.password = ((LoginABRequest) request).getPassword() ;
        }
    }

    public BoardLayer(Long id) {
        this.id = id;
    }

    public BoardLayer(String password, Long id) {
        this.id = id;
        this.password = password;
    }

    public BoardLayer(Long id, FindAPCBRequest requestDto, DepthEnum depth) {
        this.id = id;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.depth = depth;
    }

    public BoardLayer(Long id, FindACCBRequest requestDto, DepthEnum depth) {
        this.id = id;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.depth = depth;
    }

    public BoardLayer(Long id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    public BoardLayer(ReportBRequest requestDto, String ip) {
        this.id = requestDto.getId();
        this.content = requestDto.getContent();
        this.ip = ip;
    }

    public BoardLayer(ReportCBRequest requestDto, String ip) {
        this.id = requestDto.getId();
        this.ip = ip;
        this.content = requestDto.getContent();
    }

    public BoardLayer(Long id, UserTb userTb) {
        this.id = id;
        this.userTb = userTb;
    }

    public BoardLayer(Long id, UserTb userTb, FindUPCBRequest requestDto, DepthEnum depth) {
        this.id = id;
        this.userTb = userTb;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.depth = depth;
    }

    public BoardLayer(Long id, UserTb userTb, FindUCCBRequest requestDto, DepthEnum depth) {
        this.id = id;
        this.userTb = userTb;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.depth = depth;
    }

    public BoardLayer(Long id, String ip, UserTb userTb) {
        this.id = id;
        this.ip = ip;
        this.userTb = userTb;
    }

    public BoardLayer(DeleteUBRequest boardRequestDto, UserTb userTb) {
        this.id = boardRequestDto.getId();
        this.password = boardRequestDto.getPassword();
        this.userTb = userTb;
    }

    public BoardLayer(DeleteABRequest boardRequestDto) {
        this.id = boardRequestDto.getId();
        this.password = boardRequestDto.getPassword();
    }

    public BoardLayer(DeleteACBRequest request)  {
        this.id = request.getId();
        this.password = request.getPassword();
    }

    public BoardLayer(DeleteUCBRequest request, UserTb userTb)  {
        this.id = request.getId();
        this.password = request.getPassword();
        this.userTb = userTb;
    }

    public BoardLayer(UpdateACBRequest request) {
        this.id = request.getId();
        this.password = request.getPassword();
        this.content = request.getContent();
    }

    public BoardLayer(UpdateUCBRequest request, UserTb userTb) {
        this.id = request.getId();
        this.password = request.getPassword();
        this.content = request.getContent();
        this.userTb = userTb;
    }

    public BoardLayer(UpdateABRequest request) {
        this.id = request.getId();
        this.password = request.getPassword();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.files = request.getAddFiles();
    }

    public BoardLayer(UpdateUBRequest request, UserTb userTb) {
        this.id = request.getId();
        this.password = request.getPassword();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.files = request.getAddFiles();
        this.userTb = userTb;
    }

    public BoardLayer(EnrollABRequest request, String ip) {
        this.loginId = request.getId();
        this.password = request.getPassword();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.writer = request.getId();
        this.lineType = LineEnum.of(request.getLineType());
        this.files = request.getFiles();
        this.writerType = WriterEnum.ANONYMOUS_TYPE;
        this.postType = findPostType();
        this.ip = ip;
    }

    public BoardLayer(EnrollUBRequest request, UserTb userTb, String ip) {
        this.loginId = userTb.getLoginId();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.writer = userTb.getNickName();
        this.lineType = LineEnum.of(request.getLineType());
        this.files = request.getFiles();
        this.userTb = userTb;
        this.writerType = WriterEnum.MEMBER_TYPE;
        this.postType = findPostType();
        this.ip = ip;
        if (request.getIsLawFirm() != null && request.getIsLawFirm() == 1) {
            this.lawFirmTb = userTb.getLawFirmId();
        }
    }

    public BoardLayer(EnrollLFBRequest request, UserTb userTb, String ip) {
        this.id = request.getId();
        this.loginId = userTb.getLoginId();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.writer = userTb.getNickName();
        this.lineType = LineEnum.of(request.getLineType());
        this.files = request.getFiles();
        this.userTb = userTb;
        this.lawFirmTb = userTb.getLawFirmId();
        this.writerType = WriterEnum.LAW_FIRM_TYPE;
        this.postType = findPostType();
        this.ip = ip;
    }

    public BoardLayer(FindABLRequest requestDto) {
        this.page = requestDto.getPage();
        this.type = LineEnum.of(requestDto.getType());
        this.pageNum = requestDto.getPageNum();
        this.topic = BoardTopicEnum.of(requestDto.getTopic());
        this.subject = BoardTypeEnum.of(requestDto.getSubject());
        this.keyword = requestDto.getKeyword();
    }

    public BoardLayer(FindUBLRequest requestDto, UserTb userTb) {
        this.page = requestDto.getPage();
        this.type = LineEnum.of(requestDto.getType());
        this.pageNum = requestDto.getPageNum();
        this.topic = BoardTopicEnum.of(requestDto.getTopic());
        this.subject = BoardTypeEnum.of(requestDto.getSubject());
        this.keyword = requestDto.getKeyword();
        this.userTb = userTb;
    }

    public BoardLayer(EnrollUCBRequest request, UserTb userTb, String ip) {
        this.id = request.getId();
        this.parentId = request.getParentId();
        this.loginId = userTb.getLoginId();
        this.writer = userTb.getNickName();
        this.content = request.getContent();
        this.depth = request.getDepth();
        this.userTb = userTb;
        this.emoticon = request.getEmoticon();
        this.ip = ip;
        this.boardCommentId = request.getBoardCommentId();
    }

    public BoardLayer(EnrollACBRequest request, String ip) {
        this.id = request.getId();
        this.parentId = request.getParentId();
        this.loginId = request.getLoginId();
        this.password = request.getPassword();
        this.writer = request.getLoginId();
        this.content = request.getContent();
        this.depth = request.getDepth();
        this.emoticon = request.getEmoticon();
        this.ip = ip;
        this.boardCommentId = request.getBoardCommentId();
    }

    public BoardLayer(LoginUBRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.password = requestDto.getPassword();
        this.userTb = userTb;
    }


    private PostEnum findPostType() { // postType 처리 (2023-04-06 기준 일반, 이미지 타입)
        if (this.files != null && this.files.size() > 0 ) {
            return PostEnum.IMAGE_TYPE;
        }
        return PostEnum.NORMAL_TYPE;
    }

    public BoardLayer(FindLFBLRequest requestDto) {
        this.id = requestDto.getId();
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.topic = BoardTopicEnum.of(requestDto.getTopic());
        this.subject = BoardTypeEnum.of(requestDto.getSubject());
        this.keyword = requestDto.getKeyword();
    }


    public String getAlertTitle() {
        if (this.userTb == null) return "비회원 유저가 댓글을 달았습니다.";
        return new StringBuffer().append(this.userTb.getNickName()).append(" 유저가 댓글을 달았습니다.").toString();
    }
}
