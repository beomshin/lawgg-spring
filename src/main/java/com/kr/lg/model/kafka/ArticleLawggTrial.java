package com.kr.lg.model.kafka;



import com.kr.lg.common.enums.entity.type.PostType;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.trial.model.req.EnrollTrialRequest;
import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ArticleLawggTrial {

    // 도메인 객체

    private Long userId;

    private String title;

    private String writer;

    private String desc;

    private Integer dtype; // 데이터 타입 (0: 게시판, 1: 트라이얼, 2: 폼)

    private String ip;

    private Long lawFirmId;

    private Long tierId;

    private String subheading;

    private String plaintiffOpinion;

    private String defendantOpinion;

    private String plaintiffName;

    private String defendantName;

    private String playVideoUrl;

    private Integer postType;

    public ArticleLawggTrial(UserTb userTb, EnrollTrialRequest request, String ip, TrialTb trialTb) {
        boolean isEnrollFile = request.getVideo() != null && !request.getVideo().isEmpty();

        this.userId = userTb.getUserId();
        this.title = request.getTitle();
        this.writer = userTb.getNickName();
        this.desc = request.getContent();
        this.dtype = 1;
        this.ip = ip;
        this.lawFirmId = userTb.getLawFirmTb() != null ? userTb.getLawFirmTb().getLawFirmId() : null;
        this.tierId = userTb.getTierTb().getTierId();
        this.subheading = request.getSubheading();
        this.plaintiffOpinion = request.getPlaintiffOpinion();
        this.defendantOpinion = request.getDefendantOpinion();
        this.playVideoUrl = trialTb.getPlayVideo();
        this.postType = isEnrollFile ? PostType.IMAGE_TYPE.getCode() : PostType.NORMAL_TYPE.getCode();
        this.plaintiffName = request.getPlaintiff();
        this.defendantName = request.getDefendant();
    }

}
