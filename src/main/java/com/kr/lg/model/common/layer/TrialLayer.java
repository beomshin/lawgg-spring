package com.kr.lg.model.common.layer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.TrialSubjectEnum;
import com.kr.lg.enums.TrialTopicEnum;
import com.kr.lg.enums.DepthEnum;
import com.kr.lg.enums.PrecedentEnum;
import com.kr.lg.module.board.model.req.ReportTrialRequest;
import com.kr.lg.module.trial.model.req.*;
import com.kr.lg.web.dto.global.GlobalFile;
import com.kr.lg.model.net.request.trial.comment.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Slf4j
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrialLayer {

    private Long id;
    private String title;
    private String plaintiff;
    private String defendant;
    private MultipartFile playVideo;
    private String subheading;
    private String plaintiffOpinion;
    private String defendantOpinion;
    private String content;
    private List<GlobalFile> files;
    private UserTb userTb;
    private String password;
    private Integer page;
    private Integer pageNum;
    private TrialTopicEnum topic;
    private TrialSubjectEnum subject;
    private String keyword;
    private String ip;
    private List<MultipartFile> addFiles;
    private List<Long> deleteFiles;
    private Long parentId;
    private String emoticon;
    private DepthEnum depth;
    private PrecedentEnum precedent;
    private String loginId;
    private String writer;
    private Integer order;
    private LawFirmTb lawFirmTb;
    private String url;
    private MultipartFile replay;
    private Long trialCommentId;

    public TrialLayer(Long trialId) {
        this.id = trialId;
    }

    public TrialLayer(Long id, UserTb userTb) {
        this.id = id;
        this.userTb = userTb;
    }

    public TrialLayer(Long trialId, FindAPCTrialRequest requestDto, DepthEnum depth) {
        this.id = trialId;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.depth = depth;
    }

    public TrialLayer(Long trialId, FindACCTRequest requestDto, DepthEnum depth) {
        this.id = trialId;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.depth = depth;
    }

    public TrialLayer(Long id, UserTb userTb, FindUPCTRequest requestDto, DepthEnum depth) {
        this.id = id;
        this.userTb = userTb;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.depth = depth;
    }

    public TrialLayer(Long id, UserTb userTb, FindUCCTRequest requestDto, DepthEnum depth) {
        this.id = id;
        this.userTb = userTb;
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.depth = depth;
    }

    public TrialLayer(EnrollTrialWithLoginRequest request, UserTb userTb) {
        this.title = request.getTitle();
        this.plaintiff = request.getPlaintiff();
        this.defendant = request.getDefendant();
        this.subheading = request.getSubheading();
        this.plaintiffOpinion = request.getPlaintiffOpinion();
        this.defendantOpinion = request.getDefendantOpinion();
        this.content = request.getContent();
        this.files = request.getFiles();
        this.userTb = userTb;
        if (request.getIsLawFirm() != null && request.getIsLawFirm() == 1) {
            this.lawFirmTb = userTb.getLawFirmId();
        }
    }


    public TrialLayer(DeleteTrialRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.password = requestDto.getPassword();
        this.userTb = userTb;
    }

    public TrialLayer(FindTrialsRequest requestDto) {
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.topic = TrialTopicEnum.of(requestDto.getTopic());
        this.subject = TrialSubjectEnum.of(requestDto.getSubject());
        this.keyword = requestDto.getKeyword();
    }


    public TrialLayer(Long id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    public TrialLayer(Long trialId, String ip, UserTb userTb) {
        this.id = trialId;
        this.ip = ip;
        this.userTb = userTb;
    }


    public TrialLayer(RecommendTrialRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.userTb = userTb;
    }

    public TrialLayer(DeleteRecommendTrialRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.userTb = userTb;
    }

    public TrialLayer(ReportTrialRequest requestDto, String ip) {
        this.id = requestDto.getId();
        this.ip = ip;
        this.content = requestDto.getContent();
    }

    public TrialLayer(DeleteCTRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.password = requestDto.getPassword();
        this.userTb = userTb;
    }

    public TrialLayer(EnrollCTRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.loginId = userTb.getLoginId();
        this.parentId = requestDto.getParentId();
        this.content = requestDto.getContent();
        this.emoticon = requestDto.getEmoticon();
        this.depth = requestDto.getDepth();
        this.writer = userTb.getNickName();
        this.userTb = userTb;
        this.trialCommentId = requestDto.getTrialCommentId();
    }

    public TrialLayer(ReportCTRequest requestDto, String ip, UserTb userTb) {
        this.id = requestDto.getId();
        this.ip = ip;
        this.userTb = userTb;
    }

    public TrialLayer(UpdateCTRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.password = requestDto.getPassword();
        this.userTb = userTb;
    }

    public TrialLayer(VoteTrialRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.precedent = PrecedentEnum.of(requestDto.getPrecedent());
        this.userTb = userTb;
    }

    public TrialLayer(FindLawFirmTrialsRequest requestDto) {
        this.id = requestDto.getId();
        this.page = requestDto.getPage();
        this.pageNum = requestDto.getPageNum();
        this.topic = TrialTopicEnum.of(requestDto.getTopic());
        this.subject = TrialSubjectEnum.of(requestDto.getSubject());
        this.keyword = requestDto.getKeyword();
    }

    public TrialLayer(UpdateLiveTrialRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.url = requestDto.getUrl();
        this.userTb = userTb;
    }

    public TrialLayer(UpdateEndTrialRequest requestDto, UserTb userTb) {
        this.id = requestDto.getId();
        this.userTb = userTb;
        this.precedent = PrecedentEnum.of(requestDto.getPrecedent());
    }

    public TrialLayer(EnrollVideoWithLoginRequest request, UserTb userTb) {
        this.id = request.getId();
        this.playVideo = request.getPlayVideo();
        this.replay = request.getReplay();
        this.userTb = userTb;
    }

    public String getAlertTitle() {
        return new StringBuffer().append(this.userTb.getNickName()).append(" 유저가 댓글을 달았습니다.").toString();
    }


}

