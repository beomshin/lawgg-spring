package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.entity.converters.DelEnumConverter;
import com.kr.lg.common.enums.entity.converters.EmojiConverter;
import com.kr.lg.common.enums.entity.converters.ReadEnumConverter;
import com.kr.lg.common.enums.entity.converters.ReplyEnumConverter;
import com.kr.lg.common.enums.entity.element.DelEnum;
import com.kr.lg.common.enums.entity.element.ReadEnum;
import com.kr.lg.common.enums.entity.element.ReplyEnum;
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
    @Convert(converter = EmojiConverter.class)
    private String title;

    @Column(name = "content")
    @Convert(converter = EmojiConverter.class)
    private String content;

    @Column(name = "readFlag")
    @Convert(converter = ReadEnumConverter.class)
    private ReadEnum readFlag;

    @Column(name = "receiverDelFlag")
    @Convert(converter = DelEnumConverter.class)
    private DelEnum receiverDelFlag;

    @Column(name = "senderDelFlag")
    @Convert(converter = DelEnumConverter.class)
    private DelEnum senderDelFlag;

    @Column(name = "replyFlag")
    @Convert(converter = ReplyEnumConverter.class)
    private ReplyEnum replyFlag;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일
}
