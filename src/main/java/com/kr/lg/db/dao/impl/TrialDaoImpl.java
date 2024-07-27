package com.kr.lg.db.dao.impl;

import com.kr.lg.db.dao.TrialDao;
import com.kr.lg.db.entities.TrialAttachTb;
import com.kr.lg.db.entities.TrialCommentTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.enums.DepthEnum;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.db.repositories.TrialAttachRepository;
import com.kr.lg.db.repositories.TrialCommentRepository;
import com.kr.lg.db.repositories.TrialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TrialDaoImpl implements TrialDao {

    private final TrialAttachRepository trialAttachRepository;
    private final TrialRepository trialRepository;
    private final TrialCommentRepository trialCommentRepository;


    @Transactional
    @Override
    public TrialTb saveTrial(TrialLayer requestDto) {
        TrialTb trialTb = TrialTb.builder()
                .userTb(requestDto.getUserTb())
                .lawFirmTb(requestDto.getLawFirmTb())
                .tierId(requestDto.getUserTb().getTierId())
                .id(requestDto.getUserTb().getLoginId())
                .title(requestDto.getTitle())
                .password(requestDto.getUserTb().getPassword())
                .content(requestDto.getContent())
                .writer(requestDto.getUserTb().getNickName())
                .subheading(requestDto.getSubheading())
                .plaintiffOpinion(requestDto.getPlaintiffOpinion())
                .defendantOpinion(requestDto.getDefendantOpinion())
                .plaintiff(requestDto.getPlaintiff())
                .defendant(requestDto.getDefendant())
                .build();
        trialRepository.save(trialTb);
        trialCommentRepository.save(TrialCommentTb.builder().trialTb(trialTb).depth(DepthEnum.ROOT_COMMENT).build());
        List<TrialAttachTb> attachTbs = requestDto.getFiles().stream().filter(it -> it != null).map(it -> TrialAttachTb.builder()
                .trialTb(trialTb)
                .path(it.getPath())
                .oriName(it.getOriName())
                .newName(it.getNewName())
                .size(it.getSize())
                .build()).collect(Collectors.toList());
        trialAttachRepository.saveAll(attachTbs);
        return trialTb;
    }

}
