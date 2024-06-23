package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.converters.DepthEnumConverter;
import com.kr.lg.common.converters.EmojiConverter;
import com.kr.lg.common.converters.StatusEnumConverter;
import com.kr.lg.common.enums.DepthEnum;
import com.kr.lg.common.enums.StatusEnum;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "BoardCommentTb")
@Table(
        name = "BoardCommentTb",
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
public class BoardCommentTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardCommentId")
    private Long boardCommentId; // 관리자 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private BoardTb boardTb;

    @Column(name = "parentId")
    private Long parentId;

    @Column(name = "`order`")
    private Integer order;

    @Column(name = "depth")
    @Convert(converter = DepthEnumConverter.class)
    private DepthEnum depth;

    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "writer")
    private String writer;

    @Column(name = "ip")
    private String ip;

    @Column(name = "content")
    @Convert(converter = EmojiConverter.class)
    private String content;

    @Column(name = "emoticon")
    private String emoticon;

    @Column(name = "report")
    private Long report;

    @Column(name = "status")
    @Convert(converter = StatusEnumConverter.class)
    private StatusEnum status;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

}
