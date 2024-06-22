package com.kr.lg.service.home;

import com.kr.lg.entities.TrialTb;
import com.kr.lg.model.web.net.response.home.HomeMainTrialResponse;
import com.kr.lg.model.web.net.response.home.HomeResponse;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface HomeService {


	HomeResponse findHome() throws Exception;

	HomeMainTrialResponse findHotTrial() throws Exception;
}
