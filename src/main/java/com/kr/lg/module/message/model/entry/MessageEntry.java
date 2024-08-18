package com.kr.lg.module.message.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.common.utils.DateUtils;
import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class MessageEntry {

    private long messageId;
    private long senderId;
    private String title;
    private String content;
    private Integer readFlag;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt;
    private String receiverNickName;
    private String senderNickName;
    private boolean isWithinLastHour; // 한시간 이내

    public void additionalContent() {
        this.title = CommonUtils.subString(this.title, 30); // 30자 처리
        this.senderNickName = CommonUtils.subString(this.senderNickName, 6); // 6자 처리
        this.isWithinLastHour = DateUtils.isWithinLastHour(this.regDt); // 등록 1시간 이내 플래그
    }
}
