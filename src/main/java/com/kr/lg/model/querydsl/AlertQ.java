package com.kr.lg.model.querydsl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.AlertEnum;
import com.kr.lg.common.enums.ReadEnum;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Data
@Slf4j
public class AlertQ {

    private Long id;
    private Long trialId;
    private Long boardId;
    private String title;
    private String content;
    private Integer type;
    private Integer readFlag;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt;

    @QueryProjection
    public AlertQ(
            Long id,
            Long trialId,
            Long boardId,
            String title,
            String content,
            AlertEnum type,
            ReadEnum readFlag,
            Timestamp regDt
    ) {
        this.id = id;
        this.trialId = trialId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.type = type.getCode();
        this.readFlag = readFlag.getCode();
        this.regDt = regDt;
    }
}
