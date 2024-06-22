package com.kr.lg.service.trial.base.impl;

import com.kr.lg.entities.TrialTb;
import com.kr.lg.entities.TrialVoteTb;
import com.kr.lg.repositories.TrialVoteRepository;
import com.kr.lg.model.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.base.TrialVoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialVoteServiceImpl implements TrialVoteService {

    private final TrialVoteRepository trialVoteRepository;

    @Override
    public void voteTrial(TrialLayer requestDto) {
        Optional<TrialVoteTb> trialVoteTb = trialVoteRepository.findByTrialTb_TrialIdAndUserTb(requestDto.getId(), requestDto.getUserTb());
        if (trialVoteTb.isPresent()) {
            trialVoteRepository.updatePrecedent(trialVoteTb.get().getTrialVoteId(), requestDto.getPrecedent()); // 투표 변경
        }
        else {
            trialVoteRepository.save(TrialVoteTb.builder()
                    .userTb(requestDto.getUserTb())
                    .trialTb(TrialTb.builder().trialId(requestDto.getId()).build())
                    .precedent(requestDto.getPrecedent())
                    .build()); // 투표 저장
        }
    }
}
