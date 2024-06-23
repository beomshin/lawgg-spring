package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.enums.entity.converters.AcceptConverter;
import com.kr.lg.enums.entity.converters.ApplyStatusConverter;
import com.kr.lg.enums.entity.converters.EmojiConverter;
import com.kr.lg.enums.entity.element.AcceptEnum;
import com.kr.lg.enums.entity.element.ApplyStatusEnum;
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
    @Convert(converter = EmojiConverter.class)
    private String title;
    @Column(name = "introduction")
    @Convert(converter = EmojiConverter.class)
    private String introduction;

    @Convert(converter = ApplyStatusConverter.class)
    @Column(name = "status")
    private ApplyStatusEnum status;

    @Convert(converter = AcceptConverter.class)
    @Column(name = "accept")
    private AcceptEnum accept;

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
