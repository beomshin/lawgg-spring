package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.type.AlertTypeConverter;
import com.kr.lg.common.enums.convert.flag.ReadFlagConverter;
import com.kr.lg.common.enums.entity.type.AlertType;
import com.kr.lg.common.enums.entity.flag.ReadFlag;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "AlertTb")
@Table(
        name = "AlertTb",
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
public class AlertTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alertId")
    private Long alertId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserTb userTb;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private BoardTb boardTb;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trialId")
    private TrialTb trialTb;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    @Convert(converter = AlertTypeConverter.class)
    private AlertType type; // 알림 타입 ( 0: 게시판 알림, 1: 트라이얼 알림)

    @Column(name = "readFlag")
    @Convert(converter = ReadFlagConverter.class)
    private ReadFlag readFlag; // 읽음 플래그 ( 0: 미읽음, 1: 읽음 )

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

}
