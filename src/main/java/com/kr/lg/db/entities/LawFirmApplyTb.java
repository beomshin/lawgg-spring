package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.status.AcceptStatusConverter;
import com.kr.lg.common.enums.convert.status.ApplyStatusConverter;
import com.kr.lg.common.enums.entity.status.AcceptStatus;
import com.kr.lg.common.enums.entity.status.ApplyStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "LawFirmApplyTb")
@Table(
        name = "LawFirmApplyTb",
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
public class LawFirmApplyTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lawFirmAppyId")
    private Long lawFirmAppyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @Column(name = "title")
    private String title;

    @Column(name = "introduction")
    private String introduction;

    @Convert(converter = ApplyStatusConverter.class)
    @Column(name = "status")
    private ApplyStatus status; // 신청 상태 (0: 신청중, 1: 처리완료, 2: 취소)

    @Convert(converter = AcceptStatusConverter.class)
    @Column(name = "accept")
    private AcceptStatus accept; // 승인/거절 ( 0: 대기, 1: 승인, 2: 거절)

    @Column(name = "confirmDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp confirmDt; // 확정일

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

}
