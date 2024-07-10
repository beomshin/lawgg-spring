package com.kr.lg.module.main.service;

import com.kr.lg.module.main.model.dto.MainText;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface MainService {

	List<MainText> getMainBoards();

	List<MainText> getMainTrials();

	MainText getHotTrial();
}
