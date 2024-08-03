package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.status.AttachStatusConverter;
import com.kr.lg.common.enums.entity.status.AttachStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "TrialAttachTb")
@Table(
        name = "TrialAttachTb",
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
public class TrialAttachTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trialAttachId")
    private Long trialAttachId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trialId")
    private TrialTb trialTb;

    @Column(name = "path")
    private String path;

    @Column(name = "oriName")
    private String oriName;

    @Column(name = "newName")
    private String newName;

    @Column(name = "size")
    private Long size;

    @Column(name = "status")
    @Convert(converter = AttachStatusConverter.class)
    private AttachStatus status; // 첨부파일 상태 ( 1: 정상, 2: 삭제)

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

}
