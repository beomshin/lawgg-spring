package com.kr.lg.module.main.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.kr.lg.module.main.model.dto.HotPostTrialDto;
import com.kr.lg.module.main.model.dto.MainPost;
import org.springframework.stereotype.Service;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.module.main.model.dto.MainPostBoardDto;
import com.kr.lg.module.main.model.dto.MainPostTrialDto;
import com.kr.lg.db.repositories.MainBoardRepository;
import com.kr.lg.db.repositories.MainTrialRepository;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.module.main.service.MainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final MainBoardRepository mainBoardRepository;
    private final MainTrialRepository mainTrialRepository;
    private final TrialRepository trialRepository;

    @Override
    public List<MainPost> getMainPostBoards() {
        log.info("▶ [메인 페에지] 포지션 게시판 리스트 조회");
        return mainBoardRepository.findAll().stream().map(MainPostBoardDto::new).collect(Collectors.toList());
    }

    @Override
    public List<MainPost> getMainPostTrials() {
        log.info("▶ [메인 페에지] 트라이얼 리스트 조회");
        return mainTrialRepository.findAll().stream().map(MainPostTrialDto::new).collect(Collectors.toList());
    }

    @Override
    public MainPost getHotPostTrial() {
        log.info("▶ [메인 페에지] HOT 트라이얼 조회");
        TrialTb trialTb = trialRepository.findByMainPostType();
        return new HotPostTrialDto(trialTb);
    }
}
