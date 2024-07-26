package com.kr.lg.module.lawfirm.service;

import com.kr.lg.model.common.layer.LawFLayer;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.model.dto.LawFirmEnrollDto;

public interface LawFirmEnrollService {

   void saveLawFirmApply(LawFirmEnrollDto enrollDto) throws LawFirmException;
}
