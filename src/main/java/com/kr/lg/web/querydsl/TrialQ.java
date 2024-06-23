package com.kr.lg.web.querydsl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.enums.entity.element.*;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.global.GlobalCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrialQ {

    private Long trialId;
    private String thumbnail;
    private Integer view;
    private Long recommendCount;
    private String title;
    private String content;
    private Long commentCount;
    private String profile;
    private String writer;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt;
    private String plaintiff;
    private String defendant;
    private String judgeName;
    private String playVideo;
    private String plaintiffOpinion;
    private String defendantOpinion;
    private String url;
    private Integer precedent;
    private Integer liveType;
    private Integer endingType;
    private Long isRecommend;
    private Integer isVote;
    private Long created;
    private Long anonymous;
    private Long trialCommentId;
    private Long parentId;
    private Integer order;
    private Integer depth;
    private Integer status;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt;
    private Integer postType;
    private String ip;

    private Long plaintiffCount;
    private Long defendantCount;

    private Long trialAttachId;
    private String path;
    private String oriName;
    private String newName;
    private Long size;
    private List<TrialQ> files;
    private Long judgeId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp liveDt;

    private String replay;

    @QueryProjection
    public TrialQ(
            Long trialId,
            String title,
            String content,
            String writer,
            String profile,
            String thumbnail,
            Integer view,
            Long recommendCount,
            Long commentCount,
            Timestamp writeDt,
            PostEnum postType,
            LiveEnum liveType
    ) {
        this.trialId = trialId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.profile = profile;
        this.thumbnail = thumbnail;
        this.view = view;
        this.recommendCount = recommendCount;
        this.commentCount = commentCount;
        this.writeDt = writeDt;
        this.postType = postType.getCode();
        this.liveType = liveType.getCode();
    }


    @QueryProjection
    public TrialQ(
        Long trialId,
        Long judgeId,
        String title,
        String writer,
        Timestamp writeDt,
        Integer view,
        Long recommendCount,
        Long commentCount,
        String plaintiff,
        String defendant,
        String judgeName,
        String content,
        String thumbnail,
        String playVideo,
        String plaintiffOpinion,
        String defendantOpinion,
        String url,
        PrecedentEnum precedent,
        LiveEnum liveType,
        EndingEnum endingType,
        StatusEnum status,
        Long trialCommentId,
        Timestamp liveDt,
        String replay,
        PostEnum postType
    ) {
        this.trialId = trialId;
        this.judgeId = judgeId;
        this.title = title;
        this.writer = writer;
        this.writeDt = writeDt;
        this.view = view;
        this.recommendCount =recommendCount;
        this.commentCount = commentCount;
        this.plaintiff = plaintiff;
        this.defendant = defendant;
        this.judgeName = judgeName;
        this.content = content;
        this.thumbnail = thumbnail;
        this.playVideo = playVideo;
        this.plaintiffOpinion = plaintiffOpinion;
        this.defendantOpinion = defendantOpinion;
        this.url = url;
        this.precedent = precedent.getCode();
        this.liveType = liveType.getCode();
        this.endingType = endingType.getCode();
        this.status = status.getCode();
        this.trialCommentId = trialCommentId;
        this.liveDt = liveDt;
        this.replay = replay;
        this.postType = postType.getCode();
    }

    @QueryProjection
    public TrialQ(
            Long trialCommentId,
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
        this.trialCommentId = trialCommentId;
        this.parentId = parentId;
        this.order = order;
        this.depth = depth.getCode();
        this.writer = writer;
        this.content = content;
        this.status = status.getCode();
        this.regDt = regDt;
        this.created = Long.valueOf(created);
        this.anonymous = Long.valueOf(anonymous);
    }

    @QueryProjection
    public TrialQ(
            Long trialCommentId,
            Long parentId,
            Integer order,
            DepthEnum depth,
            String writer,
            String content,
            StatusEnum status,
            Timestamp regDt,
            Integer anonymous
    ) {
        this.trialCommentId = trialCommentId;
        this.parentId = parentId;
        this.order = order;
        this.depth = depth.getCode();
        this.writer = writer;
        this.content = content;
        this.status = status.getCode();
        this.regDt = regDt;
        this.anonymous = Long.valueOf(anonymous);
    }

    @QueryProjection
    public TrialQ(
            Long trialAttachId,
            String path,
            String oriName,
            String newName,
            Long size
    ) {
        this.trialAttachId = trialAttachId;
        this.path = path;
        this.oriName = oriName;
        this.newName = newName;
        this.size = size;
    }

    public void neUse() throws LgException {
        if (this.status == StatusEnum.NORMAL_STATUS.getCode()) return;
        else if (this.status == StatusEnum.DELETE_STATUS.getCode()) throw new LgException(GlobalCode.DELETE_TRIAL); // 삭제 게시판
        else if (this.status == StatusEnum.REPORT_STATUS.getCode()) throw new LgException(GlobalCode.REPORT_TRIAL); // 정지 게시판
    }
}
