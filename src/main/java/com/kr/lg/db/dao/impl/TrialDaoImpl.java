package com.kr.lg.db.dao.impl;

import com.kr.lg.model.common.listener.TrialCTEvent;
import com.kr.lg.db.dao.TrialDao;
import com.kr.lg.db.entities.TrialAttachTb;
import com.kr.lg.db.entities.TrialCommentTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.common.enums.DepthEnum;
import com.kr.lg.model.querydsl.TrialQ;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.db.query.TrialQuery;
import com.kr.lg.db.repositories.TrialAttachRepository;
import com.kr.lg.db.repositories.TrialCommentRepository;
import com.kr.lg.db.repositories.TrialRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.kr.lg.model.common.global.GlobalCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TrialDaoImpl implements TrialDao {

    private final TrialQuery trialQuery;
    private final TrialAttachRepository trialAttachRepository;
    private final TrialRepository trialRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TrialCommentRepository trialCommentRepository;

    @Override
    public Page<TrialQ> findAllListTrial(TrialLayer trialLayer, Pageable pageable) {
        JPAQuery<TrialQ> content = trialQuery.findAllListTrial(trialLayer, pageable); // 게시판 content
        JPAQuery<Long> count = trialQuery.findAllListTrialCount(trialLayer); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<TrialQ> findUserListTrial(TrialLayer trialLayer, Pageable pageable) {
        JPAQuery<TrialQ> content = trialQuery.findUserListTrial(trialLayer, pageable); // 게시판 content
        JPAQuery<Long> count = trialQuery.findUserListTrialCount(trialLayer); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<TrialQ> findLawFirmListTrial(TrialLayer trialLayer, Pageable pageable) {
        JPAQuery<TrialQ> content = trialQuery.findLawFirmListTrial(trialLayer, pageable); // 게시판 content
        JPAQuery<Long> count = trialQuery.findLawFirmListTrialCount(trialLayer); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public TrialQ findDetailTrial(TrialLayer requestDto) throws LgException {
        TrialQ trial = Optional.ofNullable(trialQuery.findDetailTrial(requestDto).fetchOne()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_TRIAL)); // 상세 조회
        trial.neUse();
        applicationEventPublisher.publishEvent(new TrialCTEvent(requestDto.getId(), requestDto.getIp())); // 조회수 증가
        trial.setFiles(trialQuery.findTrialFiles(requestDto).fetch()); // 파일 조회
        return trial;
    }


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
                .trialId(trialTb)
                .path(it.getPath())
                .oriName(it.getOriName())
                .newName(it.getNewName())
                .size(it.getSize())
                .build()).collect(Collectors.toList());
        trialAttachRepository.saveAll(attachTbs);
        return trialTb;
    }

}
