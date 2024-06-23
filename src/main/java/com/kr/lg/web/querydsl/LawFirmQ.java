package com.kr.lg.web.querydsl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.enums.entity.element.LineEnum;
import com.kr.lg.enums.entity.element.Status2Enum;
import com.kr.lg.enums.entity.element.Status3Enum;
import com.kr.lg.enums.entity.element.WriterEnum;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Optional;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LawFirmQ {

    private Long lawFirmId;
    private String name;
    private String repName;
    private String profile;
    private String background;
    private Long trialTotalCount;
    private Long trialCount;
    private Long winRate;
    private Integer status;
    private Long boardId;
    private String postName;
    private String icon;
    private String title;
    private String writer;
    private String tierName;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt;
    private Long view;
    private Long recommendCnt;
    private Long commentCnt;
    private Integer writerType;
    private Integer lineType;
    private Long userCnt;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt;
    private Long userId;
    private Long lawFirmApplyId;
    private Integer level;
    private String introduction;
    private Integer applyFlag;
    private Integer isMyLawFirmFlag;
    private Integer isSignLawFirmFlag;
    private Long myLawFirmId;

    @QueryProjection
    public LawFirmQ(
            Long lawFirmId,
            String name,
            String repName,
            String profile,
            String background,
            Status2Enum status,
            Long userCnt
    ) {
        this.lawFirmId = lawFirmId;
        this.name = name;
        this.repName = repName;
        this.profile = profile;
        this.background = background;
        this.status = status.getCode();
        this.userCnt = userCnt;
    }


    @QueryProjection
    public LawFirmQ(
            Long lawFirmId,
            String name,
            String introduction,
            String repName,
            String profile,
            String background,
            Long trialTotalCount,
            Long trialCount,
            Long winRate,
            Status2Enum status
    ) {
        this.lawFirmId = lawFirmId;
        this.name = name;
        this.introduction = introduction;
        this.repName = repName;
        this.profile = profile;
        this.background = background;
        this.trialTotalCount = trialTotalCount;
        this.trialCount = trialCount;
        this.winRate = Optional.ofNullable(winRate).orElse(0L);
        this.status = status.getCode();
    }


    @QueryProjection
    public LawFirmQ(
            Long boardId,
            String postName,
            String icon,
            String title,
            String writer,
            String tierName,
            Timestamp writeDt,
            Long view,
            Long recommendCnt,
            Long commentCnt,
            WriterEnum writerType,
            LineEnum lineType
    ) {
        this.boardId = boardId;
        this.postName = postName;
        this.icon = icon;
        this.title = title;
        this.writer = writer;
        this.tierName = tierName;
        this.writeDt = writeDt;
        this.view = view;
        this.recommendCnt = recommendCnt;
        this.commentCnt = commentCnt;
        this.writerType = writerType.getCode();
        this.lineType = lineType.getCode();
    }


    @QueryProjection
    public LawFirmQ(
            Long lawFirmId,
            String name,
            String repName,
            String profile,
            String background,
            Long trialTotalCount,
            Long trialCount,
            Long winRate,
            Status2Enum status,
            Timestamp regDt,
            Long userCnt
    ) {
        this.lawFirmId = lawFirmId;
        this.name = name;
        this.repName = repName;
        this.profile = profile;
        this.background = background;
        this.trialTotalCount = trialTotalCount;
        this.trialCount = trialCount;
        this.winRate = Optional.ofNullable(winRate).orElse(0L);
        this.status = status.getCode();
        this.regDt = regDt;
        this.userCnt = userCnt;
    }

    @QueryProjection
    public LawFirmQ(
            Long lawFirmApplyId,
            String name,
            String  profile,
            Timestamp regDt,
            Status3Enum status
    ) {
        this.lawFirmApplyId = lawFirmApplyId;
        this.name = name;
        this.profile = profile;
        this.regDt = regDt;
        this.status = status.getCode();
    }

    @QueryProjection
    public LawFirmQ(
            Long userId,
            Timestamp regDt,
            String name,
            String profile,
            Status3Enum status,
            Integer level
    ) {
        this.userId = userId;
        this.regDt = regDt;
        this.name = name;
        this.profile = profile;
        this.status = status.getCode();
        this.level = level;
    }
}
