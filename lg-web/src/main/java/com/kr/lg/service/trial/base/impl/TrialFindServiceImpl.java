package com.kr.lg.service.trial.base.impl;

import com.kr.lg.dao.TrialDao;
import com.kr.lg.entities.TrialVoteTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.enums.entity.element.PrecedentEnum;
import com.kr.lg.model.web.querydsl.TrialQ;
import com.kr.lg.repositories.TrialRecommendRepository;
import com.kr.lg.repositories.TrialRepository;
import com.kr.lg.repositories.TrialVoteRepository;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.mapper.TrialVoteTbMapper;
import com.kr.lg.model.web.common.layer.TrialLayer;
import com.kr.lg.model.web.net.response.trial.base.FindALTResponse;
import com.kr.lg.model.web.net.response.trial.base.FindADTResponse;
import com.kr.lg.model.web.net.response.trial.base.FindLFLTResponse;
import com.kr.lg.model.web.net.response.trial.base.FindUDTResponse;
import com.kr.lg.service.trial.base.TrialFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialFindServiceImpl implements TrialFindService {

    private final TrialDao trialDao;
    private final TrialVoteRepository trialVoteRepository;
    private final TrialRepository trialRepository;
    private final TrialVoteTbMapper trialVoteTbMapper;
    private final TrialRecommendRepository trialRecommendRepository;

    @Override
    public DefaultResponse findAllListTrial(TrialLayer requestDto) throws LgException {
        Page<TrialQ> trials = trialDao.findAllListTrial(requestDto, PageRequest.of(requestDto.getPage(), requestDto.getPageNum()));
        return new FindALTResponse(trials);
    }

    @Override
    public DefaultResponse findUserListTrial(TrialLayer requestDto) throws LgException {
        Page<TrialQ> trials = trialDao.findUserListTrial(requestDto, PageRequest.of(requestDto.getPage(), requestDto.getPageNum()));
        return new FindALTResponse(trials);
    }

    @Override
    public DefaultResponse findLawFirmListTrial(TrialLayer requestDto) throws LgException {
        Page<TrialQ> trials = trialDao.findLawFirmListTrial(requestDto, PageRequest.of(requestDto.getPage(), requestDto.getPageNum()));
        return new FindLFLTResponse(trials);
    }

    @Override
    public DefaultResponse findAnonymousDetailTrial(TrialLayer requestDto) throws LgException {
        TrialQ trial = trialDao.findDetailTrial(requestDto); // 상세 조회
        TrialQ percent = trialVoteTbMapper.findVotePercent(trial.getTrialId());
        trial.setPlaintiffCount(percent.getPlaintiffCount());
        trial.setDefendantCount(percent.getDefendantCount());
        return new FindADTResponse(trial);
    }

    @Override
    public DefaultResponse findUserDetailTrial(TrialLayer requestDto) throws LgException {
        TrialQ trial = trialDao.findDetailTrial(requestDto); // 상세 조회
        Optional<TrialVoteTb> voteTb = trialVoteRepository.findByTrialTb_TrialIdAndUserTb(requestDto.getId(), requestDto.getUserTb());
        TrialQ percent = trialVoteTbMapper.findVotePercent(trial.getTrialId());
        long isRecommend = trialRecommendRepository.countByTrialTb_TrialIdAndUserTb(trial.getTrialId(), requestDto.getUserTb());

        trial.setIsVote(voteTb.isPresent() ? voteTb.get().getPrecedent().getCode() : PrecedentEnum.PROCEEDING.getCode()); // 투표여부
        trial.setCreated(trialRepository.countByTrialIdAndUserTb(trial.getTrialId(), requestDto.getUserTb())); // 작성자 플래그
        trial.setPlaintiffCount(percent.getPlaintiffCount());
        trial.setDefendantCount(percent.getDefendantCount());
        trial.setIsRecommend(isRecommend);
        return new FindUDTResponse(trial);
    }

}
