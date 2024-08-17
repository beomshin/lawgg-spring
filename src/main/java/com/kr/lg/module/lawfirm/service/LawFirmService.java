package com.kr.lg.module.lawfirm.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.lawfirm.model.entry.LawFirmBoardEntry;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.module.lawfirm.model.req.*;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import org.springframework.data.domain.Page;

public interface LawFirmService {

    void applyLawFirm(ApplyLawFirmRequest request, UserTb userTb) throws LawFirmException;
    void quitLawFirm(QuitLawFirmRequest request, UserTb userTb) throws LawFirmException;
    void cancelApplyLawFirm(CancelApplyLawFirmRequest request, UserTb userTb) throws LawFirmException;
    Page<LawFirmEntry> findLawFirms(FindLawFirmsRequest request) throws LawFirmException;
    LawFirmEntry findLawFirmWithNotLogin(long id) throws LawFirmException;
    LawFirmEntry findLawFirmWithLogin(long id, UserTb userTb) throws LawFirmException;
    Page<LawFirmBoardEntry> findLawFirmBoard(FindLawFirmsBoardRequest request, long lawFirmId) throws LawFirmException;
}
