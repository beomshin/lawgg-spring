package com.kr.lg.module.trial.service.impl;

import com.kr.lg.common.enums.entity.status.TrialStatus;
import com.kr.lg.db.entities.TrialRecommendTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.TrialVoteTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.TrialAttachRepository;
import com.kr.lg.db.repositories.TrialRecommendRepository;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.db.repositories.TrialVoteRepository;
import com.kr.lg.common.enums.entity.status.PrecedentStatus;
import com.kr.lg.common.enums.logic.TrialTopic;
import com.kr.lg.module.trial.model.event.AlertTLEvent;
import com.kr.lg.module.trial.model.event.AlertVideoEvent;
import com.kr.lg.module.trial.model.event.TrialCreateCountEvent;
import com.kr.lg.module.trial.model.event.TrialRecommendEvent;
import com.kr.lg.module.trial.model.req.DeleteTrialRequest;
import com.kr.lg.module.trial.model.req.VoteTrialRequest;
import com.kr.lg.module.board.model.req.ReportTrialRequest;
import com.kr.lg.module.trial.model.dto.TrialReportDto;
import com.kr.lg.module.trial.model.dto.TrialUpdateDto;
import com.kr.lg.module.trial.model.dto.TrialVoteDto;
import com.kr.lg.module.trial.model.req.*;
import com.kr.lg.module.comment.mapper.CommentMapper;
import com.kr.lg.module.comment.model.entry.TrialCommentEntry;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.trial.model.dto.TrialEnrollDto;
import com.kr.lg.module.trial.model.entry.TrialAttachEntry;
import com.kr.lg.module.trial.model.entry.TrialEntry;
import com.kr.lg.module.trial.model.entry.TrialVoteEntry;
import com.kr.lg.module.trial.model.mapper.FindTrialParamData;
import com.kr.lg.module.trial.service.*;
import com.kr.lg.module.trial.sort.TrialSort;
import com.kr.lg.module.thirdparty.service.FileService;
import com.kr.lg.model.dto.FileDto;
import com.kr.lg.model.mapper.MapperParam;
import com.kr.lg.model.mapper.TrialParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final TrialUpdateService trialUpdateService;
    private final TrialRecommendService trialRecommendService;
    private final TrialReportService trialReportService;
    private final TrialVoteService trialVoteService;
    private final TrialDeleteService trialDeleteService;
    private final FileService<FileDto> fileService;

    private final TrialRepository trialRepository;
    private final TrialRecommendRepository trialRecommendRepository;
    private final TrialVoteRepository trialVoteRepository;
    private final TrialAttachRepository trialAttachRepository;
    private final CommentMapper commentMapper;
    private final BCryptPasswordEncoder encoder;
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
        Optional<TrialRecommendTb> recommendTb = trialRecommendRepository.findByTrialTb_TrialIdAndUserTb_UserId(trial.getTrialId(), userTb.getUserId());
        Optional<TrialVoteTb> voteTb = trialVoteRepository.findByTrialTb_TrialIdAndUserTb_UserId(trial.getTrialId(), userTb.getUserId());
        trial.setIsRecommend(recommendTb.isPresent() ? 1 : 0);
        trial.setCreated(Objects.equals(trial.getUserId(), userTb.getUserId()) ? 1 : 0);
        trial.setIsVote(voteTb.map(trialVoteTb -> trialVoteTb.getPrecedent().getCode()).orElseGet(PrecedentStatus.PROCEEDING::getCode)); // 투표여부
        trial.setComments(comments);
        return trial;
    }

    @Override
    @Transactional
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
                .lawFirmTb(request.getIsLawFirm() != null && request.getIsLawFirm() == 1 ? userTb.getLawFirmTb() : null)
                .build();
        TrialTb trialTb = trialEnrollService.enrollTrial(enrollDto);
        trialEnrollService.enrollTrialFiles(trialTb, request.getFiles());
        applicationEventPublisher.publishEvent(new TrialCreateCountEvent(userTb, 1));
        return trialTb;
    }

    @Override
    @Transactional
    public TrialTb enrollVideoWithLogin(EnrollVideoWithLoginRequest request, UserTb userTb) throws TrialException {
        FileDto replay = null;
        TrialTb trialTb = trialRepository.findLockTrial(request.getId());
        FileDto video = fileService.uploadVideo(request.getPlayVideo());

        if (request.getReplay() != null) replay = fileService.uploadReplay(request.getReplay());

        trialEnrollService.enrollTrialFiles(trialTb, Arrays.asList(video, replay));
        trialRepository.uploadVideoAndReply(trialTb.getTrialId(),
                video != null ? video.getPath() : null,
                replay != null ? replay.getPath() : null,
                video == null ? TrialStatus.FAIL_STATUS : TrialStatus.NORMAL_STATUS
        );
        applicationEventPublisher.publishEvent(new AlertVideoEvent(trialTb));

        return trialTb;
    }

    @Override
    @Transactional
    public void trialStartLive(UpdateLiveTrialRequest request, UserTb userTb) throws TrialException {
        Optional<TrialTb> trialTb = trialRepository.findById(request.getId());
        if (trialTb.isPresent()) {
            trialUpdateService.updateLiveStartTrial(TrialUpdateDto.builder()
                    .trialId(request.getId())
                    .userTb(userTb)
                    .url(request.getUrl())
                    .build());
            applicationEventPublisher.publishEvent(new AlertTLEvent(trialTb.get()));
        } else {
            throw new TrialException(TrialResultCode.NOT_EXIST_TRIAL); // 트라이얼 미존재
        }
    }

    @Override
    @Transactional
    public void trialEndLive(UpdateEndTrialRequest request, UserTb userTb) throws TrialException {
        Optional<TrialTb> trialTb = trialRepository.findById(request.getId());
        if (trialTb.isPresent()) {
            trialUpdateService.updateEndTrial(TrialUpdateDto.builder()
                    .trialId(request.getId())
                    .precedent(PrecedentStatus.of(request.getPrecedent()))
                    .build());
        } else {
            throw new TrialException(TrialResultCode.NOT_EXIST_TRIAL); // 트라이얼 미존재
        }
    }

    @Override
    @Transactional
    public void recommendTrial(RecommendTrialRequest request, UserTb userTb) throws TrialException {
        Optional<TrialRecommendTb> recommendTb = trialRecommendRepository.findByTrialTb_TrialIdAndUserTb_UserId(request.getId(), userTb.getUserId());
        if (recommendTb.isPresent()) throw new TrialException(TrialResultCode.ALREADY_RECOMMEND_TRIAL); // 중복 추천 방어코드
        trialRecommendService.recommendTrial(TrialTb.builder().trialId(request.getId()).build(), userTb);
        applicationEventPublisher.publishEvent(new TrialRecommendEvent(request.getId(), 1));
    }

    @Override
    @Transactional
    public void deleteRecommendTrial(DeleteRecommendTrialRequest request, UserTb userTb) throws TrialException {
        Optional<TrialRecommendTb> recommendTb = trialRecommendRepository.findByTrialTb_TrialIdAndUserTb_UserId(request.getId(), userTb.getUserId());
        if (!recommendTb.isPresent()) throw new TrialException(TrialResultCode.ALREADY_DELETE_RECOMMEND_TRIAL); // 이미 추천 취소 처리
        trialRecommendService.deleteRecommendTrial(request.getId(), userTb.getUserId());
    }

    @Override
    public void reportTrial(ReportTrialRequest request, String ip) throws TrialException {
        trialReportService.reportTrial(TrialReportDto.builder()
                        .ip(ip)
                        .content(request.getContent())
                        .trialTb(TrialTb.builder().trialId(request.getId()).build())
                .build());
    }

    @Override
    public void voteTrial(VoteTrialRequest request, UserTb userTb) throws TrialException {
        Optional<TrialVoteTb> trialVoteTb = trialVoteRepository.findByTrialTb_TrialIdAndUserTb_UserId(request.getId(), userTb.getUserId());
        if (trialVoteTb.isPresent()) {
            trialVoteService.changeVoteTrial(TrialVoteDto.builder()
                            .precedent(PrecedentStatus.of(request.getPrecedent()))
                            .trialVoteId(trialVoteTb.get().getTrialVoteId())
                    .build());
        } else {
            trialVoteService.voteTrial(TrialVoteDto.builder()
                            .precedent(PrecedentStatus.of(request.getPrecedent()))
                            .trialTb(TrialTb.builder().trialId(request.getId()).build())
                            .userTb(userTb)
                    .build());
        }
    }

    @Override
    @Transactional
    public void deleteTrial(DeleteTrialRequest request, UserTb userTb) throws TrialException {
        Optional<TrialTb> trialTb = trialRepository.findByTrialIdAndUserTb_UserId(request.getId(), userTb.getUserId());
        if (trialTb.isPresent()) {
            if (!encoder.matches(request.getPassword(), userTb.getPassword())) throw new TrialException(TrialResultCode.UN_MATCH_PASSWORD); // 비밀번호 불일치
            trialDeleteService.deleteTrial(trialTb.get().getTrialId());
            applicationEventPublisher.publishEvent(new TrialCreateCountEvent(userTb, -1));
        } else {
            throw new TrialException(TrialResultCode.NOT_EXIST_TRIAL); // 트라이얼 미존재
        }
    }

    private Sort getSort(int topic) {
        if (TrialTopic.ALL_TOPIC == TrialTopic.of(topic)) {
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
            throw new TrialException(TrialResultCode.NOT_EXIST_TRIAL); // 트라이얼 미존재
        }
    }

}
