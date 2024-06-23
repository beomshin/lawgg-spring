package com.kr.lg.service.home.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.kr.lg.web.net.response.home.HomeMainTrialResponse;
import org.springframework.stereotype.Service;

import com.kr.lg.entities.TrialTb;
import com.kr.lg.enums.entity.element.MainPostTypeEnum;
import com.kr.lg.web.common.layer.MainBLayer;
import com.kr.lg.web.common.layer.MainTLayer;
import com.kr.lg.web.net.response.home.HomeResponse;
import com.kr.lg.repositories.MainBoardRepository;
import com.kr.lg.repositories.MainTrialRepository;
import com.kr.lg.repositories.TrialRepository;
import com.kr.lg.service.home.HomeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final MainBoardRepository mainBoardRepository;
    private final MainTrialRepository mainTrialRepository;
    private final TrialRepository trialRepository;

    @Override
    public HomeResponse findHome() {
        List<MainBLayer> boards = mainBoardRepository.findAll().stream().map(MainBLayer::new).collect(Collectors.toList());
        List<MainTLayer> trial = mainTrialRepository.findAll().stream().map(MainTLayer::new).collect(Collectors.toList());
        return new HomeResponse(trial, boards);
    }

	@Override
	public HomeMainTrialResponse findHotTrial() {
        TrialTb trialTb = trialRepository.findByMainPostType(MainPostTypeEnum.MAIN_POST_TYPE);
		return new HomeMainTrialResponse(trialTb);
	}
}
