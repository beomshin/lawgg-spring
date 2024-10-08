package com.kr.lg.module.main.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.kr.lg.module.main.model.dto.HotPostTrialDto;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final MainBoardRepository mainBoardRepository;
    private final MainTrialRepository mainTrialRepository;
    private final TrialRepository trialRepository;

    @Override
    public List<MainPostBoardDto> getMainPostBoards() {
        log.info("▶ [메인 페에지] getMainPostBoards 메소드 실행");

        log.info("▶ [메인 페에지] 포지션 게시판 리스트 조회");
        return mainBoardRepository.findAll().stream().map(MainPostBoardDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<MainPostTrialDto> getMainPostTrials() {
        log.info("▶ [메인 페에지] getMainPostTrials 메소드 실행");

        log.info("▶ [메인 페에지] 트라이얼 리스트 조회");
        return mainTrialRepository.findAll().stream().map(MainPostTrialDto::new).collect(Collectors.toList());
    }

    @Override
    public HotPostTrialDto getHotPostTrial() {
        log.info("▶ [메인 페에지] getHotPostTrial 메소드 실행");

        log.info("▶ [메인 페에지] HOT 트라이얼 조회");
        Optional<TrialTb> trialTb = trialRepository.findByMainPostType();
        return trialTb.map(HotPostTrialDto::new).orElse(null);
    }
}
