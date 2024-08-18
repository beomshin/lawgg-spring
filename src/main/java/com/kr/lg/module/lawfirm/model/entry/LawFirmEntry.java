package com.kr.lg.module.lawfirm.model.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.common.utils.DateUtils;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;


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

    public void additionalContent() {
        List<String> list = Arrays.asList("https://i.pinimg.com/564x/4d/b8/83/4db883a3fbdd932627770bf403fde01b.jpg",
                "https://i.pinimg.com/564x/42/4b/54/424b549bf3c7337af018b7387930bb1b.jpg",
                "https://i.pinimg.com/564x/82/bb/50/82bb5044c552c9a32b9566d531a11ffe.jpg",
                "https://i.pinimg.com/564x/53/7f/ee/537fee1351057a3b3e77b9b7457920b4.jpg");

        this.name = CommonUtils.subString(this.name, 30); // 30자 처리

        if (StringUtils.isBlank(profile)) {
            profile = list.get((int) (lawFirmId%4));
        }
    }
}
