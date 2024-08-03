package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.status.MessageDeleteStatusConverter;
import com.kr.lg.common.enums.convert.flag.ReadFlagConverter;
import com.kr.lg.common.enums.convert.flag.ReplyFlagConverter;
import com.kr.lg.common.enums.entity.status.MessageDeleteStatus;
import com.kr.lg.common.enums.entity.flag.ReadFlag;
import com.kr.lg.common.enums.entity.flag.ReplyFlag;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "MessageTb")
@Table(
        name = "MessageTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={}
                )
        }
)
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class MessageTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private Long messageId; // 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId")
    private UserTb receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderId")
    private UserTb sender;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "readFlag")
    @Convert(converter = ReadFlagConverter.class)
    private ReadFlag readFlag; // 읽음 플래그 ( 0: 미읽음, 1: 읽음 )

    @Column(name = "replyFlag")
    @Convert(converter = ReplyFlagConverter.class)
    private ReplyFlag replyFlag; // 회신 플래그 ( 0: 미회신, 1: 회신)

    @Column(name = "receiverDeleteStatus")
    @Convert(converter = MessageDeleteStatusConverter.class)
    private MessageDeleteStatus receiverDeleteStatus; // 수신자 삭제 플래그 (0: 미삭제, 1: 삭제)

    @Column(name = "senderDeleteStatus")
    @Convert(converter = MessageDeleteStatusConverter.class)
    private MessageDeleteStatus senderDeleteStatus; // 발신자 삭제 플래그 (0: 미삭제, 1: 삭제)

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일
}
