package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.entity.converters.AlertEnumConverter;
import com.kr.lg.common.enums.entity.converters.ReadEnumConverter;
import com.kr.lg.common.enums.entity.element.AlertEnum;
import com.kr.lg.common.enums.entity.element.ReadEnum;
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
    @Convert(converter = AlertEnumConverter.class)
    private AlertEnum type;

    @Column(name = "readFlag")
    @Convert(converter = ReadEnumConverter.class)
    private ReadEnum readFlag;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

}
