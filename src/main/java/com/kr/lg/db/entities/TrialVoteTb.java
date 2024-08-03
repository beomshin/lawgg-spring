package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.status.PrecedentStatusConverter;
import com.kr.lg.common.enums.entity.status.PrecedentStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "TrialVoteTb")
@Table(
        name = "TrialVoteTb",
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
public class TrialVoteTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trialVoteId")
    private Long trialVoteId; // 관리자 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trialId")
    private TrialTb trialTb;

    @Column(name = "precedent")
    @Convert(converter = PrecedentStatusConverter.class)
    private PrecedentStatus precedent; // 판례 ( 0: 원고 승, 1: 피고 승, 9 :진행중)

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

}
