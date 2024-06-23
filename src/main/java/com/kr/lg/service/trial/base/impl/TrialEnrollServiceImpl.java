package com.kr.lg.service.trial.base.impl;

import com.kr.lg.dao.TrialDao;
import com.kr.lg.entities.TrialAttachTb;
import com.kr.lg.entities.TrialTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.enums.entity.element.StatusEnum;
import com.kr.lg.web.common.listener.AlertVideoEvent;
import com.kr.lg.web.common.listener.TrialCNTEvent;
import com.kr.lg.repositories.TrialAttachRepository;
import com.kr.lg.repositories.TrialRepository;
import com.kr.lg.web.common.global.GlobalFile;
import com.kr.lg.common.service.file.FileService;
import com.kr.lg.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.base.TrialEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialEnrollServiceImpl implements TrialEnrollService {

    private final TrialDao trialDao;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final FileService<GlobalFile> fileService;
    private final TrialRepository trialRepository;
    private final TrialAttachRepository trialAttachRepository;

    @Override
    public TrialTb enrollUserTrial(TrialLayer requestDto) {
        TrialTb trialTb = trialDao.saveTrial(requestDto);
        if (trialTb != null) applicationEventPublisher.publishEvent(new TrialCNTEvent(requestDto.getUserTb(), 1));
        return trialTb;
    }

    @Override
    public TrialTb enrollLawFirmTrial(TrialLayer requestDto) {
        TrialTb trialTb = trialDao.saveTrial(requestDto);
        return trialTb;
    }

    @Override
    public void enrollVideo(TrialLayer requestDto) throws LgException {
        GlobalFile video = null, replay = null;
        TrialTb trialTb = trialRepository.findLockTrial(requestDto.getId());
        List<TrialAttachTb> attachTbs = new ArrayList<>();
        if (requestDto.getPlayVideo() != null) {
            video = fileService.uploadVideo(requestDto.getPlayVideo());
            log.debug("{}", video);
            attachTbs.add(TrialAttachTb.builder()
                    .trialId(trialTb)
                    .path(video.getPath())
                    .oriName(video.getOriName())
                    .newName(video.getNewName())
                    .size(video.getSize())
                    .build());
        }

        if (requestDto.getReplay() != null) {
            replay = fileService.uploadReplay(requestDto.getReplay());
            log.debug("{}", replay);
            attachTbs.add(TrialAttachTb.builder()
                    .trialId(trialTb)
                    .path(replay.getPath())
                    .oriName(replay.getOriName())
                    .newName(replay.getNewName())
                    .size(replay.getSize())
                    .build());
        }

        trialRepository.updateTrial(trialTb.getTrialId(), video == null ? null : video.getPath(), replay == null ? null : replay.getPath(), video == null ? StatusEnum.FAIL_STATUS : StatusEnum.NORMAL_STATUS);
        trialAttachRepository.saveAll(attachTbs);

        applicationEventPublisher.publishEvent(new AlertVideoEvent(trialTb));

    }

}
