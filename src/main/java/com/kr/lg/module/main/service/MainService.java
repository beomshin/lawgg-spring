package com.kr.lg.module.main.service;

import com.kr.lg.module.main.model.dto.MainPost;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface MainService {

	List<MainPost> getMainPostBoards();

	List<MainPost> getMainPostTrials();

	MainPost getHotPostTrial();
}
