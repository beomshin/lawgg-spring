package com.kr.lg.module.lawfirm.service;

import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.web.dto.mapper.LawFirmParam;
import com.kr.lg.web.dto.mapper.MapperParam;
import org.springframework.data.domain.Page;

public interface LawFirmFindService {

    Page<LawFirmEntry> findLawFirms(LawFirmParam<?> param) throws LawFirmException;
    LawFirmEntry findLawFirm(MapperParam param) throws LawFirmException;
}
