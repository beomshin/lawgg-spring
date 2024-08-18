package com.kr.lg.module.trial.service.impl;

import com.kr.lg.common.enums.entity.status.TrialStatus;
import com.kr.lg.db.entities.*;
import com.kr.lg.db.repositories.TrialCommentRepository;
import com.kr.lg.common.enums.entity.level.CommentDepthLevel;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.db.repositories.TrialAttachRepository;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.module.trial.model.dto.TrialEnrollDto;
import com.kr.lg.module.trial.service.TrialEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class TrialEnrollServiceImpl implements TrialEnrollService {

    private final TrialRepository trialRepository;
    private final TrialCommentRepository trialCommentRepository;

    @Override
    @Transactional
    public TrialTb enrollTrial(TrialEnrollDto enrollDto) throws TrialException {
        try {
            log.info("▶ [트라이얼] 트라이얼 등록");
            TrialTb trialTb = TrialTb.builder()
                    .userTb(enrollDto.getUserTb())
                    .lawFirmTb(enrollDto.getLawFirmTb())
                    .tierId(enrollDto.getUserTb().getTierTb())
                    .id(enrollDto.getUserTb().getLoginId())
                    .title(enrollDto.getTitle())
                    .password(enrollDto.getUserTb().getPassword())
                    .content(enrollDto.getContent())
                    .writer(enrollDto.getUserTb().getNickName())
                    .subheading(enrollDto.getSubheading())
                    .plaintiffOpinion(enrollDto.getPlaintiffOpinion())
                    .defendantOpinion(enrollDto.getDefendantOpinion())
                    .plaintiff(enrollDto.getPlaintiff())
                    .defendant(enrollDto.getDefendant())
                    .playVideo(enrollDto.getPlayVideo())
                    .status(TrialStatus.NORMAL_STATUS)
                    .build();
            TrialCommentTb commentTb = TrialCommentTb.builder() // 게실글 루트 댓글 엔티티 생성
                    .trialTb(trialTb)
                    .depth(CommentDepthLevel.ROOT_COMMENT)
                    .build();
            trialRepository.save(trialTb); // 게시글 save
            trialCommentRepository.save(commentTb); // 게시글 루트 댓글 save
            return trialTb;
        } catch (Exception e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_ENROLL_TRAIL);
        }
    }

}
