package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.converters.*;
import com.kr.lg.common.enums.convert.MainPostTypeConverter;
import com.kr.lg.enums.*;
import com.kr.lg.common.enums.entity.MainPostType;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "TrialTb")
@Table(
        name = "TrialTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={}
                )
        }
)
@Getter
@ToString(exclude = {"userTb", "lawFirmTb", "judge", "tierId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class TrialTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trialId")
    private Long trialId; // 관리자 식별자

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tierId")
    private TierTb tierId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "judge")
    private UserTb judge;

    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "writeDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp writeDt; // 수정일

    @Column(name = "subheading")
    private String subheading;

    @Column(name = "plaintiffOpinion")
    private String plaintiffOpinion;

    @Column(name = "defendantOpinion")
    private String defendantOpinion;

    @Column(name = "plaintiff")
    private String plaintiff;

    @Column(name = "defendant")
    private String defendant;

    @Column(name = "url")
    private String url;

    @Column(name = "playVideo")
    private String playVideo;

    @Column(name = "altVideoUrl")
    private String altVideoUrl;
    
    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "precedent")
    @Convert(converter = PrecedentEnumConverter.class)
    private PrecedentEnum precedent;

    @Column(name = "lawFirmDefense")
    @Convert(converter = DefenseEnumConverter.class)
    private DefenseEnum lawFirmDefense;

    @Column(name = "commentCount")
    private Long commentCount;

    @Column(name = "recommendCount")
    private Long recommendCount;

    @Column(name = "view")
    private Integer view;

    @Column(name = "report")
    private Integer report;

    @Column(name = "postType")
    @Convert(converter = PostEnumConverter.class)
    private PostEnum postType;
    
    @Column(name = "mainPostType")
    @Convert(converter = MainPostTypeConverter.class)
    private MainPostType mainPostType;

    @Column(name = "liveType")
    @Convert(converter = LiveEnumConverter.class)
    private LiveEnum liveType;

    @Column(name = "endingType")
    @Convert(converter = EndingEnumConverter.class)
    private EndingEnum endingType;

    @Column(name = "status")
    @Convert(converter = StatusEnumConverter.class)
    private StatusEnum status;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

    @Column(name = "liveDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp liveDt; // 수정일

    @Column(name = "endDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp endDt; // 수정일

    @Column(name = "replay")
    private String replay;
    
    
}
