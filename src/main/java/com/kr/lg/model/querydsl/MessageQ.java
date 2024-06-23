package com.kr.lg.model.querydsl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.common.enums.entity.element.DelEnum;
import com.kr.lg.common.enums.entity.element.ReadEnum;
import com.kr.lg.model.common.global.GlobalCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageQ {

    private Long messageId;
    private UserTb receiver;
    private UserTb sender;
    private String title;
    private String content;
    private Integer readFlag;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt;

    private DelEnum delFlag;
    private DelEnum receiverDelFlag;
    private DelEnum senderDelFlag;
    private String senderLoginId;
    private String senderNickName;

    @QueryProjection
    public MessageQ(
            Long messageId,
            String senderLoginId,
            String senderNickName,
            String title,
            String content,
            ReadEnum readFlag,
            Timestamp regDt
    ) {
        this.messageId = messageId;
        this.senderLoginId = senderLoginId;
        this.senderNickName = senderNickName;
        this.title = title;
        this.content = content;
        this.readFlag = readFlag.getCode();
        this.regDt = regDt;
    }

    @QueryProjection
    public MessageQ(
            Long messageId,
            UserTb receiver,
            UserTb sender,
            String title,
            String content,
            ReadEnum readFlag,
            Timestamp regDt
    ) {
        this.messageId = messageId;
        this.receiver = receiver;
        this.sender = sender;
        this.title = title;
        this.content = content;
        this.readFlag = readFlag.getCode();
        this.regDt = regDt;
    }


    @QueryProjection
    public MessageQ(
            Long messageId,
            UserTb receiver,
            UserTb sender,
            String title,
            String content,
            ReadEnum readFlag,
            Timestamp regDt,
            DelEnum receiverDelFlag,
            DelEnum senderDelFlag
    ) {
        this.messageId = messageId;
        this.receiver = receiver;
        this.sender= sender;
        this.title = title;
        this.content = content;
        this.readFlag = readFlag.getCode();
        this.regDt = regDt;
        this.receiverDelFlag = receiverDelFlag;
        this.senderDelFlag = senderDelFlag;
    }

    public void isDelete() throws LgException {
        if (this.senderDelFlag.equals(DelEnum.DELETE_STATUS)) throw new LgException(GlobalCode.DELETE_MESSAGE);
    }

    public boolean isRead() {
        if (this.readFlag.equals(ReadEnum.READ_FLAG.getCode())) return true;
        return false;
    }
}
