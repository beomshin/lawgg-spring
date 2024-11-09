package com.kr.lg.model.kafka;

import com.kr.lg.common.enums.entity.type.PostType;
import com.kr.lg.common.enums.entity.type.WriterType;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.board.model.req.EnrollPositionRequest;
import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ArticleLawggBoard {

    // 도메인 객체

    private Long userId;

    private String title;

    private String writer;

    private String desc;

    private Integer dtype;

    private String ip;

    private Long lawFirmId;

    private String password;

    private Integer postType;

    private Integer writerType;

    private Integer lineType;

    public ArticleLawggBoard(UserTb userTb, EnrollPositionRequest request, String ip) {
        boolean isEnrollFile = request.getFiles() != null && !request.getFiles().isEmpty();

        this.writer = request.getId();
        this.title = request.getTitle();
        this.desc = request.getContent();
        this.dtype = 0;
        this.ip = ip;
        this.password = request.getPassword();
        this.postType = isEnrollFile ? PostType.IMAGE_TYPE.getCode() : PostType.NORMAL_TYPE.getCode();
        this.lineType = request.getLineType();

        if (userTb == null) {
            this.writerType = WriterType.ANONYMOUS_TYPE.getCode();
        } else {
            this.userId = userTb.getUserId();
            this.writerType = WriterType.MEMBER_TYPE.getCode();
            this.lawFirmId = userTb.getLawFirmTb() != null ? userTb.getLawFirmTb().getLawFirmId() : null;
        }
    }
}
