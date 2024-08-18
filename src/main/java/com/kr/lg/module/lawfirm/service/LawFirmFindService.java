package com.kr.lg.module.lawfirm.service;

import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.model.entry.LawFirmBoardEntry;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.model.mapper.LawFirmParam;
import com.kr.lg.model.mapper.MapperParam;
import org.springframework.data.domain.Page;

public interface LawFirmFindService {

    Page<LawFirmEntry> findLawFirms(LawFirmParam<?> param) throws LawFirmException;
    LawFirmEntry findLawFirm(MapperParam param) throws LawFirmException;
    Page<LawFirmBoardEntry> findLawFirmBoard(LawFirmParam<?> param) throws LawFirmException;
}
