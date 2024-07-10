package com.kr.lg.model.querydsl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.enums.*;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserQ {

    private Long id;
    private String loginId;
    private String profile;
    private String nickName;
    private Integer delFlag;
    private Integer status;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
    private Timestamp regDt;

    private String lawFirmName;
    private String tierName;
    private Long boardCount;
    private Long trialCount;
    private Long commentCount;
    private String email;
    private Integer personalPeriod;
    private String phone;
    private Integer authFlag;
    private String name;
    private String tireLevel;
    private Integer snsType;
    private Integer judgeFlag;
    private String gender;
    private String birth;
    private Long point;

    @QueryProjection
    public UserQ(
            String loginId
            , String profile
            , String nickName
            , DelEnum delFlag
            , Status3Enum status
            , Timestamp regDt
            , SnsEnum snsEnum
    ) {
        this.loginId = loginId;
        this.profile = profile;
        this.nickName = nickName;
        this.delFlag = delFlag.getCode();
        this.status = status.getCode();
        this.regDt = regDt;
        this.snsType = snsEnum.getCode();
    }

    @QueryProjection
    public UserQ(
            Long id,
            String loginId,
            String nickName,
            String name,
            String lawFirmName,
            String tierName,
            String tireLevel,
            String profile,
            String email,
            Long boardCount,
            Long commentCount,
            Long trialCount,
            Integer personalPeriod,
            Timestamp regDt,
            String phone,
            AuthEnum authFlag,
            SnsEnum snsType,
            JudgeEnum judgeFlag,
            String gender,
            String birth,
            Long point
    ) {
        this.id = id;
        this.loginId = loginId;
        this.nickName = nickName;
        this.name = name;
        this.lawFirmName = lawFirmName;
        this.tierName = tierName;
        this.profile = profile;
        this.email = email;
        this.boardCount = boardCount;
        this.commentCount = commentCount;
        this.trialCount = trialCount;
        this.personalPeriod = personalPeriod;
        this.regDt = regDt;
        this.phone = phone;
        this.authFlag = authFlag.getCode();
        this.tireLevel = tireLevel;
        this.snsType = snsType.getCode();
        this.judgeFlag = judgeFlag.getCode();
        this.gender = gender != null ? gender.toLowerCase().equals("male") ? "남자" : "여자" : null;
        this.birth = birth;
        this.point = point;
    }

}
