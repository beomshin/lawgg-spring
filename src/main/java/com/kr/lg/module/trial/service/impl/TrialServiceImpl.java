package com.kr.lg.module.trial.service.impl;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.TrialVoteTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.TrialAttachRepository;
import com.kr.lg.db.repositories.TrialRecommendRepository;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.db.repositories.TrialVoteRepository;
import com.kr.lg.enums.PrecedentEnum;
import com.kr.lg.enums.StatusEnum;
import com.kr.lg.enums.TrialTopicEnum;
import com.kr.lg.model.common.listener.AlertVideoEvent;
import com.kr.lg.module.trial.model.req.EnrollTrialWithLoginRequest;
import com.kr.lg.module.trial.model.req.EnrollVideoWithLoginRequest;
import com.kr.lg.module.comment.mapper.CommentMapper;
import com.kr.lg.module.comment.model.entry.TrialCommentEntry;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.trial.model.dto.TrialEnrollDto;
import com.kr.lg.module.trial.model.entry.TrialAttachEntry;
import com.kr.lg.module.trial.model.entry.TrialEntry;
import com.kr.lg.module.trial.model.entry.TrialVoteEntry;
import com.kr.lg.module.trial.model.mapper.FindTrialParamData;
import com.kr.lg.module.trial.model.req.FindTrialsRequest;
import com.kr.lg.module.trial.model.req.FindLawFirmTrialsRequest;
import com.kr.lg.module.trial.service.TrialEnrollService;
import com.kr.lg.module.trial.service.TrialFindService;
import com.kr.lg.module.trial.service.TrialService;
import com.kr.lg.module.trial.sort.TrialSort;
import com.kr.lg.service.file.FileService;
import com.kr.lg.web.dto.global.GlobalFile;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.TrialParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {


    private final TrialFindService trialFindService;
    private final TrialEnrollService trialEnrollService;
    private final FileService<GlobalFile> fileService;

    private final TrialRepository trialRepository;
    private final TrialRecommendRepository trialRecommendRepository;
    private final TrialVoteRepository trialVoteRepository;
    private final TrialAttachRepository trialAttachRepository;
    private final CommentMapper commentMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Page<TrialEntry> findTrials(FindTrialsRequest request) throws TrialException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), this.getSort(request.getTopic()));
        MapperParam param = FindTrialParamData.builder()
                .subject(request.getSubject())
                .keyword(request.getKeyword())
                .build();
        return trialFindService.findTrials(new TrialParam<>(param, pageable));
    }

    @Override
    public Page<TrialEntry> findLawFirmTrials(FindLawFirmTrialsRequest request) throws TrialException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), this.getSort(request.getTopic()));
        MapperParam param = FindTrialParamData.builder()
                .lawFirmId(request.getId())
                .subject(request.getSubject())
                .keyword(request.getKeyword())
                .build();
        return trialFindService.findTrials(new TrialParam<>(param, pageable));
    }

    @Override
    public TrialEntry findTrialWithNotLogin(long id) throws TrialException {
        MapperParam param = FindTrialParamData.builder().trialId(id).build();
        TrialEntry trial = this.findTrial(param);
        List<TrialCommentEntry> comments = commentMapper.findTrialCommentsWithNotLogin(trial.getTrialCommentId());
        trial.setComments(comments);
        return trial;
    }

    @Override
    public TrialEntry findTrialWithLogin(long id, UserTb userTb) throws TrialException {
        MapperParam param = FindTrialParamData.builder().trialId(id).build();
        TrialEntry trial = this.findTrial(param);
        List<TrialCommentEntry> comments = commentMapper.findTrialCommentsWithLogin(trial.getTrialCommentId(), userTb.getUserId());
        int isRecommend = trialRecommendRepository.countByTrialTb_TrialIdAndUserTb_UserId(trial.getTrialId(), userTb.getUserId());
        Optional<TrialVoteTb> voteTb = trialVoteRepository.findByTrialTb_TrialIdAndUserTb_UserId(trial.getTrialId(), userTb.getUserId());
        trial.setIsRecommend(isRecommend);
        trial.setCreated(Objects.equals(trial.getUserId(), userTb.getUserId()) ? 1 : 0);
        trial.setIsVote(voteTb.map(trialVoteTb -> trialVoteTb.getPrecedent().getCode()).orElseGet(PrecedentEnum.PROCEEDING::getCode)); // 투표여부
        trial.setComments(comments);
        return trial;
    }

    @Override
    public TrialTb enrollTrialWithLogin(EnrollTrialWithLoginRequest request, UserTb userTb) throws TrialException {
        TrialEnrollDto enrollDto = TrialEnrollDto.builder()
                .userTb(userTb)
                .title(request.getTitle())
                .plaintiff(request.getPlaintiff())
                .defendant(request.getDefendant())
                .subheading(request.getSubheading())
                .plaintiffOpinion(request.getPlaintiffOpinion())
                .defendantOpinion(request.getDefendantOpinion())
                .content(request.getContent())
                .lawFirmTb(request.getIsLawFirm() != null && request.getIsLawFirm() == 1 ? userTb.getLawFirmId() : null)
                .build();
        TrialTb trialTb = trialEnrollService.enrollTrial(enrollDto);
        trialEnrollService.enrollTrialFiles(trialTb, request.getFiles());
        return trialTb;
    }

    @Override
    @Transactional
    public TrialTb enrollVideoWithLogin(EnrollVideoWithLoginRequest request, UserTb userTb) throws TrialException {
        GlobalFile replay = null;
        TrialTb trialTb = trialRepository.findLockTrial(request.getId());
        GlobalFile video = fileService.uploadVideo(request.getPlayVideo());

        if (request.getReplay() != null) replay = fileService.uploadReplay(request.getReplay());

        trialEnrollService.enrollTrialFiles(trialTb, Arrays.asList(video, replay));
        trialRepository.uploadVideoAndReply(trialTb.getTrialId(),
                video != null ? video.getPath() : null,
                replay != null ? replay.getPath() : null,
                video == null ? StatusEnum.FAIL_STATUS : StatusEnum.NORMAL_STATUS
        );
        return trialTb;
    }

    private Sort getSort(int topic) {
        if (TrialTopicEnum.ALL_TOPIC == TrialTopicEnum.of(topic)) {
            return TrialSort.notificationSortWithDesc().and(TrialSort.dateWithDesc());
        } else {
            return TrialSort.notificationSortWithDesc().and(TrialSort.dateTimeWithDesc());
        }
    }

    private TrialEntry findTrial(MapperParam param) throws TrialException {
        Optional<TrialEntry> trial = trialFindService.findTrial(param);
        if (trial.isPresent()) {
            List<TrialAttachEntry> files = trialAttachRepository.findByTrialTb_TrialId(trial.get().getTrialId())
                    .stream().map(TrialAttachEntry::new).collect(Collectors.toList()); // 파일리스트 조회
            trial.get().setFiles(files);
            TrialVoteEntry vote = trialFindService.findVotePercent(param);
            trial.get().setPlaintiffCount(vote.getPlaintiffCount());
            trial.get().setDefendantCount(vote.getDefendantCount());
            return trial.get();
        } else {
            throw new TrialException(TrialResultCode.NOT_EXIST_TRIAL); // 게시판 미존재
        }
    }

}
