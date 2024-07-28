package com.kr.lg.module.user.model.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Data
@Slf4j
public class UBoardMapper {

    private Long id;
    private Long userId;
    private Long lawFirmId;
    private String title;
    private String content;
    private String writer;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt;
    private Integer postType;
    private Integer commentCount;
    private Integer recommendCount;
    private Integer view;
    private Integer report;
    private Integer type;
    private Integer status;
}
