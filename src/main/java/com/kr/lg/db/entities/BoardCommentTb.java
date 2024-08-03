package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.level.CommentDepthLevelConverter;
import com.kr.lg.common.enums.convert.status.CommentStatusConverter;
import com.kr.lg.common.enums.entity.level.CommentDepthLevel;
import com.kr.lg.common.enums.entity.status.CommentStatus;
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
    @Convert(converter = CommentDepthLevelConverter.class)
    private CommentDepthLevel depth; // 댓글 레벨 ( 0 : 루트 , 1: 댓글, 2: 대댓글)

    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "writer")
    private String writer;

    @Column(name = "ip")
    private String ip;

    @Column(name = "content")
    private String content;

    @Column(name = "emoticon")
    private String emoticon;

    @Column(name = "report")
    private Long report;

    @Column(name = "status")
    @Convert(converter = CommentStatusConverter.class)
    private CommentStatus status; // 댓글 상태 ( 1: 정상,  2: 삭제 )

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

}
