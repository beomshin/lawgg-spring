package com.kr.lg.module.main.service;

import com.kr.lg.module.main.model.dto.HotPostTrialDto;
import com.kr.lg.module.main.model.dto.MainPostBoardDto;
import com.kr.lg.module.main.model.dto.MainPostTrialDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface MainService {

	List<MainPostBoardDto> getMainPostBoards();

	List<MainPostTrialDto> getMainPostTrials();

	HotPostTrialDto getHotPostTrial();
}
