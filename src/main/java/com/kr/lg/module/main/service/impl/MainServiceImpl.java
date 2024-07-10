package com.kr.lg.module.main.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.kr.lg.module.main.model.dto.HotTrialDtoText;
import com.kr.lg.module.main.model.dto.MainText;
import org.springframework.stereotype.Service;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.enums.entity.element.MainPostTypeEnum;
import com.kr.lg.module.main.model.dto.MainTextBoardDto;
import com.kr.lg.module.main.model.dto.MainTextTrialDto;
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
    public List<MainText> getMainBoards() {
        log.info("▶ [메인 페에지] 포지션 게시판 리스트 조회");
        return mainBoardRepository.findAll().stream().map(MainTextBoardDto::new).collect(Collectors.toList());
    }

    @Override
    public List<MainText> getMainTrials() {
        log.info("▶ [메인 페에지] 트라이얼 리스트 조회");
        return mainTrialRepository.findAll().stream().map(MainTextTrialDto::new).collect(Collectors.toList());
    }

    @Override
    public MainText getHotTrial() {
        log.info("▶ [메인 페에지] HOT 트라이얼 조회");
        TrialTb trialTb = trialRepository.findByMainPostType(MainPostTypeEnum.MAIN_POST_TYPE);
        return new HotTrialDtoText(trialTb);
    }
}
