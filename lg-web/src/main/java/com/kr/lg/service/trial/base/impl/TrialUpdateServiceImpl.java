package com.kr.lg.service.trial.base.impl;

import com.kr.lg.entities.TrialAttachTb;
import com.kr.lg.entities.TrialTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.enums.entity.element.EndingEnum;
import com.kr.lg.enums.entity.element.LiveEnum;
import com.kr.lg.model.web.common.listener.AlertTLEvent;
import com.kr.lg.repositories.TrialAttachRepository;
import com.kr.lg.repositories.TrialRepository;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.utils.TrialUtils;
import com.kr.lg.model.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.base.TrialUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialUpdateServiceImpl implements TrialUpdateService {

    private final TrialRepository trialRepository;
    private final TrialUtils trialUtils;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TrialAttachRepository trialAttachRepository;
    @Override
    public void updateTrial(TrialLayer requestDto) throws LgException {
        TrialTb trialTb = trialRepository.findById(requestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        trialUtils.isWriterUser(requestDto.getUserTb(), trialTb.getUserTb());
        if (StringUtils.isBlank(requestDto.getSubheading())) requestDto.setSubheading(trialTb.getSubheading());
        if (StringUtils.isBlank(requestDto.getPlaintiffOpinion())) requestDto.setPlaintiffOpinion(trialTb.getPlaintiffOpinion());
        if (StringUtils.isBlank(requestDto.getDefendantOpinion())) requestDto.setDefendantOpinion(trialTb.getDefendantOpinion());
        if (StringUtils.isBlank(requestDto.getContent())) requestDto.setContent(trialTb.getContent());
        trialRepository.updateTrial(trialTb.getTrialId(), requestDto.getSubheading(), requestDto.getPlaintiffOpinion(), requestDto.getDefendantOpinion(), requestDto.getContent());
        List<TrialAttachTb> attachTbs = requestDto.getFiles().stream().filter(it -> it != null).map(it -> TrialAttachTb.builder()
                .trialId(trialTb)
                .path(it.getPath())
                .oriName(it.getOriName())
                .newName(it.getNewName())
                .size(it.getSize())
                .build()).collect(Collectors.toList());
        trialAttachRepository.saveAll(attachTbs);
    }

    @Override
    public void updateLiveTrial(TrialLayer requestDto) throws LgException {
        try {
            TrialTb trialTb = trialRepository.findById(requestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
            trialRepository.updateLive(trialTb.getTrialId(), requestDto.getUserTb().getLawFirmId(), requestDto.getUserTb(), requestDto.getUrl(), LiveEnum.LIVE_TYPE, new Timestamp(System.currentTimeMillis()));
            applicationEventPublisher.publishEvent(new AlertTLEvent(trialTb));
        } catch (Exception e) {
            log.error("{}", e);
            throw new LgException(GlobalCode.FAIL_UPDATE_LIVE);
        }
    }

    @Override
    public void updateEndTrial(TrialLayer requestDto) throws LgException {
        TrialTb trialTb = trialRepository.findById(requestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        trialRepository.updateEnd(trialTb.getTrialId(), requestDto.getPrecedent(), EndingEnum.ENDING_TYPE, new Timestamp(System.currentTimeMillis()));
    }


}
