package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.status.DefenseStatusConverter;
import com.kr.lg.common.enums.convert.status.PrecedentStatusConverter;
import com.kr.lg.common.enums.convert.status.TrialStatusConverter;
import com.kr.lg.common.enums.convert.type.LiveTypeConverter;
import com.kr.lg.common.enums.convert.type.PostTypeConverter;
import com.kr.lg.common.enums.convert.type.TrialEndingTypeConverter;
import com.kr.lg.common.enums.convert.type.MainPostTypeConverter;
import com.kr.lg.common.enums.entity.status.DefenseStatus;
import com.kr.lg.common.enums.entity.status.PrecedentStatus;
import com.kr.lg.common.enums.entity.status.TrialStatus;
import com.kr.lg.common.enums.entity.type.LiveType;
import com.kr.lg.common.enums.entity.type.PostType;
import com.kr.lg.common.enums.entity.type.TrialEndingType;
import com.kr.lg.common.enums.entity.type.MainPostType;
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
    @Convert(converter = PrecedentStatusConverter.class)
    private PrecedentStatus precedent; // 판례 ( 0: 원고 승, 1: 피고 승, 9 :진행중)

    @Column(name = "lawFirmDefense")
    @Convert(converter = DefenseStatusConverter.class)
    private DefenseStatus lawFirmDefense; // 로펌 승리 ( 0: 일반 상태, 1: 승소, 2: 패소)

    @Column(name = "commentCount")
    private Long commentCount;

    @Column(name = "recommendCount")
    private Long recommendCount;

    @Column(name = "view")
    private Integer view;

    @Column(name = "report")
    private Integer report;

    @Column(name = "postType")
    @Convert(converter = PostTypeConverter.class)
    private PostType postType; // 게시글 타입 ( 0 : 일반, 1: 이미지, 2: 추천, 3: 베스트, 98: 이벤트, 99: 공지 )
    
    @Column(name = "mainPostType")
    @Convert(converter = MainPostTypeConverter.class)
    private MainPostType mainPostType;

    @Column(name = "liveType")
    @Convert(converter = LiveTypeConverter.class)
    private LiveType liveType; // 라이브 상태 ( 0: 미방송, 1: 방송)

    @Column(name = "endingType")
    @Convert(converter = TrialEndingTypeConverter.class)
    private TrialEndingType endingType; // 종료 재판 ( 0: 미종료, 1: 종료)

    @Column(name = "status")
    @Convert(converter = TrialStatusConverter.class)
    private TrialStatus status; // 게시글 상태 ( 1: 정상, 2: 삭제, 3: 업로드중, 4: 업로드 실패, 9:정지 )

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
