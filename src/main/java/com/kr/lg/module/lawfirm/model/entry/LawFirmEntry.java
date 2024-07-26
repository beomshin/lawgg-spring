package com.kr.lg.module.lawfirm.model.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class LawFirmEntry {

    private long lawFirmId;
    private String name;
    private String profile;
    private String background;
    private int status;
    private String nickName;
    private Long userCnt;
    private Long trialTotalCount;
    private Long trialCount;
    private Long winRate;
    private String introduction;
    private Integer applyFlag;
    private Integer isMyLawFirmFlag;
    private Integer isSignLawFirmFlag;
    private Long myLawFirmId;

}
